package me.GFelberg.Back;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.GFelberg.Back.commands.Back;
import me.GFelberg.Back.events.BackEvent;
import me.GFelberg.Back.utils.BackUtils;

public class Main extends JavaPlugin {

	private static Main instance;

	public void onEnable() {
		instance = this;
		saveDefaultConfig();
		BackUtils.loadVariables();
		getCommand("back").setExecutor(new Back());
		Bukkit.getPluginManager().registerEvents(new BackEvent(), this);
		Bukkit.getConsoleSender().sendMessage("-----------------------------");
		Bukkit.getConsoleSender().sendMessage("Back Plugin Enabled!");
		Bukkit.getConsoleSender().sendMessage("Plugin develloped by GFelberg");
		Bukkit.getConsoleSender().sendMessage("-----------------------------");
	}

	public static Main getInstance() {
		return instance;
	}

	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage("-----------------------------");
		Bukkit.getConsoleSender().sendMessage("Back Plugin Disabled!");
		Bukkit.getConsoleSender().sendMessage("Plugin develloped by GFelberg");
		Bukkit.getConsoleSender().sendMessage("-----------------------------");
	}
}