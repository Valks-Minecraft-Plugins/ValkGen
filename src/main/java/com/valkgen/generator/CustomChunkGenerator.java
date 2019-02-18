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
		SimplexOctaveGenerator caveGen = new SimplexOctaveGenerator(new Random(world.getSeed()), 8);
		ChunkData chunk = createChunkData(world);
		//0.005
		double distance = world.getBlockAt(chunkX * 16, 0, chunkZ * 16).getLocation().distance(new Location(world, 0, 0, 0));
		if (distance <= 500) {
			generator.setScale(0.00005);
		} else if (distance > 500 && distance <= 1000) {
			generator.setScale(0.005);
		} else {
			generator.setScale(0.03);
		}
		
		
		for (int X = 0; X < 16; X++) {
			for (int Z = 0; Z < 16; Z++) {
				double dist = world.getBlockAt(chunkX * 16 + X, 0, chunkZ * 16 + Z).getLocation()
						.distance(new Location(world, 0, 0, 0));
				
				Y = (int) (generator.noise(chunkX * 16 + X, chunkZ * 16 + Z, 0.5, 0.9) * 25D
						+ 100D);
				
				if (dist <= 500) {
					CustomBiome biomeGrass = new CustomBiome(chunk, X, Y, Z);
					biomeGrass.setSurface(Material.GRASS_BLOCK);
					biomeGrass.setVegetation(new Material[] {Material.GRASS, Material.FERN}, 0.33);
					biomeGrass.setVegetation(new Material[] {Material.COAL_ORE}, 0.005);
					biomeGrass.setLayer(Material.DIRT, Y - 1, Y - 4);
					biomeGrass.setOreLayer(Material.STONE, Material.COAL_ORE, 0.06, Y - 5, 1);
					biomeGrass.setLayer(Material.BEDROCK, 0, 0);
				} else if (dist > 500 && dist <= 1000) {
					CustomBiome biomeMycel = new CustomBiome(chunk, X, Y, Z);
					biomeMycel.setSurface(Material.MYCELIUM);
					biomeMycel.setLayer(Material.DIRT, Y - 1, Y - 4);
					biomeMycel.setOreLayer(Material.STONE, Material.COAL_ORE, 0.06, Y - 5, 1);
					biomeMycel.setLayer(Material.BEDROCK, 0, 0);
				} else {
					CustomBiome biomeSand = new CustomBiome(chunk, X, Y, Z);
					biomeSand.setSurface(Material.SAND);
					biomeSand.setLayer(Material.SANDSTONE, Y - 1, Y - 4);
					biomeSand.setOreLayer(Material.STONE, Material.COAL_ORE, 0.06, Y - 5, 1);
					biomeSand.setLayer(Material.BEDROCK, 0, 0);
				}
			}
		}
		
		for (int X = 0; X < 16; X++) {
			for (int Z = 0; Z < 16; Z++) {
				Y = (int) (caveGen.noise(chunkX * 16 + X, chunkZ * 16 + Z, 0.7D, 0.7D) * 3D + 20D);
				chunk.setBlock(X, Y, Z, Material.AIR);
				chunk.setBlock(X, Y - 1, Z, Material.AIR);
				chunk.setBlock(X, Y - 2, Z, Material.AIR);
				chunk.setBlock(X, Y - 3, Z, Material.AIR);
				chunk.setBlock(X, Y - 4, Z, Material.AIR);
				chunk.setBlock(X, Y - 5, Z, Material.AIR);
				chunk.setBlock(X, Y - 6, Z, Material.AIR);
			}
		}

		return chunk;
	}
}
