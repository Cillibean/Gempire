package com.gempire.items;

import com.gempire.entities.bases.EntityGem;
import com.gempire.systems.machine.Battery;
import com.gempire.systems.machine.interfaces.IPoweredItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;

public class PoweredItem extends Item implements IPoweredItem {
    Battery battery;

    public PoweredItem(Properties properties) {
        super(properties);
        setupBattery(200);
    }

    @Override
    public Battery getBattery() {
        return this.battery;
    }

    @Override
    public void setupBattery(float maxCapacity) {
        setBattery(new Battery(maxCapacity, maxCapacity));
    }

    @Override
    public void setBattery(Battery battery) {
        this.battery = battery;
    }

    @Override
    public boolean updateItemStackNBT(CompoundNBT nbt) {
        //WritePowered(nbt);
        return super.updateItemStackNBT(nbt);
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        return true;
    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack) {
        Battery battery = getBattery();
        System.out.println("[DEBUG] " + (getMaxDamage(stack) - (int) Math.floor(battery.getCharge() * getMaxDamage(stack) / battery.getMaxCapacity())));
        return (int) Math.floor(battery.getCharge() * getMaxDamage(stack) / battery.getMaxCapacity());
        /*if(stack.getTag() == null){
            CompoundNBT compoundNBT = new CompoundNBT();
            CompoundNBT batteryComp = Battery.WriteBattery(getBattery());
            compoundNBT.put("battery", batteryComp);
            stack.setTag(compoundNBT);
        }
        if(ReadPowered(stack.getTag())) {
        }
        return 0;*/
    }
}
