package com.shim.celestialquests.enchantments;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class CompletedQuestEnchantment extends Enchantment {
    public CompletedQuestEnchantment(Rarity p_44676_, EquipmentSlot[] p_44678_) {
        super(p_44676_, EnchantmentCategory.VANISHABLE, p_44678_);
    }

    public boolean isDiscoverable() {
        return false;
    }
}
