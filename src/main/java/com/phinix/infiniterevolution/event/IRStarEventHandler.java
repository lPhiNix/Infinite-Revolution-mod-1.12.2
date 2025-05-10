package com.phinix.infiniterevolution.event;

import com.phinix.infiniterevolution.effect.NightVisionNoParticles;
import com.phinix.infiniterevolution.objects.items.InfiniteRevolutionStar;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import com.phinix.infiniterevolution.init.ItemInit;

import java.util.HashSet;
import java.util.Set;

import static com.phinix.infiniterevolution.objects.items.InfiniteRevolutionStar.*;

@Mod.EventBusSubscriber
public class IRStarEventHandler {
    public static final Set<String> entitiesWithIRStar = new HashSet<>();
    public static final Set<String> entitiesWithIRStarActive = new HashSet<>();
    public static final Set<String> entitiesWithFlight = new HashSet<>();

    //Infinite Defense
    @SubscribeEvent
    public static void onLivingHurt(LivingAttackEvent event) {
        if (event.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.getEntity();
            if (hasIRStar(player)) {
                event.setCanceled(true);
            }
        }
    }

    //Infinite Attack
    @SubscribeEvent
    public void onGetHurt(LivingHurtEvent event) {
        if (!(event.getEntityLiving() instanceof EntityPlayer)) {
            return;
        }
        EntityPlayer player = (EntityPlayer) event.getEntityLiving();
        if (!player.getHeldItemMainhand().isEmpty() && player.getHeldItemMainhand().getItem() == ItemInit.IR_STAR && player.isHandActive()) {
            event.setCanceled(true);
        }
        if (hasActiveIRStar(player) && !event.getSource().damageType.equals("infinity")) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void updateAbilities(LivingEvent.LivingUpdateEvent event) {
        if (!(event.getEntity() instanceof EntityPlayer)) {
            return;
        }

        EntityPlayer player = (EntityPlayer) event.getEntityLiving();
        String key = player.getCachedUniqueIdString() + "|" + player.world.isRemote;

        boolean hadIRStar = hasIRStar(player);
        boolean hadActiveIRStar = hasActiveIRStar(player);

        if (hadIRStar) {
            entitiesWithIRStar.add(key);
            handleIRStarStateChange(player, true);
        }

        if (!hadIRStar) {
            entitiesWithIRStar.remove(key);
            handleIRStarStateChange(player, false);
        }

        if (hadActiveIRStar) {
            entitiesWithIRStarActive.add(key);
            handleIRStarActiveStateChange(player, true);
        }

        if (!hadActiveIRStar) {
            entitiesWithIRStarActive.remove(key);
            handleIRStarActiveStateChange(player, false);
        }

        if (entitiesWithIRStarActive.contains(key)) {
            newSpeed(player);
        }
    }

    private static void handleIRStarStateChange(EntityPlayer player, boolean isNew) {
        String key = player.getCachedUniqueIdString() + "|" + player.world.isRemote;
        boolean isCreativeOrSpector = player.capabilities.isCreativeMode || player.isSpectator();

        if (isNew) {
            player.setAir(300);
            player.getFoodStats().addStats(20, 20f);
            player.extinguish();

            player.capabilities.allowFlying = true;

            entitiesWithFlight.add(key);
        } else {
            if (!isCreativeOrSpector && entitiesWithFlight.contains(key)) {

                player.capabilities.allowFlying = false;
                player.capabilities.isFlying = false;

                player.sendPlayerAbilities();

                entitiesWithFlight.remove(key);
            }
        }
    }

    private static void handleIRStarActiveStateChange(EntityPlayer player, boolean isNew) {
        PotionEffect nightVision = new NightVisionNoParticles(322, 0);

        if (isNew) {
            player.addPotionEffect(nightVision);
        } else if (hasIRStar(player)) {
            player.removePotionEffect(nightVision.getPotion());
        }
    }

    private static void newSpeed(EntityPlayer player) {
        boolean flying = player.capabilities.isFlying;
        boolean swimming = player.isInsideOfMaterial(Material.WATER) || player.isInWater();
        if (player.onGround || flying || swimming) {
            boolean sneaking = player.isSneaking();

            float speed = 0.15f * (flying ? 1.1f : 1.0f)
                    //* (swimming ? 1.2f : 1.0f)
                    * (sneaking ? 0.1f : 1.0f);

            if (player.moveForward > 0f) {
                player.moveRelative(0f, 0f, 1f, speed);
            } else if (player.moveForward < 0f) {
                player.moveRelative(0f, 0f, 1f, -speed * 0.3f);
            }

            if (player.moveStrafing != 0f) {
                player.moveRelative(1f, 0f, 0f, speed * 0.5f * Math.signum(player.moveStrafing));
            }
        }
    }

    @SubscribeEvent
    public static void jumpBoost(LivingEvent.LivingJumpEvent event) {
        EntityLivingBase entity = event.getEntityLiving();
        if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entity;
            if (entitiesWithIRStarActive.contains(player.getCachedUniqueIdString() + "|" + player.world.isRemote)) {
                entity.motionY += 0.4f;
            }
        }
    }

    public static boolean hasIRStar(EntityPlayer player) {
        for (ItemStack stack : player.inventory.mainInventory) {
            if (!stack.isEmpty() && stack.getItem() == ItemInit.IR_STAR) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasActiveIRStar(EntityPlayer player) {
        for (ItemStack stack : player.inventory.mainInventory) {
            if (!stack.isEmpty() && stack.getItem() == ItemInit.IR_STAR && isActive(stack)) {
                return true;
            }
        }
        return false;
    }

    @SubscribeEvent
    public static void onPlayerLeftClick(PlayerInteractEvent.LeftClickBlock event) {
        if (hasActiveIRStar(event.getEntityPlayer())) {
            if (!event.getEntityPlayer().isSpectator() && !event.getEntityPlayer().capabilities.isCreativeMode) {
                ItemStack stack = event.getItemStack();
                if (!stack.isEmpty() && stack.getItem() instanceof InfiniteRevolutionStar) {
                    IBlockState state = event.getWorld().getBlockState(event.getPos());
                    Block block = state.getBlock();
                    World world = event.getWorld();
                    BlockPos pos = event.getPos();
                    if (state.getBlockHardness(world, pos) == -1.0f) {
                        EntityPlayer player = event.getEntityPlayer();

                        if (world.isAirBlock(pos)) return;
                        if (!world.isRemote) {
                            world.destroyBlock(pos, true);
                            if (block == Blocks.BEDROCK || block == Blocks.END_PORTAL_FRAME) {
                                ItemStack bedrockStack = new ItemStack(block);
                                EntityItem entityItem = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), bedrockStack);
                                world.spawnEntity(entityItem);
                            }
                            event.setCanceled(true);
                        }
                    }
                }
            }
        }
    }
}
