package me.GFelberg.Back.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.GFelberg.Back.Main;
import me.GFelberg.Back.data.BackSystem;

public class BackUtils {

	public static String prefix;

	public static void loadVariables() {
		prefix = Main.getInstance().getConfig().getString("Back.Prefix").replace("&", "§");
	}

	public void reloadConfig(Player p) {

		if (!(p.hasPermission("back.reload"))) {
			p.sendMessage(ChatColor.RED + "You dont have permission to perform this command!");
			return;
		} else {
			Main.getInstance().reloadConfig();
			Main.getInstance().loadVariables();
			BackSystem.loadClickOptionMessages();
			BackSystem.loadBlacklistWorlds();
			p.sendMessage(prefix + " " + ChatColor.GREEN + "Plugin reloaded successfully!");
			Bukkit.getConsoleSender().sendMessage("======================================");
			Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Back Plugin reloaded");
			Bukkit.getConsoleSender().sendMessage("======================================");
		}
	}

	public void helpPage(Player p) {
		HelpPageUtils helpUtils = new HelpPageUtils();

		if (!(p.hasPermission("back.admin"))) {
			p.sendMessage(ChatColor.WHITE + "-----------------------------------------");
			p.sendMessage(ChatColor.AQUA + "Back - Help Commands");
			p.sendMessage(ChatColor.YELLOW + "/back help: " + helpUtils.getHelp_page());
			p.sendMessage(ChatColor.YELLOW + "/back : " + helpUtils.getHelp_back());
			p.sendMessage(ChatColor.WHITE + "-----------------------------------------");
			return;
		} else {
			p.sendMessage(ChatColor.WHITE + "-----------------------------------------");
			p.sendMessage(ChatColor.AQUA + "Back - Help Commands");
			p.sendMessage(ChatColor.YELLOW + "/back help: " + helpUtils.getHelp_page());
			p.sendMessage(ChatColor.YELLOW + "/back : " + helpUtils.getHelp_back());
			p.sendMessage(ChatColor.YELLOW + "/back reload : " + helpUtils.getHelp_reload());
			p.sendMessage(ChatColor.WHITE + "-----------------------------------------");
		}
	}
}