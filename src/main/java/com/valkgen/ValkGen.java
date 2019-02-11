package com.valkgen;

import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

import com.valkgen.generator.CustomChunkGenerator;
import com.valkgen.generator.CustomNetherChunkGenerator;

public class ValkGen extends JavaPlugin {
	@Override
	public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
		if (worldName.equals("world"))
			return new CustomChunkGenerator();
		else
			return new CustomNetherChunkGenerator();
	}
}
