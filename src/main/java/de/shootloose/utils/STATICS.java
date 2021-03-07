package de.shootloose.utils;

import java.util.ArrayList;
import java.util.Arrays;

public class STATICS {
    private static final String[] console = {"BOT IST STARTING...", "BOT HAS SUCCESSFULLY STARTED!", "BOT IS SHUTTING DOWN...", "BOT IS OFFLINE.", "USE 'shutdown' TO SHUTDOWN!", "SUCCESSFULLY CONNECTED TO DATABASE!", "CAN'T CONNECT TO DATABASE!", "SUCCESSFULLY DISCONNECTED FROM DATABASE!", "ERROR WHILE DISCONNECTING FROM DATABASE!"};
    private static final ArrayList<String> activities = new ArrayList<>(Arrays.asList("Version: 1.0", "Check out: backxtar_official", "Check out: raffa.oure"));

    //InstagramUsername InstagramPassword BotToken
    private static final String[] login = {"", "", ""};

    public static String[] getConsole() {
        return console;
    }
    public static ArrayList<String> getActivities() {
        return activities;
    }
    public static String[] getLogin() { return login; }
}


