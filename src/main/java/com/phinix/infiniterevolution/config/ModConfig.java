package com.phinix.infiniterevolution.config;

import com.phinix.infiniterevolution.objects.items.InfiniteRevolutionLootBox.ItemLootBox;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.LoaderExceptionModCrash;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModConfig {

    private static Configuration config;

    public static boolean generateInfinityCatalystOre = true;
    public static int infinityCatalystDropChance = 20;
    public static double lootboxFortuneWeight = 0.3;
    public static Map<Integer, List<ItemLootBox>> lootMap = new HashMap<>();

    public static void init(FMLPreInitializationEvent event) {
        config = new Configuration(event.getSuggestedConfigurationFile());
        syncConfig();
    }

    public static void syncConfig() {
        try {
            config.load();

            generateInfinityCatalystOre = config.getBoolean("generateInfinityCatalystOre", "generation", true,
                    "Enable generation of Infinity Catalyst Ore");

            infinityCatalystDropChance = config.getInt("infinityCatalystDropChance", "generation", 1, 1, 100,
                    "Probability (1/n) of dropping Infinity Catalyst Dust when mining Infinity Catalyst Ore");

            lootboxFortuneWeight = config.get("lootbox", "lootboxFortuneWeight", 0.3,
                    "Weight factor for loot box fortune calculation").getDouble(0.3);

            loadLootConfig();

        } catch (Exception e) {
            throw new LoaderExceptionModCrash("Infinite Revolution Config Doesn't Work", new Throwable());
        } finally {
            if (config.hasChanged()) {
                config.save();
            }
        }
    }

    private static void loadLootConfig() {
        for (int tier = 0; tier <= 2; tier++) {
            String[] items = config.getStringList("items", "tier" + tier, new String[]{},
                    "Define the items for loot box tier " + tier + ". Format: item_name,amount,probability,meta");

            List<ItemLootBox> lootList = new ArrayList<>();
            for (String item : items) {
                String[] parts = item.split(",");
                if (parts.length == 4) {
                    String itemName = parts[0];
                    int amount = Integer.parseInt(parts[1]);
                    double probability = Double.parseDouble(parts[2]);
                    int meta = Integer.parseInt(parts[3]);

                    lootList.add(new ItemLootBox(itemName, amount, probability, meta));
                }
            }
            lootMap.put(tier, lootList);
        }
    }
}
