package Buttonlar;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import java.util.ArrayList;
import java.util.List;
public class Inlinebutton {
    private List<String> name = new ArrayList<>();
    public List<List<InlineKeyboardButton>> buttons(String... nomi) {
        this.name.addAll(List.of(nomi));
        List< List<InlineKeyboardButton>> rowList = new ArrayList<>();
        List<InlineKeyboardButton> row = new ArrayList<>();
        for (String s : name) {
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(s);
            button.setCallbackData(s);
            row.add(button);
        }
        rowList.add(row);
        return rowList;
    }
    public List<InlineKeyboardButton> button(String... nomi) {
        this.name.addAll(List.of(nomi));
        List<InlineKeyboardButton> row = new ArrayList<>();
        for (String s : name) {
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(s);
            button.setCallbackData(s);
            row.add(button);
        }
        return row;
    }
    public InlineKeyboardMarkup inlineKeyboardMarkup(List<List<InlineKeyboardButton>> lists){
        InlineKeyboardMarkup inlineKeyboardMarkub = new InlineKeyboardMarkup( );
        inlineKeyboardMarkub.setKeyboard(lists);
        return inlineKeyboardMarkub;
    }
}
