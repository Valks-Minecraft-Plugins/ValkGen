package com.valkgen;

import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

import com.valkgen.generator.CustomChunkGenerator;

public class ValkGen extends JavaPlugin {
	@Override
	public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
	    return new CustomChunkGenerator();
	}
}
