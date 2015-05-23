package me.cashvillan.doublejump;

import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;

public class Listeners implements Listener {
	
	@EventHandler
	public void onDamage(EntityDamageEvent event) {
		Entity entity = event.getEntity();
		
		if (entity instanceof Player) {
			if (entity.isOp()) {
				if (event.getCause() == DamageCause.FALL) {
					event.setCancelled(true);
				}
			}
		}
	}

	@EventHandler
	public void onMove(PlayerMoveEvent event) {
		Player p = event.getPlayer();
		
		if (p.getGameMode() != GameMode.CREATIVE) {
			if (p.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() != Material.AIR) {
				p.setAllowFlight(true);
			}
		}
	}
	
	@EventHandler
	public void onFly(PlayerToggleFlightEvent event) {
		Player p = event.getPlayer();
		if (p.getGameMode() != GameMode.CREATIVE) {
			event.setCancelled(true);
			p.setAllowFlight(false);
			p.setVelocity(p.getLocation().getDirection().multiply(Float.parseFloat((String) FileManager.getValue("power"))).setY(Float.parseFloat((String) FileManager.getValue("height"))));
			p.getWorld().playSound(p.getLocation(), Sound.ENDERDRAGON_WINGS, 1, 2);
			p.getWorld().playEffect(p.getLocation(), Effect.STEP_SOUND, 64);
		}
	}
}
