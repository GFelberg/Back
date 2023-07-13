package me.GFelberg.Back;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import me.GFelberg.Back.commands.Back;
import me.GFelberg.Back.data.BackConfig;
import me.GFelberg.Back.data.BackSystem;
import me.GFelberg.Back.events.BackEvent;
import me.GFelberg.Back.utils.BackUtils;
import me.GFelberg.Back.utils.UpdateChecker;

public class Main extends JavaPlugin implements Listener {

	private static Main instance;

	public void onEnable() {
		instance = this;
		saveDefaultConfig();
		loadVariables();
		loadBackConfig();
		loadEvents();
		BackSystem.loadAllBackLocations();
		BackSystem.loadClickOptionMessages();
		getCommand("back").setExecutor(new Back());
		Bukkit.getConsoleSender().sendMessage("----------------------------");
		Bukkit.getConsoleSender().sendMessage("Back Plugin Enabled!");
		Bukkit.getConsoleSender().sendMessage("Plugin developed by GFelberg");
		Bukkit.getConsoleSender().sendMessage("----------------------------");

		new UpdateChecker(this, 94702).getVersion(version -> {
			if (this.getDescription().getVersion().equals(version)) {
				getLogger().info("No update available.");
			} else {
				getLogger().info("There is a new update available.");
			}
		});
	}

	public static Main getInstance() {
		return instance;
	}

	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage("----------------------------");
		Bukkit.getConsoleSender().sendMessage("Back Plugin Disabled!");
		Bukkit.getConsoleSender().sendMessage("Plugin developed by GFelberg");
		Bukkit.getConsoleSender().sendMessage("----------------------------");
	}

	public void loadVariables() {
		BackUtils.loadVariables();
		BackSystem.loadVariables();
	}

	public void loadBackConfig() {
		BackConfig.setupConfig();
		BackConfig.getConfig().options().copyDefaults(true);
		BackConfig.saveConfig();
	}

	public void loadEvents() {
		Bukkit.getPluginManager().registerEvents(this, this);
		Bukkit.getPluginManager().registerEvents(new BackEvent(), this);
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player p = event.getPlayer();
		FileConfiguration config = this.getConfig();

		if (config.getBoolean("update-check") && p.isOp()
				|| config.getBoolean("update-check") && p.hasPermission("back.update")) {
			(new UpdateChecker(this, 94702)).getVersion((version) -> {
				if (!this.getDescription().getVersion().equalsIgnoreCase(version)) {
					p.sendMessage("There is a new update available of Back.");
				}
			});
		}
	}
}