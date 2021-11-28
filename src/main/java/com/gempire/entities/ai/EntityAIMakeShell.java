package com.gempire.entities.ai;

import com.gempire.blocks.machine.PowerCrystalBlock;
import com.gempire.entities.gems.starter.EntityNacre;
import com.gempire.init.ModBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;

public class EntityAIMakeShell extends Goal {
    public EntityNacre follower;
    public BlockPos target;
    public double speed;

    public EntityAIMakeShell(EntityNacre entityIn, double speedIn) {
        this.follower = entityIn;
        this.speed = speedIn;
    }

    @Override
    public boolean shouldExecute() {
        BlockPos hopper = BlockPos.ZERO;
        boolean found = false;
        for(int x = -4; x < 5; x++){
            for(int y = -2; y < 3; y++){
                for(int z = -4; z < 5; z++){
                    if(!found) {
                        if (this.follower.world.getBlockState(this.follower.getPosition().add(x, y, z)).getBlock() == Blocks.PRISMARINE) {
                            hopper = this.follower.getPosition().add(x, y, z);
                            found = true;
                            System.out.println("Redstone Found");
                        }
                    }
                }
            }
        }
        if(found){
            double maxDistance = Double.MAX_VALUE;
            double newDistance = this.follower.getDistanceSq(hopper.getX(), hopper.getY(), hopper.getZ());
            if (newDistance <= maxDistance) {
                maxDistance = newDistance;
                this.target = hopper;
            }
        }
        return this.target != null && this.target != BlockPos.ZERO && this.follower.hopperGoal;
    }

    @Override
    public boolean shouldContinueExecuting() {
        return this.target != null && !this.follower.getNavigator().noPath() &&
                this.follower.getDistanceSq(target.getX(), target.getY(), target.getZ()) > Math.pow(4, 2) && this.follower.hopperGoal;
    }

    @Override
    public void startExecuting() {
        super.startExecuting();
        this.follower.setPathPriority(PathNodeType.WATER, 0);
        this.follower.getNavigator().tryMoveToXYZ(target.getX(), target.getY(), target.getZ(), this.speed);
        if (this.follower.getDistanceSq(target.getX(), target.getY(), target.getZ()) < 4) {
            if (this.follower.world.getBlockState(this.target).getBlock() == Blocks.PRISMARINE) {
                if (this.follower.world.getBlockState(this.target.up()).getBlock() instanceof PowerCrystalBlock) {
                    if (this.follower.world.getBlockState(this.target.up().up()).getBlock() == Blocks.CONDUIT) {
                        this.follower.world.createExplosion(null, this.target.getX(), this.target.getY(), this.target.getZ(), .75f, Explosion.Mode.NONE);
                        this.follower.world.setBlockState(this.target, ModBlocks.SHELL_BLOCK.get().getDefaultState());
                        this.follower.world.setBlockState(this.target.up(), Blocks.AIR.getDefaultState());
                        this.follower.world.setBlockState(this.target.up().up(), Blocks.AIR.getDefaultState());
                        this.follower.hopperGoal = false;
                    }
                }
            }
        }
    }

    @Override
    public void resetTask() {
        this.target = null;
        this.follower.getNavigator().clearPath();
        this.follower.setPathPriority(PathNodeType.WATER, 0);
    }
}
