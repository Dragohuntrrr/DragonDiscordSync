package me.Dragonhuntrrr.DragonDiscordSync.Discord;

import me.Dragonhuntrrr.DragonDiscordSync.Discord.Commands.Cookie;
import me.Dragonhuntrrr.DragonDiscordSync.Discord.Commands.Link;
import me.Dragonhuntrrr.DragonDiscordSync.Util.File.FileBasics;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import javax.security.auth.login.LoginException;
import java.util.logging.Level;

public class DiscordBot {

    public static JDA api;
    public static String prefix = FileBasics.FILETYPE.CONFIG.getString("Bot-prefix");

    public static void startBot() {

        try {
            api = JDABuilder.createDefault(FileBasics.FILETYPE.CONFIG.getString("Bot-token"))
                    .addEventListeners(new Cookie()).build();
        } catch (LoginException e) {
            Bukkit.getLogger().severe(ChatColor.translateAlternateColorCodes('&', "&4&lDragonDiscordSync: &cInvalid token provided!"));
        }

    }
    public static void setActivities() {

        if (FileBasics.FILETYPE.CONFIG.getString("Bot-activity").equalsIgnoreCase("Playing")) {
            api.getPresence().setActivity(Activity.of(Activity.ActivityType.DEFAULT,
                    FileBasics.FILETYPE.CONFIG.getString("Bot-activity-text")));
        } else if (FileBasics.FILETYPE.CONFIG.getString("Bot-activity").equalsIgnoreCase("Listening")) {
            api.getPresence().setActivity(Activity.of(Activity.ActivityType.LISTENING,
                    FileBasics.FILETYPE.CONFIG.getString("Bot-activity-text")));
        } else if (FileBasics.FILETYPE.CONFIG.getString("Bot-activity").equalsIgnoreCase("Watching")) {
            api.getPresence().setActivity(Activity.of(Activity.ActivityType.WATCHING,
                    FileBasics.FILETYPE.CONFIG.getString("Bot-activity-text")));
        } else if (FileBasics.FILETYPE.CONFIG.getString("Bot-activity").equalsIgnoreCase("Custom")) {
            api.getPresence().setActivity(Activity.of(Activity.ActivityType.CUSTOM_STATUS,
                    FileBasics.FILETYPE.CONFIG.getString("Bot-activity-text")));
        } else if (FileBasics.FILETYPE.CONFIG.getString("Bot-activity").equalsIgnoreCase("Streaming")) {
            api.getPresence().setActivity(Activity.of(Activity.ActivityType.CUSTOM_STATUS,
                    FileBasics.FILETYPE.CONFIG.getString("Bot-activity-text"), FileBasics.FILETYPE.CONFIG.getString("Bot-activity-url")));
        }
    }

}
