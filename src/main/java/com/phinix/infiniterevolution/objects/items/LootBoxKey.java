package com.phinix.infiniterevolution.objects.items;

import com.phinix.infiniterevolution.InfiniteRevolution;
import com.phinix.infiniterevolution.init.ItemInit;
import com.phinix.infiniterevolution.util.IHasModel;
import com.phinix.infiniterevolution.util.TextUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;

public class LootBoxKey extends Item implements IHasModel {
    private final int tier;
    public LootBoxKey(String name, CreativeTabs tab, int tier) {
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(tab);

        if (!InfiniteRevolutionLootBox.getLootMap().containsKey(tier)) {
            throw new IllegalArgumentException("Invalid tier: " + tier);
        }

        this.tier = tier;

        ItemInit.ITEMS.add(this);
    }

    public int getTier() {
        return this.tier;
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        switch (this.tier) {
            case 0:
                return TextFormatting.WHITE + super.getItemStackDisplayName(stack);
            case 1:
                return TextFormatting.DARK_GRAY + super.getItemStackDisplayName(stack);
            case 2:
                return TextUtils.makeFabulous(super.getItemStackDisplayName(stack), null);
            default:
                throw new IllegalArgumentException();
        }
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        String seeTier = Integer.toString(this.tier + 1);
        TextFormatting color;
        switch (this.tier) {
            case 0:
                color = TextFormatting.DARK_GRAY;
                break;
            case 1:
                color = TextFormatting.YELLOW;
                break;
            case 2:
                color = TextFormatting.DARK_PURPLE;
                break;
            default:
                throw new IllegalArgumentException();
        }

        tooltip.add(color + "" + TextFormatting.ITALIC + "Tier " + seeTier);
    }

    public static boolean hasCorrectKey(EntityPlayer player, int lootBoxTier) {
        for (ItemStack stack : player.inventory.mainInventory) {
            if (!stack.isEmpty() && stack.getItem() instanceof LootBoxKey) {
                if (((LootBoxKey) stack.getItem()).tier == lootBoxTier) {
                    return true;
                }
            }
        }
        return false;
    }

    public static ItemStack getCorrectKeyStack(EntityPlayer player, int tier) {
        for (ItemStack stack : player.inventory.mainInventory) {
            if (stack != null && stack.getItem() instanceof LootBoxKey && ((LootBoxKey) stack.getItem()).getTier() == tier) {
                return stack;
            }
        }
        return null;
    }

    @Override
    public void registerModels() {
        InfiniteRevolution.proxy.registerItemRenderer(this, 0, "inventory");
        Item item = new Item();
        item.getRegistryName();
    }
}
