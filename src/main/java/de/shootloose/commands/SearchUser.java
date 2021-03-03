package de.shootloose.commands;

import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import de.shootloose.interfaces.CommandInterface;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class SearchUser implements CommandInterface {
    @Override
    public void performCommand(User user, MessageChannel channel, Message message, MessageReceivedEvent event, EventWaiter waiter) {
        
    }
}
