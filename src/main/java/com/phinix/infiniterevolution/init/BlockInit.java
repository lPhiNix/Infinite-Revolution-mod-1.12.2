package com.phinix.infiniterevolution.init;

import com.phinix.infiniterevolution.InfiniteRevolution;
import com.phinix.infiniterevolution.objects.blocks.BlockBase;
import com.phinix.infiniterevolution.objects.blocks.InfinityCatalystOre;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import java.util.ArrayList;
import java.util.List;

public class BlockInit {
    public static final List<Block> BLOCKS = new ArrayList<Block>();

    public static final Block TRINIUM_ORE = new BlockBase("trinium_ore", Material.ROCK, InfiniteRevolution.infiniteRevolutionTab, 6.0f, 5.0f, "pickaxe", 4);
    public static final Block TRINIUM_BLOCK = new BlockBase("trinium_block", Material.IRON, InfiniteRevolution.infiniteRevolutionTab, 7.0f, 10.0f, "pickaxe", 4);
    public static final Block INFINITY_CATALYST_ORE = new InfinityCatalystOre("infinity_catalyst_ore", Material.ROCK, InfiniteRevolution.infiniteRevolutionTab, -1, 100.0f, "pickaxe", 600);

    //public static final Block CYCLOTRON_CONTROLLER = new BlockBase("cyclotron_controller", Material.IRON, Main.infiniteRevolutionTab, 10.0f, 10.0f, "pickaxe", 5);
    //public static final Block CYCLOTRON_CONTROLLER_ACTIVE = new BlockBase("cyclotron_controller_active", Material.IRON, Main.infiniteRevolutionTab, 10.0f, 10.0f, "pickaxe", 5);
    //public static final Block QUANTUM_COIL = new BlockBase("quantum_coil", Material.IRON, Main.infiniteRevolutionTab, 2.0f, 50.0f, "pickaxe", 0);
    //public static final Block CYCLOTRON_PRESSURE_CASING = new BlockBase("cyclotron_pressure_casing", Material.IRON, Main.infiniteRevolutionTab, 10.0f, 10.0f, "pickaxe", 3);
    //public static final Block CYCLOTRON_FLUID_PORT = new BlockBase("cyclotron_fluid_port", Material.IRON, Main.infiniteRevolutionTab, 10.0f, 10.0f, "pickaxe", 3);
    //public static final Block CYCLOTRON_ENERGY_PORT = new BlockBase("cyclotron_energy_port", Material.IRON, Main.infiniteRevolutionTab, 10.0f, 10.0f, "pickaxe", 3);
    //public static final Block CYCLOTRON_INPUT_PORT = new BlockBase("cyclotron_input_port", Material.IRON, Main.infiniteRevolutionTab, 10.0f, 10.0f, "pickaxe", 3);
    //public static final Block CYCLOTRON_OUTPUT_PORT = new BlockBase("cyclotron_output_port", Material.IRON, Main.infiniteRevolutionTab, 10.0f, 10.0f, "pickaxe", 3);
}