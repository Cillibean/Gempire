package com.gempire.items.tools;

import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.SpectralArrow;
import net.minecraft.world.item.BowItem;

public class ItemEmpressBow extends BowItem {
    public ItemEmpressBow(Properties p_40660_) {
        super(p_40660_);
    }

    @Override
    public AbstractArrow customArrow(AbstractArrow arrow) {
        return new EmpressArrow(arrow.level, arrow.getX(), arrow.getY(), arrow.getZ());
    }
}
