package de.shootloose.interfaces;

public class SpamFilter {
    static long user = 0L;
    static long user2 = 0L;
    static long user3 = 0L;
    static long user4 = 0L;
    static long user5 = 0L;
    static long message = 0L;
    static long message2 = 0L;
    static long message3 = 0L;
    static long message4 = 0L;
    static long message5 = 0L;

    public static boolean getSpam = false;
    public static boolean isSpam(long userID) {

        message5 = message4;
        message4 = message3;
        message3 = message2;
        message2 = message;
        message = System.currentTimeMillis();

        if (message - message5 > 3000) {
            getSpam = false;
            return getSpam;
        }

        user5 = user4;
        user4 = user3;
        user3 = user2;
        user2 = user;
        user = userID;

        int count = 0;
        if (user5 == user) {
            count++;
        }
        if (user4 == user) {
            count++;
        }
        if (user3 == user) {
            count++;
        }
        if (user2 == user) {
            count++;
        }

        if(count > 1) {getSpam = true;}
        return getSpam;
    }
}
