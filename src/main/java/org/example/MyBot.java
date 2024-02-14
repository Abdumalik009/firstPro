package org.example;

import org.example.model.User;
import org.example.modul.enums.BotState;
import org.example.modul.enums.Language;
import org.example.service.BotbuttonService;
import org.example.service.impl.UserServiceImpl;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.groupadministration.GetChatMember;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.chatmember.ChatMember;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.example.service.BotbuttonService;


import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MyBot extends TelegramLongPollingBot {
    public MyBot(String botToken) {
        super(botToken);
    }

    public static UserServiceImpl userService = new UserServiceImpl();
    BotbuttonService botbuttonService = new BotbuttonService();

    @Override
    public void onUpdateReceived(Update update) {
        Long chatId = update.getMessage().getChatId();
        String text = update.getMessage().getText();
        if (update.hasMessage() && update.getMessage().hasText()) {


            if (text.equals("/start")) {
                ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
                List<KeyboardRow> rowList = new ArrayList<>();
                KeyboardRow row1 = new KeyboardRow();
                KeyboardRow row2 = new KeyboardRow();
                KeyboardButton uz = new KeyboardButton();
                uz.setText("\uD83C\uDDFA\uD83C\uDDFFO'zbek tili");
                KeyboardButton ru = new KeyboardButton();
                ru.setText("\uD83C\uDDF7\uD83C\uDDFARus tili");
                User user = new User();
                user.setChatId(chatId);
                user.setState(BotState.Lang);
                user.setLang(Language.UZ);
                userService.create(user);

                row1.add(uz);
                row2.add(ru);


                rowList.add(row1);
                rowList.add(row2);
                markup.setKeyboard(rowList);
                markup.setResizeKeyboard(true);
                SendMessage message = new SendMessage();
                message.setText("\uD83C\uDDFA\uD83C\uDDFF Tilni tanlang\n" +
                        "\uD83C\uDDF7\uD83C\uDDFA Выберите язык");
                message.setChatId(chatId);
                message.setReplyMarkup(markup);
                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }


            } else {
                User user = userService.get(chatId);
                if (user.getLang() == Language.UZ) {
                    switch (user.getState()) {
                        case Lang -> {
                            if (text.equals("\uD83C\uDDFA\uD83C\uDDFFO'zbek tili")) {
                                user.setLang(Language.UZ);
                            } else if (text.equals("\uD83C\uDDF7\uD83C\uDDFARus tili")) {
                                user.setLang(Language.RU);
                            }
                        }

                    }
                } else if (user.getLang() == Language.RU) {
                    switch (user.getState()) {
                        case Lang -> {
                            if (text.equals("\uD83C\uDDFA\uD83C\uDDFFO'zbek tili")) {

                            } else if (text.equals("\uD83C\uDDF7\uD83C\uDDFARus tili")) {

                            }
                        }

                    }
                }

            }
        } else if (update.getMessage().hasContact()) {

        }
    }


    @Override
    public String getBotUsername() {
        return "PDP_Telegram_bot";
    }
}