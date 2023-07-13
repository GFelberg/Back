package me.GFelberg.Back.events;

import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import me.GFelberg.Back.Main;
import me.GFelberg.Back.data.BackConfig;
import me.GFelberg.Back.data.BackSystem;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class BackEvent implements Listener {

	@EventHandler
	public void onDeath(PlayerDeathEvent event) {
		Player p = event.getEntity();
		if (p.hasPermission("back.back")) {
			BackSystem.back.put(p, p.getLocation());
			BackSystem.cooldown.put(p.getUniqueId(), System.currentTimeMillis());
			BackSystem.createBackLocation(p);

			if (Main.getInstance().getConfig().getBoolean("ClickOptionEnable")) {
				List<String> messages_list = BackSystem.clickoption_messages;
				TextComponent text = new TextComponent();

				for (String msg : messages_list) {
					TextComponent line = new TextComponent(msg);
					line.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/back"));
					text.addExtra("\n");
					text.addExtra(line);
				}
				text.addExtra("\n");
				p.spigot().sendMessage(text);
			}
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