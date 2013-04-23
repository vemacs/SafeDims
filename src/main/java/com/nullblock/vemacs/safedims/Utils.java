package com.nullblock.vemacs.safedims;

import java.util.Iterator;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class Utils {
	public static Location getSafeY(Location location) {
		Location safe = location;
		for (int height = (int) location.getY(); height >= 0; height--) {
			safe.setY(height);
			if ((!safe.getBlock().getType().isTransparent())
					&& (new Location(safe.getWorld(), safe.getX(),
							safe.getY() + 2, safe.getZ()).getBlock().getType()
							.isTransparent())) {
				break;
			}
		}
		return safe;
	}

	public static Boolean isDangerous(String worldname) {
		Boolean bool = Boolean.valueOf(false);
		List dangerous = Bukkit.getServer().getPluginManager()
				.getPlugin("SafeDims").getConfig().getStringList("dangerous");

		Iterator itr = dangerous.iterator();
		while (itr.hasNext()) {
			if (worldname.toLowerCase().contains((String) itr.next())) {
				bool = Boolean.valueOf(true);
			}
		}
		return bool;
	}
}