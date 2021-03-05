package de.shootloose.commands;

import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import de.shootloose.interfaces.CommandInterface;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

public class CreateVote implements CommandInterface {
    @Override
    public void performCommand(User user, MessageChannel channel, Message message, MessageReceivedEvent event, EventWaiter waiter) {

        switch (channel.getType()) {
            case TEXT:
                Guild guild = event.getGuild();
                break;
            case PRIVATE:
            case GROUP:
                List<Guild> guilds = user.getJDA().getGuilds();
                break;
            default:
        }
    }
}
