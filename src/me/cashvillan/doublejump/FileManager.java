package me.cashvillan.doublejump;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class FileManager {
	
	//fileloading
    public static FileConfiguration Config;
    public static File configFile;

    public static void loadConfig() {    	
		configFile = new File(Main.getInstance().getDataFolder(), "config.yml");
		
	    if (!configFile.exists()) {
	        try {
	        	configFile.mkdir();
	        	configFile.createNewFile();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
		    
	    Config = YamlConfiguration.loadConfiguration(configFile);
    }
    
    public static FileConfiguration getConfig() {
    	return Config;
    }
    
    public static void saveConfig() {
    	try {
			Config.save(configFile);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    //filemanagement
	public static void setValue(String key, Object value) {
		FileManager.getConfig().set("settings." + key, value);
		FileManager.saveConfig();
	}
	
	public static Object getValue(String key) {
		return FileManager.getConfig().get("settings." + key);
	}
	
	public static boolean hasValue(Player player, String key) {
		return FileManager.getConfig().contains("players." + key);
	}
}