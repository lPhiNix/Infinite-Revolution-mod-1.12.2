package com.phinix.infiniterevolution.objects.items;

import com.phinix.infiniterevolution.InfiniteRevolution;
import com.phinix.infiniterevolution.init.ItemInit;
import com.phinix.infiniterevolution.util.IHasModel;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;

public class ItemBase extends Item implements IHasModel {
    private final String textFormattingTittle;
    private final String description;
    private final String textFormattingDesc;
    public ItemBase (String name, CreativeTabs tab, String textFormattingTittle, String description, String textFormattingDesc) {
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(tab);

        this.textFormattingTittle = textFormattingTittle;
        this.description = description;
        this.textFormattingDesc = textFormattingDesc;

        ItemInit.ITEMS.add(this);
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
        if (textFormattingTittle == null) {
            return super.getItemStackDisplayName(stack);
        } else {
            return textFormattingTittle + super.getItemStackDisplayName(stack);
        }
    }

    @Override
    public void registerModels() {
        InfiniteRevolution.proxy.registerItemRenderer(this, 0, "inventory");
    }
}
