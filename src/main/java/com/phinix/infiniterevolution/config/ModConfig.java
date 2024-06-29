package com.phinix.infiniterevolution.config;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.LoaderExceptionModCrash;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ModConfig {

    private static Configuration config;

    public static boolean generateInfinityCatalystOre = true;
    public static int infinityCatalystDropChance = 20;

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

        } catch (Exception e) {
            throw new LoaderExceptionModCrash("Infinite Revolution Config Doesn't Work", new Throwable());
        } finally {
            if (config.hasChanged()) {
                config.save();
            }
        }
    }
}