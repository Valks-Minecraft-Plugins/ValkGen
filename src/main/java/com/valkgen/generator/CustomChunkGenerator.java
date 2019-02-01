package com.valkgen.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
	    generator.setScale(0.005D);
	
	    for (int X = 0; X < 16; X++)
	        for (int Z = 0; Z < 16; Z++) {
	            Y = (int) (generator.noise(chunkX*16+X, chunkZ*16+Z, 0.5D, 0.5D)*25D+100D);
	            if (Math.random() < 0.333) {
	            	Material[] veg = {Material.GRASS, Material.FERN};
	            	chunk.setBlock(X, Y + 1, Z, veg[new Random().nextInt(veg.length)]);
	            }
	            chunk.setBlock(X, Y, Z, Material.GRASS_BLOCK);
	            chunk.setBlock(X, Y-1, Z, Material.DIRT);
	            chunk.setBlock(X, Y-2, Z, Material.DIRT);
	            chunk.setBlock(X, Y-3, Z, Material.DIRT);
	            for (int i = Y-4; i > 0; i--)
	                chunk.setBlock(X, i, Z, Material.STONE);
	            chunk.setBlock(X, 0, Z, Material.BEDROCK);
	        }
	    return chunk;
	}
}
