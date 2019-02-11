package com.valkgen.generator;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.util.noise.SimplexOctaveGenerator;

public class CustomNetherChunkGenerator extends ChunkGenerator {
	int Y = 40;

	@Override
	public ChunkData generateChunkData(World world, Random random, int chunkX, int chunkZ, BiomeGrid biome) {
		SimplexOctaveGenerator generator = new SimplexOctaveGenerator(new Random(world.getSeed()), 8);
		ChunkData chunk = createChunkData(world);
		generator.setScale(0.015D);

		for (int X = 0; X < 16; X++)
			for (int Z = 0; Z < 16; Z++) {
				Y = (int) (generator.noise(chunkX * 16 + X, chunkZ * 16 + Z, 0.5D, 0.5D) * 25D + 100D);
				if (Math.random() < 0.005) {
					Material[] veg = { Material.COAL_ORE };
					chunk.setBlock(X, Y + 1, Z, veg[new Random().nextInt(veg.length)]);
				}
				chunk.setBlock(X, Y, Z, Material.GRAY_CONCRETE);
				if (Math.random() < 0.01) {
					chunk.setBlock(X, Y - 1, Z, Material.LAVA);
				}
				chunk.setBlock(X, Y - 2, Z, Material.GRAY_CONCRETE);
				chunk.setBlock(X, Y - 3, Z, Material.GRAY_CONCRETE);
				for (int i = Y - 4; i > 0; i--) {
					if (Math.random() < 0.01) {
						chunk.setBlock(X, i, Z, Material.DIAMOND_ORE);
					} else {
						chunk.setBlock(X, i, Z, Material.OBSIDIAN);
					}
				}

				chunk.setBlock(X, 0, Z, Material.BEDROCK);
			}

		return chunk;
	}
}
