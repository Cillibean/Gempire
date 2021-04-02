package com.gempire.gui;

import com.gempire.tileentities.TankTE;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.BucketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;

import java.util.UUID;

public class BucketHandlerSlot extends Slot {
    public TankTE tank;

    public BucketHandlerSlot(IInventory inventoryIn, int index, int xPosition, int yPosition, TankTE tank) {
        super(inventoryIn, index, xPosition, yPosition);
        this.tank = tank;
    }

    @Override
    public void onSlotChange(ItemStack oldStackIn, ItemStack newStackIn) {
        super.onSlotChange(oldStackIn, newStackIn);
        System.out.println("Access Successful");
        if(this.isItemValid(newStackIn)){
            System.out.println("Is bucket");
            BucketItem bucket = (BucketItem)newStackIn.getItem();
            boolean filled = this.tank.fill(new FluidStack(bucket.getFluid(), 1000), IFluidHandler.FluidAction.EXECUTE) > 0;
            System.out.println("Filled: " + filled);
            if(filled){
                System.out.println("Tank is now at " + this.tank.getFluidAmount() + "mb");
                this.putStack(new ItemStack(Items.BUCKET));
            }
            else{
                System.out.println("Tank is full");
            }
        }
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return stack.getItem() instanceof BucketItem && super.isItemValid(stack);
    }
}
