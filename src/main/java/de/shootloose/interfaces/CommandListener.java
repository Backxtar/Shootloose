package de.shootloose.interfaces;

import de.shootloose.Shootloose;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class CommandListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        User user = event.getAuthor();

        if(SpamFilter.isSpam(user.getIdLong())) {return;}
        if(user.isBot()) {return;}

        String command, message = event.getMessage().getContentDisplay();

        if(event.isFromType(ChannelType.TEXT) || event.isFromType(ChannelType.PRIVATE)) {
            MessageChannel channel = event.getChannel();

            if(message.startsWith("!!")) {
                String[] tmp = message.substring(3).split(" ");
                command = tmp[0];
            } else {
                return;
            }
            Shootloose.shootloose.getCmdReg().perform(command, user, channel, event.getMessage(), event, Shootloose.shootloose.getWaiter());
        }
    }
}
