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
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.*;

public class InfiniteRevolutionLootBox extends Item implements IHasModel {
    private static final Map<Integer, List<ItemLootBox>> LOOT_MAP = new HashMap<>();

    static {
        LOOT_MAP.put(0, Arrays.asList(
                new ItemLootBox("infinite_revolution:neutronium_lootbox", 1, 0.01, 0), // 1 Neutronium LootBox
                new ItemLootBox("minecraft:bedrock", 10, 0.01, 0), // Bedrock
                new ItemLootBox("mysticalagradditions:storage", 10, 0.05, 1), // Insanium Block
                new ItemLootBox("draconicevolution:draconic_block", 10, 0.05, 0), // Awakened Draconium Block
                new ItemLootBox("draconicevolution:draconium_block", 10, 0.07, 0), // Draconium Block
                new ItemLootBox("infinite_revolution:trinium_block", 10, 0.1, 0), // Trinium Block
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
                new ItemLootBox("minecraft:lapis_block", 10, 5.0, 0), // Lapis Lazuli Block
                new ItemLootBox("minecraft:gold_block", 10, 5.0, 0), // Gold Block
                new ItemLootBox("minecraft:iron_block", 10, 5.0, 0), // Iron Block
                new ItemLootBox("minecraft:obsidian", 10, 5.0, 0), // Obsidian Block
                new ItemLootBox("minecraft:emerald_block", 10, 5.0, 0), // Emerald Block
                new ItemLootBox("minecraft:redstone_block", 10, 5.0, 0), // Redstone Block
                new ItemLootBox("minecraft:quartz_block", 10, 5.0, 0), // Quartz Block
                new ItemLootBox("minecraft:coal_block", 10, 5.0, 0), // Coal Block
                new ItemLootBox("thermalfoundation:storage", 10, 5.0, 0), // Copper Block
                new ItemLootBox("thermalfoundation:storage", 10, 5.0, 1), // Tin Block
                new ItemLootBox("thermalfoundation:storage", 10, 5.0, 2), // Silver Block
                new ItemLootBox("thermalfoundation:storage", 10, 5.0, 3), // Lead Block
                new ItemLootBox("thermalfoundation:storage", 10, 5.0, 4), // Alumnium Block
                new ItemLootBox("thermalfoundation:storage", 10, 5.0, 5), // Nickel Block
                new ItemLootBox("thermalfoundation:storage", 10, 5.0, 8), // Mana Infused Metal Block
                new ItemLootBox("thermalfoundation:storage_alloy", 10, 5.0, 0), // Steel Block
                new ItemLootBox("thermalfoundation:storage_alloy", 10, 5.0, 2), // Invar Block
                new ItemLootBox("thermalfoundation:storage_alloy", 10, 5.0, 1), // Electrum Block
                new ItemLootBox("thermalfoundation:storage_alloy", 10, 5.0, 3), // Bronze Block
                new ItemLootBox("thermalfoundation:storage_alloy", 10, 5.0, 4), // Constantan Block
                new ItemLootBox("thermalfoundation:storage_alloy", 10, 5.0, 5), // Signalium Block
                new ItemLootBox("thermalfoundation:storage_alloy", 10, 5.0, 6), // Lumium Block
                new ItemLootBox("thermalfoundation:storage_resource", 10, 5.0, 0), // Charcoal Block
                new ItemLootBox("thermalfoundation:storage_resource", 10, 5.0, 1), // Coal Cock Block
                new ItemLootBox("bewitchment:block_of_garnet", 10, 5.0, 0), // Garnet Block
                new ItemLootBox("bewitchment:block_of_opal", 10, 5.0, 0), // Opal Block
                new ItemLootBox("bewitchment:block_of_cold_iron", 10, 5.0, 0), // Cold Iron Block
                new ItemLootBox("bewitchment:block_of_salt", 10, 5.0, 0), // Salt Block
                new ItemLootBox("biomesoplenty:crystal", 10, 5.0, 0), // Celestial Crystal Block
                new ItemLootBox("biomesoplenty:gem_block", 10, 5.0, 0), // End Amethyst Block
                new ItemLootBox("biomesoplenty:gem_block", 10, 5.0, 1), // Ruby Block
                new ItemLootBox("biomesoplenty:gem_block", 10, 5.0, 2), // Peridot Block
                new ItemLootBox("biomesoplenty:gem_block", 10, 5.0, 3), // Topaz Block
                new ItemLootBox("biomesoplenty:gem_block", 10, 5.0, 4), // Tanzanite Block
                new ItemLootBox("biomesoplenty:gem_block", 10, 5.0, 5), // Malachite Block
                new ItemLootBox("biomesoplenty:gem_block", 10, 5.0, 6), // Sapphire Block
                new ItemLootBox("biomesoplenty:gem_block", 10, 5.0, 7), // Amber Block
                new ItemLootBox("ic2:resource", 10, 5.0, 10), // Uranium Block
                new ItemLootBox("enderio:block_alloy", 10, 5.0, 0), // Electrical Steel Block
                new ItemLootBox("enderio:block_alloy", 10, 5.0, 1), // Energetic Alloy Block
                new ItemLootBox("enderio:block_alloy", 10, 5.0, 3), // Redstone Alloy Block
                new ItemLootBox("enderio:block_alloy", 10, 5.0, 4), // Conductive Iron Block
                new ItemLootBox("enderio:block_alloy", 10, 5.0, 5), // Pulsating Iron Block
                new ItemLootBox("enderio:block_alloy", 10, 5.0, 6), // Dark Steel Block
                new ItemLootBox("enderio:block_alloy", 10, 5.0, 7), // Soularium Block
                new ItemLootBox("enderio:block_alloy", 10, 5.0, 9), // Iron Alloy Block
                new ItemLootBox("twilightforest:block_storage", 10, 5.0, 0), // Ironwood Block
                new ItemLootBox("twilightforest:block_storage", 10, 5.0, 2), // Steeleaf Block
                new ItemLootBox("twilightforest:block_storage", 10, 5.0, 3), // Arctic Fur Block
                new ItemLootBox("twilightforest:block_storage", 10, 5.0, 4), // Carminite Block
                new ItemLootBox("tconstruct:metal", 10, 5.0, 3), // Knightslime Block
                new ItemLootBox("tconstruct:metal", 10, 5.0, 4), // Pigiron Block
                new ItemLootBox("tconstruct:metal", 10, 5.0, 5), // Alumnium Brass Block
                new ItemLootBox("tconstruct:metal", 10, 5.0, 6), // Silky Jewel Block
                new ItemLootBox("appliedenergistics2:quartz_block", 10, 5.0, 0), // Certus Quartz Block
                new ItemLootBox("appliedenergistics2:fluix_block", 10, 5.0, 0), // Fluix Block
                new ItemLootBox("botania:storage", 10, 5.0, 0), // Manasteel Block
                new ItemLootBox("galacticraftcore:basic_block_core", 10, 5.0, 13), // Silicon Block
                new ItemLootBox("growthcraft:salt_block", 10, 5.0, 0), // Rock Salt Block
                new ItemLootBox("mysticalagriculture:ingot_storage", 10, 5.0, 0), // Base Essence Block
                new ItemLootBox("mysticalagriculture:ingot_storage", 10, 5.0, 1), // Inferium Block
                new ItemLootBox("mysticalagriculture:ingot_storage", 10, 5.0, 2), // Prudentium Block
                new ItemLootBox("mysticalagriculture:ingot_storage", 10, 5.0, 3), // Intermedium Block
                new ItemLootBox("mysticalagriculture:ingot_storage", 10, 5.0, 6), // Solium Block
                new ItemLootBox("embers:block_dawnstone", 10, 5.0, 0), // Dawnstone Block
                new ItemLootBox("materialis:fairy_block", 10, 5.0, 0), // Fairy Block
                new ItemLootBox("materialis:pokefennium_block", 10, 5.0, 0), // Pokefennium Block
                new ItemLootBox("materialis:red_aurum_block", 10, 5.0, 0), // Red Aurum Block
                new ItemLootBox("materialis:drulloy_block", 10, 5.0, 0), // Drulloy Block
                new ItemLootBox("materialis:alumite_block", 10, 5.0, 0), // Alumite Block
                new ItemLootBox("aether_legacy:zanite_block", 10, 5.0, 0) // Zanite Block
        ));

        LOOT_MAP.put(1, Arrays.asList(
                new ItemLootBox("infinite_revolution:infinite_lootbox", 1, 0.1, 0), // 1 Infinity LootBox
                new ItemLootBox("avaritia:block_resource", 1, 0.1, 1), // 1 Infinite Block
                new ItemLootBox("avaritiaddons:avaritiaddons_chest", 1, 0.1, 1), // 1 Infinity Chest
                new ItemLootBox("avaritia:resource", 4, 0.2, 6), // 4 Infinity Ingot
                new ItemLootBox("avaritia:resource", 2, 0.8, 5), // 2 Infinity Catalyst
                new ItemLootBox("eternalsingularity:eternal_singularity", 1, 0.9, 0), // 1 Eternal Singularity
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
                new ItemLootBox("avaritia:block_resource", 4, 5.0, 0), // 4 Neutronium Block
                new ItemLootBox("avaritia:resource", 9, 10.0, 4), // 9 Neutronium Ingots
                new ItemLootBox("avaritia:block_resource", 2, 11.0, 2), // 2 Crystal Matrix Ingot
                new ItemLootBox("avaritia:endest_pearl", 4, 12.0, 0), // 4 Endest Pearl
                new ItemLootBox("avaritia:ultimate_stew", 16, 13.0, 0), // 16 Ultimate Stew
                new ItemLootBox("avaritia:neutron_collector", 1, 13.0, 0), // 1 Neutronium Collector
                new ItemLootBox("avaritia:resource", 64, 14.0, 2) // 64 Pile of Neutrons
        ));

        LOOT_MAP.put(2, Arrays.asList(
                new ItemLootBox("extrautils2:creativechest", 1, 0.0001, 0), // 1 Creative Chest
                new ItemLootBox("draconicevolution:creative_rf_source", 1, 0.01, 0), // 1 Creative RF Source
                new ItemLootBox("draconicevolution:dislocator_advanced", 1, 0.02, 0), // 1 Advanced Dislocator
                new ItemLootBox("avaritia:block_resource", 4, 0.1, 1), // 4 Infinity Blocks
                new ItemLootBox("avaritia:infinity_chestplate", 1, 0.2, 0), // 1 Infinity Breadsplate
                new ItemLootBox("avaritia:infinity_pants", 1, 0.3, 0), // 1 Infinity Leggings
                new ItemLootBox("avaritia:infinity_helmet", 1, 0.4, 0), // 1 Infinity Helmet
                new ItemLootBox("avaritia:infinity_boots", 1, 0.4, 0), // 1 Infinity Boots
                new ItemLootBox("avaritia:infinity_sword", 1, 0.5, 0), // 1 Sword Of Cosmos
                new ItemLootBox("avaritia:infinity_pickaxe", 1, 0.5, 0), // 1 World Breaker
                new ItemLootBox("avaritia:infinity_shovel", 1, 0.5, 0), // 1 Planet Eater
                new ItemLootBox("avaritia:infinity_axe", 1, 0.5, 0), // 1 Nature's Ruin
                new ItemLootBox("avaritia:infinity_bow", 1, 0.6, 0), // 1 Longbow Of the Heavens
                new ItemLootBox("sgcraft:stargatebase", 1, 0.8, 0), // 1 Stargate Base
                new ItemLootBox("galacticraftcore:infinite_battery", 1, 0.8, 0), // 1 Infinity Battery
                new ItemLootBox("avaritiaddons:infinity_compressor", 1, 1.0, 0), // 1 Infinity Compressor
                new ItemLootBox("avaritia:resource", 5, 1.1, 5), // 5 Infinity Catalyst
                new ItemLootBox("eternalsingularity:eternal_singularity", 8, 1.2, 0), // 8 Eternal Singularity
                new ItemLootBox("galacticraftcore:infinite_oxygen", 1, 1.5, 0), // 1 Infinite Oxygen Supply
                new ItemLootBox("extrautils2:drum", 1, 1.5, 4), // 1 Creative Drum
                new ItemLootBox("liquid:evilcraftblood", 1, 1.5, 0), // 1 Creative Blood Drop
                new ItemLootBox("refinedstorage:storage_disk", 1, 1.5, 4), // 1 Creative Storage Disk
                new ItemLootBox("sgcraft:stargatering", 4, 2.0, 0), // 4 Stargate Ring Block
                new ItemLootBox("draconicevolution:draconic_staff_of_power", 1, 2.1, 0), // 1 Draconic Staff Of Power
                new ItemLootBox("draconicevolution:draconic_chest", 1, 3.2, 0), // 1 Draconic Chestplate
                new ItemLootBox("draconicevolution:draconic_legs", 1, 3.3, 0), // 1 Draconic Leggings
                new ItemLootBox("draconicevolution:draconic_helm", 1, 3.4, 0), // 1 Draconic Helmet
                new ItemLootBox("draconicevolution:draconic_boots", 1, 3.4, 0), // 1 Draconic Boots
                new ItemLootBox("draconicevolution:draconic_sword", 1, 3.5, 0), // 1 Draconic Sword
                new ItemLootBox("draconicevolution:draconic_pick", 1, 3.5, 0), // 1 Draconic Pickaxe
                new ItemLootBox("draconicevolution:draconic_bow", 1, 3.5, 0), // 1 Draconic Bow
                new ItemLootBox("draconicevolution:draconic_axe", 1, 3.5, 0), // 1 Draconic Axe
                new ItemLootBox("draconicevolution:draconic_shovel", 1, 3.5, 0), // 1 Draconic Shovel
                new ItemLootBox("sgcraft:ic2capacitor", 16, 5.0, 0), // 16 Ridiculously Large Circuit
                new ItemLootBox("avaritia:block_resource", 16, 5.0, 0), // 16 Neutronium Block
                new ItemLootBox("draconicevolution:chaos_shard", 24, 5.0, 0), // 24 Chaos Shards
                new ItemLootBox("avaritia:block_resource", 64, 5.0, 2), // 64 Crystal Matrix
                new ItemLootBox("infinite_revolution:unbreakable_bedrock_pickaxe", 1, 5.0, 0), // 1 Bedrock Pickaxe
                new ItemLootBox("minecraft:bedrock", 64, 5.0, 0) // 64 Bedrock
        ));
    }

    private final List<ItemLootBox> lootList;
    private final int tier;

    public InfiniteRevolutionLootBox(String name, CreativeTabs tab, int tier) {
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(tab);

        if (!LOOT_MAP.containsKey(tier)) {
            throw new IllegalArgumentException("Invalid tier: " + tier);
        }

        this.lootList = LOOT_MAP.get(tier);
        this.tier = tier;

        ItemInit.ITEMS.add(this);
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
                throw new IllegalArgumentException();
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
                throw new IllegalArgumentException();
        }

        tooltip.add(color + "" + TextFormatting.ITALIC + "Tier " + seeTier);
    }

    @Override
    public void registerModels() {
        InfiniteRevolution.proxy.registerItemRenderer(this, 0, "inventory");
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (LootBoxKey.hasCorrectKey(playerIn, this.tier)) {
            if (!worldIn.isRemote) {

                ItemStack keyStack = LootBoxKey.getCorrectKeyStack(playerIn, this.tier);
                if (!playerIn.isCreative() && keyStack != null) {
                    keyStack.shrink(1);
                }

                ItemLootBox itemLootBox = getRandomItemStack();
                ItemStack randomItemStack = new ItemStack(getItemFromNameRegistry(itemLootBox.item), itemLootBox.amount, itemLootBox.meta);

                if (!playerIn.inventory.addItemStackToInventory(randomItemStack.copy())) {
                    playerIn.dropItem(randomItemStack.copy(), false);
                }

                playerIn.sendMessage(new TextComponentString(getReceiveMessage(this.tier)));
            }

            ItemStack stack = playerIn.getHeldItem(handIn);
            if (!playerIn.isCreative()) {
                stack.shrink(1);
            }
        }
        else {
            if (!worldIn.isRemote) {
                playerIn.sendMessage(new TextComponentString(TextFormatting.RED + "You need a key of the same tier to open this loot box!"));
            }
        }

        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    public String getReceiveMessage(int tier) {
        switch (tier) {
            case 0:
                return TextFormatting.WHITE + "" + TextFormatting.ITALIC + "You received a interstellar random item from a Remote Planet!";
            case 1:
                return TextFormatting.BLACK + "" + TextFormatting.ITALIC + "You received a cosmic random item from a Neutron Star!";
            case 2:
                return TextUtils.makeFabulous("You received a trascendental random item from Universe!", TextFormatting.BOLD);
            default:
                throw new IllegalArgumentException();
        }
    }

    private ItemLootBox getRandomItemStack() {
        double totalProbability = lootList.stream().mapToDouble(item -> item.probability).sum();
        double randomValue = new Random().nextDouble() * totalProbability;

        for (ItemLootBox item : lootList) {
            randomValue -= item.probability;
            if (randomValue <= 0.0) {
                return item;
            }
        }

        return lootList.get(lootList.size() - 1);
    }

    public static Item getItemFromNameRegistry(String name) {
        return ForgeRegistries.ITEMS.getValue(new ResourceLocation(name));
    }

    private static class ItemLootBox {
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