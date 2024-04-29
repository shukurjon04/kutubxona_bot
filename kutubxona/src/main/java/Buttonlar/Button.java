package Buttonlar;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class Button {
    private List<String> name = new ArrayList<>();



    public KeyboardRow Keyboard(String... nomi){
        this.name.addAll(List.of(nomi));

        KeyboardRow  row = new KeyboardRow();
        for (String s : name) {
            KeyboardButton button = new KeyboardButton(s);
            row.add(button);
        }
        return row;

    }
    public ReplyKeyboardMarkup createKeyboard(List<String> names) {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboard = new ArrayList<>();

        int columns = Math.min(names.size(), 3);
        for (int i = 0; i < names.size(); i += columns) {
            KeyboardRow row = new KeyboardRow();
            for (int j = 0; j < columns; j++) {
                if (i + j < names.size()) {
                    row.add(new KeyboardButton(names.get(i + j)));
                }
            }
            keyboard.add(row);
        }

        keyboardMarkup.setKeyboard(keyboard);
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(true);
        keyboardMarkup.setInputFieldPlaceholder("Kitoblar royxati");
        return keyboardMarkup;
    }
    public SendMessage sendDynamicButtons(Long chatId) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();

        // 9 ta tugma yaratish
        for (int i = 1; i <= 9; i++) {
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText("saodat asri " + i);
            button.setCallbackData("button_" + i);
            List<InlineKeyboardButton> rowInline = new ArrayList<>();
            rowInline.add(button);
            rowsInline.add(rowInline);
        }

        // 10-tugmani "Keyingisi" deb nomlash
        InlineKeyboardButton nextButton = new InlineKeyboardButton();
        nextButton.setText("Keyingisi");
        nextButton.setCallbackData("next");
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        rowInline.add(nextButton);
        rowsInline.add(rowInline);

        // Tugmalar ro'yxatini yangilash
        inlineKeyboardMarkup.setKeyboard(rowsInline);

        // Foydalanuvchiga tugmalar ro'yxatini yuborish
        SendMessage message = new SendMessage();
        message.setChatId(chatId.toString());
        message.setText("Tugmalar:");
        message.setReplyMarkup(inlineKeyboardMarkup);

       return message;
    }


    public ReplyKeyboardMarkup markup(List<KeyboardRow> row,String text){
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        markup.setKeyboard(row);
        markup.setResizeKeyboard(true);
        markup.setOneTimeKeyboard(true);
        markup.setInputFieldPlaceholder(text);
        return markup;
    }
}
