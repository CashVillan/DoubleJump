package me.cashvillan.doublejump;

import java.io.File;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	static Main plugin;
	private final Listeners Listeners = new Listeners();
	
	public void onEnable() {
		plugin = this;
		registerCommands();
		registerEvents();
		
		if (!new File(this.getDataFolder() + "config.yml").exists()) saveDefaultConfig(); else saveConfig();
		saveDefaultConfig();
		saveConfig();
		FileManager.loadConfig();
	}
	
	public void registerEvents() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(this.Listeners, this);
	}
	
	public void registerCommands() {
		getCommand("doublejump").setExecutor(new Commands());
	}
	
	public static Main getInstance() {
		return plugin;
	}
}
