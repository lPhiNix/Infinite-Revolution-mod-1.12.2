package com.phinix.infiniterevolution.objects.items;

import com.phinix.infiniterevolution.InfiniteRevolution;
import com.phinix.infiniterevolution.init.ItemInit;
import com.phinix.infiniterevolution.util.IHasModel;
import com.phinix.infiniterevolution.util.TextUtils;
import com.phinix.infiniterevolution.config.ModConfig;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.*;

public class InfiniteRevolutionLootBox extends Item implements IHasModel {
    public static final int MAX_FORTUNE_LEVEL = 3;
    public static final double FORTUNE_WEIGHT = ModConfig.lootboxFortuneWeight;
    private final List<ItemLootBox> lootList;
    private final int tier;
    public InfiniteRevolutionLootBox(String name, CreativeTabs tab, int tier) {
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(tab);
        setMaxStackSize(1);

        setHasSubtypes(true);

        if (!ModConfig.lootMap.containsKey(tier)) {
            throw new IllegalArgumentException("Invalid tier: " + tier);
        }

        this.lootList = ModConfig.lootMap.get(tier);
        this.tier = tier;

        ItemInit.ITEMS.add(this);
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return stack.getMetadata() != 0;
    }

    @Override
    public int getMetadata(int damage) {
        return damage;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        switch (tier) {
            case 0:
                return TextFormatting.WHITE + "" + TextFormatting.BOLD + super.getItemStackDisplayName(stack);
            case 1:
                return TextFormatting.DARK_GRAY + "" + TextFormatting.BOLD + super.getItemStackDisplayName(stack);
            case 2:
                return TextUtils.makeFabulous(super.getItemStackDisplayName(stack), TextFormatting.BOLD);
            default:
                throw new IllegalArgumentException("Invalid tier: " + tier);
        }
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        String seeTier = Integer.toString(this.tier + 1);
        TextFormatting color;

        switch (tier) {
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
                throw new IllegalArgumentException("Invalid tier: " + tier);
        }

        tooltip.add(color + "" + TextFormatting.ITALIC + "Tier " + seeTier);

        int fortune = stack.getMetadata();
        int percentage = ((int) (FORTUNE_WEIGHT * 100)) * fortune;

        if (fortune != 0) {
            tooltip.add(color + "You feel that a bit more \"Fortune\" might be a good idea . . .");
            tooltip.add(TextFormatting.GREEN + "Your luck is increased by " + percentage + " %");
            tooltip.add(TextFormatting.GRAY + getFortuneText(fortune));
        }
    }

    public static String getFortuneText(int metadata) {
        switch (metadata) {
            case 1:
                return "Fortune I";
            case 2:
                return "Fortune II";
            case 3:
                return "Fortune III";
            default:
                throw new IllegalArgumentException();
        }
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (LootBoxKey.hasCorrectKey(playerIn, this.tier) || playerIn.isCreative()) {
            if (!worldIn.isRemote) {
                ItemStack keyStack = LootBoxKey.getCorrectKeyStack(playerIn, this.tier);
                if (!playerIn.isCreative() && keyStack != null) {
                    keyStack.shrink(1);
                }

                int fortuneLevel = playerIn.getHeldItem(handIn).getMetadata();
                ItemLootBox itemLootBox = getRandomItemStack(fortuneLevel);
                ItemStack randomItemStack = new ItemStack(getItemFromNameRegistry(itemLootBox.item), itemLootBox.amount, itemLootBox.meta);

                if (!playerIn.inventory.addItemStackToInventory(randomItemStack.copy())) {
                    playerIn.dropItem(randomItemStack.copy(), false);
                }

                playerIn.sendMessage(getReceiveMessage(this.tier, itemLootBox.amount, randomItemStack));

                ItemStack stack = playerIn.getHeldItem(handIn);
                if (!playerIn.isCreative()) {
                    stack.shrink(1);
                    if (stack.isEmpty()) {
                        playerIn.inventory.setInventorySlotContents(playerIn.inventory.currentItem, ItemStack.EMPTY);
                        playerIn.inventory.markDirty();
                    }
                }
            }

        } else {
            if (!worldIn.isRemote) {
                playerIn.sendMessage(new TextComponentString(TextFormatting.RED + "You need a key of the same tier to open this loot box!"));
            }
        }

        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    public ITextComponent getReceiveMessage(int tier, int amount, ItemStack itemStack) {
        ITextComponent amountText = new TextComponentString(String.valueOf(amount));
        String itemText = itemStack.getDisplayName();
        ITextComponent message;
        final String symbolX = " x ";

        String style;

        switch (tier) {
            case 0:
                style = TextFormatting.WHITE + "" + TextFormatting.BOLD;
                message = new TextComponentString(style + "You received ")
                        .appendSibling(new TextComponentString(itemText))
                        .appendSibling(new TextComponentString(symbolX))
                        .appendSibling(amountText)
                        .appendSibling(new TextComponentString(style + " from a Remote Planet!"));
                break;
            case 1:
                style = TextFormatting.DARK_GRAY + "" + TextFormatting.BOLD;
                message = new TextComponentString(style + "You received ")
                        .appendSibling(new TextComponentString(itemText))
                        .appendSibling(new TextComponentString(symbolX))
                        .appendSibling(amountText)
                        .appendSibling(new TextComponentString(style + " from a Neutron Star!"));
                break;
            case 2:
                style = TextFormatting.DARK_PURPLE + "" + TextFormatting.BOLD;
                message = new TextComponentString(style + "You received ")
                        .appendSibling(new TextComponentString(itemText))
                        .appendSibling(new TextComponentString(symbolX))
                        .appendSibling(amountText)
                        .appendSibling(new TextComponentString(style + " from The Universe!"));
                break;
            default:
                throw new IllegalArgumentException("Invalid tier: " + tier);
        }

        return message;
    }

    private ItemLootBox getRandomItemStack(int fortuneLevel) {
        if (lootList.isEmpty()) {
            throw new IllegalStateException("Loot list cannot be empty");
        }

        double adjustedProbabilitySum = 0.0;

        for (ItemLootBox item : lootList) {
            double weight = Math.pow(1.0 / item.probability, fortuneLevel * FORTUNE_WEIGHT);
            adjustedProbabilitySum += item.probability * weight;
        }

        double randomValue = new Random().nextDouble() * adjustedProbabilitySum;

        for (ItemLootBox item : lootList) {
            double weight = Math.pow(1.0 / item.probability, fortuneLevel * FORTUNE_WEIGHT);
            randomValue -= item.probability * weight;
            if (randomValue <= 0.0) {
                return item;
            }
        }

        return lootList.get(lootList.size() - 1);
    }

    public static Item getItemFromNameRegistry(String name) {
        return ForgeRegistries.ITEMS.getValue(new ResourceLocation(name));
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (this.isInCreativeTab(tab)) {
            for (int i = 0; i <= MAX_FORTUNE_LEVEL; i++) {
                items.add(new ItemStack(this, 1, i));
            }
        }
    }

    @Override
    public void registerModels() {
        for (int i = 0; i <= MAX_FORTUNE_LEVEL; i++) {
            InfiniteRevolution.proxy.registerItemRenderer(this, i, "inventory");
        }
    }

    public static class ItemLootBox {
        public final String item;
        public final int amount;
        public final double probability;
        public final int meta;

        public ItemLootBox(String item, int amount, double probability, int meta) {
            this.item = item;
            this.amount = amount;
            this.probability = probability;
            this.meta = meta;
        }
    }
}
