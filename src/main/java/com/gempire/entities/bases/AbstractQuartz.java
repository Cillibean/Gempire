package com.gempire.entities.bases;

import com.gempire.util.Abilities;
import com.gempire.util.GemPlacements;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public abstract class AbstractQuartz extends EntityVaryingGem {

    public AbstractQuartz(EntityType<? extends CreatureEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    public Abilities[] possibleAbilities() {
        return new Abilities[]{
            Abilities.PYROKINESIS, Abilities.CRYOKINESIS, Abilities.UNHINGED, Abilities.BEEFCAKE, Abilities.TANK, Abilities.KNOCKBACK, Abilities.PARALYSIS, Abilities.POWERHOUSE
        };
    }

    @Override
    public boolean UsesUniqueNames() {
        return true;
    }
}
