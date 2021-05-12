package me.Dragonhuntrrr.DragonDiscordSync.Discord;

import me.Dragonhuntrrr.DragonDiscordSync.Discord.Commands.Cookie;
import me.Dragonhuntrrr.DragonDiscordSync.Discord.Sync.DiscordMessageSync;
import me.Dragonhuntrrr.DragonDiscordSync.Util.File.FileBasics;
import me.Dragonhuntrrr.DragonDiscordSync.Util.Message;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;
import java.util.Locale;

public class DiscordBot {

    public static JDA api;
    public static String prefix = FileBasics.FILETYPE.CONFIG.getString("Discord.Bot.Prefix");

    public static final Cookie cookie = new Cookie();
    public static final DiscordMessageSync dms = new DiscordMessageSync();

    private static String status = FileBasics.FILETYPE.CONFIG.getString("Discord.Bot.Status")
            .toUpperCase(Locale.ROOT)
            .replaceAll("DEFAULT", "ONLINE")
            .replaceAll("DND", "DO_NOT_DISTURB");

    public static void startBot() {

        try {
            api = JDABuilder.createDefault(FileBasics.FILETYPE.CONFIG.getString("Discord.Bot.Token"))
                    .addEventListeners(cookie, dms)
                    .setActivity(Activity.of(Activity.ActivityType.DEFAULT, FileBasics.FILETYPE.CONFIG.getString("Discord.Bot.Activity")))
                    .setStatus(OnlineStatus.valueOf(status))
                    .build();
        } catch (LoginException e) {
            Message.Severe("Invalid token provided!");
        }
    }

}
