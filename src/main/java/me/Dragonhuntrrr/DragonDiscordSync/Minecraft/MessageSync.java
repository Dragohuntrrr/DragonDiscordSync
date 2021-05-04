package me.Dragonhuntrrr.DragonDiscordSync.Minecraft;

import me.Dragonhuntrrr.DragonDiscordSync.Discord.DiscordBot;
import me.Dragonhuntrrr.DragonDiscordSync.Util.File.FileBasics;
import me.Dragonhuntrrr.DragonDiscordSync.Util.Message;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.awt.*;
import java.util.Locale;

public class MessageSync implements Listener {

    @EventHandler
    public void onMessage(AsyncPlayerChatEvent event) {

        String message = event.getMessage();
        String playerName = event.getPlayer().getDisplayName();
        String playerUUID = event.getPlayer().getUniqueId().toString();
        String playerPNG = "https://mc-heads.net/avatar/" + playerUUID + "/50";
        String MainChannelID = FileBasics.FILETYPE.CONFIG.getString("Discord.Channels.Main-Channel");
        String ChatSyncChannelID = FileBasics.FILETYPE.CONFIG.getString("Discord.Channels.ChatSync-ChannelID");
        Color EmbedColor = Color.decode(FileBasics.FILETYPE.CONFIG.getString("Discord.Events.Options.ChatEmbedColor"));


        if (ChatSyncChannelID == null) {
            if (MainChannelID != null) {
                TextChannel textChannel = DiscordBot.api.getTextChannelById(MainChannelID);

                EmbedBuilder eb = new EmbedBuilder();
                eb.setTitle(playerName + " » " + message);
                if (FileBasics.FILETYPE.CONFIG.getString("Discord.Events.Options.PlayerHead").equalsIgnoreCase("true"))
                    eb.setThumbnail(playerPNG);
                eb.setColor(EmbedColor);

                textChannel.sendMessage(eb.build()).queue();
            } else {
                Message.Severe("Error while trying to sync message: No channels specified!");
            }
        } else {

            TextChannel textChannel = DiscordBot.api.getTextChannelById(MainChannelID);

            EmbedBuilder eb = new EmbedBuilder();
            eb.setTitle(playerName + " » " + message);
            if (FileBasics.FILETYPE.CONFIG.getString("Discord.Events.Options.PlayerHead").equalsIgnoreCase("true"))
                eb.setThumbnail(playerPNG);
            eb.setColor(EmbedColor);

            textChannel.sendMessage(eb.build()).queue();
        }


    }

}
