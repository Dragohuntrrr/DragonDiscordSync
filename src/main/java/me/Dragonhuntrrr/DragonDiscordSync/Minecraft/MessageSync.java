package me.Dragonhuntrrr.DragonDiscordSync.Minecraft;

import me.Dragonhuntrrr.DragonDiscordSync.Discord.DiscordBot;
import me.Dragonhuntrrr.DragonDiscordSync.Util.File.FileBasics;
import me.Dragonhuntrrr.DragonDiscordSync.Util.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class MessageSync implements Listener {

    @EventHandler
    public void onMessage(AsyncPlayerChatEvent event) {
        if (FileBasics.FILETYPE.CONFIG.getBoolean("Discord.Events.Sync.Death")) {
            String message = event.getMessage();
            String playerName = event.getPlayer().getDisplayName();
            String MainChannelID = FileBasics.FILETYPE.CONFIG.getString("Discord.Channels.Main-Channel");
            String ChatSyncChannelID = FileBasics.FILETYPE.CONFIG.getString("Discord.Channels.ChatSync-ChannelID");
            if (ChatSyncChannelID == null) {
                if (MainChannelID != null) {
                    TextChannel textChannel = DiscordBot.api.getTextChannelById(MainChannelID);

                    textChannel.sendMessage(playerName + " » " + message).queue();
                } else {
                    Message.Severe("Error while trying to sync message: No channels specified!");
                }
            } else {

                TextChannel textChannel = DiscordBot.api.getTextChannelById(MainChannelID);

                textChannel.sendMessage(playerName + " » " + message).queue();
            }


        }
    }

}
