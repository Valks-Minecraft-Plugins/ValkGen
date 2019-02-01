package com.valkgen.populators;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;

public class Populator extends BlockPopulator {
	protected Location loc;

	@Override
	public void populate(World world, Random random, Chunk chunk) {
		int x = chunk.getX() * 16 + random.nextInt(10);
		int z = chunk.getZ() * 16 + random.nextInt(10);
		
		loc = new Location(world, x, world.getHighestBlockYAt(x, z), z);
	}
}
