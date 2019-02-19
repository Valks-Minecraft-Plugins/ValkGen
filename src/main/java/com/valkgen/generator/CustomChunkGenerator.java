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

	int Y1 = 50;
	int Y2 = 50;
	int Y3 = 50;
	int Y4 = 50;

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
				
				
				if (dist <= 1000) {
					Y1 = (int) (generator.noise(chunkX * 16 + X, chunkZ * 16 + Z, 0.0, 0.0) * 25D
							+ 100D);
				}
				
				if (dist <= 1000) {
					generator.setScale(0.0001);
					
					CustomBiome biome1 = new CustomBiome(chunk, X, Y1, Z);
					biome1.setSurface(Material.GRASS_BLOCK);
					biome1.setVegetation(new Material[] {Material.GRASS, Material.FERN}, 0.33);
					biome1.setVegetation(new Material[] {Material.COAL_ORE}, 0.005);
					biome1.setLayer(Material.DIRT, Y1 - 1, Y1 - 4);
					biome1.setOreLayer(Material.STONE, Material.COAL_ORE, 0.06, Y1 - 5, 1);
					biome1.setLayer(Material.BEDROCK, 0, 0);
				} else if (dist > 1000 && dist <= 2000) {
					generator.setScale(0.005);
					Y2 = (int) (generator.noise(chunkX * 16 + X, chunkZ * 16 + Z, 0.5, 0.9) * 25D
							+ 100D);
					
					CustomBiome biome1 = new CustomBiome(chunk, X, Y2, Z);
					biome1.setSurface(Material.GRASS_BLOCK);
					biome1.setVegetation(new Material[] {Material.GRASS, Material.FERN}, 0.33);
					biome1.setVegetation(new Material[] {Material.COAL_ORE}, 0.005);
					biome1.setLayer(Material.DIRT, Y2 - 1, Y2 - 4);
					biome1.setOreLayer(Material.STONE, Material.COAL_ORE, 0.06, Y2 - 5, 1);
					biome1.setLayer(Material.BEDROCK, 0, 0);
				} else if (dist > 2000 && dist <= 3000){
					generator.setScale(0.01);
					Y3 = (int) (generator.noise(chunkX * 16 + X, chunkZ * 16 + Z, 1, 1) * 25D
							+ 100D);
					
					CustomBiome biome1 = new CustomBiome(chunk, X, Y3, Z);
					biome1.setSurface(Material.GRASS_BLOCK);
					biome1.setVegetation(new Material[] {Material.GRASS, Material.FERN}, 0.33);
					biome1.setVegetation(new Material[] {Material.COAL_ORE}, 0.005);
					biome1.setLayer(Material.DIRT, Y3 - 1, Y3 - 4);
					biome1.setOreLayer(Material.STONE, Material.COAL_ORE, 0.06, Y3 - 5, 1);
					biome1.setLayer(Material.BEDROCK, 0, 0);
				} else {
					generator.setScale(0.05);
					Y4 = (int) (generator.noise(chunkX * 16 + X, chunkZ * 16 + Z, 1, 1) * 25D
							+ 100D);
					
					CustomBiome biome1 = new CustomBiome(chunk, X, Y4, Z);
					biome1.setSurface(Material.GRASS_BLOCK);
					biome1.setVegetation(new Material[] {Material.GRASS, Material.FERN}, 0.33);
					biome1.setVegetation(new Material[] {Material.COAL_ORE}, 0.005);
					biome1.setLayer(Material.DIRT, Y4 - 1, Y4 - 4);
					biome1.setOreLayer(Material.STONE, Material.COAL_ORE, 0.06, Y4 - 5, 1);
					biome1.setLayer(Material.BEDROCK, 0, 0);
					biome1.setOceanLayer(Material.WATER, 60);
				}
				
				if (dist == 1000 - 3) {
					for (int i = 0; i < 20; i++)
						chunk.setBlock(X, Y1 + i, Z, Material.COBBLESTONE);
				}
			}
		}

		return chunk;
	}
}
