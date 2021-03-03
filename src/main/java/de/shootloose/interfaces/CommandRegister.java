package de.shootloose.interfaces;

import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.concurrent.ConcurrentHashMap;

public class CommandRegister {
    public ConcurrentHashMap<String, CommandInterface> commands;

    public CommandRegister() {
        this.commands = new ConcurrentHashMap<>();
    }

    public boolean perform(String command, User user, MessageChannel channel, Message message, MessageReceivedEvent event, EventWaiter waiter) {
        CommandInterface cmd;

        if((cmd = this.commands.get(command.toLowerCase())) != null) {
            cmd.performCommand(user, channel, message, event, waiter);
            return true;
        }
        return false;
    }
}
