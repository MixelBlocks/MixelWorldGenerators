package de.mixelblocks.worlds.generators;

import de.mixelblocks.worlds.MixelWorldGeneratorPlugin;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.generator.*;
import org.bukkit.util.noise.SimplexOctaveGenerator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WasteLandsGenerator extends ChunkGenerator {

    private final MixelWorldGeneratorPlugin plugin;

    public WasteLandsGenerator(MixelWorldGeneratorPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void generateSurface(@NotNull WorldInfo worldInfo, @NotNull Random random, int chunkX, int chunkZ, @NotNull ChunkData chunkData) {
        SimplexOctaveGenerator generator = new SimplexOctaveGenerator(new Random(worldInfo.getSeed()), 16);
        generator.setScale(0.010);
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                int currentHeight = (int) ((generator.noise(chunkX * 16 + x, chunkZ * 16 + z, 0.5D, 0.5D, true) +1 ) * 15D + 50D);
                chunkData.setBlock(x, currentHeight, z, Material.SAND);
                chunkData.setBlock(x, currentHeight-1, z, Material.SAND);
                chunkData.setBlock(x, currentHeight-2, z, Material.SAND);
                chunkData.setBlock(x, currentHeight-3, z, Material.SAND);
                chunkData.setBlock(x, currentHeight-4, z, Material.SAND);
                for (int i = currentHeight-5; i > 0; i--)
                    chunkData.setBlock(x, i, z, Material.STONE);
            }
        }
    }

    @Override
    public void generateBedrock(@NotNull WorldInfo worldInfo, @NotNull Random random, int chunkX, int chunkZ, @NotNull ChunkData chunkData) {
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                chunkData.setBlock(x, 0, z, Material.BEDROCK);
            }
        }
    }

    @Override
    public @Nullable BiomeProvider getDefaultBiomeProvider(@NotNull WorldInfo worldInfo) {
        return new BiomeProvider() {
            @Override
            public @NotNull Biome getBiome(@NotNull WorldInfo worldInfo, int coordsX, int coordsY, int coordsZ) {
                return Biome.DESERT;
            }

            @Override
            public @NotNull List<Biome> getBiomes(@NotNull WorldInfo worldInfo) {
                ArrayList<Biome> biomes =  new ArrayList<>();
                biomes.add(Biome.DESERT);
                biomes.add(Biome.SAVANNA);
                return biomes;
            }
        };
    }

}
