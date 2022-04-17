package com.gempire.entities.bases;

import com.gempire.util.Abilities;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public abstract class AbstractGarnet extends EntityVaryingGem {
    //TO-DO: IMPLEMENT GARNET FUNCTIONS.
    // All Garnets will do the following: attract weaker gems that are set to wander (or are untamed and have no master) (no skill name, do we even need one? hardcode it?)
    // teleport away when they have under 20% of health (also no skill name, probably hardcoded)
    // and disarm their opponents. (Abilities.DISARMING)
    public AbstractGarnet(EntityType<? extends PathfinderMob> type, Level worldIn) {
        super(type, worldIn);
    }

    @Override
    public Abilities[] possibleAbilities() {
        return new Abilities[]{
                Abilities.NO_ABILITY, Abilities.DISARMING
        };
    }
}
