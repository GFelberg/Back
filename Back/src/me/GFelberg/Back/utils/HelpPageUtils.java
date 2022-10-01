package me.GFelberg.Back.utils;

import me.GFelberg.Back.Main;

public class HelpPageUtils {

	public String getHelp_page() {
		return Main.getInstance().getConfig().getString("Help.Page").replace("&", "§");
	}

	public String getHelp_back() {
		return Main.getInstance().getConfig().getString("Help.Back").replace("&", "§");
	}
	
	public String getHelp_reload() {
		return Main.getInstance().getConfig().getString("Help.Reload").replace("&", "§");
	}
}