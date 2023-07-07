package com.gempire.entities.bases;

import com.gempire.entities.abilities.*;
import com.gempire.entities.abilities.base.Ability;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.level.Level;

import java.util.ArrayList;

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
    public ArrayList<Ability> possibleAbilities() {
        ArrayList<Ability> arrayList = new ArrayList<>();
        arrayList.add(new AbilityZilch());
        arrayList.add(new AbilityPyrokinesis());
        arrayList.add(new AbilityCryokinesis());
        arrayList.add(new AbilityUnhinged());
        arrayList.add(new AbilityBeefcake());
        arrayList.add(new AbilityTank());
        arrayList.add(new AbilityKnockback());
        arrayList.add(new AbilityParalysis());
        arrayList.add(new AbilityPowerhouse());
        arrayList.add(new AbilityHealer());
        arrayList.add(new AbilityFirstAid());
        arrayList.add(new AbilityBerserker());
        arrayList.add(new AbilityBeastmaster());
        arrayList.add(new AbilityElectrokinesis());
        arrayList.add(new AbilityAerokinesis());
        arrayList.add(new AbilityHydrokinesis());
        arrayList.add(new AbilityArcher());
        return arrayList;
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
