package com.gempire.items;

import com.gempire.systems.machine.interfaces.IPowerProvider;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class ItemIceShard extends Item  {

    public ItemIceShard(Properties properties) {
        super(properties);
    }


    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        boolean spawned = false;
        if(!worldIn.isRemote) {
            ItemStack itemstack = playerIn.getHeldItem(handIn);
            RayTraceResult raytraceresult = this.rayTrace(worldIn, playerIn, RayTraceContext.FluidMode.NONE);
            if (raytraceresult.getType() == RayTraceResult.Type.MISS) {
                return super.onItemRightClick(worldIn, playerIn, handIn);
            } else {
                if (raytraceresult.getType() == RayTraceResult.Type.BLOCK) {
                    BlockRayTraceResult blockraytraceresult = (BlockRayTraceResult) raytraceresult;
                    BlockPos blockpos = blockraytraceresult.getPos();
                    Direction direction = blockraytraceresult.getFace();
                    if (!worldIn.isBlockModifiable(playerIn, blockpos) || !playerIn.canPlayerEdit(blockpos.offset(direction), direction, itemstack)) {
                        return super.onItemRightClick(worldIn, playerIn, handIn);
                    }

                    if (worldIn.isBlockModifiable(playerIn, blockpos) && playerIn.canPlayerEdit(blockpos, blockraytraceresult.getFace(), itemstack)) {
                        spawned = this.testWire(worldIn, playerIn, blockpos, itemstack);
                    }
                }
                //Problem with the claiming of gems ??
                if (!playerIn.isCreative() && spawned) {
                    playerIn.getHeldItemMainhand().shrink(1);
                }
            }
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    public boolean testWire(World world, PlayerEntity player, BlockPos pos, ItemStack stack) {
        if(world.getTileEntity(pos) instanceof IPowerProvider){
            IPowerProvider provider = (IPowerProvider) world.getTileEntity(pos);
            player.sendMessage(new StringTextComponent("VOLTAGE: " + provider.getVoltage() + "V"), player.getUniqueID());
            player.sendMessage(new StringTextComponent("POWER: " + provider.getBattery().getCharge() + "J"), player.getUniqueID());
            return true;
        }
        return false;
    }
}
