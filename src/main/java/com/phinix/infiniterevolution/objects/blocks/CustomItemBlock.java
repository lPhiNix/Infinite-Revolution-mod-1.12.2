package com.phinix.infiniterevolution.objects.blocks;

import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;

public class CustomItemBlock extends ItemBlock {
    private final String textFormattingTittle;
    private final String description;
    private final String textFormattingDesc;

    public CustomItemBlock(Block block, String textFormattingTittle, String description, String textFormattingDesc) {
        super(block);

        this.textFormattingTittle = textFormattingTittle;
        this.description = description;
        this.textFormattingDesc = textFormattingDesc;
    }

    @Override
    public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
        super.addInformation(stack, world, tooltip, flag);

        if (textFormattingDesc != null) {
            tooltip.add(textFormattingDesc + description);
        }
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return textFormattingTittle + super.getItemStackDisplayName(stack);
    }
}
