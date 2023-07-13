package me.GFelberg.Back.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.GFelberg.Back.data.BackSystem;
import me.GFelberg.Back.utils.BackUtils;

public class Back implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (command.getName().equalsIgnoreCase("back")) {

			if (!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.RED + "This command can be only made by players!");
				return true;
			}

			if (!(sender.hasPermission("back.back"))) {
				sender.sendMessage(BackSystem.back_nopermission);
				return true;
			}

			Player p = (Player) sender;
			BackUtils utils = new BackUtils();
			BackSystem sys = new BackSystem();

			if (args.length == 0) {
				sys.backPlayer(p);
				return true;
			}

			if (args.length == 1) {

				if (args[0].equalsIgnoreCase("reload")) {
					utils.reloadConfig(p);
				} else if (args[0].equalsIgnoreCase("help")) {
					utils.helpPage(p);
				}
				return true;
			}
		}
		return true;
	}
}