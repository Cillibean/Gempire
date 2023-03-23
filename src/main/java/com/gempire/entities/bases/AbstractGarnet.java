package com.gempire.entities.bases;

import com.gempire.util.Abilities;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;

public abstract class AbstractGarnet extends EntityVaryingGem {
    //TO-DO: IMPLEMENT GARNET FUNCTIONS.
    // All Garnets will do the following: attract weaker gems that are set to wander (or are untamed and have no master) (no skill name, do we even need one? hardcode it?)
    // teleport away when they have under 20% of health (also no skill name, probably hardcoded)
    // and disarm their opponents. (Abilities.DISARMING)
    public AbstractGarnet(EntityType<? extends PathfinderMob> type, Level worldIn) {
        super(type, worldIn);
    }
    public static AttributeSupplier.Builder registerAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 60.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.4D)
                .add(Attributes.ATTACK_DAMAGE, 6.0D)
                .add(Attributes.ATTACK_SPEED, 0.5D);
    }
    @Override
    public Abilities[] possibleAbilities() {
        return new Abilities[]{
                Abilities.NO_ABILITY//, Abilities.DISARMING
        };
    }
}
