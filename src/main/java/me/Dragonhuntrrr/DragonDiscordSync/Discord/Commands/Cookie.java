package me.Dragonhuntrrr.DragonDiscordSync.Discord.Commands;

import me.Dragonhuntrrr.DragonDiscordSync.Discord.DiscordBot;
import me.Dragonhuntrrr.DragonDiscordSync.Util.File.FileBasics;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Cookie extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if (args[0].equalsIgnoreCase(DiscordBot.prefix + "cookie")) {

            String userID = event.getAuthor().getId();
            Integer cookieCount = FileBasics.FILETYPE.COOKIES.getInt(userID + ".cookies");

            Date date = new Date();
            Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
            calendar.setTime(date);   // assigns calendar to given date
            Integer hour = calendar.get(Calendar.HOUR); // gets the hour

            if (cookieCount == 3) {

                FileBasics.FILETYPE.COOKIES.setValue(userID + ".time", hour);
                event.getChannel().sendMessage("Here ya go! \uD83C\uDF6A").queue();
                FileBasics.FILETYPE.COOKIES.setValue(userID + ".cookies", cookieCount + 1);
                FileBasics.FILETYPE.COOKIES.save();

            } else if (cookieCount == 4) {

                if (hour != FileBasics.FILETYPE.COOKIES.getInt(userID + ".time")) {
                    FileBasics.FILETYPE.COOKIES.setValue(userID + ".cookies", 1);
                    event.getChannel().sendMessage("This is your last one! \uD83C\uDF6A").queue();
                    FileBasics.FILETYPE.COOKIES.setValue(userID + ".cookies", cookieCount + 1);
                    FileBasics.FILETYPE.COOKIES.save();

                } else {
                    event.getChannel().sendMessage("You've had enough cookies this hour!").queue();
                }
            } else {
                event.getChannel().sendMessage("Here ya go! \uD83C\uDF6A").queue();
                FileBasics.FILETYPE.COOKIES.setValue(userID + ".cookies", cookieCount + 1);
                FileBasics.FILETYPE.COOKIES.save();
            }

        }
    }

}
