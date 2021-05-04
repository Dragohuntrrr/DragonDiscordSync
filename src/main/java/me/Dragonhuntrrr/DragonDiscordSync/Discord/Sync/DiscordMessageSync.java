package me.Dragonhuntrrr.DragonDiscordSync.Discord.Sync;

import me.Dragonhuntrrr.DragonDiscordSync.Discord.DiscordBot;
import me.Dragonhuntrrr.DragonDiscordSync.Util.File.FileBasics;
import me.Dragonhuntrrr.DragonDiscordSync.Util.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class DiscordMessageSync extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        if (!event.getAuthor().isBot()) {
            String message = event.getMessage().getContentRaw();
            String playerName = event.getAuthor().getName();
            String channelName = event.getChannel().getName();
            TextChannel MainChannel = DiscordBot.api.getTextChannelById(FileBasics.FILETYPE.CONFIG.getString("Discord.Channels.Main-Channel"));
            TextChannel ChatSyncChannel = DiscordBot.api.getTextChannelById(FileBasics.FILETYPE.CONFIG.getString("Discord.Channels.ChatSync-ChannelID"));

            if (event.getChannel() == MainChannel || event.getChannel() == ChatSyncChannel) {
                Message.Broadcast(FileBasics.FILETYPE.CONFIG.getString("Minecraft.Options.ChatFormat")
                        .replaceAll("%user%", playerName)
                        .replaceAll("%message%", message)
                        .replaceAll("%channel%", channelName));
            }
        }
    }

}
