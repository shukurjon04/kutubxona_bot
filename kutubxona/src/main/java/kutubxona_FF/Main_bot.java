package kutubxona_FF;

import databasecollection.KitobxonTable;
import Buttonlar.Inlinebutton;
import databasecollection.kitobtable;
import databasecollection.Admintable;
import faoliyat.*;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import registr.Admin;
import registr.Kitobxon;
import nusxalar.Container;
import registr.USer;
import registr.kitob;

import java.util.List;

import static java.awt.SystemColor.text;

public class Main_bot extends TelegramLongPollingBot {

    Admintable admintable = new Admintable();
    KitobxonTable kitobxonTable = new KitobxonTable();
    kitobtable kitobtable = new kitobtable();

    private kitob kitob(String text) {
        if (kitobtable.istherebook(text)) {
            return kitobtable.kitobreturn(text);
        }
        kitob kitob = new kitob();
        kitob.setName(text);
        kitobtable.addkitob(kitob.getName(), kitob.getFile());
        return kitob;
    }

    private USer user(long chatid) {
        if (admintable.chatid(chatid)) {
            return admintable.admin1(chatid);
        } else if (kitobxonTable.chatid(chatid)) {
            return kitobxonTable.kitobxon(chatid);
        }
        Kitobxon kitobxon = new Kitobxon(chatid);
        kitobxonTable.addbaza(kitobxon.getName(), kitobxon.getLogin(), kitobxon.getPassword(), kitobxon.getSurname(), kitobxon.getChatid(), kitobxon.getStep(), Faoliyat_turlari.Kitobxon);
        return kitobxon;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            long chatid = message.getChatId();
            USer uSer = user(chatid);
            if (message.hasDocument()) {
                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId(uSer.getChatid());
                sendMessage.setText("kitob yuklandi");
                Document document = message.getDocument();
                String file = document.getFileId();
                System.out.println(file);
                kitob kitob = kitobtable.kitobreturnb();
                System.out.println(kitob);
                kitobtable.updatefile(file,kitob.getName());
                try {
                    execute(sendMessage);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else if (message.hasText()) {
                String text = message.getText();
                if (text.equals("/start")) {
                    try {
                        /*List<KeyboardRow> keyboardRowList = new ArrayList<>();
                        Button button = new Button();
                        keyboardRowList.add(button.Keyboard(Faoliyat_turlari.Admin, Faoliyat_turlari.Kitobxon));
                        sendMessage.setReplyMarkup(button.markup(keyboardRowList, "faoliyat turini tanlang"));*/
                        SendMessage sendMessage = new SendMessage();
                        sendMessage.setChatId(chatid);
                        //........buttonlar.
                        sendMessage.setText("Assalomu alaykum hush kelibsz\nRo'yxatdan o'tmagan bo'lsangiz \n ro'yxatdan o'tish tugmasini bosing 👇");
                        List<List<InlineKeyboardButton>> rowList;
                        Inlinebutton inlinebutton = new Inlinebutton();
                        rowList = inlinebutton.buttons("Ro'yxatdan o'tish", "Tizimga kirish");
                        sendMessage.setReplyMarkup(inlinebutton.inlineKeyboardMarkup(rowList));
                        //.........tugadi.
                        uSer.setStep(holatlar.began);
                        execute(sendMessage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else if (uSer.getStep().equals(button_nomlari.Royxatdan_otish)) {
                    if (uSer.getFaoliat().equals(Faoliyat_turlari.Admin)) {
                        if (!uSer.getLogin().isEmpty() && !uSer.getPassword().isEmpty() && !uSer.getName().isEmpty() && !uSer.getSurname().isEmpty() && uSer.getChatid() != 0) {
                            SendMessage sendMessage = new SendMessage();
                            sendMessage.setChatId(uSer.getChatid());
                            sendMessage.setText(" Siz allaqachon bazaga qoshilgansz ");
                            List<List<InlineKeyboardButton>> rowList;
                            Inlinebutton inlinebutton = new Inlinebutton();
                            rowList = inlinebutton.buttons("Tizimga kirish");
                            sendMessage.setReplyMarkup(inlinebutton.inlineKeyboardMarkup(rowList));
                            try {
                                execute(sendMessage);
                            } catch (TelegramApiException e) {
                                throw new RuntimeException(e);
                            }
                        } else
                            Container.adminservicimpl.addbaza(uSer.getStep(), text);
                    } else {
                        Container.kitobserceimpl.addbaza(text, uSer, kitobxonTable);
                    }
                }
                else if (uSer.getStep().equals(button_nomlari.Tizimga_kirish)) {
                    if (uSer.getFaoliat().equals(Faoliyat_turlari.Kitobxon)) {
                        Container.adminservicimpl.tizimga_kirish(uSer.getStep(), uSer.getFaoliat(), text, uSer);
                    } else
                        Container.adminservicimpl.tizimga_kirish(uSer.getStep(), uSer.getFaoliat(), text, uSer);
                }
                // admin bajarib turgan ishlar
                else if (uSer.getStep().equals(adminmenyu.kitobo_chirish)) {
                    Container.adminservicimpl.kitobo_chirish(text, chatid,kitobtable);
                }
                else if (uSer.getStep().equals(adminmenyu.kitobxon_ochirish)) {
                    Container.adminservicimpl.kitobxon_ochirish(text, uSer, kitobxonTable);
                }
                else if (uSer.getStep().equals(adminmenyu.kitob_olish)) {
                    SendDocument sendDocument = kitobtable.kitobfile(text, uSer.getChatid());
                    if (uSer.getFaoliat().equals(Faoliyat_turlari.Kitobxon)) {
                        kitobtable.updatetavsif(text, uSer);
                    }
                    try {
                        execute(sendDocument);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else if (uSer.getStep().equals(adminmenyu.kitob_qoshish)) {
                    SendMessage message1 = new SendMessage();
                    message1.setChatId(chatid);
                    kitob kitob = kitob(text);
                    if (text.equals(kitob.getName())) {
                            message1.setText("pdf file yuboring");
                    }
                    try {
                        execute(message1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } else if (update.hasCallbackQuery()) {
            CallbackQuery query = update.getCallbackQuery();
            String callBackData = query.getData();
            USer uSer = user(query.getFrom().getId());
            if (callBackData.equals(button_nomlari.Royxatdan_otish) || callBackData.equals(button_nomlari.Tizimga_kirish)) {
                switch (callBackData) {
                    case button_nomlari.Royxatdan_otish -> {
                        if (!uSer.getLogin().equals("null") && !uSer.getPassword().equals("null") && !uSer.getName().equals("null") && !uSer.getSurname().equals("null")) {
                            SendMessage sendMessage = new SendMessage();
                            System.out.println(uSer.getLogin());
                            System.out.println(uSer.getStep());
                            sendMessage.setChatId(uSer.getChatid());
                            sendMessage.setText(" Siz allaqachon bazaga qoshilgansz ");
                            List<List<InlineKeyboardButton>> rowList;
                            Inlinebutton inlinebutton = new Inlinebutton();
                            rowList = inlinebutton.buttons("Tizimga kirish");
                            sendMessage.setReplyMarkup(inlinebutton.inlineKeyboardMarkup(rowList));
                            try {
                                execute(sendMessage);
                            } catch (TelegramApiException e) {
                                throw new RuntimeException(e);
                            }
                            if (uSer.getFaoliat().equals(Faoliyat_turlari.Kitobxon)) {
                                kitobxonTable.updatestep(button_nomlari.Tizimga_kirish, uSer.getChatid());
                            } else
                                admintable.updatestep(button_nomlari.Tizimga_kirish, uSer.getChatid());
                        } else {
                            SendMessage message1 = new SendMessage();
                            message1.setChatId(uSer.getChatid());
                            message1.setText("Ismingizni kiriting");
                            try {
                                execute(message1);
                            } catch (TelegramApiException e) {
                                e.printStackTrace();
                            }
                            if (uSer.getFaoliat().equals(Faoliyat_turlari.Kitobxon)) {
                                kitobxonTable.updatestep(button_nomlari.Royxatdan_otish, uSer.getChatid());
                            } else
                                admintable.updatestep(button_nomlari.Royxatdan_otish, uSer.getChatid());
                        }
                    }
                    case button_nomlari.Tizimga_kirish -> {
                        if (uSer.getLogin().equals("null") && uSer.getPassword().equals("null") && uSer.getName().equals("null") && uSer.getSurname().equals("null")) {
                            SendMessage sendMessage = new SendMessage();
                            sendMessage.setChatId(uSer.getChatid());
                            sendMessage.setText(" Siz hali ro'yxatdan o'tmagansz ");
                            List<List<InlineKeyboardButton>> rowList;
                            Inlinebutton inlinebutton = new Inlinebutton();
                            rowList = inlinebutton.buttons("Ro'yxatdan o'tish");
                            sendMessage.setReplyMarkup(inlinebutton.inlineKeyboardMarkup(rowList));
                            try {
                                execute(sendMessage);
                            } catch (TelegramApiException e) {
                                throw new RuntimeException(e);
                            }
                            if (uSer.getFaoliat().equals(Faoliyat_turlari.Kitobxon)) {
                                kitobxonTable.updatestep(button_nomlari.Royxatdan_otish, uSer.getChatid());
                            } else
                                admintable.updatestep(button_nomlari.Royxatdan_otish, uSer.getChatid());
                        }
                        SendMessage message1 = new SendMessage();
                        message1.setChatId(uSer.getChatid());
                        message1.setText("parolingizni kiriting");
                        try {
                            execute(message1);
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        if (uSer.getFaoliat().equals(Faoliyat_turlari.Kitobxon)) {
                            kitobxonTable.updatestep(button_nomlari.Tizimga_kirish, uSer.getChatid());
                        } else
                            admintable.updatestep(button_nomlari.Tizimga_kirish, uSer.getChatid());
                    }
                }
            } else if (callBackData.equals(adminmenyu.kitobxonlar_royxati) || callBackData.equals(adminmenyu.kitobxon_qoshish) || callBackData.equals(adminmenyu.kitob_qoshish) || callBackData.equals(adminmenyu.kitob_royxati) || callBackData.equals(adminmenyu.kitobo_chirish) || callBackData.equals(adminmenyu.kitobxon_ochirish)) {
                switch (callBackData) {
                    case adminmenyu.kitobxonlar_royxati -> {
                        Container.adminservicimpl.kitobxonlar_royxati(kitobxonTable, uSer);
                        admintable.updatestep(adminmenyu.kitobxonlar_royxati, uSer.getChatid());
                    }
                    case adminmenyu.kitob_qoshish -> {
                        SendMessage message1 = new SendMessage();
                        message1.setText("kitob nomini kiriting");
                        message1.setChatId(uSer.getChatid());
                        try {
                            execute(message1);
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        if (uSer.getFaoliat().equals(Faoliyat_turlari.Admin)) {
                            admintable.updatestep(adminmenyu.kitob_qoshish, uSer.getChatid());
                        }

                    }
                    case adminmenyu.kitob_royxati -> {
                        if (uSer.getFaoliat().equals(Faoliyat_turlari.Kitobxon)) {
                            Container.kitobserceimpl.kitoblar_royxati(uSer.getChatid());
                            kitobxonTable.updatestep(adminmenyu.kitob_royxati, uSer.getChatid());
                        } else {
                            Container.adminservicimpl.kitob_royxati(uSer.getChatid());
                            admintable.updatestep(adminmenyu.kitob_olish, uSer.getChatid());
                        }
                    }
                    case adminmenyu.kitobo_chirish -> {
                        SendMessage message1 = new SendMessage();
                        message1.setText("kitob nomini kiriting");
                        message1.setChatId(uSer.getChatid());
                        try {
                            execute(message1);
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        if (uSer.getFaoliat().equals(Faoliyat_turlari.Admin)) {
                            admintable.updatestep(adminmenyu.kitobo_chirish, uSer.getChatid());
                        }
                    }
                    case adminmenyu.kitobxon_ochirish -> {
                        SendMessage message1 = new SendMessage();
                        message1.setText("kitobxon ismini kiriting");
                        message1.setChatId(uSer.getChatid());
                        try {
                            execute(message1);
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        if (uSer.getFaoliat().equals(Faoliyat_turlari.Admin)) {
                            admintable.updatestep(adminmenyu.kitobxon_ochirish, uSer.getChatid());
                        }
                    }
                }

            } else if (callBackData.equals(kitobxonmenyu.kitob_olish) || callBackData.equals(kitobxonmenyu.kitoblar_royxati)) {
                switch (callBackData) {
                    case kitobxonmenyu.kitob_olish -> {
                        Container.kitobserceimpl.kitob_olish(uSer.getChatid());
                        if (uSer.getFaoliat().equals(Faoliyat_turlari.Kitobxon)) {
                            kitobxonTable.updatestep(adminmenyu.kitob_olish, uSer.getChatid());
                        } else
                            admintable.updatestep(adminmenyu.kitob_olish, uSer.getChatid());
                    }
                    case kitobxonmenyu.kitoblar_royxati -> {
                        Container.kitobserceimpl.kitoblar_royxati(uSer.getChatid());
                        if (uSer.getFaoliat().equals(Faoliyat_turlari.Kitobxon)) {
                            kitobxonTable.updatestep(adminmenyu.kitob_royxati, uSer.getChatid());
                        } else
                            admintable.updatestep(adminmenyu.kitob_olish, uSer.getChatid());
                    }
                }

            }
        }

    }


    @Override
    public String getBotUsername() {
        return "kutubxona_ARM_bot";
    }

    @Override
    public String getBotToken() {
        return "7184765367:AAH0vkXw1FbKMIN78kfynxv35Yy6GvZU3uE";
    }


}