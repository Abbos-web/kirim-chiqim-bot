package uz.pdp.utils;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.LinkedList;
import java.util.List;

public class InlineButtonUtil {


    public static InlineKeyboardMarkup menuButtons(){

        InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
        inlineKeyboardButton.setText("Add Plus");
        inlineKeyboardButton.setCallbackData("plus_callback");
        List<InlineKeyboardButton> row = new LinkedList<>();
        row.add( inlineKeyboardButton );

        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        inlineKeyboardButton2.setText("Add Minus");
        inlineKeyboardButton2.setCallbackData("minus_callback");
        List<InlineKeyboardButton> row2 = new LinkedList<>();
        row2.add( inlineKeyboardButton2 );

        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText("Show plus list");
        button.setCallbackData("income_callback");
        InlineKeyboardButton button2 = new InlineKeyboardButton();
        button2.setText("Show minus list");
        button2.setCallbackData("expense_callback");
        List<InlineKeyboardButton> row3 = new LinkedList<>();
        row3.add( button );
        row3.add( button2 );

        InlineKeyboardButton inlineKeyboardButton4 = new InlineKeyboardButton();
        inlineKeyboardButton4.setText("Result");
        inlineKeyboardButton4.setCallbackData("result_callback");

        List<InlineKeyboardButton> row4 = new LinkedList<>();
        row4.add( inlineKeyboardButton4 );

        List< List<InlineKeyboardButton> > rowList = new LinkedList<>();
        rowList.add( row );
        rowList.add( row2 );
        rowList.add( row3 );
        rowList.add( row4 );

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(rowList);

        return inlineKeyboardMarkup;
    }


}
