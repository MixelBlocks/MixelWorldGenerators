package de.mixelblocks.worlds.generators;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;

import de.mixelblocks.worlds.coordinates.BedrockPlatformCoordinates;
import de.mixelblocks.worlds.MixelWorldGeneratorPlugin;
import org.bukkit.generator.WorldInfo;
import org.jetbrains.annotations.NotNull;

public class VoidGenerator extends ChunkGenerator {

	private final MixelWorldGeneratorPlugin plugin;
	private final ArrayList<BedrockPlatformCoordinates> bedrockCoordinates;

	public VoidGenerator(MixelWorldGeneratorPlugin plugin, ArrayList<BedrockPlatformCoordinates> bedrockCoordinates) {
		this.plugin = plugin;
		if(bedrockCoordinates != null) this.bedrockCoordinates = bedrockCoordinates;
		else this.bedrockCoordinates = new ArrayList<>();
	}

	@Override
	public void generateSurface(@NotNull WorldInfo worldInfo, @NotNull Random random, int chunkX, int chunkZ, @NotNull ChunkData chunkData) {
		Iterator<BedrockPlatformCoordinates> bedrockCoordinatesIterator = this.bedrockCoordinates.iterator();
		while (bedrockCoordinatesIterator.hasNext()) {
			BedrockPlatformCoordinates block = bedrockCoordinatesIterator.next();
			if (block.getX() >= chunkX * 16 && block.getX() < (chunkX + 1) * 16 && block.getZ() >= chunkZ * 16 && block.getZ() < (chunkZ + 1) * 16) {
				int x = block.getX() % 16;
				if (x < 0) x += 16;
				int z = block.getZ() % 16;
				if (z < 0) z += 16;
				chunkData.setBlock(x, block.getY(), z, Material.BEDROCK);
				bedrockCoordinatesIterator.remove();
			}
		}
	}

	@Override
	public Location getFixedSpawnLocation(World world, Random random) {
		if (this.plugin.getConfig().getBoolean("generators.VoidGenerator.bedrock.enabled"))
			return new Location(world, this.plugin.getConfig().getInt("generators.VoidGenerator.bedrock.x"),
					(this.plugin.getConfig().getInt("generators.VoidGenerator.bedrock.y") + 1),
					this.plugin.getConfig().getInt("generators.VoidGenerator.bedrock.z"));
		else return new Location(world, 0,65,0);
	}

}
