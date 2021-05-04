package me.Dragonhuntrrr.DragonDiscordSync.Util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class Message {

    private static String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
    private static String prefix() {
        return "[DragonDiscordSync]: ";
    }

    public static void Severe(String message) {
        Bukkit.getLogger().severe(color("&4&l" + prefix() + " &c" + message));
    }
    public static void Info(String message) {
        Bukkit.getLogger().info(color("&6&l" + prefix() + " &e" + message));
    }
    public static void Log(String message) {
        Bukkit.getLogger().fine(color("&f&l" + prefix() + " " + message));
    }

}
