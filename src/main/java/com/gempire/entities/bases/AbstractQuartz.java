package com.gempire.entities.bases;

import com.gempire.util.GempireAbilities;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.level.Level;

public abstract class AbstractQuartz extends EntityVaryingGem implements RangedAttackMob {

    public AbstractQuartz(EntityType<? extends PathfinderMob> type, Level worldIn) {
        super(type, worldIn);
    }

    public static AttributeSupplier.Builder registerAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 50.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.35D)
                .add(Attributes.ATTACK_DAMAGE, 5.0D)
                .add(Attributes.ATTACK_SPEED, 1.0D);
    }
    @Override
    public GempireAbilities[] possibleAbilities() {
        return new GempireAbilities[]{
            GempireAbilities.NO_ABILITY, GempireAbilities.PYROKINESIS, GempireAbilities.CRYOKINESIS,
                GempireAbilities.UNHINGED, GempireAbilities.BEEFCAKE, GempireAbilities.TANK,
                GempireAbilities.KNOCKBACK, GempireAbilities.PARALYSIS, GempireAbilities.POWERHOUSE,
                GempireAbilities.HEALER, GempireAbilities.FIRST_AID, GempireAbilities.BERSERKER,
                GempireAbilities.BEASTMASTER, GempireAbilities.ELECTROKINESIS, GempireAbilities.AEROKINESIS,
                GempireAbilities.HYDROKINESIS, GempireAbilities.ARCHER
        };
    }
    @Override
    public SoundEvent getInstrument()
    {
        return SoundEvents.NOTE_BLOCK_BASS.get();
    }
    @Override
    public boolean UsesUniqueNames() {
        return true;
    }

    public boolean isFocused() {
        return true;
    }

    public int generateHardness() {
        return 7;
    }

    @Override
    public int exitHoleSize() {
        return 3;
    }
}
