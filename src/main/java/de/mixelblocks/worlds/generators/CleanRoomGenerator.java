package de.mixelblocks.worlds.generators;

import de.mixelblocks.worlds.MixelWorldGeneratorPlugin;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.generator.BiomeProvider;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.generator.WorldInfo;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CleanRoomGenerator extends ChunkGenerator {

    private final MixelWorldGeneratorPlugin plugin;

    public CleanRoomGenerator(MixelWorldGeneratorPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void generateSurface(@NotNull WorldInfo worldInfo, @NotNull Random random, int chunkX, int chunkZ, @NotNull ChunkData chunkData) {
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                chunkData.setBlock(x, worldInfo.getMinHeight() + 4, z, Material.GRASS_BLOCK);
                chunkData.setBlock(x, worldInfo.getMinHeight() + 3, z, Material.STONE);
                chunkData.setBlock(x, worldInfo.getMinHeight() + 2, z, Material.STONE);
                chunkData.setBlock(x, worldInfo.getMinHeight() + 1, z, Material.STONE);
            }
        }
    }

    @Override
    public void generateBedrock(@NotNull WorldInfo worldInfo, @NotNull Random random, int chunkX, int chunkZ, @NotNull ChunkData chunkData) {
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                chunkData.setBlock(x, worldInfo.getMinHeight(), z, Material.BEDROCK);
            }
        }
    }

    @Override
    public @Nullable BiomeProvider getDefaultBiomeProvider(@NotNull WorldInfo worldInfo) {
        return new BiomeProvider() {
            @Override
            public @NotNull Biome getBiome(@NotNull WorldInfo worldInfo, int coordsX, int coordsY, int coordsZ) {
                return Biome.BADLANDS;
            }

            @Override
            public @NotNull List<Biome> getBiomes(@NotNull WorldInfo worldInfo) {
                ArrayList<Biome> biomes =  new ArrayList<>();
                biomes.add(Biome.BADLANDS);
                return biomes;
            }
        };
    }
}
