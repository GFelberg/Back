package me.GFelberg.Back.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import me.GFelberg.Back.Main;

public class BackUtils {

	public static Map<Player, Location> back = new HashMap<Player, Location>();
	public static HashMap<UUID, Long> cooldown = new HashMap<UUID, Long>();
	public static int cooldownTime;
	public static String prefix, message, failed, delay_before, delay_after;

	public static void loadVariables() {
		prefix = Main.getInstance().getConfig().getString("Back.Prefix").replace("&", "§");
		message = Main.getInstance().getConfig().getString("Back.Message").replace("&", "§");
		failed = Main.getInstance().getConfig().getString("Back.Failed").replace("&", "§");
		delay_before = Main.getInstance().getConfig().getString("Cooldown.DelayBefore").replace("&", "§");
		delay_after = Main.getInstance().getConfig().getString("Cooldown.DelayAfter").replace("&", "§");
		cooldownTime = Main.getInstance().getConfig().getInt("Cooldown.Time");
	}

	public void reloadConfig(Player p) {

		if (!(p.hasPermission("back.reload"))) {
			p.sendMessage(ChatColor.RED + "You dont have permission to perform this command!");
		} else {
			Main.getInstance().reloadConfig();
			loadVariables();
			p.sendMessage(prefix + " " + ChatColor.GREEN + "Plugin reloaded successfully!");
			Bukkit.getServer().getConsoleSender().sendMessage("======================================");
			Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Back Plugin reloaded");
			Bukkit.getServer().getConsoleSender().sendMessage("======================================");
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
		} else {
			p.sendMessage(ChatColor.WHITE + "-----------------------------------------");
			p.sendMessage(ChatColor.AQUA + "Back - Help Commands");
			p.sendMessage(ChatColor.YELLOW + "/back help: " + helpUtils.getHelp_page());
			p.sendMessage(ChatColor.YELLOW + "/back : " + helpUtils.getHelp_back());
			p.sendMessage(ChatColor.YELLOW + "/back reload : " + helpUtils.getHelp_reload());
			p.sendMessage(ChatColor.WHITE + "-----------------------------------------");
		}
	}

	public void backPlayer(Player p) {

		if (!(back.containsKey(p))) {
			p.sendMessage(failed);
		}

		else if (back.containsKey(p)) {
			if (!(Main.getInstance().getConfig().getBoolean("Cooldown.Enable"))) {
				teleportPlayer(p);
			} else {
				UUID uuid = p.getUniqueId();
				if (cooldown.containsKey(uuid)) {
					if (p.hasPermission("back.bypass")) {
						cooldown.remove(uuid);
						teleportPlayer(p);
					} else {
						long time = ((cooldown.get(uuid) / 1000) + cooldownTime) - (System.currentTimeMillis() / 1000);
						if (time > 0) {
							p.sendMessage(delay_before + " " + time + " " + delay_after);
						} else {
							cooldown.remove(uuid);
							teleportPlayer(p);
						}
					}
				}
			}
		}
	}

	public void teleportPlayer(Player p) {
		p.teleport(back.get(p));
		back.remove(p);
		p.sendMessage(message);
	}
}