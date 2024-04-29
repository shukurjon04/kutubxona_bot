package servisimpl;

import Buttonlar.Button;
import Buttonlar.Inlinebutton;
import databasecollection.kitobtable;
import databasecollection.KitobxonTable;
import databasecollection.Admintable;
import faoliyat.holatlar;
import kutubxona_FF.*;
import Adminservice.Adminservice;
import nusxalar.Container;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import faoliyat.*;
import registr.Kitobxon;
import registr.USer;
import registr.kitob;

import java.util.ArrayList;
import java.util.List;

public class Adminservicimpl extends Main_bot implements Adminservice {
    private long chatid;


    @Override
    public void setchatid(long chatid) {
        this.chatid = chatid;
    }

    @Override
    public void registr() {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("Ro'yxatdan o'tmagan bo'lsangiz \n ro'yxatdan o'tish tugmasini bosing 👇");
        sendMessage.setChatId(chatid);
        List<List<InlineKeyboardButton>> rowList;
        Inlinebutton inlinebutton = new Inlinebutton();
        rowList = inlinebutton.buttons("Ro'yxatdan o'tish", "Tizimga kirish");
        sendMessage.setReplyMarkup(inlinebutton.inlineKeyboardMarkup(rowList));
        try {
            execute(sendMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String addbaza(String state, String text) {
        SendMessage message1 = new SendMessage();
        if (state.equals("")) {
            Container.admin.setName(text);
            state = holatlar.ism;
            message1.setChatId(chatid);
            message1.setText("familyangizni kiriting");
        } else if (state.equals(holatlar.ism)) {
            Container.admin.setSurname(text);
            state = holatlar.familya;
            message1.setChatId(chatid);
            message1.setText("parolingizni yarating");
        } else if (state.equals(holatlar.familya)) {
            Container.admin.setPassword(text);
            state = holatlar.password;
            message1.setChatId(chatid);
            message1.setText("loginingizni kiriting");
        } else if (state.equals(holatlar.password)) {
            Container.admin.setLogin(text);
            state = holatlar.login;

        }
        if (Container.admin.getName() != null && Container.admin.getLogin() != null && Container.admin.getSurname() != null && Container.admin.getPassword() != null) {
            Admintable mysqlconnect = new Admintable();
            if (mysqlconnect.chatid(Container.admin.getChatid())) {
                message1.setChatId(chatid);
                message1.setText(" Siz allaqachon bazaga qoshilgansz ");
                List<List<InlineKeyboardButton>> rowList;
                Inlinebutton inlinebutton = new Inlinebutton();
                rowList = inlinebutton.buttons("Tizimga kirish");
                message1.setReplyMarkup(inlinebutton.inlineKeyboardMarkup(rowList));
            } else {
//                mysqlconnect.insertIntoTableadmin(Container.admin.getName(), Container.admin.getLogin(), Container.admin.getPassword(), Container.admin.getSurname(), Container.admin.getChatid());
                message1.setChatId(chatid);
                message1.setText("Hush kelibsz siz ro'yxatdan o'tdingiz \n tizimga kirish tugmasini bosing 👇 ");
                List<List<InlineKeyboardButton>> rowList;
                Inlinebutton inlinebutton = new Inlinebutton();
                rowList = inlinebutton.buttons("Ro'yxatdan o'tish", "Tizimga kirish");
                message1.setReplyMarkup(inlinebutton.inlineKeyboardMarkup(rowList));
                state = "";
            }
        }
        try {
            execute(message1);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        return state;
    }

    private kitob kitob(String text) {
        kitobtable kitobtable = new kitobtable();
        if (kitobtable.istherebook(text)) {
            return kitobtable.kitobreturn(text);
        }
        kitob kitob = new kitob();
        kitob.setName(text);
        kitobtable.addkitob(kitob.getName(),kitob.getFile());
        return kitob;
    }

    @Override
    public void kitob_qoshish(String text, long chatid) {
        SendMessage message1 = new SendMessage();
        message1.setChatId(chatid);
        kitob kitob = kitob(text);;
        if (text.equals(kitob.getName())) {
            System.out.println(kitob.getName());
            if (kitob.getFile().equals("null")) {
                message1.setText("pdf file yuboring");
            }else {
                message1.setText("bu kitob bazada bor");
            }
        }
        try {
            execute(message1);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void kitobo_chirish(String text, long chatid , kitobtable kitobtable) {
        SendMessage message1 = new SendMessage();
        message1.setChatId(chatid);
        if (kitobtable.istherebook(text)) {
            kitobtable.ochirish(text);
            message1.setText("kitob o'chirildi");
        } else {
            message1.setText("bazada bunday kitob mavjud emas");
        }
        try {
            execute(message1);
        } catch (
                Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void kitobxon_ochirish(String text, USer uSer, KitobxonTable kitobxonTable) {
        SendMessage message1 = new SendMessage();
        message1.setChatId(uSer.getChatid());
        if (kitobxonTable.istherereader(text)) {
            kitobxonTable.ochirishkitobxon(text);
            message1.setText("kitobxon o'chirildi");
        } else {
            message1.setText("bazada bunday kitobxon mavjud emas");
        }
        try {
            execute(message1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void tizimga_kirish(String state, String faoliyat, String text, USer uSer) {
        SendMessage message1 = new SendMessage();
        message1.setChatId(uSer.getChatid());
        if (uSer.getPassword().equals(text)) {
            message1.setText("loginingizni kiriting");
        } else if (uSer.getLogin().equals(text)) {
            if (!uSer.getLogin().equals("null") && !uSer.getPassword().equals("null")) {
                Admintable admintable = new Admintable();
                KitobxonTable kitobxonTable = new KitobxonTable();
                if (kitobxonTable.kitobxonname(uSer.getPassword(), uSer.getLogin()) != null) {
                    message1.setText("Xush kelibsz" + " " + kitobxonTable.kitobxonname(uSer.getPassword(), uSer.getLogin()) + "\n Kitobxon menyusi 👇");
                    List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
                    Inlinebutton inlinebutton2 = new Inlinebutton();
                    rowList.add(inlinebutton2.button("kitoblar_royxati", "kitob_olish"));
                    message1.setReplyMarkup(inlinebutton2.inlineKeyboardMarkup(rowList));
                    kitobxonTable.updatestep(kitobxonmenyu.menyu, uSer.getChatid());
                } else if (admintable.name(uSer.getPassword(), uSer.getLogin()) != null) {
                    message1.setText("Xush kelibsz" + " " + admintable.name(uSer.getPassword(), uSer.getLogin()) + "\n Admin menyusi 👇");
                    List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
                    Inlinebutton inlinebutton = new Inlinebutton();
                    Inlinebutton inlinebutton1 = new Inlinebutton();
                    Inlinebutton inlinebutton2 = new Inlinebutton();
                    rowList.add(inlinebutton.button("kitob_qoshish", "kitobo_chirish"));
                    rowList.add(inlinebutton2.button("kitob_royxati", "kitobxonlar_royxati"));
                    rowList.add(inlinebutton1.button("kitobxon_ochirish"));
                    message1.setReplyMarkup(inlinebutton.inlineKeyboardMarkup(rowList));
                    admintable.updatestep(kitobxonmenyu.menyu, uSer.getChatid());
                }
            }
        } else {
            message1.setText("bazada bunday ma'lumot topilmadi😟😟\nmani aldomisz😒😒 ro'yxatdan o'ting");
            List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
            Inlinebutton inlinebutton2 = new Inlinebutton();
            rowList.add(inlinebutton2.button("Ro'yxatdan o'tish", "Tizimga kirish"));
            message1.setReplyMarkup(inlinebutton2.inlineKeyboardMarkup(rowList));
        }
        try {
            execute(message1);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void kitob_royxati(long chatid) {
        kitobtable kitobtable = new kitobtable();
        List<SendMessage> list = kitobtable.kitobwiev(chatid);
        List<String> strings = new ArrayList<>();
        for (SendMessage sendMessage : list) {
            strings.add(String.valueOf(sendMessage.getText()));
        }
        Button button = new Button();
        SendMessage message = new SendMessage();
        message.setText("Kitoblar");
        message.setChatId(chatid);
        message.setReplyMarkup(button.createKeyboard(strings));
        try {
            execute(message);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void kitobxonlar_royxati(KitobxonTable kitobxonTable, USer uSer) {
        List<SendMessage> list = kitobxonTable.kitobxonview(uSer.getChatid());
        List<String> strings = new ArrayList<>();

        for (SendMessage sendMessage : list) {
            strings.add(String.valueOf(sendMessage.getText()));
        }
        Button button = new Button();
        SendMessage message = new SendMessage();
        if (strings.isEmpty()) {
            message.setText("hali Kitobxonlar mavjud emas");
        } else {
            message.setText("Kitobxonlar");
        }
        message.setChatId(uSer.getChatid());
        message.setReplyMarkup(button.createKeyboard(strings));
        try {
            execute(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
