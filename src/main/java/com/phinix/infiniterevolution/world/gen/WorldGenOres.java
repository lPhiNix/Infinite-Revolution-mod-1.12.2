package com.phinix.infiniterevolution.world.gen;

import com.phinix.infiniterevolution.config.ModConfig;
import com.phinix.infiniterevolution.init.BlockInit;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class WorldGenOres implements IWorldGenerator {
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        if (world.provider.getDimension() == 0) {
            generateOverworld(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider);
        }
        if (world.provider.getDimension() == 1) {
            generateEnd(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider);
        }
        if (world.provider.getDimension() == -1) {
            generateNether(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider);
        }
    }

    private void generateOverworld(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {}

    private void generateNether(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {}

    private void generateEnd(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        if (ModConfig.generateInfinityCatalystOre) {
            generateOre(BlockInit.INFINITY_CATALYST_ORE.getDefaultState(), world, random, chunkX * 16, chunkZ * 16, 3, 100, 3, 4, Blocks.END_STONE);
        }
        generateOre(BlockInit.TRINIUM_ORE.getDefaultState(), world, random, chunkX * 16, chunkZ * 16, 3, 100, random.nextInt(5) + 9, 8, Blocks.END_STONE);
    }

    private void generateOre(IBlockState ore, World world, Random random, int x, int z, int minY, int maxY, int size, int chances, Block targetBlock) {
        int deltaY = maxY - minY;

        for (int i = 0; i < chances; i++) {
            BlockPos pos = new BlockPos(x + random.nextInt(16), minY + random.nextInt(deltaY), z + random.nextInt(16));

            WorldGenMinable generator = new WorldGenMinable(ore, size, BlockMatcher.forBlock(targetBlock));
            generator.generate(world, random, pos);
        }
    }
}
