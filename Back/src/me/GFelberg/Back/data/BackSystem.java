package me.GFelberg.Back.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import me.GFelberg.Back.Main;

public class BackSystem {

	public static Map<Player, Location> back = new HashMap<Player, Location>();
	public static HashMap<UUID, Long> cooldown = new HashMap<UUID, Long>();
	public static List<String> clickoption_messages = new ArrayList<String>();
	public static List<String> worlds = new ArrayList<String>();
	public static int cooldownTime;
	public static String back_message, back_failed_message, back_cooldown_message, back_nopermission,
			back_blocked_world;

	public static void loadVariables() {
		back_message = Main.getInstance().getConfig().getString("Back.Message").replace("&", "§");
		back_failed_message = Main.getInstance().getConfig().getString("Back.Failed").replace("&", "§");
		back_nopermission = Main.getInstance().getConfig().getString("Back.NoPermission").replace("&", "§");
		back_cooldown_message = Main.getInstance().getConfig().getString("Cooldown.Message").replace("&", "§");
		back_blocked_world = Main.getInstance().getConfig().getString("Back.BlockedWorld").replace("&", "§");
		cooldownTime = Main.getInstance().getConfig().getInt("Cooldown.Time");
		worlds = Main.getInstance().getConfig().getStringList("BlockedWorlds");
	}

	public void backPlayer(Player p) {

		if (Main.getInstance().getConfig().getBoolean("WorldBlacklist.Enable")) {
			if (!(back.containsKey(p))) {
				p.sendMessage(back_failed_message);
				return;
			}
			Location loc = back.get(p);
			for (String blockedWorld : worlds) {
				if (loc.getWorld().getName().equals(blockedWorld)) {
					p.sendMessage(back_blocked_world);
					return;
				} else {
					UUID uuid = p.getUniqueId();
					if (cooldown.containsKey(uuid)) {
						if (p.hasPermission("back.bypass")) {
							cooldown.remove(uuid);
							teleportPlayer(p);
						} else {
							long time = ((cooldown.get(uuid) / 1000) + cooldownTime)
									- (System.currentTimeMillis() / 1000);
							if (time > 0) {
								p.sendMessage(back_cooldown_message.replace("%back_delaytime%", String.valueOf(time)));
							} else {
								cooldown.remove(uuid);
								teleportPlayer(p);
							}
						}
					}
				}
			}
		} else {
			if (!(back.containsKey(p))) {
				p.sendMessage(back_failed_message);
				return;
			} else {
				if (!(Main.getInstance().getConfig().getBoolean("Cooldown.Enable"))) {
					teleportPlayer(p);
				} else {
					UUID uuid = p.getUniqueId();
					if (cooldown.containsKey(uuid)) {
						if (p.hasPermission("back.bypass")) {
							cooldown.remove(uuid);
							teleportPlayer(p);
						} else {
							long time = ((cooldown.get(uuid) / 1000) + cooldownTime)
									- (System.currentTimeMillis() / 1000);
							if (time > 0) {
								p.sendMessage(back_cooldown_message.replace("%back_delaytime%", String.valueOf(time)));
							} else {
								cooldown.remove(uuid);
								teleportPlayer(p);
							}
						}
					}
				}
			}
		}
	}

	public void teleportPlayer(Player p) {
		FileConfiguration customConfig = BackConfig.getConfig();
		customConfig.set("DeathLocations." + p.getUniqueId().toString(), null);
		BackConfig.saveConfig();
		p.teleport(back.get(p));
		back.remove(p);
		p.sendMessage(back_message);
	}

	public static void createBackLocation(Player p) {
		FileConfiguration customConfig = BackConfig.getConfig();
		UUID uuid = p.getUniqueId();
		String path = "DeathLocations." + uuid.toString();
		customConfig.set(path + ".Player", p.getName());
		customConfig.set(path + ".World", p.getWorld().getName());
		customConfig.set(path + ".X", p.getLocation().getX());
		customConfig.set(path + ".Y", p.getLocation().getY());
		customConfig.set(path + ".Z", p.getLocation().getZ());
		customConfig.set(path + ".Yaw", p.getLocation().getYaw());
		customConfig.set(path + ".Pitch", p.getLocation().getPitch());
		BackConfig.saveConfig();
	}

	public static void loadBackLocation(Player p) {
		FileConfiguration customConfig = BackConfig.getConfig();
		UUID uuid = p.getUniqueId();
		String path = "DeathLocations." + uuid.toString().toString();
		String world = (String) customConfig.get(path + ".World");
		double x = (double) customConfig.get(path + ".X");
		double y = (double) customConfig.get(path + ".Y");
		double z = (double) customConfig.get(path + ".Z");
		float yaw = (float) customConfig.getDouble(path + ".Yaw");
		float pitch = (float) customConfig.getDouble(path + ".Pitch");
		Location loc = new Location(Bukkit.getWorld(world), x, y, z, (float) yaw, (float) pitch);
		back.put(p, loc);
	}

	public static void loadAllBackLocations() {

		for (Player players : Bukkit.getOnlinePlayers()) {
			FileConfiguration customConfig = BackConfig.getConfig();
			if (customConfig.getString("DeathLocations." + players.getUniqueId().toString()) == null) {
				return;
			} else {
				UUID uuid = players.getUniqueId();
				String path = "DeathLocations." + uuid.toString();
				String world = (String) customConfig.get(path + ".World");
				double x = (double) customConfig.get(path + ".X");
				double y = (double) customConfig.get(path + ".Y");
				double z = (double) customConfig.get(path + ".Z");
				float yaw = (float) customConfig.getDouble(path + ".Yaw");
				float pitch = (float) customConfig.getDouble(path + ".Pitch");
				Location loc = new Location(Bukkit.getWorld(world), x, y, z, (float) yaw, (float) pitch);
				back.put(players, loc);
			}
		}
	}

	public static void loadClickOptionMessages() {
		FileConfiguration config = Main.getInstance().getConfig();
		List<String> clickMessage = config.getStringList("ClickOption");
		clickoption_messages = new ArrayList<>();

		for (String msg : clickMessage) {
			String clickMessage_formatted = ChatColor.translateAlternateColorCodes('&', msg);
			clickoption_messages.add(clickMessage_formatted);
		}
	}

	public static void loadBlacklistWorlds() {
		FileConfiguration config = Main.getInstance().getConfig();
		List<String> worlds_list = config.getStringList("BlockedWorlds");
		worlds = new ArrayList<>();

		for (String w : worlds_list) {
			String world = ChatColor.translateAlternateColorCodes('&', w);
			worlds.add(world);
		}
	}
}
