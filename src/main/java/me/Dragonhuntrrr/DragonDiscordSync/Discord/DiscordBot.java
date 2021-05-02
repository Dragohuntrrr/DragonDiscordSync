package me.Dragonhuntrrr.DragonDiscordSync.Discord;

import me.Dragonhuntrrr.DragonDiscordSync.DragonDiscordSync;
import me.Dragonhuntrrr.DragonDiscordSync.Util.File.FileBasics;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import org.bukkit.Bukkit;

import javax.security.auth.login.LoginException;

public class DiscordBot {

    public static void startBot(String token) {
        JDABuilder builder = JDABuilder.createDefault(token);

        if (FileBasics.FILETYPE.CONFIG.getString("Bot-activity").equalsIgnoreCase("Playing")) {
            builder.setActivity(Activity.of(Activity.ActivityType.DEFAULT,
                    FileBasics.FILETYPE.CONFIG.getString("Bot-activity-text")));
        } else if (FileBasics.FILETYPE.CONFIG.getString("Bot-activity").equalsIgnoreCase("Listening")) {
            builder.setActivity(Activity.of(Activity.ActivityType.LISTENING,
                    FileBasics.FILETYPE.CONFIG.getString("Bot-activity-text")));
        } else if (FileBasics.FILETYPE.CONFIG.getString("Bot-activity").equalsIgnoreCase("Watching")) {
            builder.setActivity(Activity.of(Activity.ActivityType.WATCHING,
                    FileBasics.FILETYPE.CONFIG.getString("Bot-activity-text")));
        } else if (FileBasics.FILETYPE.CONFIG.getString("Bot-activity").equalsIgnoreCase("Custom")) {
            builder.setActivity(Activity.of(Activity.ActivityType.CUSTOM_STATUS,
                    FileBasics.FILETYPE.CONFIG.getString("Bot-activity-text")));
        } else if (FileBasics.FILETYPE.CONFIG.getString("Bot-activity").equalsIgnoreCase("Streaming")) {
            builder.setActivity(Activity.of(Activity.ActivityType.CUSTOM_STATUS,
                    FileBasics.FILETYPE.CONFIG.getString("Bot-activity-text"), FileBasics.FILETYPE.CONFIG.getString("Bot-activity-url")));
        }

        try {
            builder.build();
        } catch (LoginException e) {
            Bukkit.getLogger().severe("[DragonDiscordSync]: Invalid Bot-token!");
        }
    }

}
