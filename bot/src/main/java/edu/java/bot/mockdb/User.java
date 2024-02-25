package edu.java.bot.mockdb;

import java.util.HashSet;

class User {

    User(Object chatId, HashSet<String> links) {
        this.chatId = chatId;
        this.links = links;
    }

    Object chatId;
    public HashSet<String> links;
}
