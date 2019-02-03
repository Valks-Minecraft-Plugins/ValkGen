package com.valkgen.populators;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

public class OrePopulator extends Populator {
	@Override
	public void populate(World world, Random random, Chunk chunk) {
		super.populate(world, random, chunk);
		generateOre(world, random, chunk, Material.COAL_ORE, 6, 50, 8, 3);
		generateOre(world, random, chunk, Material.DIAMOND_ORE, 50, 70, 2, 4);
	}
	
	private void generateOre(World world, Random random, Chunk chunk, Material ore, int minDepth, int maxDepth, int size, int density) {
		int depth = new Random().nextInt((maxDepth - minDepth) + 1) + minDepth;
		for (int n = 0; n < density; n++) {
			for (int x = 0; x < new Random().nextInt(size) + 1; x++) {
				for (int z = 0; z < new Random().nextInt(size) + 1; z++) {
					for (int y = 0; y < new Random().nextInt(size) + 1; y++) {
						Block b = new Location(world, loc.getX() + x, loc.getY() - y - depth, loc.getZ() + z).getBlock();
						if (b.getType() == Material.STONE) {
							b.setType(ore);
						}
					}
				}
			}
		}
	}
}
