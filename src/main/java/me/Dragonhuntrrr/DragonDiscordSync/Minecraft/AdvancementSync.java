package me.Dragonhuntrrr.DragonDiscordSync.Minecraft;

import me.Dragonhuntrrr.DragonDiscordSync.Discord.DiscordBot;
import me.Dragonhuntrrr.DragonDiscordSync.Util.File.FileBasics;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;

import java.awt.*;

public class AdvancementSync implements Listener {

    @EventHandler
    public void onAdvancement(PlayerAdvancementDoneEvent event) {

        String advancement = event.getAdvancement().getKey().getKey();
        String playerName = event.getPlayer().getDisplayName();
        String playerUUID = event.getPlayer().getUniqueId().toString();
        String playerPNG = "https://mc-heads.net/avatar/" + playerUUID + "/50";
        String AdvancementChannelID = FileBasics.FILETYPE.CONFIG.getString("Discord.Channels.AdvancementSync-ChannelID");
        Color EmbedColor = Color.decode(FileBasics.FILETYPE.CONFIG.getString("Discord.Events.Options.AchievementEmbedColor"));
        String MainChannelID = FileBasics.FILETYPE.CONFIG.getString("Discord.Channels.Main-Channel");

        if (AdvancementChannelID == null) {
            if (!(MainChannelID == null)) {

                TextChannel MainChannel = DiscordBot.api.getTextChannelById(MainChannelID);

                EmbedBuilder eb = new EmbedBuilder();
                eb.setTitle(playerName + " Got the achievement " + advancement);
                if (FileBasics.FILETYPE.CONFIG.getString("Discord.Events.Options.PlayerHead").equalsIgnoreCase("true"))
                    eb.setThumbnail(playerPNG);
                eb.setColor(EmbedColor);

                MainChannel.sendMessage(eb.build()).queue();
            } else {
                Bukkit.getLogger().severe("DragonDiscordSync: Invalid MainChannel-ID provided");
            }
        } else {

            TextChannel AdvancementChannel = DiscordBot.api.getTextChannelById(AdvancementChannelID);

            EmbedBuilder eb = new EmbedBuilder();
            eb.setTitle(playerName + " Got the achievement " + advancement);
            if (FileBasics.FILETYPE.CONFIG.getString("Discord.Events.Options.PlayerHead").equalsIgnoreCase("true"))
                eb.setThumbnail(playerPNG);
            eb.setColor(EmbedColor);

            AdvancementChannel.sendMessage(eb.build()).queue();
        }
    }

}
