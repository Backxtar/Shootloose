package de.shootloose.commands.welcome;

import de.shootloose.utils.EmbedHelper;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;

public class ThanksForInvite {

    public static void sendThanks(GuildJoinEvent event) {
        Guild guild = event.getGuild();
        User owner = guild.getOwner().getUser();

        EmbedBuilder builder = EmbedHelper.getBuilder(owner, "Invite to " + guild.getName(), "Thank you for inviting " + event.getJDA().getSelfUser().getName() + "!");
        owner.openPrivateChannel().queue(privateChannel -> privateChannel.sendMessage(builder.build()).queue());
    }
}
