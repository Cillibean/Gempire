package com.gempire.entities.gems;

import com.gempire.init.ModItems;
import com.gempire.items.ItemGem;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.HandSide;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class EntityGem extends CreatureEntity {
    public Item droppedGemstone;

    public EntityGem(EntityType<? extends CreatureEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    public void onDeath(DamageSource source){
        //When the Gem dies.
        if(!this.world.isRemote){
            ItemEntity item = new ItemEntity(this.world, this.getPosX(), this.getPosY(), this.getPosZ(), new ItemStack(this.droppedGemstone));
            item.setNoDespawn();
            this.world.addEntity(item);
        }
        super.onDeath(source);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
    }

    @Override
    public boolean canDespawn(double xix){
        return false;
    }
}
