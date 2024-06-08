package com.phinix.infiniterevolution.event;

import com.phinix.infiniterevolution.objects.items.BedrockPickaxe;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class BedrockPickaxeEventHandler {
    public static final float NEW_UNBREAKABLE_BLOCK_HARDNESS = 500.0f;
    @SubscribeEvent
    public static void onPlayerMineBlock(PlayerInteractEvent.LeftClickBlock event) {
        World world = event.getWorld();
        BlockPos pos = event.getPos();
        IBlockState state = world.getBlockState(pos);
        Block block = state.getBlock();
        EntityPlayer player = event.getEntityPlayer();
        ItemStack heldItem = player.getHeldItemMainhand();

        if (heldItem.getItem() instanceof BedrockPickaxe) {
            if (state.getBlockHardness(world, pos) == -1.0f) {
                block.setHardness(NEW_UNBREAKABLE_BLOCK_HARDNESS);
            }
        } else if (state.getBlockHardness(world, pos) == NEW_UNBREAKABLE_BLOCK_HARDNESS) {
            block.setHardness(-1.0f);
        }
    }

    @SubscribeEvent
    public static void onHarvestDrops(BlockEvent.HarvestDropsEvent event) {
        World world = event.getWorld();
        BlockPos pos = event.getPos();
        IBlockState state = event.getState();
        EntityPlayer player = event.getHarvester();
        Block block = state.getBlock();

        if (state.getBlockHardness(world, pos) == NEW_UNBREAKABLE_BLOCK_HARDNESS || state.getBlockHardness(world, pos) == -1.0f &&
                player != null && player.getHeldItemMainhand().getItem() instanceof BedrockPickaxe) {

            if (block == Blocks.BEDROCK || block == Blocks.END_PORTAL_FRAME) {
                Item item = Item.getItemFromBlock(block);
                int metadata = block.getMetaFromState(state);
                ItemStack drop = new ItemStack(item, 1, metadata);

                event.getDrops().add(drop);
            }
        }
    }
}