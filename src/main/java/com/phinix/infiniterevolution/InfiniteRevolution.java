package com.phinix.infiniterevolution;

import com.phinix.infiniterevolution.proxy.CommonProxy;
import com.phinix.infiniterevolution.tabs.IRTab;
import com.phinix.infiniterevolution.util.Reference;
import com.phinix.infiniterevolution.world.gen.WorldGenOres;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = InfiniteRevolution.MODID,
        name = InfiniteRevolution.NAME,
        version = InfiniteRevolution.VERSION,
        dependencies = InfiniteRevolution.DEPENDENCIES
)

public class InfiniteRevolution {
    public static final String MODID = "infinite_revolution";
    public static final String NAME = "Infinite Revolution";
    public static final String VERSION = "1.2";
    public static final String DEPENDENCIES = "required-after:galacticraftcore@[4.0.2.255,)";

    public static Logger logger = LogManager.getLogger(MODID);

    @Instance
    public static InfiniteRevolution instance;

    @SidedProxy(clientSide = Reference.CLIENT, serverSide = Reference.COMMON)
    public static CommonProxy proxy;

    public static final CreativeTabs infiniteRevolutionTab = new IRTab("infiniteRevolutionTab");

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger.info("Pre-initialization of Infinite Revolution Mod");
        GameRegistry.registerWorldGenerator(new WorldGenOres(), 3);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        logger.info("Initialization of Infinite Revolution Mod");
    }

    @EventHandler
    public static void postInit(FMLPostInitializationEvent event) {
        logger.info("Post-initialization of Infinite Revolution Mod");
    }
}
