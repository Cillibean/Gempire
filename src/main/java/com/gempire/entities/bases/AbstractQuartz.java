package com.gempire.entities.bases;

import com.gempire.util.Abilities;
import com.gempire.util.Color;
import com.gempire.util.GemPlacements;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public abstract class AbstractQuartz extends EntityVaryingGem {

    public AbstractQuartz(EntityType<? extends CreatureEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    public Abilities[] possibleAbilities() {
        return new Abilities[]{
            Abilities.NO_ABILITY, Abilities.PYROKINESIS, Abilities.CRYOKINESIS, Abilities.UNHINGED, Abilities.BEEFCAKE, Abilities.TANK, Abilities.KNOCKBACK, Abilities.PARALYSIS,
                Abilities.POWERHOUSE, Abilities.HEALER, Abilities.FIRST_AID
        };
    }

    @Override
    public boolean UsesUniqueNames() {
        return true;
    }

}
