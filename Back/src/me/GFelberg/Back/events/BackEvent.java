package me.GFelberg.Back.events;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import me.GFelberg.Back.data.BackConfig;
import me.GFelberg.Back.data.BackSystem;

public class BackEvent implements Listener {

	@EventHandler
	public void onDeath(PlayerDeathEvent event) {
		Player p = event.getEntity();
		if (p.hasPermission("back.back")) {
			BackSystem.back.put(p, p.getLocation());
			BackSystem.cooldown.put(p.getUniqueId(), System.currentTimeMillis());
			BackSystem.createBackLocation(p);
		}
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player p = event.getPlayer();
		FileConfiguration customConfig = BackConfig.getConfig();

		if (customConfig.getString("DeathLocations." + p.getUniqueId().toString()) == null) {
			return;
		} else {
			BackSystem.loadBackLocation(p);
		}
	}
}