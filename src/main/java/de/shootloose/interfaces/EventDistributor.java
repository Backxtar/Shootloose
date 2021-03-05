package de.shootloose.interfaces;

import de.shootloose.commands.welcome.SendWelcome;
import de.shootloose.commands.welcome.ThanksForInvite;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class EventDistributor extends ListenerAdapter {

    @Override
    public void onGuildJoin(@NotNull GuildJoinEvent event) {
        ThanksForInvite.sendThanks(event);
    }

    @Override
    public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent event) {
        SendWelcome.sendWelcome(event);
    }
}
