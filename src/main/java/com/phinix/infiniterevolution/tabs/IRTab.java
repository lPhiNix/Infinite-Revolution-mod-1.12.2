package com.phinix.infiniterevolution.tabs;

import com.phinix.infiniterevolution.init.ItemInit;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class IRTab extends CreativeTabs {
    public IRTab (String label) {
        super(label);
    }

    public ItemStack getTabIconItem() {
        return new ItemStack(ItemInit.IR_STAR);
    }
}
