package com.valkgen.generator;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.generator.ChunkGenerator.ChunkData;

public class CustomBiome {
	private ChunkData chunk;
	private int X, Y, Z;

	public CustomBiome(ChunkData chunk, int X, int Y, int Z) {
		this.chunk = chunk;
		this.X = X;
		this.Y = Y;
		this.Z = Z;
	}
	
	public void setVegetation(Material[] materials, double chance) {
		if (Math.random() < chance)
			chunk.setBlock(X, Y + 1, Z, materials[new Random().nextInt(materials.length)]);
	}
	
	public void setSurface(Material material) {
		chunk.setBlock(X, Y, Z, material);
	}

	public void setLayer(Material material, int high, int low) {
		for (int i = high; i >= low; i--)
			chunk.setBlock(X, Math.min(Y, i), Z, material);
	}
	
	public void setOreLayer(Material common, Material ore, double chance, int high, int low) {
		for (int i = high; i >= low; i--)
			if (Math.random() < chance)
				chunk.setBlock(X, Math.min(Y, i), Z, ore);
			else
				chunk.setBlock(X, Math.min(Y, i), Z, common);
	}
	
	public void setOceanLayer(Material liquid, int high) {
		for (int i = high; i >= 0; i--)
			if (chunk.getType(X, i, Z) == Material.AIR)
				chunk.setBlock(X, i, Z, liquid);
	}
}
