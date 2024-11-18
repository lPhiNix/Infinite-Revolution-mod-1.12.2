package com.phinix.infiniterevolution.objects.items;

import com.phinix.infiniterevolution.InfiniteRevolution;
import com.phinix.infiniterevolution.init.ItemInit;
import com.phinix.infiniterevolution.util.IHasModel;
import com.phinix.infiniterevolution.util.TextUtils;
import ic2.core.item.tool.HarvestLevel;
import javafx.scene.layout.FlowPane;
import micdoodle8.mods.galacticraft.core.energy.item.ItemElectricBase;
import micdoodle8.mods.galacticraft.core.proxy.ClientProxyCore;
import morph.avaritia.handler.AvaritiaEventHandler;
import morph.avaritia.util.DamageSourceInfinitySword;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
public class InfiniteRevolutionStar extends ItemElectricBase implements IHasModel {
    public static final float AMPERAGE = Float.MAX_VALUE;
    public InfiniteRevolutionStar(String name, CreativeTabs tab) {
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(tab);

        setMaxStackSize(1);
        setHarvestLevel("pickaxe", 999);

        ItemInit.ITEMS.add(this);
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase victim, EntityLivingBase player) {
        if (player.world.isRemote) {
            return true;
        }
        if (isActive(stack)) {
            if (victim instanceof EntityPlayer) {
                EntityPlayer pvp = (EntityPlayer) victim;
                if (AvaritiaEventHandler.isInfinite(pvp)) {
                    victim.attackEntityFrom(new DamageSourceInfinitySword(player).setDamageBypassesArmor(), 4.0F);
                    return true;
                }
                if (pvp.getHeldItem(EnumHand.MAIN_HAND) != null && pvp.getHeldItem(EnumHand.MAIN_HAND).getItem() == ItemInit.IR_STAR && pvp.isHandActive()) {
                    return true;
                }
            }

            victim.getCombatTracker().trackDamage(new DamageSourceInfinitySword(player), victim.getHealth(), victim.getHealth());
            victim.setHealth(0);
            victim.onDeath(new EntityDamageSource("infinity", player));
            return true;
        }
        return false;
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        if (!entity.world.isRemote && entity instanceof EntityPlayer) {
            if (isActive(stack)) {
                EntityPlayer victim = (EntityPlayer) entity;
                if (victim.capabilities.isCreativeMode && !victim.isDead && victim.getHealth() > 0 && !AvaritiaEventHandler.isInfinite(victim)) {
                    victim.getCombatTracker().trackDamage(new DamageSourceInfinitySword(player), victim.getHealth(), victim.getHealth());
                    victim.setHealth(0);
                    victim.onDeath(new EntityDamageSource("infinity", player));
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public float getDestroySpeed(ItemStack stack, IBlockState state) {
        return isActive(stack) ? Float.MAX_VALUE : super.getDestroySpeed(stack, state);
    }

    @Override
    public int getHarvestLevel(ItemStack stack, String level, EntityPlayer player, IBlockState state) {
        return isActive(stack) ? Integer.MAX_VALUE : super.getHarvestLevel(stack, level, player, state);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        String itemName = super.getItemStackDisplayName(stack);
        return TextUtils.makeFabulous(itemName, TextFormatting.BOLD);
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFormatting.DARK_GRAY + "" + TextFormatting.ITALIC + "The minute you think of giving up, think of the reason you held on for so long . . .");
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand enumHand) {
        ItemStack stack = player.getHeldItem(enumHand);
        if (stack.getItem() == ItemInit.IR_STAR) {
            if (player.isSneaking() && enumHand == EnumHand.MAIN_HAND) {
                boolean active = isActive(stack);
                setActive(stack, !active);
            }
        }
        return super.onItemRightClick(world, player, enumHand);
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return isActive(stack);
    }

    public static boolean isActive(ItemStack stack) {
        return stack.hasTagCompound() && stack.getTagCompound().getBoolean("IRStarActive");
    }

    private static void setActive(ItemStack stack, boolean active) {
        if (!stack.hasTagCompound()) {
            stack.setTagCompound(new NBTTagCompound());
        }
        stack.getTagCompound().setBoolean("IRStarActive", active);
    }

    @Override
    public void registerModels() {
        InfiniteRevolution.proxy.registerItemRenderer(this, 0, "inventory");
    }

    // Galacticraft Methods Infinity Battery
    @Override
    protected void setMaxTransfer() {
        this.transferMax = AMPERAGE;
    }

    @Override
    public int getTierGC(ItemStack itemStack) {
        return 3;
    }

    @Override
    public CreativeTabs getCreativeTab() {
        return InfiniteRevolution.infiniteRevolutionTab;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public EnumRarity getRarity(ItemStack itemStack) {
        return ClientProxyCore.galacticraftItem;
    }

    @Override
    public float getElectricityStored(ItemStack itemStack) {
        return this.getMaxElectricityStored(itemStack);
    }

    @Override
    public void setElectricity(ItemStack itemStack, float joules) {}

    @Override
    public float getMaxElectricityStored(ItemStack itemStack) {
        return Float.POSITIVE_INFINITY;
    }

    @Override
    public float getTransfer(ItemStack itemStack) {
        return 0.0F;
    }

    @Override
    public float recharge(ItemStack theItem, float energy, boolean doReceive) {
        return 0F;
    }

    @Override
    public float discharge(ItemStack theItem, float energy, boolean doTransfer) {
        return energy;
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> list) {
        if (tab == InfiniteRevolution.infiniteRevolutionTab || tab == CreativeTabs.SEARCH) {
            list.add(new ItemStack(this, 1, 0));
        }
    }
}