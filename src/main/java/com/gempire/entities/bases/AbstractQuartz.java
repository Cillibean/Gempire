package com.gempire.entities.bases;

import com.gempire.util.Abilities;
import com.gempire.util.Color;
import com.gempire.util.GemPlacements;
import net.minecraft.client.Minecraft;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public abstract class AbstractQuartz extends EntityVaryingGem {

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
    public Abilities[] possibleAbilities() {
        return new Abilities[]{
            Abilities.NO_ABILITY, Abilities.PYROKINESIS, Abilities.CRYOKINESIS, Abilities.UNHINGED, Abilities.BEEFCAKE, Abilities.TANK, Abilities.KNOCKBACK, Abilities.PARALYSIS,
                Abilities.POWERHOUSE, Abilities.HEALER, Abilities.FIRST_AID, Abilities.BERSERKER, Abilities.BEASTMASTER, Abilities.ELECTROKINESIS, Abilities.AEROKINESIS, Abilities.HYDROKINESIS
        };
    }
    @Override
    public SoundEvent getInstrument()
    {
        return SoundEvents.NOTE_BLOCK_BASS;
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


}
