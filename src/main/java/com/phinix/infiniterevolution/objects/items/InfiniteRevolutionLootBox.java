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
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.*;

public class InfiniteRevolutionLootBox extends Item implements IHasModel {
    private static final Map<Integer, List<ItemLootBox>> LOOT_MAP = new HashMap<>();

    static {
        LOOT_MAP.put(0, Arrays.asList(
                new ItemLootBox("infinite_revolution:neutronium_lootbox", 1, 0.05, 0), // 1 Neutronium LootBox
                new ItemLootBox("mysticalagradditions:storage", 10, 0.05, 1), // Insanium Block
                new ItemLootBox("draconicevolution:draconic_block", 10, 0.05, 0), // Awakened Draconium Block
                new ItemLootBox("draconicevolution:draconium_block", 10, 0.07, 0), // Draconium Block
                new ItemLootBox("avaritiatweaks:gaia_block", 10, 0.1, 0), // Gaia Block
                new ItemLootBox("abyssalcraft:ingotblock", 10, 0.1, 3), // Ethaxium Block
                new ItemLootBox("mysticalagradditions:storage", 10, 0.1, 2), // Insanium Coal Block
                new ItemLootBox("mysticalagriculture:ingot_storage", 10, 0.5, 5), // Supremium Block
                new ItemLootBox("botania:storage", 10, 0.5, 2), // Elementium Block
                new ItemLootBox("thermalfoundation:storage", 10, 0.5, 7), // Iridium Block
                new ItemLootBox("abyssalcraft:ingotblock", 10, 1.0, 2), // Dredium Block
                new ItemLootBox("botania:storage", 10, 1.0, 4), // Dragonstone Block
                new ItemLootBox("extraplanets:eris", 10, 1.0, 7), // Dark Iron Block
                new ItemLootBox("mysticalagriculture:ingot_storage", 10, 1.0, 4), // Superium Block
                new ItemLootBox("botania:storage", 10, 1.5, 3), // Mana Diamond Block
                new ItemLootBox("thermalfoundation:storage", 10, 1.5, 6), // Platinium Block
                new ItemLootBox("thermalfoundation:storage_alloy", 10, 1.5, 7), // Enderium Block
                new ItemLootBox("minecraft:diamond_block", 10, 1.5, 0), // Diamond Block
                new ItemLootBox("botania:storage", 10, 1.5, 1), // Terrasteel Block
                new ItemLootBox("tconstruct:metal", 10, 1.5, 2), // Manyullyn Block
                new ItemLootBox("tconstruct:metal", 10, 1.5, 0), // Cobalt Block
                new ItemLootBox("tconstruct:metal", 10, 1.5, 1), // Ardite Block
                new ItemLootBox("twilightforest:block_storage", 10, 1.5, 1), // Fiery Metal Block
                new ItemLootBox("sgcraft:naquadahblock", 10, 1.5, 0), // Naquadah Alloy Block
                new ItemLootBox("enderio:block_alloy", 10, 1.5, 8), // End Steel Block
                new ItemLootBox("abyssalcraft:ingotblock", 10, 1.5, 1), // Refined Coralium Block
                new ItemLootBox("abyssalcraft:ingotblock", 10, 1.5, 0), // Abyssalnite Block
                new ItemLootBox("extraplanets:mercury", 10, 3.0, 7), // Mercury Block
                new ItemLootBox("extraplanets:jupiter", 10, 3.0, 8), // Palladium Block
                new ItemLootBox("extraplanets:pluto", 10, 3.0, 7), // Tungsten Block
                new ItemLootBox("extraplanets:uranus", 10, 3.0, 4), // Crystal Block
                new ItemLootBox("extraplanets:uranus", 10, 3.0, 8), // White Gem Block
                new ItemLootBox("extraplanets:neptune", 10, 3.0, 11), // Blue Gem Block
                new ItemLootBox("extraplanets:jupiter", 10, 3.0, 12), // Red Gem Block
                new ItemLootBox("extraplanets:neptune", 10, 3.0, 7), // Zinc Block
                new ItemLootBox("galacticraftplanets:mars", 10, 3.0, 8), // Desh Block
                new ItemLootBox("galacticraftplanets:asteroids_block", 10, 3.0, 7), // Titanium Block
                new ItemLootBox("extraplanets:saturn", 10, 3.0, 7), // Magnesium Block
                new ItemLootBox("extraplanets:mercury", 10, 3.0, 11), // Carbon Block
                new ItemLootBox("galacticraftcore:basic_block_core", 10, 3.0, 12), // Meteoric Iron Block
                new ItemLootBox("aether_legacy:enchanted_gravitite", 10, 3.0, 0), // Gravitite Block
                new ItemLootBox("minecraft:lapis_block", 32, 5.0, 0), // Lapis Lazuli Block
                new ItemLootBox("minecraft:gold_block", 32, 5.0, 0), // Gold Block
                new ItemLootBox("minecraft:iron_block", 10, 5.0, 0), // Iron Block
                new ItemLootBox("minecraft:obsidian", 32, 5.0, 0), // Obsidian Block
                new ItemLootBox("minecraft:emerald_block", 32, 5.0, 0), // Emerald Block
                new ItemLootBox("minecraft:redstone_block", 32, 5.0, 0), // Redstone Block
                new ItemLootBox("minecraft:quartz_block", 32, 5.0, 0), // Quartz Block
                new ItemLootBox("minecraft:coal_block", 32, 5.0, 0), // Coal Block
                new ItemLootBox("thermalfoundation:storage", 32, 5.0, 0), // Copper Block
                new ItemLootBox("thermalfoundation:storage", 32, 5.0, 1), // Tin Block
                new ItemLootBox("thermalfoundation:storage", 32, 5.0, 2), // Silver Block
                new ItemLootBox("thermalfoundation:storage", 32, 5.0, 3), // Lead Block
                new ItemLootBox("thermalfoundation:storage", 32, 5.0, 4), // Alumnium Block
                new ItemLootBox("thermalfoundation:storage", 32, 5.0, 5), // Nickel Block
                new ItemLootBox("thermalfoundation:storage", 32, 5.0, 8), // Mana Infused Metal Block
                new ItemLootBox("thermalfoundation:storage_alloy", 32, 5.0, 0), // Steel Block
                new ItemLootBox("thermalfoundation:storage_alloy", 32, 5.0, 2), // Invar Block
                new ItemLootBox("thermalfoundation:storage_alloy", 32, 5.0, 1), // Electrum Block
                new ItemLootBox("thermalfoundation:storage_alloy", 32, 5.0, 3), // Bronze Block
                new ItemLootBox("thermalfoundation:storage_alloy", 32, 5.0, 4), // Constantan Block
                new ItemLootBox("thermalfoundation:storage_alloy", 32, 5.0, 5), // Signalium Block
                new ItemLootBox("thermalfoundation:storage_alloy", 32, 5.0, 6), // Lumium Block
                new ItemLootBox("thermalfoundation:storage_resource", 32, 5.0, 1), // Coal Cock Block
                new ItemLootBox("bewitchment:block_of_garnet", 32, 5.0, 0), // Garnet Block
                new ItemLootBox("bewitchment:block_of_opal", 32, 5.0, 0), // Opal Block
                new ItemLootBox("bewitchment:block_of_salt", 32, 5.0, 0), // Salt Block
                new ItemLootBox("biomesoplenty:crystal", 32, 5.0, 0), // Celestial Crystal Block
                new ItemLootBox("biomesoplenty:gem_block", 32, 5.0, 0), // End Amethyst Block
                new ItemLootBox("biomesoplenty:gem_block", 32, 5.0, 1), // Ruby Block
                new ItemLootBox("biomesoplenty:gem_block", 32, 5.0, 2), // Peridot Block
                new ItemLootBox("biomesoplenty:gem_block", 32, 5.0, 3), // Topaz Block
                new ItemLootBox("biomesoplenty:gem_block", 32, 5.0, 4), // Tanzanite Block
                new ItemLootBox("biomesoplenty:gem_block", 32, 5.0, 5), // Malachite Block
                new ItemLootBox("biomesoplenty:gem_block", 32, 5.0, 6), // Sapphire Block
                new ItemLootBox("biomesoplenty:gem_block", 32, 5.0, 7), // Amber Block
                new ItemLootBox("ic2:resource", 32, 5.0, 10), // Uranium Block
                new ItemLootBox("enderio:block_alloy", 32, 5.0, 0), // Electrical Steel Block
                new ItemLootBox("enderio:block_alloy", 32, 5.0, 1), // Energetic Alloy Block
                new ItemLootBox("enderio:block_alloy", 32, 5.0, 3), // Redstone Alloy Block
                new ItemLootBox("enderio:block_alloy", 32, 5.0, 4), // Conductive Iron Block
                new ItemLootBox("enderio:block_alloy", 32, 5.0, 5), // Pulsating Iron Block
                new ItemLootBox("enderio:block_alloy", 32, 5.0, 6), // Dark Steel Block
                new ItemLootBox("enderio:block_alloy", 32, 5.0, 7), // Soularium Block
                new ItemLootBox("enderio:block_alloy", 32, 5.0, 9), // Iron Alloy Block
                new ItemLootBox("twilightforest:block_storage", 32, 5.0, 0), // Ironwood Block
                new ItemLootBox("twilightforest:block_storage", 32, 5.0, 2), // Steeleaf Block
                new ItemLootBox("twilightforest:block_storage", 32, 5.0, 3), // Arctic Fur Block
                new ItemLootBox("twilightforest:block_storage", 32, 5.0, 4), // Carminite Block
                new ItemLootBox("tconstruct:metal", 32, 5.0, 3), // Knightslime Block
                new ItemLootBox("tconstruct:metal", 32, 5.0, 4), // Pigiron Block
                new ItemLootBox("tconstruct:metal", 32, 5.0, 5), // Alumnium Brass Block
                new ItemLootBox("tconstruct:metal", 32, 5.0, 6), // Silky Jewel Block
                new ItemLootBox("appliedenergistics2:quartz_block", 10, 5.0, 0), // Certus Quartz Block
                new ItemLootBox("appliedenergistics2:fluix_block", 10, 5.0, 0), // Fluix Block
                new ItemLootBox("botania:storage", 32, 5.0, 0), // Manasteel Block
                new ItemLootBox("galacticraftcore:basic_block_core", 32, 5.0, 13), // Silicon Block
                new ItemLootBox("growthcraft:salt_block", 32, 5.0, 0), // Rock Salt Block
                new ItemLootBox("mysticalagriculture:ingot_storage", 32, 5.0, 0), // Base Essence Block
                new ItemLootBox("mysticalagriculture:ingot_storage", 32, 5.0, 1), // Inferium Block
                new ItemLootBox("mysticalagriculture:ingot_storage", 32, 5.0, 2), // Prudentium Block
                new ItemLootBox("mysticalagriculture:ingot_storage", 32, 5.0, 3), // Intermedium Block
                new ItemLootBox("mysticalagriculture:ingot_storage", 32, 5.0, 6), // Solium Block
                new ItemLootBox("embers:block_dawnstone", 32, 5.0, 0), // Dawnstone Block
                new ItemLootBox("aether_legacy:zanite_block", 32, 5.0, 0) // Zanite Block
        ));

        LOOT_MAP.put(1, Arrays.asList(
                new ItemLootBox("infinite_revolution:infinite_lootbox", 1, 0.01, 0), // 1 Infinity LootBox
                new ItemLootBox("avaritia:block_resource", 1, 0.01, 1), // 1 Infinite Block
                new ItemLootBox("avaritiaddons:avaritiaddons_chest", 1, 0.1, 1), // 1 Infinity Chest
                new ItemLootBox("avaritia:resource", 1, 0.2, 6), // 1 Infinity Ingot
                new ItemLootBox("avaritia:resource", 2, 0.5, 5), // 2 Infinity Catalyst
                new ItemLootBox("galacticraftcore:infinite_oxygen", 1, 0.8, 0), // 1 Infinite Oxygen Supply
                new ItemLootBox("evilcraft:creative_blood_drop", 1, 0.8, 0), // 1 Creative Blood Drop
                new ItemLootBox("refinedstorage:storage_disk", 1, 0.8, 4), // 1 Creative Storage Disk
                new ItemLootBox("avaritia:singularity", 1, 1.0, 14), // 1 Iridium Singularity
                new ItemLootBox("avaritia:singularity", 1, 1.4, 11), // 1 Emerald Singularity
                new ItemLootBox("avaritia:singularity", 1, 1.5, 0), // 1 Iron Singularity
                new ItemLootBox("avaritia:singularity", 1, 1.5, 1), // 1 Gold Singularity
                new ItemLootBox("avaritia:singularity", 1, 1.5, 2), // 1 Lapis Singularity
                new ItemLootBox("avaritia:singularity", 1, 1.5, 3), // 1 Redstone Singularity
                new ItemLootBox("avaritia:singularity", 1, 1.5, 4), // 1 Nether Quartz Singularity
                new ItemLootBox("avaritia:singularity", 1, 1.5, 5), // 1 Copper Singularity
                new ItemLootBox("avaritia:singularity", 1, 1.5, 6), // 1 Tin Singularity
                new ItemLootBox("avaritia:singularity", 1, 1.5, 7), // 1 Lead Singularity
                new ItemLootBox("avaritia:singularity", 1, 1.5, 8), // 1 Silver Singularity
                new ItemLootBox("avaritia:singularity", 1, 1.5, 9), // 1 Nickel Singularity
                new ItemLootBox("avaritia:singularity", 1, 1.5, 10), // 1 Diamond Singularity
                new ItemLootBox("infinite_revolution:infinity_catalyst_dust", 16, 1.9, 0), // 1 Infinity Catalyst Dust
                new ItemLootBox("infinite_revolution:unbreakable_bedrock_pickaxe", 1, 2.0, 0), // 1 Bedrock Pickaxe
                new ItemLootBox("avaritia:neutron_collector", 1, 2.0, 0), // 1 Neutronium Collector
                new ItemLootBox("avaritia:block_resource", 6, 5.0, 0), // 6 Neutronium Block
                new ItemLootBox("avaritia:resource", 32, 10.0, 4), // 32 Neutronium Ingots
                new ItemLootBox("avaritia:block_resource", 4, 12.0, 2), // 4 Crystal Matrix
                new ItemLootBox("avaritia:endest_pearl", 6, 12.0, 0), // 6 Endest Pearl
                new ItemLootBox("avaritia:ultimate_stew", 16, 12.0, 0) // 16 Ultimate Stew
        ));

        LOOT_MAP.put(2, Arrays.asList(
                new ItemLootBox("draconicevolution:creative_rf_source", 1, 0.01, 0), // 1 Creative RF Source
                new ItemLootBox("avaritia:infinity_chestplate", 1, 0.1, 0), // 1 Infinity Breadsplate
                new ItemLootBox("avaritia:block_resource", 4, 0.1, 1), // 4 Infinity Blocks
                new ItemLootBox("avaritia:infinity_pants", 1, 0.2, 0), // 1 Infinity Leggings
                new ItemLootBox("avaritia:infinity_helmet", 1, 0.2, 0), // 1 Infinity Helmet
                new ItemLootBox("avaritia:infinity_boots", 1, 0.2, 0), // 1 Infinity Boots
                new ItemLootBox("avaritia:infinity_sword", 1, 0.2, 0), // 1 Sword Of Cosmos
                new ItemLootBox("avaritia:infinity_pickaxe", 1, 0.2, 0), // 1 World Breaker
                new ItemLootBox("avaritia:infinity_shovel", 1, 0.2, 0), // 1 Planet Eater
                new ItemLootBox("avaritia:infinity_axe", 1, 0.2, 0), // 1 Nature's Ruin
                new ItemLootBox("avaritia:infinity_bow", 1, 0.6, 0), // 1 Longbow Of the Heavens
                new ItemLootBox("galacticraftcore:infinite_battery", 1, 0.8, 0), // 1 Infinity Battery
                new ItemLootBox("avaritiaddons:infinity_compressor", 1, 1.0, 0), // 1 Infinity Compressor
                new ItemLootBox("avaritia:resource", 5, 2, 5), // 5 Infinity Catalyst
                new ItemLootBox("eternalsingularity:eternal_singularity", 8, 3, 0), // 8 Eternal Singularity
                new ItemLootBox("extrautils2:drum", 1, 5, 4), // 1 Creative Drum
                new ItemLootBox("sgcraft:stargatering", 4, 6, 0), // 4 Stargate Ring Block
                new ItemLootBox("draconicevolution:draconic_staff_of_power", 1, 8, 0), // 1 Draconic Staff Of Power
                new ItemLootBox("draconicevolution:draconic_chest", 1, 9, 0), // 1 Draconic Chestplate
                new ItemLootBox("draconicevolution:draconic_legs", 1, 9, 0), // 1 Draconic Leggings
                new ItemLootBox("draconicevolution:draconic_helm", 1, 9, 0), // 1 Draconic Helmet
                new ItemLootBox("draconicevolution:draconic_boots", 1, 9, 0), // 1 Draconic Boots
                new ItemLootBox("draconicevolution:draconic_sword", 1, 10, 0), // 1 Draconic Sword
                new ItemLootBox("draconicevolution:draconic_pick", 1, 10, 0), // 1 Draconic Pickaxe
                new ItemLootBox("draconicevolution:draconic_bow", 1, 10, 0), // 1 Draconic Bow
                new ItemLootBox("draconicevolution:draconic_axe", 1, 10, 0), // 1 Draconic Axe
                new ItemLootBox("draconicevolution:draconic_shovel", 1, 10, 0), // 1 Draconic Shovel
                new ItemLootBox("sgcraft:ic2capacitor", 16, 20.0, 0), // 16 Ridiculously Large Circuit
                new ItemLootBox("avaritia:block_resource", 16, 20.0, 0), // 16 Neutronium Block
                new ItemLootBox("draconicevolution:chaos_shard", 24, 20.0, 0), // 24 Chaos Shards
                new ItemLootBox("avaritia:block_resource", 64, 20.0, 2) // 64 Crystal Matrix
        ));
    }

    public static final int MAX_FORTUNE_LEVEL = 3;
    public static final double FORTUNE_WEIGHT = 0.3;
    private final List<ItemLootBox> lootList;
    private final int tier;

    public InfiniteRevolutionLootBox(String name, CreativeTabs tab, int tier) {
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(tab);

        setHasSubtypes(true);

        if (!LOOT_MAP.containsKey(tier)) {
            throw new IllegalArgumentException("Invalid tier: " + tier);
        }

        this.lootList = LOOT_MAP.get(tier);
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

    public static Map<Integer, List<ItemLootBox>> getLootMap() {
        return LOOT_MAP;
    }

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
            }

            ItemStack stack = playerIn.getHeldItem(handIn);
            if (!playerIn.isCreative()) {
                stack.shrink(1);
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
        ITextComponent itemText = itemStack.getTextComponent();
        ITextComponent message;
        String style;
        final String symbolX = " x ";

        switch (tier) {
            case 0:
                style = TextFormatting.WHITE + "" + TextFormatting.BOLD;
                message = new TextComponentString(style + "You received     ")
                        .appendSibling(itemText)
                        .appendSibling(new TextComponentString(symbolX))
                        .appendSibling(amountText)
                        .appendSibling(new TextComponentString(style + " from a Remote Planet!"));
                break;
            case 1:
                style = TextFormatting.DARK_GRAY + "" + TextFormatting.BOLD;
                message = new TextComponentString(style + "You received     ")
                        .appendSibling(itemText)
                        .appendSibling(new TextComponentString(symbolX))
                        .appendSibling(amountText)
                        .appendSibling(new TextComponentString(style + " from a Neutron Star!"));
                break;
            case 2:
                String fabulousPrefix = TextUtils.makeFabulous("You received     ", TextFormatting.BOLD);
                message = new TextComponentString(fabulousPrefix)
                        .appendSibling(itemText)
                        .appendSibling(new TextComponentString(symbolX))
                        .appendSibling(amountText)
                        .appendSibling(new TextComponentString(TextUtils.makeFabulous(" from the Universe!", TextFormatting.BOLD)));
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
        private final String item;
        private final int amount;
        private final double probability;
        private final int meta;

        public ItemLootBox(String item, int amount, double probability, int meta) {
            this.item = item;
            this.amount = amount;
            this.probability = probability;
            this.meta = meta;
        }
    }
}