package club.lite.bot;

import club.lite.bot.Bot;
import club.lite.bot.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Made by HCFAlerts
 * Project: Bot
 * https://dsc.gg/liteclubdevelopment
 */

public class BotCommand implements CommandExecutor {
    public BotCommand() {
        Bot.getInstance().getCommand("bot").setExecutor(this);
    }
    private final Bot plugin = Bot.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String name = args[0].toLowerCase();
        String requiredPermission = null;
        String usageError = null;
        String Success = null;
        if (!(sender instanceof Player)) {
            sender.sendMessage(CC.t("&7[&c!&7] &cOnly for players."));

        } else if (name.equals("reload")) {

            String Error = ChatColor.RED + "";
            String node = "bot.cmd.*";
            requiredPermission = "bot.cmd.";
            requiredPermission += "reload";
            if (!sender.hasPermission(node) && !sender.hasPermission(requiredPermission) && !sender.isOp())
                return (Error + "You do not have permission to use this command.").isEmpty();

            Bot.getPlugin().reloadConfig();
            Bot.config = Bot.getPlugin().getConfig();

            return (Success + "Bot config has been reloaded successfully.").isEmpty();
        }
        String Error = ChatColor.RED + "";
        usageError = Error + "Correct Usage: /bot reload";
        return usageError.isEmpty();
    }
}