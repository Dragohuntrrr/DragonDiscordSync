package me.Dragonhuntrrr.DragonDiscordSync;

import me.Dragonhuntrrr.DragonDiscordSync.Discord.DiscordBot;
import me.Dragonhuntrrr.DragonDiscordSync.Util.File.FileBasics;
import me.Dragonhuntrrr.DragonDiscordSync.Util.bStats.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

public class DragonDiscordSync extends JavaPlugin {

    private static DragonDiscordSync instance;
    FileBasics configs = new FileBasics();

    @Override
    public void onEnable() {

        instance = this;

        Metrics metrics = new Metrics(this, 11215);

        configs.load();
        FileBasics.FILETYPE.CONFIG.setValue("Plugin-Version", this.getDescription().getVersion());
        FileBasics.FILETYPE.CONFIG.save();

        DiscordBot.startBot(FileBasics.FILETYPE.CONFIG.getString("Bot-token"));

        System.out.println("DragonDiscordSync: Plugin enabled!");
    }

    public static DragonDiscordSync getInstance() {
        return instance;
    }
}
