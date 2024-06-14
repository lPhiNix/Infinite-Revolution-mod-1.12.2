package com.phinix.infiniterevolution.objects.items;

import com.phinix.infiniterevolution.InfiniteRevolution;
import com.phinix.infiniterevolution.init.ItemInit;
import com.phinix.infiniterevolution.util.IHasModel;
import com.phinix.infiniterevolution.util.TextUtils;
import micdoodle8.mods.galacticraft.core.energy.item.ItemElectricBase;
import micdoodle8.mods.galacticraft.core.proxy.ClientProxyCore;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
@Mod.EventBusSubscriber
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
    public void registerModels() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
        ModelLoader.setCustomMeshDefinition(this, stack -> {
            ResourceLocation modelLocation = new ResourceLocation("infinite_revolution:item/ir_star");
            return new ModelResourceLocation(modelLocation, "inventory");
        });
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        String itemName = super.getItemStackDisplayName(stack);
        return TextUtils.makeFabulous(itemName, TextFormatting.BOLD);
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFormatting.DARK_GRAY + "" + TextFormatting.ITALIC + "The minute you think of giving up, think of the reason you held on for so long . . .");
    }

    @SubscribeEvent
    public static void onRightClick(PlayerInteractEvent.RightClickItem event) {
        EntityPlayer player = event.getEntityPlayer();
        ItemStack stack = event.getItemStack();
        if (player != null && stack != null && stack.getItem() == ItemInit.IR_STAR) {
            if (player.isSneaking() && event.getHand() == EnumHand.MAIN_HAND) {
                boolean active = isActive(stack);
                setActive(stack, !active);
            }
        }
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
    public EnumRarity getRarity(ItemStack par1ItemStack) {
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