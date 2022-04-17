package com.gempire.entities.bases;

import com.gempire.util.Abilities;
import com.gempire.util.Color;
import com.gempire.util.GemPlacements;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.EntityType;
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

    @Override
    public Abilities[] possibleAbilities() {
        return new Abilities[]{
            Abilities.NO_ABILITY, Abilities.PYROKINESIS, Abilities.CRYOKINESIS, Abilities.UNHINGED, Abilities.BEEFCAKE, Abilities.TANK, Abilities.KNOCKBACK, Abilities.PARALYSIS,
                Abilities.POWERHOUSE, Abilities.HEALER, Abilities.FIRST_AID, Abilities.BERSERKER, Abilities.BEASTMASTER, Abilities.CHARMER, Abilities.FRAGOKINESIS, Abilities.AZUR_PYROKINESIS,
                Abilities.ELECTROKINESIS, Abilities.AQUAPHILE, Abilities.AEROKINESIS
        };
    }

    @Override
    public boolean UsesUniqueNames() {
        return true;
    }

}
