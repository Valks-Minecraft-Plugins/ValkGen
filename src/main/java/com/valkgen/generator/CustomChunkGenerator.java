package com.valkgen.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.util.noise.SimplexOctaveGenerator;

import com.valkgen.populators.OrePopulator;
import com.valkgen.populators.TreePopulator;

public class CustomChunkGenerator extends ChunkGenerator {
	List<BlockPopulator> populators = new ArrayList<BlockPopulator>();

	int Y = 50;

	public CustomChunkGenerator() {
		populators.add(new TreePopulator());
		populators.add(new OrePopulator());
	}

	@Override
	public List<BlockPopulator> getDefaultPopulators(World world) {
		return populators;
	}

	@Override
	public ChunkData generateChunkData(World world, Random random, int chunkX, int chunkZ, BiomeGrid biome) {
		SimplexOctaveGenerator generator = new SimplexOctaveGenerator(new Random(world.getSeed()), 8);
		ChunkData chunk = createChunkData(world);
		generator.setScale(0.005);

		for (int X = 0; X < 16; X++) {
			for (int Z = 0; Z < 16; Z++) {
				double dist = world.getBlockAt(chunkX * 16 + X, 0, chunkZ * 16 + Z).getLocation()
						.distance(new Location(world, 0, 0, 0));

				if (dist <= 1000) { // Flat Grass Biome
					generator.setScale(0.0001);
					Y = (int) (generator.noise(chunkX * 16 + X, chunkZ * 16 + Z, 0.0, 0.0) * 25D + 100D);

					CustomBiome biome1 = new CustomBiome(chunk, X, Y, Z);
					biome1.setSurface(Material.GRASS_BLOCK);
					biome1.setVegetation(new Material[] { Material.GRASS, Material.FERN }, 0.33);
					biome1.setVegetation(new Material[] { Material.COAL_ORE }, 0.005);
					biome1.setLayer(Material.DIRT, Y - 1, Y - 4);
					biome1.setOreLayer(Material.STONE, new Material[] {Material.COAL_ORE}, 0.01, Y - 5, 1);
					biome1.setLayer(Material.BEDROCK, 0, 0);
				} else if (dist > 1000 && dist <= 2000) { // Hilly Grass Biome
					generator.setScale(0.005);
					Y = (int) (generator.noise(chunkX * 16 + X, chunkZ * 16 + Z, 0.5, 0.9) * 25D + 100D);

					CustomBiome biome1 = new CustomBiome(chunk, X, Y, Z);
					biome1.setSurface(Material.GRASS_BLOCK);
					biome1.setVegetation(new Material[] { Material.GRASS, Material.FERN }, 0.33);
					biome1.setVegetation(new Material[] { Material.COAL_ORE }, 0.005);
					biome1.setLayer(Material.DIRT, Y - 1, Y - 4);
					biome1.setOreLayer(Material.STONE, new Material[] {Material.COAL_ORE, Material.IRON_ORE}, 0.02, Y - 5, 1);
					biome1.setLayer(Material.BEDROCK, 0, 0);
				} else if (dist > 2000 && dist <= 3000) { // Super Hilly Grass Biome
					generator.setScale(0.01);
					Y = (int) (generator.noise(chunkX * 16 + X, chunkZ * 16 + Z, 1, 1) * 25D + 100D);

					CustomBiome biome1 = new CustomBiome(chunk, X, Y, Z);
					biome1.setSurface(Material.GRASS_BLOCK);
					biome1.setVegetation(new Material[] { Material.GRASS, Material.FERN }, 0.33);
					biome1.setVegetation(new Material[] { Material.COAL_ORE }, 0.005);
					biome1.setLayer(Material.DIRT, Y - 1, Y - 4);
					biome1.setOreLayer(Material.STONE, new Material[] {Material.COAL_ORE, Material.IRON_ORE}, 0.03, Y - 5, 1);
					biome1.setLayer(Material.BEDROCK, 0, 0);
				} else if (dist > 3000 && dist <= 4000) { // Extreme Hilly Grass Biome
					generator.setScale(0.05);
					Y = (int) (generator.noise(chunkX * 16 + X, chunkZ * 16 + Z, 1, 1) * 25D + 100D);

					CustomBiome biome1 = new CustomBiome(chunk, X, Y, Z);
					biome1.setSurface(Material.GRASS_BLOCK);
					biome1.setVegetation(new Material[] { Material.GRASS, Material.FERN }, 0.33);
					biome1.setVegetation(new Material[] { Material.COAL_ORE }, 0.005);
					biome1.setLayer(Material.DIRT, Y - 1, Y - 4);
					biome1.setOreLayer(Material.STONE, new Material[] {Material.COAL_ORE, Material.IRON_ORE}, 0.04, Y - 5, 1);
					biome1.setLayer(Material.BEDROCK, 0, 0);
					biome1.setOceanLayer(Material.WATER, 60);
				} else if (dist > 4000 && dist <= 5000){ // Sand Biome
					generator.setScale(0.0005);
					Y = (int) (generator.noise(chunkX * 16 + X, chunkZ * 16 + Z, 0.0, 0.0) * 25D + 100D);

					CustomBiome biome1 = new CustomBiome(chunk, X, Y, Z);
					biome1.setSurface(Material.SAND);
					biome1.setVegetation(new Material[] { Material.COAL_ORE }, 0.005);
					biome1.setVegetation(new Material[] { Material.DEAD_BUSH }, 0.010);
					biome1.setLayer(Material.SANDSTONE, Y - 1, Y - 4);
					biome1.setOreLayer(Material.STONE, new Material[] {Material.COAL_ORE}, 0.10, Y - 5, 1);
					biome1.setLayer(Material.BEDROCK, 0, 0);
				} else if (dist > 5000 && dist <= 6000){ // Hilly Sand Biome
					generator.setScale(0.02);
					Y = (int) (generator.noise(chunkX * 16 + X, chunkZ * 16 + Z, 0.0, 0.0) * 25D + 100D);

					CustomBiome biome1 = new CustomBiome(chunk, X, Y, Z);
					biome1.setSurface(Material.SAND);
					biome1.setVegetation(new Material[] { Material.COAL_ORE }, 0.005);
					biome1.setVegetation(new Material[] { Material.DEAD_BUSH }, 0.010);
					biome1.setLayer(Material.SANDSTONE, Y - 1, Y - 4);
					biome1.setOreLayer(Material.STONE, new Material[] {Material.COAL_ORE}, 0.15, Y - 5, 1);
					biome1.setLayer(Material.BEDROCK, 0, 0);
				} else if (dist > 6000 && dist <= 6070){ // Water Barrier
					for (int i = 140; i > 0; i--)
						chunk.setBlock(X, i, Z, Material.WATER);
					
					chunk.setBlock(X, 1, Z, Material.STONE);
					chunk.setBlock(X, 0, Z, Material.BEDROCK);
				} else if (dist > 6070 && dist <= 7000) { // Flat Nether
					generator.setScale(0.005);
					Y = (int) (generator.noise(chunkX * 16 + X, chunkZ * 16 + Z, 0.0, 0.0) * 25D + 100D);

					CustomBiome biome1 = new CustomBiome(chunk, X, Y, Z);
					biome1.setSurface(Material.NETHERRACK);
					biome1.setVegetation(new Material[] { Material.COAL_ORE }, 0.005);
					biome1.setVegetation(new Material[] { Material.FIRE }, 0.01);
					biome1.setLayer(Material.NETHERRACK, Y - 1, Y - 4);
					biome1.setOreLayer(Material.NETHERRACK, new Material[] {Material.NETHER_QUARTZ_ORE}, 0.06, Y - 5, 1);
					biome1.setLayer(Material.BEDROCK, 0, 0);
				} else if (dist > 7000 && dist <= 8000){ // Super Hilly Nether Biome
					generator.setScale(0.01);
					Y = (int) (generator.noise(chunkX * 16 + X, chunkZ * 16 + Z, 0.0, 0.0) * 25D + 100D);

					CustomBiome biome1 = new CustomBiome(chunk, X, Y, Z);
					biome1.setSurface(Material.NETHERRACK);
					biome1.setVegetation(new Material[] { Material.COAL_ORE }, 0.005);
					biome1.setVegetation(new Material[] { Material.FIRE }, 0.05);
					biome1.setLayer(Material.NETHERRACK, Y - 1, Y - 4);
					biome1.setOreLayer(Material.NETHERRACK, new Material[] {Material.NETHER_QUARTZ_ORE, Material.GOLD_ORE, Material.DIAMOND_ORE}, 0.06, Y - 5, 1);
					biome1.setLayer(Material.BEDROCK, 0, 0);
				} else if (dist > 8000 && dist <= 9000) {
					generator.setScale(0.01);
					Y = (int) (generator.noise(chunkX * 16 + X, chunkZ * 16 + Z, 0.0, 0.0) * 25D + 100D);
					
					CustomBiome biome1 = new CustomBiome(chunk, X, Y, Z);
					biome1.setSurface(Material.END_STONE);
					biome1.setVegetation(new Material[] { Material.IRON_ORE }, 0.005);
					biome1.setLayer(Material.END_STONE, Y - 1, Y - 4);
					biome1.setOreLayer(Material.END_STONE, new Material[] {Material.NETHER_QUARTZ_ORE, Material.DIAMOND_ORE}, 0.06, Y - 5, 1);
					biome1.setLayer(Material.BEDROCK, 0, 0);
				} else {
					generator.setScale(0.01);
					Y = (int) (generator.noise(chunkX * 16 + X, chunkZ * 16 + Z, 0.0, 0.0) * 25D + 100D);
					
					CustomBiome biome1 = new CustomBiome(chunk, X, Y, Z);
					biome1.setSurface(Material.END_STONE);
					biome1.setVegetation(new Material[] { Material.IRON_ORE }, 0.005);
					biome1.setLayer(Material.END_STONE, Y - 1, Y - 4);
					biome1.setOreLayer(Material.END_STONE, new Material[] {Material.WHITE_GLAZED_TERRACOTTA}, 0.01, Y - 5, 1);
					biome1.setLayer(Material.BEDROCK, 0, 0);
				}
				
				for (int i = 0; i < 10; i++)
					chunk.setBlock(X, Y - 20 - i, Z, Material.AIR);
				
				for (int i = 0; i < 30; i++)
					if (chunk.getType(X, Y - 60 - i, Z) != Material.BEDROCK)
						chunk.setBlock(X, Y - 60 - i, Z, Material.AIR);
				chunk.setBlock(X, Y - 86, Z, Material.LAVA);
				chunk.setBlock(X, Y - 87, Z, Material.LAVA);
				chunk.setBlock(X, Y - 88, Z, Material.LAVA);
				chunk.setBlock(X, Y - 89, Z, Material.LAVA);
				chunk.setBlock(X, Y - 90, Z, Material.LAVA);

				// Biome Wall Separator
				for (int i = 1; i <= 8; i++)
					biomeWall(chunk, X, Z, dist, 1000 * i);
				biomeWall(chunk, X, Z, dist, 6070);
			}
		}

		return chunk;
	}

	private void biomeWall(ChunkData chunk, int X, int Z, double dist, int distance) {
		if (dist >= distance - 3 && dist <= distance + 3) {
			chunk.setBlock(X, 150, Z, Material.CHISELED_STONE_BRICKS);
			for (int i = 149; i > 0; i--)
				if (chunk.getType(X, i, Z) == Material.AIR || chunk.getType(X, i, Z) == Material.FERN
						|| chunk.getType(X, i, Z) == Material.GRASS || chunk.getType(X, i, Z) == Material.WATER)
					if (Math.random() < 0.8) {
						chunk.setBlock(X, i, Z, Material.STONE_BRICKS);
					} else {
						if (Math.random() < 0.5) {
							chunk.setBlock(X, i, Z, Material.CRACKED_STONE_BRICKS);
						} else {
							if (Math.random() < 0.5) {
								chunk.setBlock(X, i, Z, Material.STONE);
							} else {
								chunk.setBlock(X, i, Z, Material.MOSSY_STONE_BRICKS);
							}
						}
					}
		}
	}
}
