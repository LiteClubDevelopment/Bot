package club.lite.bot.utils;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.bukkit.ChatColor;

/**
 * Made by HCFAlerts
 * Project: Bot
 * https://dsc.gg/liteclubdevelopment
 */

public class CC {
    private static final Function<String, String> REPLACER = s2 -> ChatColor.translateAlternateColorCodes((char)'&', (String)s2);
    public static String LINE = CC.t("&7&m-------------------------");

    public static String t(String t2) {
        return REPLACER.apply(t2);
    }

    public static List<String> t(List<String> t2) {
        return t2.stream().map(REPLACER).collect(Collectors.toList());
    }
}

