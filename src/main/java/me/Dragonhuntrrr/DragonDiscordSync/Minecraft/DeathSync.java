package me.Dragonhuntrrr.DragonDiscordSync.Minecraft;

import me.Dragonhuntrrr.DragonDiscordSync.Discord.DiscordBot;
import me.Dragonhuntrrr.DragonDiscordSync.Util.File.FileBasics;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.awt.*;

public class DeathSync implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {

        String playerUUID = event.getEntity().getPlayer().getUniqueId().toString();
        String playerPNG = "https://mc-heads.net/avatar/" + playerUUID + "/50";
        String deathMessage = event.getDeathMessage();
        String DeathChannelID = FileBasics.FILETYPE.CONFIG.getString("Discord.Channels.DeathSync-ChannelID");
        String MainChannelID = FileBasics.FILETYPE.CONFIG.getString("Discord.Channels.Main-Channel");
        Color EmbedColor = Color.decode(FileBasics.FILETYPE.CONFIG.getString("Discord.Events.Options.DeathEmbedColor"));

        if (DeathChannelID == null) {
            if (!(MainChannelID == null)) {

                TextChannel MainChannel = DiscordBot.api.getTextChannelById(MainChannelID);

                EmbedBuilder eb = new EmbedBuilder();
                eb.setTitle(deathMessage);
                eb.setColor(EmbedColor);
                if (FileBasics.FILETYPE.CONFIG.getString("Discord.Events.Options.PlayerHead").equalsIgnoreCase("true"))
                    eb.setThumbnail(playerPNG);

                MainChannel.sendMessage(eb.build()).queue();
            } else {
                Bukkit.getLogger().severe("DragonDiscordSync: Invalid MainChannel-ID provided");
            }
        } else {

            TextChannel DeathChannel = DiscordBot.api.getTextChannelById(DeathChannelID);

            EmbedBuilder eb = new EmbedBuilder();
            eb.setTitle(deathMessage);
            eb.setColor(EmbedColor);
            if (FileBasics.FILETYPE.CONFIG.getString("Discord.Events.Options.PlayerHead").equalsIgnoreCase("true"))
                eb.setThumbnail(playerPNG);

            DeathChannel.sendMessage(eb.build()).queue();
        }
    }
}
