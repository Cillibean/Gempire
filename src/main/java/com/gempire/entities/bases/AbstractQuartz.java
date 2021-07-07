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
    public static final DataParameter<Integer> MARKING_COLOR = EntityDataManager.<Integer>createKey(EntityGem.class, DataSerializers.VARINT);
    public static final DataParameter<Integer> MARKING_VARIANT = EntityDataManager.<Integer>createKey(EntityGem.class, DataSerializers.VARINT);
    public static final DataParameter<Integer> MARKING_2_COLOR = EntityDataManager.<Integer>createKey(EntityGem.class, DataSerializers.VARINT);
    public static final DataParameter<Integer> MARKING_2_VARIANT = EntityDataManager.<Integer>createKey(EntityGem.class, DataSerializers.VARINT);

    public AbstractQuartz(EntityType<? extends CreatureEntity> type, World worldIn) {
        super(type, worldIn);
        this.dataManager.register(AbstractQuartz.MARKING_COLOR, 0);
        this.dataManager.register(AbstractQuartz.MARKING_VARIANT, 0);
        this.dataManager.register(AbstractQuartz.MARKING_2_COLOR, 0);
        this.dataManager.register(AbstractQuartz.MARKING_2_VARIANT, 0);
    }

    @Nullable
    @Override
    public ILivingEntityData onInitialSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
        this.setMarkingVariant(this.generateMarkingVariant());
        this.setMarkingColor(this.generateMarkingColor());
        this.setMarking2Variant(this.generateMarking2Variant());
        this.setMarking2Color(this.generateMarking2Color());
        return spawnDataIn;
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putInt("markingVariant", this.getMarkingVariant());
        compound.putInt("markingColor", this.getMarkingColor());
        compound.putInt("marking2Variant", this.getMarking2Variant());
        compound.putInt("marking2Color", this.getMarking2Color());
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        this.setMarkingVariant(compound.getInt("markingVariant"));
        this.setMarkingColor(compound.getInt("markingColor"));
        this.setMarking2Variant(compound.getInt("marking2Variant"));
        this.setMarking2Color(compound.getInt("marking2Color"));
    }

    public int generateMarkingVariant(){
        return this.hasMarkings() ? this.rand.nextInt(this.maxMarkings()) : 0;
    }

    public int generateMarking2Variant(){
        return this.hasMarkings2() ? this.rand.nextInt(this.maxMarkings2()) : 0;
    }

    public int generateMarkingColor(){
        ArrayList<Integer> markings = new ArrayList<>();
        ResourceLocation paletteTexture = new ResourceLocation(this.getModID() + ":textures/entity/" + this.getWholeGemName().toLowerCase() + "/marking_palette.png");
        BufferedImage palette = null;
        try{
            palette = ImageIO.read(Minecraft.getInstance().getResourceManager().getResource(paletteTexture).getInputStream());
            System.out.println("Palette Read!");
            for (int x = 0; x < palette.getWidth(); x++) {
                int color = palette.getRGB(x, this.getSkinColorVariant());
                if((color>>24) == 0x00){
                    continue;
                }
                markings.add(color);
            }
        }
        catch (IOException e){
            e.printStackTrace();
            markings.add(0x00000);
        }
        return Color.lerpHex(markings);
    }

    public int generateMarking2Color(){
        ArrayList<Integer> markings = new ArrayList<>();
        ResourceLocation paletteTexture = new ResourceLocation(this.getModID() + ":textures/entity/" + this.getWholeGemName().toLowerCase() + "/marking_2_palette.png");
        BufferedImage palette = null;
        try{
            palette = ImageIO.read(Minecraft.getInstance().getResourceManager().getResource(paletteTexture).getInputStream());
            System.out.println("Palette Read!");
            for (int x = 0; x < palette.getWidth(); x++) {
                int color = palette.getRGB(x, this.getSkinColorVariant());
                if((color>>24) == 0x00){
                    continue;
                }
                markings.add(color);
            }
        }
        catch (IOException e){
            e.printStackTrace();
            markings.add(0x00000);
        }
        return Color.lerpHex(markings);
    }

    @Override
    public Abilities[] possibleAbilities() {
        return new Abilities[]{
            Abilities.NO_ABILITY, Abilities.PYROKINESIS, Abilities.CRYOKINESIS, Abilities.UNHINGED, Abilities.BEEFCAKE, Abilities.TANK, Abilities.KNOCKBACK, Abilities.PARALYSIS,
                Abilities.POWERHOUSE
        };
    }

    @Override
    public boolean UsesUniqueNames() {
        return true;
    }

    public abstract boolean hasMarkings();
    public abstract boolean hasMarkings2();
    public abstract int maxMarkings();
    public abstract int maxMarkings2();

    public void setMarkingColor(int color){
        this.dataManager.set(AbstractQuartz.MARKING_COLOR, color);
    }

    public int getMarkingColor(){
        return this.dataManager.get(AbstractQuartz.MARKING_COLOR);
    }
    public void setMarkingVariant(int value){
        this.dataManager.set(AbstractQuartz.MARKING_VARIANT, value);
    }

    public int getMarkingVariant(){
        return this.dataManager.get(AbstractQuartz.MARKING_VARIANT);
    }

    public void setMarking2Color(int color){
        this.dataManager.set(AbstractQuartz.MARKING_2_COLOR, color);
    }

    public int getMarking2Color(){
        return this.dataManager.get(AbstractQuartz.MARKING_2_COLOR);
    }
    public void setMarking2Variant(int value){
        this.dataManager.set(AbstractQuartz.MARKING_2_VARIANT, value);
    }

    public int getMarking2Variant(){
        return this.dataManager.get(AbstractQuartz.MARKING_2_VARIANT);
    }
}
