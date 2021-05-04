package me.Dragonhuntrrr.DragonDiscordSync.Discord;

import me.Dragonhuntrrr.DragonDiscordSync.Discord.Commands.Cookie;
import me.Dragonhuntrrr.DragonDiscordSync.Discord.Commands.Link;
import me.Dragonhuntrrr.DragonDiscordSync.Util.File.FileBasics;
import me.Dragonhuntrrr.DragonDiscordSync.Util.Message;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import javax.security.auth.login.LoginException;

public class DiscordBot {

    public static JDA api;
    public static String prefix = FileBasics.FILETYPE.CONFIG.getString("Discord.Bot.Prefix");

    public static void startBot() {

        try {
            api = JDABuilder.createDefault(FileBasics.FILETYPE.CONFIG.getString("Discord.Bot.Token"))
                    .addEventListeners(new Cookie()).build();
        } catch (LoginException e) {
            Message.Severe("Invalid token provided!");
        }
    }

}
