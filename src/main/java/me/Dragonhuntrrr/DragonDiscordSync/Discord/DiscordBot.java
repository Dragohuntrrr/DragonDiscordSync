package me.Dragonhuntrrr.DragonDiscordSync.Discord;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

public class DiscordBot {

    public static void startBot(String token) {
        JDABuilder builder = JDABuilder.createDefault(token);

        builder.setActivity(Activity.listening("to Dragons instructions"));

        try {
            builder.build();
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }

}
