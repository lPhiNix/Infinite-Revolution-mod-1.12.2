package com.phinix.infiniterevolution.objects.items;

import com.phinix.infiniterevolution.InfiniteRevolution;
import com.phinix.infiniterevolution.init.ItemInit;
import com.phinix.infiniterevolution.util.IHasModel;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemHealingAxe extends ItemAxe implements IHasModel {
    public ItemHealingAxe(String name, CreativeTabs tab) {
        super(ToolMaterial.DIAMOND);
        this.setUnlocalizedName(name);
        this.setRegistryName(name);
        this.setCreativeTab(tab);

        this.setMaxDamage(0);

        ItemInit.ITEMS.add(this);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return TextFormatting.GRAY + "" + TextFormatting.BOLD + super.getItemStackDisplayName(stack);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, World playerIn, List<String> tooltip, ITooltipFlag advanced) {
        tooltip.add(TextFormatting.DARK_GRAY + "" + TextFormatting.ITALIC + "No Hunger Anymore...");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack) {
        return true;
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        float healAmount = 2.0F;
        player.heal(healAmount);

        return false;
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if (isSelected && entityIn instanceof EntityPlayer && worldIn.getTotalWorldTime() % 40L == 0L) {
            ((EntityPlayer) entityIn).getFoodStats().addStats(1, 0.2F);
        }
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving) {
        return true;
    }

    @Override
    public void registerModels() {
        InfiniteRevolution.proxy.registerItemRenderer(this, 0, "inventory");
    }
}
