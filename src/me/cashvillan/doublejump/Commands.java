package me.cashvillan.doublejump;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {
	
	public static boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
		} catch (NumberFormatException e) {
			return false;
		} catch (NullPointerException e) {
			return false;
		}
		return true;
	}
	
	public void usages(Player p) {
		p.sendMessage(ChatColor.RED + "Commands:");
		p.sendMessage("[/doublejump set <value> <integer>] Set the settings of DoubleJump ingame!");
		p.sendMessage("[/doublejump get <value>] Get a current DoubleJump setting!");
	}
	
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player p = (Player) sender;
		if (!(p.isOp())) {
			p.sendMessage(ChatColor.RED + "You must be OP to edit DoubleJump ingame!");
		}
		
		if (cmd.getName().equalsIgnoreCase("doublejump")) {
			if (args.length == 0) {
				usages(p);
				return true;
			}
			if (args[0].equalsIgnoreCase("set")) {
				if (args.length == 3) {
					if (args[1].equalsIgnoreCase("power")) {
						String args2 = args[2];
						if (isInteger(args2) == false) {
							p.sendMessage(ChatColor.RED + "The power must be a number!");
							return true;
						}
						if (isInteger(args2) == true) {
							FileManager.setValue("power", args2);
							p.sendMessage(ChatColor.GREEN + "Power set to '" + args2 + "'.");
							return true;
						}
					}
					if (args[1].equalsIgnoreCase("height")) {
						String args2 = args[2];
						if (isInteger(args2) == false) {
							p.sendMessage(ChatColor.RED + "The height must be a number!");
							return true;
						}
						if (isInteger(args2) == true) {
							FileManager.setValue("height", args2);
							p.setVelocity(p.getLocation().getDirection().multiply(Float.parseFloat((String) FileManager.getValue("power"))).setY(Float.parseFloat((String) FileManager.getValue("height"))));
							p.sendMessage(ChatColor.GREEN + "Height set to '" + args2 + "'.");
							return true;
						}
					} else {
						p.sendMessage(ChatColor.RED + "Usage: [/doublejump set <value> <integer>] Valid values are power & height.");
						return true;
					}
				} else {
					p.sendMessage(ChatColor.RED + "Usage: [/doublejump set <value> <integer>] Valid values are power & height.");
					return true;
				}
			}
			if (args[0].equalsIgnoreCase("get")) {
				if (args.length == 2) {
					String key = args[1];
					if (key.equalsIgnoreCase("power")) {
						p.sendMessage(ChatColor.GREEN + "The " + key.toLowerCase() + " is '" + FileManager.getValue(key.toLowerCase()) + "'.");
						return true;
					}
					if (key.equalsIgnoreCase("height")) {
						p.sendMessage(ChatColor.GREEN + "The " + key.toLowerCase() + " is '" + FileManager.getValue(key.toLowerCase()) + "'.");
						return true;
					}
				}
				p.sendMessage(ChatColor.RED + "Usage: [/doublejump get <value>] Valid values are power & height.");
				return true;
			}
			usages(p);
		}
		return false;
	}
}
