package me.Dragonhuntrrr.DragonDiscordSync.Minecraft;

import me.Dragonhuntrrr.DragonDiscordSync.Discord.DiscordBot;
import me.Dragonhuntrrr.DragonDiscordSync.Util.File.FileBasics;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.awt.*;

public class MessageSync implements Listener {

    @EventHandler
    public void onMessage(AsyncPlayerChatEvent event) {

        String message = event.getMessage();
        String playerName = event.getPlayer().getDisplayName();
        String ChannelID = FileBasics.FILETYPE.CONFIG.getString("ChatSync-ChannelID");

        TextChannel textChannel = DiscordBot.api.getTextChannelById(ChannelID);

        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle(playerName + " » " + message);
        eb.setColor(Color.white);

        textChannel.sendMessage(eb.build()).queue();
    }

}
