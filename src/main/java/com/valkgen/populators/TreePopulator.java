package com.valkgen.populators;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.TreeType;
import org.bukkit.World;

public class TreePopulator extends Populator {
	@Override
	public void populate(World world, Random random, Chunk chunk) {
		super.populate(world, random, chunk);
		
		world.generateTree(loc, TreeType.TREE);
	}
}
