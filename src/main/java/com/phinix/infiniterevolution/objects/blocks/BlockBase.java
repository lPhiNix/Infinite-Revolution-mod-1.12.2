package com.phinix.infiniterevolution.objects.blocks;

import com.phinix.infiniterevolution.InfiniteRevolution;
import com.phinix.infiniterevolution.init.BlockInit;
import com.phinix.infiniterevolution.init.ItemInit;
import com.phinix.infiniterevolution.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;

public class BlockBase extends Block implements IHasModel {
    public BlockBase(String name, Material material, CreativeTabs tab, float hardness, float resistance, String toolClass, int harvestLevel) {
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(tab);

        setHardness(hardness);
        setResistance(resistance);
        setHarvestLevel(toolClass, harvestLevel);

        BlockInit.BLOCKS.add(this);
        ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    @Override
    public void registerModels() {
        InfiniteRevolution.proxy.registerItemRenderer(ItemBlock.getItemFromBlock(this), 0, "inventory");
    }
}
