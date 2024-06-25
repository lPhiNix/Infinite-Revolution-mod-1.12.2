package com.phinix.infiniterevolution.init;
import com.phinix.infiniterevolution.InfiniteRevolution;
import com.phinix.infiniterevolution.objects.items.*;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemInit {
    public static final List<Item> ITEMS = new ArrayList<Item>();

    //SPECIAL
    public static final Item IR_STAR = new InfiniteRevolutionStar("ir_star", InfiniteRevolution.infiniteRevolutionTab);
    public static final Item GIGA_CHAD_TOKEN = new GigaChadToken("giga_chad_token", InfiniteRevolution.infiniteRevolutionTab);
    public static final Item NOT_NOOB_CERTIFICATE = new NotNoobCertificate("not_noob_certificate", InfiniteRevolution.infiniteRevolutionTab);
    public static final Item UNBREAKABLE_BEDROCK_PICKAXE = new BedrockPickaxe("unbreakable_bedrock_pickaxe", InfiniteRevolution.infiniteRevolutionTab);

    //LOOTBOXES
    public static final Item TRINIUM_LOOTBOX = new InfiniteRevolutionLootBox("trinium_lootbox", InfiniteRevolution.infiniteRevolutionTab, 0);
    public static final Item NEUTRONIUM_LOOTBOX = new InfiniteRevolutionLootBox("neutronium_lootbox", InfiniteRevolution.infiniteRevolutionTab, 1);
    public static final Item INFINITE_LOOTBOX = new InfiniteRevolutionLootBox("infinite_lootbox", InfiniteRevolution.infiniteRevolutionTab, 2);

    //KEYS
    public static final Item TRINIUM_KEY = new LootBoxKey("trinium_key", InfiniteRevolution.infiniteRevolutionTab, 0);
    public static final Item NEUTRONIUM_KEY = new LootBoxKey("neutronium_key", InfiniteRevolution.infiniteRevolutionTab, 1);
    public static final Item INFINITE_KEY = new LootBoxKey("infinite_key", InfiniteRevolution.infiniteRevolutionTab, 2);

    //TRINIUM
    public static final Item TRINIUM_INGOT = new ItemBase("trinium_ingot", InfiniteRevolution.infiniteRevolutionTab);
    public static final Item TRINIUM_DUST = new ItemBase("trinium_dust", InfiniteRevolution.infiniteRevolutionTab);
    public static final Item TRINIUM_PLATE = new ItemBase("trinium_plate", InfiniteRevolution.infiniteRevolutionTab);
    public static final Item DENSE_TRINIUM_PLATE = new ItemBase("dense_trinium_plate", InfiniteRevolution.infiniteRevolutionTab);
}
