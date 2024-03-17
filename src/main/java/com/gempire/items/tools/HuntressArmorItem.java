package com.gempire.items.tools;

import com.gempire.init.ModArmorMaterials;
import com.gempire.init.ModEffects;
import com.gempire.init.ModItems;
import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.renderer.GeoArmorRenderer;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

public class HuntressArmorItem extends ArmorItem implements GeoItem {
    public HuntressArmorItem(ArmorMaterial p_40386_, Type p_266831_, Properties p_40388_) {
        super(p_40386_, p_266831_, p_40388_);
    }

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    // Create our armor model/renderer for forge and return it
    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private GeoArmorRenderer<?> renderer;

            @Override
            public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> original) {
                if (this.renderer == null)
                    this.renderer = new HuntressArmorRenderer();

                // This prepares our GeoArmorRenderer for the current render frame.
                // These parameters may be null however, so we don't do anything further with them
                this.renderer.prepForRender(livingEntity, itemStack, equipmentSlot, original);

                return this.renderer;
            }
        });
    }

    // Let's add our animation controller
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, 20, state -> {
            // Apply our generic idle animation.
            // Whether it plays or not is decided down below.
            state.setAnimation(DefaultAnimations.IDLE);

            // Let's gather some data from the state to use below
            // This is the entity that is currently wearing/holding the item
            Entity entity = state.getData(DataTickets.ENTITY);

            // We'll just have ArmorStands always animate, so we can return here
            if (entity instanceof ArmorStand)
                return PlayState.CONTINUE;

            // For this example, we only want the animation to play if the entity is wearing all pieces of the armor
            // Let's collect the armor pieces the entity is currently wearing
            Set<Item> wornArmor = new ObjectOpenHashSet<>();

            for (ItemStack stack : entity.getArmorSlots()) {
                // We can stop immediately if any of the slots are empty
                if (stack.isEmpty())
                    return PlayState.STOP;

                wornArmor.add(stack.getItem());
            }

            // Check each of the pieces match our set
            boolean isFullSet = wornArmor.containsAll(ObjectArrayList.of(
                    ModItems.GUARDIAN_BOOTS.get(),
                    ModItems.GUARDIAN_LEGGINGS.get(),
                    ModItems.GUARDIAN_CHESTPLATE.get(),
                    ModItems.GUARDIAN_HELMET.get()));

            // Play the animation if the full set is being worn, otherwise stop
            return isFullSet ? PlayState.CONTINUE : PlayState.STOP;
        }));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }


    private static final Map<ArmorMaterial, MobEffectInstance> MATERIAL_TO_EFFECT_MAP =
            (new ImmutableMap.Builder<ArmorMaterial, MobEffectInstance>())
                    .put(ModArmorMaterials.HUNTRESS, new MobEffectInstance(ModEffects.HUNTRESS.get(), 200, 1, false, false, true)).build();


    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {
        if(!world.isClientSide()) {
            if(hasFullSuitOfArmorOn(player)) {
                evaluateArmorEffects(player);
            }
        }
    }

    private void evaluateArmorEffects(Player player) {
        for (Map.Entry<ArmorMaterial, MobEffectInstance> entry : MATERIAL_TO_EFFECT_MAP.entrySet()) {
            ArmorMaterial mapArmorMaterial = entry.getKey();
            MobEffectInstance mapStatusEffect = entry.getValue();

            if(hasCorrectArmorOn(mapArmorMaterial, player)) {
                addStatusEffectForMaterial(player, mapArmorMaterial, mapStatusEffect);
            }
        }
    }

    private void addStatusEffectForMaterial(Player player, ArmorMaterial mapArmorMaterial,
                                            MobEffectInstance mapStatusEffect) {
        boolean hasPlayerEffect = player.hasEffect(mapStatusEffect.getEffect());

        if(hasCorrectArmorOn(mapArmorMaterial, player)) {
            player.addEffect(new MobEffectInstance(mapStatusEffect.getEffect(),
                    mapStatusEffect.getDuration(), 1, false, false, true));

            //if(new Random().nextFloat() > 0.6f) { // 40% of damaging the armor! Possibly!
            //    player.getInventory().hurtArmor(DamageSource.MAGIC, 1f, new int[]{0, 1, 2, 3});
            //}
        }
    }

    private boolean hasFullSuitOfArmorOn(Player player) {
        ItemStack boots = player.getInventory().getArmor(0);
        ItemStack leggings = player.getInventory().getArmor(1);
        ItemStack breastplate = player.getInventory().getArmor(2);
        ItemStack helmet = player.getInventory().getArmor(3);

        return !helmet.isEmpty() && !breastplate.isEmpty()
                && !leggings.isEmpty() && !boots.isEmpty();
    }

    private boolean hasCorrectArmorOn(ArmorMaterial material, Player player) {
        for (ItemStack armorStack: player.getInventory().armor) {
            if(!(armorStack.getItem() instanceof ArmorItem)) {
                return false;
            }
        }

        ArmorItem boots = ((ArmorItem)player.getInventory().getArmor(0).getItem());
        ArmorItem leggings = ((ArmorItem)player.getInventory().getArmor(1).getItem());
        ArmorItem breastplate = ((ArmorItem)player.getInventory().getArmor(2).getItem());
        ArmorItem helmet = ((ArmorItem)player.getInventory().getArmor(3).getItem());

        return helmet.getMaterial() == material && breastplate.getMaterial() == material &&
                leggings.getMaterial() == material && boots.getMaterial() == material;
    }
}
