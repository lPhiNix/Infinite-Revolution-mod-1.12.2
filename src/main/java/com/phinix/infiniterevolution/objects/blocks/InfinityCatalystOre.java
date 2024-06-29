package com.phinix.infiniterevolution.objects.blocks;

import com.phinix.infiniterevolution.InfiniteRevolution;
import com.phinix.infiniterevolution.config.ModConfig;
import com.phinix.infiniterevolution.init.BlockInit;
import com.phinix.infiniterevolution.init.ItemInit;
import com.phinix.infiniterevolution.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class InfinityCatalystOre extends Block implements IHasModel {
    public InfinityCatalystOre(
            String name,
            Material material,
            CreativeTabs tab,
            float hardness,
            float resistance,
            String toolClass,
            int harvestLevel
    ) {
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(tab);

        setHardness(hardness);
        setResistance(resistance);
        setHarvestLevel(toolClass, harvestLevel);

        String textFormattingTittle = TextFormatting.LIGHT_PURPLE + "";
        String description = "The Remainder...";
        String textFormattingDesc = TextFormatting.DARK_GRAY + "" + TextFormatting.ITALIC;

        BlockInit.BLOCKS.add(this);
        ItemInit.ITEMS.add(new CustomItemBlock(this, textFormattingTittle, description, textFormattingDesc).setRegistryName(this.getRegistryName()));
    }

    @Override
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        Random rand = world instanceof World ? ((World) world).rand : new Random();

        if (state.getBlock() == BlockInit.INFINITY_CATALYST_ORE) {
            ItemStack infinityCatalystDust = new ItemStack(ItemInit.INFINITY_CATALYST_DUST);

            if (rand.nextInt(100) < ModConfig.infinityCatalystDropChance) {
                drops.add(infinityCatalystDust);
            }
        } else {
            super.getDrops(drops, world, pos, state, fortune);
        }
    }

    @Override
    public void registerModels() {
        InfiniteRevolution.proxy.registerItemRenderer(ItemBlock.getItemFromBlock(this), 0, "inventory");
    }
}
