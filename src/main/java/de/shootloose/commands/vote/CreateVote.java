package de.shootloose.commands.vote;

import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import de.shootloose.interfaces.CommandInterface;
import de.shootloose.utils.EmbedHelper;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.ArrayList;
import java.util.List;

public class CreateVote implements CommandInterface {
    private EmbedBuilder builder;

    @Override
    public void performCommand(User user, MessageChannel channel, Message message, MessageReceivedEvent event, EventWaiter waiter) {

        switch (channel.getType()) {
            case TEXT:
                Guild guild = event.getGuild();
                break;
            case PRIVATE:
                List<Guild> guilds = user.getMutualGuilds();
                StringBuilder stringBuilder = new StringBuilder();

                for(int i = 0; i < guilds.size(); i++) {
                    String input = "`" + (i+1) + "` - " + guilds.get(i).getName();
                    stringBuilder.append(input).append("\n");
                }

                builder = EmbedHelper.getBuilder(user, "Server Choise", user.getAsMention() + ", please choose a server:")
                        .addField("Servers:", stringBuilder.toString(), false);
                message.reply(builder.build()).queue();

                boolean server = true, txtChannel = true;
                while (server) {

                }

                break;
            default:
        }
    }
}
