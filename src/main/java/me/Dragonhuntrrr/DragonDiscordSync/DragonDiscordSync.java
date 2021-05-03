package me.Dragonhuntrrr.DragonDiscordSync;

import me.Dragonhuntrrr.DragonDiscordSync.Discord.DiscordBot;
import me.Dragonhuntrrr.DragonDiscordSync.Minecraft.MessageSync;
import me.Dragonhuntrrr.DragonDiscordSync.Util.File.FileBasics;
import me.Dragonhuntrrr.DragonDiscordSync.Util.bStats.Metrics;
import net.dv8tion.jda.api.OnlineStatus;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import javax.security.auth.login.LoginException;

public class DragonDiscordSync extends JavaPlugin {

    private static DragonDiscordSync instance;
    FileBasics configs = new FileBasics();

    @Override
    public void onEnable() {

        instance = this;
        Metrics metrics = new Metrics(this, 11215);
        registerEvents(this, new MessageSync());
        DiscordBot.startBot();
        configs.load();
        System.out.println("DragonDiscordSync: Plugin enabled!");
    }

    public static DragonDiscordSync getInstance() {
        return instance;
    }

    public static void registerEvents(org.bukkit.plugin.Plugin plugin, Listener... listeners) {
        for (Listener listener : listeners) {
            Bukkit.getServer().getPluginManager().registerEvents(listener, plugin);
        }
    }
}
