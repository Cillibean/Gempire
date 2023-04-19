package com.gempire.entities.gems;

import com.gempire.entities.abilities.AbilityAbundance;
import com.gempire.entities.abilities.AbilityDisarming;
import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.ai.*;
import com.gempire.entities.bases.EntityGem;
import com.gempire.entities.bases.EntityVaryingGem;
import com.gempire.util.Abilities;
import com.gempire.util.GemPlacements;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.Random;

public class EntityGarnet extends EntityVaryingGem {

    public EntityGarnet(EntityType<? extends PathfinderMob> type, Level worldIn) {
        super(type, worldIn);
    }

    public static AttributeSupplier.Builder registerAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 60.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.4D)
                .add(Attributes.ATTACK_DAMAGE, 6.0D)
                .add(Attributes.ATTACK_SPEED, 0.5D);
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(7, new FloatGoal(this));
        this.goalSelector.addGoal(6, new PanicGoal(this, 1.1D));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 4.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(7, new EntityAIFollowAssigned(this, 1.0D));
        this.goalSelector.addGoal(7, new EntityAIWander(this, 1.0D));
        this.goalSelector.addGoal(7, new EntityAIFollowOwner(this, 1.0D));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, EntityGem.class, 1, false, false, this::checkRebel));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Mob.class, 1, false, false, (p_234199_0_) -> p_234199_0_.getClassification(true) == MobCategory.MONSTER));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.1D, false));
        this.targetSelector.addGoal(1, new OwnerHurtByTargetGemGoal(this));
        this.targetSelector.addGoal(2, new OwnerHurtTargetGemGoal(this));
    }

    @Override
    public GemPlacements[] getPlacements() {
        return new GemPlacements[] {
                GemPlacements.FOREHEAD, GemPlacements.LEFT_EYE, GemPlacements.RIGHT_EYE, GemPlacements.NOSE, GemPlacements.LEFT_CHEEK, GemPlacements.RIGHT_CHEEK, GemPlacements.MOUTH, GemPlacements.CHEST, GemPlacements.BACK, GemPlacements.BELLY,
                GemPlacements.LEFT_SHOULDER, GemPlacements.RIGHT_SHOULDER, GemPlacements.LEFT_HAND, GemPlacements.RIGHT_HAND, GemPlacements.LEFT_PALM, GemPlacements.RIGHT_PALM, GemPlacements.LEFT_THIGH, GemPlacements.RIGHT_THIGH, GemPlacements.LEFT_KNEE, GemPlacements.RIGHT_KNEE,
                GemPlacements.LEFT_ANKLE, GemPlacements.RIGHT_ANKLE };
    }

    @Override
    public int generateHairVariant() {
        return this.random.nextInt(7);
    }

    @Override
    public boolean hasOutfitPlacementVariant() {
        return false;
    }

    @Override
    public int[] outfitPlacementVariants() {
        return new int[]{
                11, 17
        };
    }
    @Override
    public int[] NeglectedColors() {
        return null;
    }

    public Abilities[] possibleAbilities(){
        return new Abilities[]{
                Abilities.NO_ABILITY, Abilities.TANK, Abilities.BEEFCAKE, Abilities.POWERHOUSE, Abilities.UNHINGED, Abilities.BERSERKER, Abilities.ABUNDANCE
        };
    }
    public Abilities[] definiteAbilities(){
        return new Abilities[]{
                    Abilities.DISARMING
        };
    }

    public boolean doHurtTarget(Entity entityIn) {
        for (Ability ability : this.getAbilityPowers()) {
            if (ability instanceof AbilityDisarming) {
                if (!entityIn.level.isClientSide) {
                    if (this.random.nextFloat() < 0.25f) {
                        ItemStack item = ((PathfinderMob) entityIn).getItemBySlot(EquipmentSlot.MAINHAND);
                        if (item.isDamageableItem()) {
                            item.setDamageValue(item.getMaxDamage() - this.random.nextInt(1 + this.random.nextInt(Math.max(item.getMaxDamage() - 3, 1))));
                        }
                        ItemEntity dropitem = new ItemEntity(entityIn.level, entityIn.getX(), entityIn.getY(), entityIn.getZ(), item);
                        entityIn.level.addFreshEntity(dropitem);
                        entityIn.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
                    }
                }
            }
        }
        return super.doHurtTarget(entityIn);
    }
    @Override
    public boolean UsesUniqueNames() {
        return true;
    }

    @Override
    public String NameFromColor(byte i) {
        String name = "";
        switch(i){
            case 1:
                name = "hessonite";
                break;
            case 2:
                name = "rhodolite";
                break;
            case 3:
                name = "umbalite";
                break;
            case 4:
                name = "topazolite";
                break;
            case 5:
                name = "demantoid";
                break;
            case 6:
                name = "imperial";
                break;
            case 7:
                name = "kimzeyite";
                break;
            case 8:
                name = "katoite";
                break;
            case 9:
                name = "bekily";
                break;
            case 10:
                name = "grape";
                break;
            case 11:
                name = "blue";
                break;
            case 12:
                name = "andalusite";
                break;
            case 13:
                name = "grossularite";
                break;
            case 14:
                name = "pyrope";
                break;
            case 15:
                name = "melanite";
                break;
            case 16:
                name = "iridescent_andradite";
                break;
            default:
                name = "leuco";
                break;
        }
        return name;
    }


    @Override
    public boolean generateIsEmotional() {
        return true;
    }

    @Override
    public byte EmotionThreshold() {
        return 10;
    }

    public boolean canChangeUniformColorByDefault() {
        return true;
    }

    public boolean canChangeInsigniaColorByDefault(){
        return true;
    }

    @Override
    public boolean fireImmune(){
        return true;
    }

    public boolean hasSkinColorVariant() {
        return true;
    }
    public int generateSkinVariant() {
        return 0;
    }

    public int generateOutfitVariant(){
        return this.random.nextInt(8);
    }

    public int generateInsigniaVariant(){
        return this.getOutfitVariant();
    }

    @Override
    public int baseFocus() {
        return 7;
    }

}
