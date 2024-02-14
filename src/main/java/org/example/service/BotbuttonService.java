package org.example.service;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class BotbuttonService {




public  ReplyKeyboardMarkup markup(){
    ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
    List<KeyboardRow> rowList = new ArrayList<>();
    KeyboardRow row1 = new KeyboardRow();
    KeyboardRow row2 = new KeyboardRow();
    KeyboardButton uz = new KeyboardButton();
    uz.setText("1-модуль ");
    KeyboardButton ru = new KeyboardButton();
    ru.setText("2-модуль");

    row1.add(uz);
    row2.add(ru);

    rowList.add(row1);
    rowList.add(row2);
    markup.setKeyboard(rowList);
    markup.setResizeKeyboard(true);

    return  markup;
}



}
