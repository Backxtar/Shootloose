package de.shootloose.utils;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;

import javax.jws.soap.SOAPBinding;
import java.awt.*;
import java.util.List;
import java.util.Random;

public class EmbedHelper {
    public static EmbedBuilder getBuilder(User user, String title, String desc) {
        return new EmbedBuilder()
                .setAuthor("Executed by " + user.getName(), user.getEffectiveAvatarUrl())
                .setTitle(title)
                .setDescription(desc)
                .setColor(Color.decode(getRandomColor()))
                .setFooter(getRandomTip(), "http://i.epvpimg.com/lHmUcab.png");
    }

    public static EmbedBuilder getBuilder(String url, String title, String desc) {
        return new EmbedBuilder()
                .setThumbnail(url)
                .setTitle(title)
                .setDescription(desc)
                .setColor(Color.decode(getRandomColor()))
                .setFooter(getRandomTip(), "http://i.epvpimg.com/lHmUcab.png");
    }

    public static EmbedBuilder getBuilder(String desc) {
        return new EmbedBuilder()
                .setDescription(desc)
                .setColor(Color.decode(getRandomColor()))
                .setFooter(getRandomTip(), "http://i.epvpimg.com/lHmUcab.png");
    }

    private static EmbedBuilder noInput() {
        return new EmbedBuilder()
                .setDescription("There was no input recognized. The listener stopped listening.")
                .setFooter(getRandomTip(), "http://i.epvpimg.com/lHmUcab.png");
    }

    public static void changeColor(Message message, EmbedBuilder builder, MessageChannel channel) {
        builder.setColor(Color.decode("#2c2f33"));
        message.editMessage(builder.build()).queue();
        channel.sendMessage(noInput().build()).queue();
    }

    private static String getRandomTip() {
        String[] values = {"You can also send me private messages!", "Type: !!help to get a list of all commands.", "You can change my prefix with !!prefix"};
        Random random = new Random();
        return values[random.nextInt(values.length)];
    }

    private static String getRandomColor() {
        String[] values = {"#515BD4", "#8134AF", "#DD2A7B", "#FEDA77", "#F58529"};
        Random random = new Random();
        return values[random.nextInt(values.length)];
    }
}
