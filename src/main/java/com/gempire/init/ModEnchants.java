package com.gempire.init;

import com.gempire.Gempire;
import com.gempire.enchants.GemProtectionEnchant;
import com.gempire.enchants.ShatterEnchant;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ModEnchants {
    public static ArrayList<Enchantment> VANILLA_ENCHANTMENTS = new ArrayList<>();

    public static ArrayList<Enchantment> GEMPIRE_ENCHANTMENTS = new ArrayList<>();
    public static HashMap<Item, Integer> POWERFUL_ITEMS_DISCOUNT = new HashMap<>();

    public static final DeferredRegister<Enchantment> ENCHANTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, Gempire.MODID);

    public static final RegistryObject<Enchantment> GEM_PROTECTION = ENCHANTS.register("gem_protection",
            () -> new GemProtectionEnchant(Enchantment.Rarity.RARE, EnchantmentCategory.ARMOR, EquipmentSlot.values()));

    public static final RegistryObject<Enchantment> SHATTER = ENCHANTS.register("shatter",
            () -> new ShatterEnchant(Enchantment.Rarity.RARE, EnchantmentCategory.WEAPON, EquipmentSlot.MAINHAND));

    public static void registerItemDiscounts(){
        ModEnchants.POWERFUL_ITEMS_DISCOUNT.put(Items.GOLD_INGOT, 25);
        ModEnchants.POWERFUL_ITEMS_DISCOUNT.put(Items.GOLDEN_APPLE, 35);
        ModEnchants.POWERFUL_ITEMS_DISCOUNT.put(Items.DIAMOND, 50);
        ModEnchants.POWERFUL_ITEMS_DISCOUNT.put(Items.HEART_OF_THE_SEA, 20000);
        ModEnchants.POWERFUL_ITEMS_DISCOUNT.put(Items.NETHER_STAR, 20000);
        ModEnchants.POWERFUL_ITEMS_DISCOUNT.put(Items.NETHERITE_INGOT, 100);
    }

    public static void registerVanillaEnchantments(){
        ArrayList<Enchantment> enchants = new ArrayList<>();
        enchants.add(Enchantments.FLAMING_ARROWS);
        enchants.add(Enchantments.BLOCK_FORTUNE);
        enchants.add(Enchantments.FISHING_SPEED);
        enchants.add(Enchantments.RIPTIDE);
        enchants.add(Enchantments.SMITE);
        enchants.add(Enchantments.QUICK_CHARGE);
        enchants.add(Enchantments.AQUA_AFFINITY);
        enchants.add(Enchantments.BANE_OF_ARTHROPODS);
        enchants.add(Enchantments.BLAST_PROTECTION);
        enchants.add(Enchantments.CHANNELING);
        enchants.add(Enchantments.DEPTH_STRIDER);
        enchants.add(Enchantments.BLOCK_EFFICIENCY);
        enchants.add(Enchantments.FALL_PROTECTION);
        enchants.add(Enchantments.FIRE_ASPECT);
        enchants.add(Enchantments.FIRE_PROTECTION);
        enchants.add(Enchantments.FROST_WALKER);
        enchants.add(Enchantments.IMPALING);
        enchants.add(Enchantments.INFINITY_ARROWS);
        enchants.add(Enchantments.KNOCKBACK);
        enchants.add(Enchantments.MOB_LOOTING);
        enchants.add(Enchantments.LOYALTY);
        enchants.add(Enchantments.FISHING_LUCK);
        enchants.add(Enchantments.MENDING);
        enchants.add(Enchantments.MULTISHOT);
        enchants.add(Enchantments.PIERCING);
        enchants.add(Enchantments.POWER_ARROWS);
        enchants.add(Enchantments.PROJECTILE_PROTECTION);
        enchants.add(Enchantments.ALL_DAMAGE_PROTECTION);
        enchants.add(Enchantments.PUNCH_ARROWS);
        enchants.add(Enchantments.RESPIRATION);
        enchants.add(Enchantments.SHARPNESS);
        enchants.add(Enchantments.SILK_TOUCH);
        enchants.add(Enchantments.SOUL_SPEED);
        enchants.add(Enchantments.SWEEPING_EDGE);
        enchants.add(Enchantments.THORNS);
        enchants.add(Enchantments.UNBREAKING);

        ModEnchants.VANILLA_ENCHANTMENTS = enchants;
    }

    public static void registerPrimeRequiredEnchantments(){
        ArrayList<Enchantment> enchants = new ArrayList<>();
        enchants.add(ModEnchants.GEM_PROTECTION.get());
        enchants.add(ModEnchants.SHATTER.get());

        ModEnchants.GEMPIRE_ENCHANTMENTS = enchants;
    }
}
