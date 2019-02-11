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
		SimplexOctaveGenerator caveGen = new SimplexOctaveGenerator(new Random(world.getSeed()), 8);
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
	            if (Math.random() < 0.0005) {
	            	chunk.setBlock(X, Y-1, Z, Material.LAVA);
	            } else if (Math.random() < 0.005){
	            	chunk.setBlock(X, Y-1, Z, Material.WATER);
	            } else {
	            	chunk.setBlock(X, Y-1, Z, Material.DIRT);
	            }
	            chunk.setBlock(X, Y-2, Z, Material.DIRT);
	            chunk.setBlock(X, Y-3, Z, Material.DIRT);
	            for (int i = Y-4; i > 0; i--) {
	            	if (Math.random() < 0.01) {
	            		chunk.setBlock(X, i, Z, Material.COAL_ORE);
	            	} else {
	            		chunk.setBlock(X, i, Z, Material.STONE);
	            	}
	            }
	            
	            chunk.setBlock(X, Y-32, Z, Material.AIR);
	            chunk.setBlock(X, Y-33, Z, Material.AIR);
	            chunk.setBlock(X, Y-34, Z, Material.AIR);
	            chunk.setBlock(X, Y-35, Z, Material.AIR);
	            chunk.setBlock(X, Y-36, Z, Material.AIR);
	            chunk.setBlock(X, Y-37, Z, Material.AIR);
	            chunk.setBlock(X, Y-38, Z, Material.AIR);
	            chunk.setBlock(X, Y-39, Z, Material.AIR);
	            chunk.setBlock(X, Y-40, Z, Material.LAVA);
	            chunk.setBlock(X, Y-41, Z, Material.LAVA);
	            chunk.setBlock(X, Y-42, Z, Material.LAVA);
	            chunk.setBlock(X, Y-43, Z, Material.LAVA);
	            chunk.setBlock(X, Y-44, Z, Material.LAVA);
	            
	            chunk.setBlock(X, 0, Z, Material.BEDROCK);
	        }
	    
	    for (int X = 0; X < 16; X++) {
	    	for (int Z = 0; Z < 16; Z++) {
	    		Y = (int) (caveGen.noise(chunkX*16+X, chunkZ*16+Z, 0.7D, 0.7D)*3D+20D);
	    		chunk.setBlock(X, Y, Z, Material.AIR);
	    		chunk.setBlock(X, Y-1, Z, Material.AIR);
	    		chunk.setBlock(X, Y-2, Z, Material.AIR);
	    		chunk.setBlock(X, Y-3, Z, Material.AIR);
	    		chunk.setBlock(X, Y-4, Z, Material.AIR);
	    		chunk.setBlock(X, Y-5, Z, Material.AIR);
	    		chunk.setBlock(X, Y-6, Z, Material.AIR);
	    	}
	    }
	    
	    return chunk;
	}
}
