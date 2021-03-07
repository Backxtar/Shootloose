package de.shootloose.commands.vote;

import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import de.shootloose.interfaces.CommandInterface;
import de.shootloose.utils.EmbedHelper;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CreateVote implements CommandInterface {
    private Guild guild;
    private TextChannel textChannel;

    private EmbedBuilder builder;
    private Message mesSend;

    @Override
    public void performCommand(User user, MessageChannel channel, Message message, MessageReceivedEvent event, EventWaiter waiter) {

        switch (channel.getType()) {
            case TEXT:
                guild = event.getGuild();
                break;
            case PRIVATE:
                List<Guild> guilds = user.getMutualGuilds();
                StringBuilder stringBuilder = new StringBuilder();

                for (int i = 0; i < guilds.size(); i++) {
                    String input = "`" + (i+1) + "` - " + guilds.get(i).getName();
                    stringBuilder.append(input).append("\n");
                }

                builder = EmbedHelper.getBuilder(user, "Server Choise", user.getAsMention() + ", please choose a server:")
                        .addField("Servers:", stringBuilder.toString(), false);
                mesSend = message.reply(builder.build()).complete();

                boolean server = true, txtChannel = true;
                while (server) {
                    waiter.waitForEvent(MessageReceivedEvent.class, e1 -> e1.getAuthor().equals(event.getAuthor()) && e1.getChannel().equals(event.getChannel()) && !e1.getMessage().equals(event.getMessage()), e1 -> {

                    },1, TimeUnit.MINUTES, () -> EmbedHelper.changeColor(mesSend, builder, channel));
                }
                break;
            default:
        }
    }
}
