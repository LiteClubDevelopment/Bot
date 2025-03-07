package club.lite.bot;

import club.lite.bot.utils.FileConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import lombok.Getter;
import club.lite.bot.utils.Logger;
import club.lite.bot.utils.CC;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Made by HCFAlerts
 * Project: Bot
 * https://dsc.gg/liteclubdevelopment
 */

public final class Bot extends JavaPlugin {
    public static FileConfiguration config;
    @Getter
    private static Bot instance;
    public String ConfigRoute;
    public static Random RANDOM = ThreadLocalRandom.current();
    @Getter
    private JDA discordBot;
    @Getter
    private static Bot plugin;
    @Getter
    private boolean loaded = false;
    private final String disableMessage = "null";
    public static final String LINE_CONSOLE = CC.t("&3=========================");

    @Override
    public void onEnable() {
        plugin = this;
        instance = this;

        if (!this.getDescription().getName().equals("Bot")
                || !this.getDescription().getWebsite().equals("https://dsc.gg/liteclubdevelopment")
                || !this.getDescription().getDescription().equals("Simple Discord Bot Status Plugin")
                || !this.getDescription().getVersion().equals("1.1")
                || !this.getDescription().getAuthors().contains("HCFAlerts")) {
            Bukkit.getServer().getPluginManager().disablePlugin(this);
            for (int i = 0; i < 100; i++) {
                Bukkit.getServer().getConsoleSender().sendMessage(CC.t("&cWhy are you changing the plugin.yml?"));
            }
            return;
        }

        this.loaded = true;
        FileConfig configConfig = new FileConfig(this, "config.yml");
        Gson gson = (new GsonBuilder()).setPrettyPrinting().create();
        Logger.state("Enabled");

        String discordToken = Bot.getInstance().getConfig().getString("TOKEN");
        String activityType = Bot.getInstance().getConfig().getString("ACTIVITY.TYPE").toUpperCase();
        String activityText = Bot.getInstance().getConfig().getString("ACTIVITY.ACTIVITY");
        String streamUrl= Bot.getInstance().getConfig().getString("ACTIVITY.STREAM_URL", "https://twitch.tv/liteclubdevelopment");
        OnlineStatus status = OnlineStatus.valueOf(Bot.getInstance().getConfig().getString("ACTIVITY.STATUS").toUpperCase());

        Activity activity;
        switch (activityType) {
            case "PLAYING":
                activity = Activity.playing(activityText);
                break;
            case "WATCHING":
                activity = Activity.watching(activityText);
                break;
            case "LISTENING":
                activity = Activity.listening(activityText);
                break;
            case "STREAMING":
                activity = Activity.streaming(activityText, streamUrl);
                break;
            case "COMPETING":
                activity = Activity.competing(activityText);
                break;
            default:
                activity = Activity.playing(activityText);
                break;
        }

        this.discordBot = JDABuilder.createDefault(discordToken)
                .setStatus(status)
                .setActivity(activity)
                .enableIntents(GatewayIntent.GUILD_MEMBERS, new GatewayIntent[0])
                .build();
    }

    @Override
    public void onDisable() {
        Logger.state("Disabled");
    }
}