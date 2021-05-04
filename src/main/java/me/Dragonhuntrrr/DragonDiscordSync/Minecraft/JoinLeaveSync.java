package me.Dragonhuntrrr.DragonDiscordSync.Minecraft;

import me.Dragonhuntrrr.DragonDiscordSync.Discord.DiscordBot;
import me.Dragonhuntrrr.DragonDiscordSync.Util.File.FileBasics;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.awt.*;

public class JoinLeaveSync implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        String playerName = event.getPlayer().getDisplayName();
        String playerUUID = event.getPlayer().getUniqueId().toString();
        String playerPNG = "https://mc-heads.net/avatar/" + playerUUID + "/50";
        String JoinLeaveChannelID = FileBasics.FILETYPE.CONFIG.getString("Discord.Channels.JoinLeaveSync-ChannelID");
        Color EmbedColor = Color.decode(FileBasics.FILETYPE.CONFIG.getString("Discord.Events.Options.JoinLeaveEmbedColor"));
        String MainChannelID = FileBasics.FILETYPE.CONFIG.getString("Discord.Channels.Main-Channel");

            if (JoinLeaveChannelID == null) {
                if (!(MainChannelID == null)) {

                    TextChannel MainChannel = DiscordBot.api.getTextChannelById(MainChannelID);

                    EmbedBuilder eb = new EmbedBuilder();
                    eb.setTitle(playerName + " joined the server");
                    if (FileBasics.FILETYPE.CONFIG.getString("Discord.Events.Options.PlayerHead").equalsIgnoreCase("true"))
                      eb.setThumbnail(playerPNG);
                    eb.setColor(EmbedColor);

                    MainChannel.sendMessage(eb.build()).queue();
                } else {
                    Bukkit.getLogger().severe("DragonDiscordSync: Invalid MainChannel-ID provided");
                }
            } else {

                TextChannel JoinLeaveChannel = DiscordBot.api.getTextChannelById(JoinLeaveChannelID);

                EmbedBuilder eb = new EmbedBuilder();
                eb.setTitle(playerName + " joined the server");
                if (FileBasics.FILETYPE.CONFIG.getString("Discord.Events.Options.PlayerHead").equalsIgnoreCase("true"))
                  eb.setThumbnail(playerPNG);
                eb.setColor(EmbedColor);

                JoinLeaveChannel.sendMessage(eb.build()).queue();
            }
        }

}
