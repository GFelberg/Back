package me.GFelberg.Back.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import me.GFelberg.Back.utils.BackUtils;

public class BackEvent implements Listener {

	@EventHandler
	public void onDeath(PlayerDeathEvent event) {
		Player p = event.getEntity();
		if (p.hasPermission("back.back")) {
			BackUtils.back.put(p, p.getLocation());
			BackUtils.cooldown.put(p.getUniqueId(), System.currentTimeMillis());
		}
	}
}