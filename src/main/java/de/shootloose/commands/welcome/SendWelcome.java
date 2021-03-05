package de.shootloose.commands.welcome;

import de.shootloose.utils.EmbedHelper;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;

public class SendWelcome {

    public static void sendWelcome(GuildMemberJoinEvent event) {
        User user = event.getUser();
        if(user.isBot()) { return; }
        Guild guild = event.getGuild();

        EmbedBuilder builder = EmbedHelper.getBuilder(user.getEffectiveAvatarUrl(), "Welcome " + user.getName(), "Thank you for joining " + guild.getName() + "!");
        user.openPrivateChannel().queue(privateChannel -> privateChannel.sendMessage(builder.build()).queue());
    }
}
