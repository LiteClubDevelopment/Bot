package club.lite.bot.utils;

import club.lite.bot.Bot;
import org.bukkit.Bukkit;

public class Logger {
    public static final String LINE_CONSOLE = CC.t("&3=========================");

    public static void state(String state) {
        Logger.print(LINE_CONSOLE);
        Logger.print("- &3Bot &8x &bHCFAlerts");
        Logger.print(LINE_CONSOLE);
        Logger.print("- &bDiscord&f: dsc.gg/liteclubdevelopment");
        Logger.print("- &bVersion&f: v" + Bot.getPlugin().getDescription().getVersion());
        Logger.print("- &bStatus&f: " + state);
        Logger.print(LINE_CONSOLE);
    }

    public static void print(String ... message) {
        for (String s2 : message) {
            Bukkit.getServer().getConsoleSender().sendMessage(CC.t(s2));
        }
    }
}

