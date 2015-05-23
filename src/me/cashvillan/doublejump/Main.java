package me.cashvillan.doublejump;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
	
	static Main plugin;
	private final Listeners Listeners = new Listeners();
	
	public void onEnable() {
		plugin = this;
		registerCommands();
		registerEvents();
		
		saveDefaultConfig();
		saveConfig();
		FileManager.loadConfig();
		
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
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
