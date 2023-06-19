package me.GFelberg.Back.data;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import me.GFelberg.Back.Main;

public class BackSystem {

	public static Map<Player, Location> back = new HashMap<Player, Location>();
	public static HashMap<UUID, Long> cooldown = new HashMap<UUID, Long>();
	public static int cooldownTime;
	public static String back_message, back_failed_message, back_cooldown_message;

	public static void loadVariables() {
		back_message = Main.getInstance().getConfig().getString("Back.Message").replace("&", "§");
		back_failed_message = Main.getInstance().getConfig().getString("Back.Failed").replace("&", "§");
		back_cooldown_message = Main.getInstance().getConfig().getString("Cooldown.Message").replace("&", "§");
		cooldownTime = Main.getInstance().getConfig().getInt("Cooldown.Time");
	}

	public void backPlayer(Player p) {

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
						long time = ((cooldown.get(uuid) / 1000) + cooldownTime) - (System.currentTimeMillis() / 1000);
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
}
