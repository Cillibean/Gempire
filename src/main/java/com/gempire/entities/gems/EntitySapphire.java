package com.gempire.entities.gems;

import com.gempire.Gempire;
import com.gempire.entities.ai.EntityAIFollowOwner;
import com.gempire.entities.ai.EntityAIWander;
import com.gempire.entities.bases.EntityGem;
import com.gempire.entities.bases.EntityVaryingGem;
import com.gempire.systems.injection.Crux;
import com.gempire.util.Abilities;
import com.gempire.util.Color;
import com.gempire.util.CruxType;
import com.gempire.util.GemPlacements;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.Texture;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.apache.commons.io.IOUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class EntitySapphire extends EntityVaryingGem {

    public EntitySapphire(EntityType<? extends CreatureEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MobEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 50.0D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.4D)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 1.0D);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(9, new SwimGoal(this));
        this.goalSelector.addGoal(6, new PanicGoal(this, 1.1D));
        this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 4.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(7, new EntityAIWander(this, 1.0D));
        this.goalSelector.addGoal(7, new EntityAIFollowOwner(this, 1.0D));
    }

    @Override
    public int generateSkinColor(){
        ArrayList<Integer> skins = new ArrayList<>();
        ResourceLocation paletteTexture = new ResourceLocation(Gempire.MODID + ":textures/entity/" + this.getGemName().toLowerCase() + "/skin_palette.png");
        BufferedImage palette = null;
        try{
            palette = ImageIO.read(Minecraft.getInstance().getResourceManager().getResource(paletteTexture).getInputStream());
            System.out.println("Palette Read!");
            for (int x = 0; x < palette.getWidth(); x++) {
                int color = palette.getRGB(x, this.getSkinColorVariant());
                if((color>>24) == 0x00){
                    continue;
                }
                skins.add(color);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return Color.lerpHex(skins);
    }

    @Override
    public int generateSkinVariant() {
        return 0;
    }

    @Override
    public GemPlacements[] getPlacements() {
        return new GemPlacements[]{
                GemPlacements.TOP_OF_HEAD, GemPlacements.FOREHEAD, GemPlacements.BACK_OF_HEAD, GemPlacements.LEFT_EYE,
                GemPlacements.LEFT_CHEEK, GemPlacements.RIGHT_CHEEK, GemPlacements.LEFT_EAR, GemPlacements.RIGHT_EAR,
                GemPlacements.CHEST, GemPlacements.BACK, GemPlacements.BELLY, GemPlacements.LEFT_ARM, GemPlacements.RIGHT_ARM,
                GemPlacements.LEFT_HAND, GemPlacements.RIGHT_HAND, GemPlacements.LEFT_PALM, GemPlacements.RIGHT_PALM
        };
    }

    @Override
    public int generateHairColor() {
        ArrayList<Integer> skins = new ArrayList<>();
        ResourceLocation paletteTexture = new ResourceLocation(Gempire.MODID + ":textures/entity/" + this.getGemName().toLowerCase() + "/hair_palette.png");
        BufferedImage palette = null;
        try{
            palette = ImageIO.read(Minecraft.getInstance().getResourceManager().getResource(paletteTexture).getInputStream());
            System.out.println("Palette Read!");
            for (int x = 0; x < palette.getWidth(); x++) {
                int color = palette.getRGB(x, this.getSkinColorVariant());
                if((color>>24) == 0x00){
                    continue;
                }
                skins.add(color);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return Color.lerpHex(skins);
    }

    @Override
    public int generateHairVariant() {
        return this.rand.nextInt(5);
    }

    @Override
    public int generateGemColor() {
        ArrayList<Integer> skins = new ArrayList<>();
        ResourceLocation paletteTexture = new ResourceLocation(Gempire.MODID + ":textures/entity/" + this.getGemName().toLowerCase() + "/gem_palette.png");
        BufferedImage palette = null;
        try{
            palette = ImageIO.read(Minecraft.getInstance().getResourceManager().getResource(paletteTexture).getInputStream());
            System.out.println("Palette Read!");
            for (int x = 0; x < palette.getWidth(); x++) {
                int color = palette.getRGB(x, this.getSkinColorVariant());
                if((color>>24) == 0x00){
                    continue;
                }
                skins.add(color);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return Color.lerpHex(skins);
    }

    public int generateOutfitVariant() {
        return this.rand.nextInt(6);
    }

    public int generateInsigniaVariant() {
        return this.getOutfitVariant();
    }

    @Override
    public int generateAbilitySlots(){
        //TODO: Temporary
        return 1;
    }

    @Override
    public Abilities[] possibleAbilities() {
        return new Abilities[]{
                Abilities.NO_ABILITY
        };
    }

    @Override
    public Abilities[] definiteAbilities() {
        return new Abilities[]{
                Abilities.NO_ABILITY
        };
    }

    @Override
    public boolean generateIsEmotional() {
        return false;
    }

    public boolean canChangeUniformColorByDefault() {
        return true;
    }

    public boolean canChangeInsigniaColorByDefault(){
        return true;
    }

    @Override
    public boolean isImmuneToFire(){
        return false;
    }

    public boolean hasSkinColorVariant(){
        return true;
    }

    @Override
    public int[] NeglectedColors() {
        return new int[]{
                14
        };
    }

    public boolean isColorValid(int color){
        for(int i : this.NeglectedColors()){
            if(this.NeglectedColors()[i] == color){
                return false;
            }
        }
        return true;
    }
}
