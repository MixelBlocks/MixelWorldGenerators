package de.mixelblocks.worlds;

import java.util.ArrayList;

import de.mixelblocks.worlds.coordinates.*;
import de.mixelblocks.worlds.generators.*;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

public class MixelWorldGeneratorPlugin extends JavaPlugin {

	private VoidGenerator voidGenerator;
	private CleanRoomGenerator cleanRoomGenerator;
	private WasteLandsGenerator wasteLandsGenerator;

	@Override
	public void onEnable() {
		this.saveDefaultConfig();
		this.reloadConfig();
		ArrayList<String> generatorList = new ArrayList<>();

		ArrayList<BedrockPlatformCoordinates> voidBedrockCoordinates = new ArrayList<>();
		if (this.getConfig().getBoolean("generators.VoidGenerator.bedrock.enabled")) {
			int xCord = this.getConfig().getInt("generators.VoidGenerator.bedrock.x");
			int yCord = this.getConfig().getInt("generators.VoidGenerator.bedrock.y");
			int zCord = this.getConfig().getInt("generators.VoidGenerator.bedrock.z");
			int radius = Math.abs(this.getConfig().getInt("generators.VoidGenerator.bedrock.radius"));
			if (radius == 0) {
				voidBedrockCoordinates.add(new BedrockPlatformCoordinates(xCord, yCord, zCord));
			} else {
				for (int x = -radius; x <= radius; x++) {
					for (int z = -radius; z <= radius; z++)
						voidBedrockCoordinates.add(new BedrockPlatformCoordinates(xCord + x, yCord, zCord + z));
				}
			}
		}
		generatorList.add("VoidGenerator");
		this.voidGenerator = new VoidGenerator(this, voidBedrockCoordinates);
		generatorList.add("CleanRoomGenerator");
		this.cleanRoomGenerator = new CleanRoomGenerator(this);
		generatorList.add("WasteLandsGenerator");
		this.wasteLandsGenerator = new WasteLandsGenerator(this);
		
		this.getLogger().info("Added <MixelWorldGenerators:[" + generatorList + "]> generators to the server!");
	}
	
	@Override
	public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
	    switch(id) {
			case "VoidGenerator": return getVoidGenerator();
			case "CleanRoomGenerator": return getCleanRoomGenerator();
			case "WasteLandsGenerator": return getWasteLandsGenerator();
	    	default: return null;
	    }
	}

	public VoidGenerator getVoidGenerator() {
		return voidGenerator;
	}

	public CleanRoomGenerator getCleanRoomGenerator() {
		return cleanRoomGenerator;
	}

	public WasteLandsGenerator getWasteLandsGenerator() {
		return wasteLandsGenerator;
	}

}
