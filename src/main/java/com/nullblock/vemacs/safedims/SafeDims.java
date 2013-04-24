package com.nullblock.vemacs.safedims;

import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class SafeDims extends JavaPlugin implements Listener {
	public void onDisable() {
	}

	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
		saveDefaultConfig();
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerTeleport(PlayerTeleportEvent event) {
		Location to = event.getTo();
		if (Utils.isDangerous(to.getWorld().getName()).booleanValue()) {
			getLogger().info(
					"Orig Y for " + event.getPlayer().getName() + " to "
							+ to.getWorld().getName() + ": " + to.getY());

			Location step = event.getTo().getBlock()
					.getRelative(BlockFace.DOWN).getLocation();

			Location safe = Utils.getSafeY(step);
			Location adjust = new Location(to.getWorld(), safe.getX(),
					safe.getY() + 1.8, safe.getZ());

			getLogger().info(
					"New Y for " + event.getPlayer().getName() + " to "
							+ adjust.getWorld().getName() + ": "
							+ adjust.getY());

			event.setTo(adjust);
		}
	}
}