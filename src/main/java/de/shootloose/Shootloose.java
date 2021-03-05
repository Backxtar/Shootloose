package de.shootloose;

import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.exceptions.IGLoginException;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import de.shootloose.interfaces.CommandListener;
import de.shootloose.interfaces.CommandRegister;
import de.shootloose.interfaces.EventDistributor;
import de.shootloose.utils.SQLHelper;
import de.shootloose.utils.STATICS;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

import javax.security.auth.login.LoginException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Shootloose {

    public static Shootloose shootloose;
    public ShardManager shardManager;
    public IGClient instaClient;
    private final CommandRegister cmdReg;
    private final EventWaiter waiter;
    private Thread loop;
    private boolean shutdown = false;
    private int nextActivityPos = 0;
    private String actualActivity = "Members: %members";

    public static void main(String[] args) {
        try {
            new Shootloose();
        } catch (LoginException | IllegalArgumentException | IGLoginException e) {
            e.printStackTrace();
        }
    }

    public Shootloose() throws LoginException, IllegalArgumentException, IGLoginException {
        shootloose = this;
        this.cmdReg = new CommandRegister();
        this.waiter = new EventWaiter();

        System.out.println(STATICS.getConsole()[0]);

        DefaultShardManagerBuilder shardManagerBuilder = DefaultShardManagerBuilder.create(
                GatewayIntent.GUILD_PRESENCES,
                GatewayIntent.GUILD_MESSAGES,
                GatewayIntent.DIRECT_MESSAGES,
                GatewayIntent.DIRECT_MESSAGE_TYPING,
                GatewayIntent.GUILD_MESSAGE_TYPING,
                GatewayIntent.DIRECT_MESSAGE_REACTIONS,
                GatewayIntent.GUILD_MESSAGE_REACTIONS,
                GatewayIntent.GUILD_EMOJIS,
                GatewayIntent.GUILD_MEMBERS)

                .setToken(STATICS.getLogin()[2])
                .setStatus(OnlineStatus.ONLINE)
                .setAutoReconnect(true)
                .setShardsTotal(-1)

                .addEventListeners(new CommandListener())
                .addEventListeners(new EventDistributor())
                .addEventListeners(this.waiter);
        shardManager = shardManagerBuilder.build();

        IGClient.Builder igClient = IGClient.builder()
                .username(STATICS.getLogin()[0])
                .password(STATICS.getLogin()[1]);
        instaClient = igClient.build();

        SQLHelper.connect();
        shutdown();
        runTimers();
        System.out.println(STATICS.getConsole()[1]);
    }

    private void shutdown() {
        new Thread(() -> {
            String line;
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                while ((line = reader.readLine()) != null) {
                    if (line.equalsIgnoreCase("shutdown")) {
                        shutdown = true;

                        if (shardManager != null) {
                            shardManager.setStatus(OnlineStatus.OFFLINE);
                            shardManager.shutdown();
                            SQLHelper.disconnect();
                            System.out.println(STATICS.getConsole()[3]);
                            System.exit(0);
                        }
                        if(loop != null) {
                            loop.interrupt();
                        }
                        reader.close();
                    } else {
                        System.out.println(STATICS.getConsole()[4]);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void runTimers() {
        this.loop = new Thread(() -> {
            long everyTenSeconds = System.currentTimeMillis();

            while (!shutdown) {
                if(System.currentTimeMillis() >= everyTenSeconds + 1000 * 10) {
                    everyTenSeconds = System.currentTimeMillis();
                    switchActivity();
                }
            }
        });
        this.loop.setName("Timers");
        this.loop.start();
    }

    private void switchActivity() {
        shardManager.getShards().forEach(jda -> {
            nextActivityPos = STATICS.getActivities().indexOf(actualActivity) >= STATICS.getActivities().size() - 1 ? 0 : nextActivityPos + 1;
            actualActivity = STATICS.getActivities().get(nextActivityPos).replaceAll("%members", "" + jda.getUsers().size());
            jda.getPresence().setActivity(Activity.watching(actualActivity));
        });
    }

    public CommandRegister getCmdReg() {return cmdReg;}
    public EventWaiter getWaiter() {return waiter;}
}
