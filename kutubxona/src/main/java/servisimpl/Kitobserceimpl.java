package servisimpl;

import Buttonlar.Button;
import Buttonlar.Inlinebutton;
import databasecollection.*;
import faoliyat.holatlar;
import kutubxona_FF.*;
import Adminservice.Kitobxonservice;
import nusxalar.Container;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import registr.USer;

import java.util.ArrayList;
import java.util.List;

public class Kitobserceimpl extends Main_bot implements Kitobxonservice {
    private long chatid;


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
    public void setchatid(long chatid) {
        this.chatid =chatid;
    }

    @Override
    public void addbaza( String text, USer uSer,KitobxonTable kitobxonTable) {
        SendMessage message1 = new SendMessage();
        message1.setChatId(uSer.getChatid());
        if (uSer.getName().equals("null")) {
            kitobxonTable.updatename(text,uSer.getChatid());
            message1.setText("familyangizni kiriting");
            try {
                execute(message1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (uSer.getSurname().equals("null")) {
            kitobxonTable.updatesurname(text,uSer.getChatid());
            message1.setText("parolingizni yarating");
            try {
                execute(message1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (uSer.getPassword().equals("null")) {
            kitobxonTable.updatepasword(text,uSer.getChatid());
            message1.setText("loginingizni kiriting");
            try {
                execute(message1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (uSer.getLogin().equals("null")) {
            kitobxonTable.updatelogin(text,uSer.getChatid());
            System.out.println("fffff");
            if (!uSer.getName().equals("null")&& !uSer.getSurname().equals("null") && !uSer.getPassword().equals("null")) {
                System.out.println(uSer.getName());
                if (kitobxonTable.chatid(uSer.getChatid())) {
                    message1.setChatId(uSer.getChatid());
                    message1.setText("Hush kelibsz siz ro'yxatdan o'tdingiz \n tizimga kirish tugmasini bosing 👇");
                    List<List<InlineKeyboardButton>> rowList;
                    Inlinebutton inlinebutton = new Inlinebutton();
                    rowList = inlinebutton.buttons( "Tizimga kirish");
                    message1.setReplyMarkup(inlinebutton.inlineKeyboardMarkup(rowList));
                    try {
                        execute(message1);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else {
                    kitobxonTable.addbaza(uSer.getName(),uSer.getSurname(), uSer.getPassword(), uSer.getLogin(),  uSer.getChatid(),uSer.getStep(),uSer.getFaoliat());
                    message1.setChatId(uSer.getChatid());
                    message1.setText("Hush kelibsz siz ro'yxatdan o'tdingiz \n tizimga kirish tugmasini bosing 👇 ");
                    List<List<InlineKeyboardButton>> rowList;
                    Inlinebutton inlinebutton = new Inlinebutton();
                    rowList = inlinebutton.buttons("Ro'yxatdan o'tish", "Tizimga kirish");
                    message1.setReplyMarkup(inlinebutton.inlineKeyboardMarkup(rowList));
                    try {
                        execute(message1);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
    }

    @Override
    public void kitoblar_royxati(long chatid) {
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
    public void kitob_olish(long chatid) {
        kitoblar_royxati(chatid);

    }
}
