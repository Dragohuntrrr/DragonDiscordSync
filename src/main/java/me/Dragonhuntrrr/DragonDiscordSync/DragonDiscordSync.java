package me.Dragonhuntrrr.DragonDiscordSync;

import me.Dragonhuntrrr.DragonDiscordSync.Discord.DiscordBot;
import org.bukkit.plugin.java.JavaPlugin;

public class DragonDiscordSync extends JavaPlugin {

    private static DragonDiscordSync instance;

    @Override
    public void onEnable() {
        instance = this;
        System.out.println("DragonDiscordSync: Plugin enabled!");
        DiscordBot.startBot("ODMzODI5OTU4MTc4NDM5MjI4.YH4CqQ.7RSDUZ0E3vX0MoyoDfuBhjZpbDc");
    }

    public static DragonDiscordSync getInstance() {
        return instance;
    }
}
