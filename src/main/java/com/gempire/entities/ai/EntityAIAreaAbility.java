package com.gempire.entities.ai;

import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.abilities.interfaces.IAreaAbility;
import com.gempire.entities.abilities.interfaces.IEffectAbility;
import com.gempire.entities.abilities.interfaces.IViolentAbility;
import com.gempire.entities.bases.EntityGem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class EntityAIAreaAbility extends Goal {
    public EntityGem gem;
    public World world;
    public double speed;
    boolean flag1, flag2, flag3, flagOwner, flagFocus;
    List<LivingEntity> entities = null;
    ArrayList<Ability> abilities = null;

    public EntityAIAreaAbility(EntityGem entityIn, double speedIn) {
        this.gem = entityIn;
        this.speed = speedIn;
        this.world = entityIn.world;
    }

    @Override
    public boolean shouldExecute() {
        List<LivingEntity> entity = this.world.getEntitiesWithinAABB(LivingEntity.class, new AxisAlignedBB(this.gem.getPosX(), this.gem.getPosY(), this.gem.getPosZ(), this.gem.getPosX() + 1, this.gem.getPosY() + 1 , this.gem.getPosZ() + 1)
                        .grow(16, this.world.getHeight(), 16));
        this.entities = entity;
        this.abilities = this.gem.getAbilityPowers();
        return this.gem.usesAreaAbilities() && this.gem.ticksExisted % 100 == 0;
    }

    @Override
    public boolean shouldContinueExecuting() {
        return this.abilities != null && this.entities != null && this.gem.ticksExisted % 100 == 0 && this.gem.usesAreaAbilities();
    }

    @Override
    public void startExecuting(){
        System.out.println("Ability List: " + this.abilities);
        for(Ability ability : this.gem.getAbilityPowers()){
            this.flag1 = ability instanceof IEffectAbility;
            this.flag2 = ability instanceof IAreaAbility;
            //this.flag3 = ability instanceof IViolentAbility;
            for(LivingEntity entity : this.entities){
                System.out.println("Entity Type: " + entity);
                System.out.println("For Entity: Flag 1: " + this.flag1 + " - Flag 2: " + this.flag2 + " - Flag 3: " + this.flag3);
                this.flagOwner = this.gem.isOwner(entity);
                this.flagFocus = this.gem.focusCheck();
                IEffectAbility effectAbility = null;
                IAreaAbility areaAbility = null;
                if (this.flagFocus && entity != this.gem) {
                    //Run through effect abilities - Check if they apply to the player only - Apply for each entity
                    if (this.flag1) {
                        System.out.println("Is effect ability");
                        effectAbility = (IEffectAbility) ability;
                        if (effectAbility.playerOnly()) {
                            if (this.flagOwner) {
                                System.out.println("Is owner");
                                entity.addPotionEffect(effectAbility.effect());
                                System.out.println("Effect Ability Deployed On Player");
                            }
                        } else {
                            System.out.println("Is not player only");
                            entity.addPotionEffect(effectAbility.effect());
                            System.out.println("Effect Ability Deployed");
                        }
                    }
                    //Run through area abilities - Apply for each entity
                    if (this.flag2) {
                        System.out.println("Area ability");
                        areaAbility = (IAreaAbility) ability;
                        areaAbility.AOeffect(entity, this.gem.OWNERS);
                        System.out.println("AOE Ability Deployed");
                    }
                }
            }
        }
        super.startExecuting();
    }

    @Override
    public void resetTask() {
        this.entities = null;
    }
}
