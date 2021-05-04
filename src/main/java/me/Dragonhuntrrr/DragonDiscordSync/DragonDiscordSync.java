package me.Dragonhuntrrr.DragonDiscordSync;

import me.Dragonhuntrrr.DragonDiscordSync.Discord.DiscordBot;
import me.Dragonhuntrrr.DragonDiscordSync.Minecraft.AdvancementSync;
import me.Dragonhuntrrr.DragonDiscordSync.Minecraft.DeathSync;
import me.Dragonhuntrrr.DragonDiscordSync.Minecraft.JoinLeaveSync;
import me.Dragonhuntrrr.DragonDiscordSync.Minecraft.MessageSync;
import me.Dragonhuntrrr.DragonDiscordSync.Util.File.FileBasics;
import me.Dragonhuntrrr.DragonDiscordSync.Util.Message;
import me.Dragonhuntrrr.DragonDiscordSync.Util.bStats.Metrics;
import net.dv8tion.jda.api.OnlineStatus;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import javax.security.auth.login.LoginException;

public class DragonDiscordSync extends JavaPlugin {

    String botToken;
    Integer botTokenIntegers;

    private static DragonDiscordSync instance;

    private static final AdvancementSync advancementSync = new AdvancementSync();
    private static final DeathSync deathSync = new DeathSync();
    private static final MessageSync messageSync = new MessageSync();
    private static final JoinLeaveSync joinLeaveSync = new JoinLeaveSync();
    private static final FileBasics configs = new FileBasics();

    @Override
    public void onEnable() {
        instance = this;

        Metrics metrics = new Metrics(this, 11215);
        registerEvents(this, advancementSync, messageSync, deathSync, joinLeaveSync);
        configs.load();

        botToken = FileBasics.FILETYPE.CONFIG.getString("Discord.Bot.Token");
        botTokenIntegers = botToken.length();

        Message.Info("Logging in with botToken ending with " + botToken.substring(botTokenIntegers / 2));
        DiscordBot.startBot();
        Message.Log("Plugin enabled!");
    }

    public static void registerEvents(org.bukkit.plugin.Plugin plugin, Listener... listeners) {
        for (Listener listener : listeners) {
            Bukkit.getServer().getPluginManager().registerEvents(listener, plugin);
        }
    }

    public static DragonDiscordSync getInstance() {
        return instance;
    }
}
