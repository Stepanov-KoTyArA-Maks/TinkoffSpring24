package edu.java.bot.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.mockdb.MockDB;

public class StartCommand implements CommandInterface {

    public String command() {
        return "/start";
    }

    public String description() {
        return "Register user";
    }

    public SendMessage handle(Update update) {
        Object chatId = update.message().chat().id();
        MockDB.createUser(chatId);
        return new SendMessage(chatId, "Hello, you are registered for the TrackSiteUpdates_bot\n "
            + "For a list of possible commands, enter /help");
    }
}
