package de.shootloose.utils;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;

import java.util.Random;

public class EmbedHelper {
    public static EmbedBuilder getBuilder(User user, String title, String desc) {
        return new EmbedBuilder()
                .setAuthor("Executed by " + user.getName(), user.getEffectiveAvatarUrl())
                .setTitle(title)
                .setDescription(desc)
                .setFooter(getRandomTip(), "http://i.epvpimg.com/lHmUcab.png");
    }

    public static EmbedBuilder getBuilder(String url, String title, String desc) {
        return new EmbedBuilder()
                .setThumbnail(url)
                .setTitle(title)
                .setDescription(desc)
                .setFooter(getRandomTip(), "http://i.epvpimg.com/lHmUcab.png");
    }

    public static EmbedBuilder getBuilder(String desc) {
        return new EmbedBuilder()
                .setDescription(desc)
                .setFooter(getRandomTip(), "http://i.epvpimg.com/lHmUcab.png");
    }

    private static String getRandomTip() {
        String[] values = {"If you dont want to share your data with others you can write me a private message.", "Type: kg!help to get a list of all commands.", "Always use a valid GW2 API-Key!"};
        Random random = new Random();
        return values[random.nextInt(values.length)];
    }
}
