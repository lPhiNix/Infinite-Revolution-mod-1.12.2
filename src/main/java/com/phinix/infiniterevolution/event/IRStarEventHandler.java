package com.phinix.infiniterevolution.event;

import com.phinix.infiniterevolution.effect.NightVisionNoParticles;
import com.phinix.infiniterevolution.objects.items.InfiniteRevolutionStar;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import com.phinix.infiniterevolution.init.ItemInit;

import static com.phinix.infiniterevolution.objects.items.InfiniteRevolutionStar.isActive;

@Mod.EventBusSubscriber
public class IRStarEventHandler {

    private static int onPlayerTickCount = 0;

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onLivingHurt(LivingAttackEvent event) {
        if (event.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.getEntity();
            if (hasIRStar(player)) {
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onLivingAttack(LivingAttackEvent event) {
        Entity sourceEntity = event.getSource().getTrueSource();
        if (sourceEntity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) sourceEntity;
            if (hasActiveIRStar(player)) {
                event.getEntityLiving().attackEntityFrom(DamageSource.GENERIC, Float.MAX_VALUE);
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onBlockBreakSpeed(PlayerEvent.BreakSpeed event) {
        EntityPlayer player = event.getEntityPlayer();
        if (player != null) {
            ItemStack heldItem = player.getHeldItemMainhand();
            if (!heldItem.isEmpty() && heldItem.getItem() == ItemInit.IR_STAR && isActive(heldItem)) {
                event.setNewSpeed(Float.MAX_VALUE);
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(PlayerEvent.LivingUpdateEvent event) {
        if (!(event.getEntityLiving() instanceof EntityPlayer)) {
            return;
        }

        EntityPlayer player = (EntityPlayer) event.getEntityLiving();

        if (hasActiveIRStar(player)) {
            player.setAir(300);
            player.getFoodStats().addStats(20, 20F);
            player.extinguish();
            player.capabilities.allowFlying = true;

            player.capabilities.setPlayerWalkSpeed(0.3f);
            player.capabilities.setFlySpeed(0.15f);

            PotionEffect nightVisionNoParticles = new NightVisionNoParticles(150, 0);
            player.addPotionEffect(nightVisionNoParticles);

            onPlayerTickCount = 0;
        } else if (hasIRStar(player)) {
            player.setAir(300);
            player.getFoodStats().addStats(20, 20F);
            player.extinguish();
            player.capabilities.allowFlying = true;

            player.capabilities.setPlayerWalkSpeed(0.1f);
            player.capabilities.setFlySpeed(0.05f);

            onPlayerTickCount = 0;
        } else {
            if (!player.capabilities.isCreativeMode && !player.isSpectator() && onPlayerTickCount == 0) {
                player.capabilities.allowFlying = false;
                player.capabilities.isFlying = false;
                onPlayerTickCount = 1;
            }

            player.capabilities.setPlayerWalkSpeed(0.1f);
            player.capabilities.setFlySpeed(0.05f);
        }
    }

    @SubscribeEvent
    public static void onPlayerLeftClick(PlayerInteractEvent.LeftClickBlock event) {
        if (!event.getEntityPlayer().isSpectator() && !event.getEntityPlayer().capabilities.isCreativeMode) {
            ItemStack stack = event.getItemStack();
            if (!stack.isEmpty() && stack.getItem() instanceof InfiniteRevolutionStar) {
                IBlockState state = event.getWorld().getBlockState(event.getPos());
                if (isUnbreakableBlock(state.getBlock())) {
                    World world = event.getWorld();
                    EntityPlayer player = event.getEntityPlayer();
                    BlockPos pos = event.getPos();

                    if (world.isAirBlock(pos)) return;
                    if (!world.isRemote) {
                        Block block = state.getBlock();
                        world.destroyBlock(pos, true);
                        ItemStack bedrockStack = new ItemStack(block);
                        EntityItem entityItem = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), bedrockStack);
                        world.spawnEntity(entityItem);
                    }
                    event.setCanceled(true);
                }
            }
        }
    }

    private static boolean isUnbreakableBlock(Block block) {
        return block.getBlockHardness(null, null, null) == -1.0f;
    }

    private static boolean hasIRStar(EntityPlayer player) {
        for (ItemStack stack : player.inventory.mainInventory) {
            if (!stack.isEmpty() && stack.getItem() == ItemInit.IR_STAR) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasActiveIRStar(EntityPlayer player) {
        for (ItemStack stack : player.inventory.mainInventory) {
            if (!stack.isEmpty() && stack.getItem() == ItemInit.IR_STAR && isActive(stack)) {
                return true;
            }
        }
        return false;
    }
}
