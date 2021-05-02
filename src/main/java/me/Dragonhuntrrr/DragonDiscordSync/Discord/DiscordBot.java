package me.Dragonhuntrrr.DragonDiscordSync.Discord;

import me.Dragonhuntrrr.DragonDiscordSync.DragonDiscordSync;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import org.bukkit.Bukkit;

import javax.security.auth.login.LoginException;

public class DiscordBot {

    public static void startBot(String token) {
        JDABuilder builder = JDABuilder.createDefault(token);

        builder.setActivity(Activity.of(Activity.ActivityType.CUSTOM_STATUS, "Wag wan!"));

        try {
            builder.build();
        } catch (LoginException e) {
            Bukkit.getLogger().severe("Invalid Bot-token!");
        }
    }

}
