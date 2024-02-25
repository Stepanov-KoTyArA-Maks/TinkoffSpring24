package edu.java.bot.mockdb;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class MockDB {

    private MockDB() {
    }

    private static HashMap<Object, User> userList = new HashMap<>();

    public static void createUser(Object chatId) {
        if (!userList.containsKey(chatId)) {
            userList.put(chatId, new User(chatId, new HashSet<String>()));
        }
    }

    public static void addLink(Object chatId, String link) {
        if (!userList.containsKey(chatId)) {
            createUser(chatId);
        }
        userList.get(chatId).links.add(link);
    }

    public static int deleteLink(Object chatId, String link) {
        if (!userList.get(chatId).links.contains(link)) {
            return 1;
        }

        userList.get(chatId).links.remove(link);
        return 0;
    }

    public static List<String> getLinks(Object chatId) {
        if (!userList.containsKey(chatId)) {
            return null;
        }

        return userList.get(chatId).links.stream().toList();
    }

}
