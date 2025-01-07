package com.gempire.entities.ai;

import com.gempire.entities.bases.EntityGem;
import com.gempire.entities.gems.EntityPeridot;
import com.gempire.entities.gems.starter.EntityPebble;
import com.gempire.init.ModBlocks;
import com.gempire.util.PeridotRepairResources;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.pathfinder.BlockPathTypes;

import java.util.*;
import java.util.stream.IntStream;

public class EntityAIGalaxyWarp extends Goal {
    public EntityPeridot follower;
    public BlockPos target;
    public double speed;
    public boolean hasResource = true;
    public boolean checked = false;

    public EntityAIGalaxyWarp(EntityPeridot entityIn, double speedIn) {
        this.follower = entityIn;
        this.speed = speedIn;
    }

    @Override
    public boolean canUse() {
        if (follower.getItemBySlot(EquipmentSlot.MAINHAND).getItem() instanceof PickaxeItem) {
            BlockPos hopper = BlockPos.ZERO;
            boolean found = false;
            for (int x = -4; x < 5; x++) {
                for (int y = -2; y < 3; y++) {
                    for (int z = -4; z < 5; z++) {
                        if (!found) {
                            if (this.follower.level().getBlockState(this.follower.blockPosition().offset(x, y, z)).getBlock() == ModBlocks.CRACKED_GALAXY_WARP.get()) {
                                hopper = this.follower.blockPosition().offset(x, y, z);
                                found = true;
                            }
                        }
                    }
                }
            }
            if (found) {
                double maxDistance = Double.MAX_VALUE;
                double newDistance = this.follower.distanceToSqr(hopper.getX(), hopper.getY(), hopper.getZ());
                if (newDistance <= maxDistance) {
                    maxDistance = newDistance;
                    this.target = hopper;
                }
            }
        }
        return this.follower.movementType == 0 && this.target != null && this.target != BlockPos.ZERO && follower.getItemBySlot(EquipmentSlot.MAINHAND).getItem() instanceof PickaxeItem;
    }

    @Override
    public boolean canContinueToUse() {
        return this.follower.movementType == 0 && this.target != null && !this.follower.getNavigation().isDone() &&
                this.follower.distanceToSqr(target.getX(), target.getY(), target.getZ()) > Math.pow(4, 2) && follower.level().getBlockState(target) == ModBlocks.CRACKED_GALAXY_WARP.get().defaultBlockState();
    }

    @Override
    public void start(){
        super.start();
        /*
        this.follower.setPathfindingMalus(BlockPathTypes.WATER, 0);
        this.follower.getNavigation().moveTo(target.getX(), target.getY(), target.getZ(), this.speed);
        if(this.follower.distanceToSqr(target.getX(), target.getY(), target.getZ()) < 4){
            if(this.follower.level().getBlockState(this.target).getBlock() == Blocks.HOPPER){
                if(this.follower.level().getBlockState(this.target.north()).getBlock() == Blocks.IRON_BARS){
                    if(this.follower.level().getBlockState(this.target.south()).getBlock() == Blocks.IRON_BARS){
                        if(this.follower.level().getBlockState(this.target.west()).getBlock() == Blocks.IRON_BARS){
                            if(this.follower.level().getBlockState(this.target.east()).getBlock() == Blocks.IRON_BARS){
                                this.follower.level().explode(null, this.target.getX(), this.target.getY(), this.target.getZ(), .75f, Level.ExplosionInteraction.NONE);
                                this.follower.level().setBlockAndUpdate(this.target, ModBlocks.DRILL_BLOCK.get().defaultBlockState());
                                this.follower.level().setBlockAndUpdate(this.target.north(), Blocks.AIR.defaultBlockState());
                                this.follower.level().setBlockAndUpdate(this.target.south(), Blocks.AIR.defaultBlockState());
                                this.follower.level().setBlockAndUpdate(this.target.west(), Blocks.AIR.defaultBlockState());
                                this.follower.level().setBlockAndUpdate(this.target.east(), Blocks.AIR.defaultBlockState());
                                follower.getItemBySlot(EquipmentSlot.MAINHAND).hurtAndBreak(1, follower, (p_43296_) -> p_43296_.broadcastBreakEvent(EquipmentSlot.MAINHAND));                            }
                        }
                    }
                }
            }
         }

         */
    }

    @Override
    public void tick() {
        super.tick();
        if (checked) {
            if (hasResource) checkInputs();
            else checkInventory();
        } else {
            checkFixed();
        }
    }

    public void checkFixed() {
        PeridotRepairResources.register();
        LinkedList<String> completed = new LinkedList<>(Arrays.asList(follower.getCompleted().split(",")));
        String[] materialRough = follower.getWarpMaterials().split(",");
        String[] materials = new String[10];
        if (completed.size() == 3) {
            String[] materialRough2 = remove(0, materialRough);
            String[] materialRough3 = remove(0, materialRough2);
            materials = remove(0, materialRough3);
        } else if (completed.size() == 2) {
            String[] materialRough2 = remove(0, materialRough);
            materials = remove(0, materialRough2);
        } else if (completed.size() == 1) {
            materials = remove(0, materialRough);
        }
        if (materials[0] == null) follower.level().setBlockAndUpdate(target, ModBlocks.GALAXY_WARP.get().defaultBlockState());
        checked = true;
    }

    public void checkInputs() {
        hasResource = false;
        PeridotRepairResources.register();
        LinkedList<String> completed = new LinkedList<>(Arrays.asList(follower.getCompleted().split(",")));
        //LinkedList<String> materials = new LinkedList<>(Arrays.asList(follower.getWarpMaterials().split(",")));
        String[] amountRough = follower.getWarpAmounts().split(",");
        String[] amounts = new String[10];
        String[] materialRough = follower.getWarpMaterials().split(",");
        String[] materials = new String[10];
        if (completed.size() == 3) {
            String[] amountRough2 = remove(0, amountRough);
            String[] amountRough3 = remove(0, amountRough2);
            amounts = remove(0, amountRough3);
            String[] materialRough2 = remove(0, materialRough);
            String[] materialRough3 = remove(0, materialRough2);
            materials = remove(0, materialRough3);
        } else if (completed.size() == 2) {
            String[] amountRough2 = remove(0, amountRough);
            amounts = remove(0, amountRough2);
            String[] materialRough2 = remove(0, materialRough);
            materials = remove(0, materialRough2);
        } else if (completed.size() == 1) {
            amounts = remove(0, amountRough);
            materials = remove(0, materialRough);
        }
        //materials.removeAll(completed);
        if (materials[0] != null) {
            Item currentMaterial = PeridotRepairResources.list.get(Integer.parseInt(materials[0]));
            int currentAmount = Integer.parseInt(amounts[0]);
            for (UUID uuid : follower.OWNERS) {
                follower.level().getPlayerByUUID(uuid).sendSystemMessage(Component.translatable("Peridot requires " + currentAmount + " " + getItemName(currentMaterial.toString(), currentAmount > 1)));
            }
        }
    }

    public String getItemName(String value, boolean plural) {
        String[] strings = value.split("_");
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < strings.length; i++) {
            String letter = strings[i].substring(0, 1).toUpperCase();
            strings[i] = letter + strings[i].substring(1);
            string.append(strings[i]);
            if (i < strings.length - 1) string.append(" ");
            else if (plural) string.append("s");
        }
        return string.toString();
    }

    public String[] remove(int index, String[] arr) {
        String[] newArr = new String[arr.length - 1];
        if(index < 0 || index > arr.length) {
            return arr;
        }
        int j = 0;
        for(int i = 0; i < arr.length; i++) {
            if(i == index) {
                i++;
            }
            newArr[j++] = arr[i];
        }

        return newArr;
    }

    public void checkInventory() {
        PeridotRepairResources.register();
        LinkedList<String> completed = new LinkedList<>(Arrays.asList(follower.getCompleted().split(",")));
        String[] amountRough = follower.getWarpAmounts().split(",");
        String[] amounts = new String[10];
        String[] materialRough = follower.getWarpMaterials().split(",");
        String[] materials = new String[10];
        if (completed.size() == 3) {
            String[] amountRough2 = remove(0, amountRough);
            String[] amountRough3 = remove(0, amountRough2);
            amounts = remove(0, amountRough3);
            String[] materialRough2 = remove(0, materialRough);
            String[] materialRough3 = remove(0, materialRough2);
            materials = remove(0, materialRough3);
        } else if (completed.size() == 2) {
            String[] amountRough2 = remove(0, amountRough);
            amounts = remove(0, amountRough2);
            String[] materialRough2 = remove(0, materialRough);
            materials = remove(0, materialRough2);
        } else if (completed.size() == 1) {
            amounts = remove(0, amountRough);
            materials = remove(0, materialRough);
        }
        if (materials[0] != null) {
            Item currentMaterial = PeridotRepairResources.list.get(Integer.parseInt(materials[0]));
            int currentAmount = Integer.parseInt(amounts[0]);
            if (follower.consumeItemCheck(currentMaterial, currentAmount)) {
                hasResource = true;
                if (follower.getCompleted().isEmpty()) {
                    follower.setCompleted(materials[0]);
                } else {
                    follower.setCompleted(follower.getCompleted() + "," + materials[0]);
                }
                System.out.println(follower.getCompleted());
                checked = false;
            }
        }
    }

    @Override
    public void stop() {
        this.target = null;
        follower.setCompleted("");
        this.follower.getNavigation().stop();
        this.follower.setPathfindingMalus(BlockPathTypes.WATER, 0);
    }
}
