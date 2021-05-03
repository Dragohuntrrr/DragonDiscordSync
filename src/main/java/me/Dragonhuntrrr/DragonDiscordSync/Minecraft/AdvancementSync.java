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

        String advancement = event.getAdvancement().toString();
        String criteria = event.getAdvancement().getCriteria().toString();
        String playerName = event.getPlayer().getDisplayName();
        String AdvancementChannelID = FileBasics.FILETYPE.CONFIG.getString("ChatSync-ChannelID");
        String MainChannelID = FileBasics.FILETYPE.CONFIG.getString("Main-ChannelID");

        if (AdvancementChannelID == null) {
            if (!(MainChannelID == null)) {

                TextChannel MainChannel = DiscordBot.api.getTextChannelById(MainChannelID);

                EmbedBuilder eb = new EmbedBuilder();
                eb.setTitle(playerName + " Got the achievement " + advancement);
                eb.setDescription(criteria);
                eb.setColor(Color.green);

                MainChannel.sendMessage(eb.build()).queue();
            } else {
                Bukkit.getLogger().severe("DragonDiscordSync: Invalid MainChannel-ID provided");
            }
        } else {

            TextChannel AdvancementChannel = DiscordBot.api.getTextChannelById(AdvancementChannelID);

            EmbedBuilder eb = new EmbedBuilder();
            eb.setTitle(playerName + " Got the achievement " + advancement);
            eb.setDescription(criteria);
            eb.setColor(Color.green);

            AdvancementChannel.sendMessage(eb.build()).queue();
        }
    }

}
