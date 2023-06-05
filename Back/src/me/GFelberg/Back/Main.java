package me.GFelberg.Back;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.GFelberg.Back.commands.Back;
import me.GFelberg.Back.data.BackConfig;
import me.GFelberg.Back.data.BackSystem;
import me.GFelberg.Back.events.BackEvent;
import me.GFelberg.Back.utils.BackUtils;

public class Main extends JavaPlugin {

	private static Main instance;

	public void onEnable() {
		instance = this;
		saveDefaultConfig();
		loadVariables();
		BackSystem.loadAllBackLocations();
		loadBackConfig();
		getCommand("back").setExecutor(new Back());
		Bukkit.getPluginManager().registerEvents(new BackEvent(), this);
		Bukkit.getConsoleSender().sendMessage("----------------------------");
		Bukkit.getConsoleSender().sendMessage("Back Plugin Enabled!");
		Bukkit.getConsoleSender().sendMessage("Plugin developed by GFelberg");
		Bukkit.getConsoleSender().sendMessage("----------------------------");
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
}