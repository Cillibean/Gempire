package com.gempire.init;

import com.gempire.Gempire;
import com.gempire.aura.ClientAuraData;
import com.gempire.aura.PlayerAura;
import com.gempire.aura.PlayerAuraProvider;
import com.gempire.entities.abilities.AbilityAbundance;
import com.gempire.entities.abilities.AbilityLootmaster;
import com.gempire.entities.abilities.base.Ability;
import com.gempire.entities.ai.EntityAIAvoidSpinel;
import com.gempire.entities.ai.EntityAIFollowSpinel;
import com.gempire.entities.bases.EntityGem;
import com.gempire.entities.gems.*;
import com.gempire.networking.AuraDataSyncS2C;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.common.BasicItemListing;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LootingLevelEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = Gempire.MODID)
public class EventHandler {

    static boolean hasAura = false;

    @SubscribeEvent
    public void OnEntitySpawn(EntityJoinLevelEvent event){
        if(event.getEntity() instanceof LivingEntity) {
            if (event.getEntity().getClassification(true) == MobCategory.MONSTER) {
                Mob entity = (Mob) event.getEntity();
                entity.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(entity, EntityGem.class, 1, false, false, (p_234199_0_) -> {
                    return !(((EntityGem)p_234199_0_).doesIntimidate());
                }));
                if (entity instanceof PathfinderMob) {
                    entity.goalSelector.addGoal(1, new AvoidEntityGoal<>((PathfinderMob) entity, EntityTopaz.class, 6.0F, 1.0D, 1.2D));
                    entity.goalSelector.addGoal(2, new EntityAIFollowSpinel((Mob) event.getEntity(), 1.1D));
                }
            }
            else if (event.getEntity().getClassification(true) == MobCategory.CREATURE)
            {
                if (!(event.getEntity() instanceof EntityGem || event.getEntity() instanceof TamableAnimal))
                {
                    Mob entity = (Mob) event.getEntity();
                    entity.goalSelector.addGoal(3, new EntityAIFollowSpinel((Mob) event.getEntity(), 1.1D));
                    entity.goalSelector.addGoal(2, new EntityAIAvoidSpinel((Mob) event.getEntity(), 1.1D));
                }
            }
        }
    }

    @SubscribeEvent
    public void OnLootmaster(LootingLevelEvent event) {
        if (event.getEntity().getLastHurtByMob() instanceof EntityGarnet) {
            EntityGarnet garnet = ((EntityGarnet) event.getEntity().getLastHurtByMob());
            for(Ability ability : garnet.getAbilityPowers()){
                if(ability instanceof AbilityLootmaster){
                    if (garnet.isPrimary())
                        event.setLootingLevel(4);
                    else if (!garnet.isDefective())
                        event.setLootingLevel(2);
                }
            }
        }
    }

    @SubscribeEvent
    public void addCustomTrades(VillagerTradesEvent event) {
        if (event.getType() == VillagerProfession.MASON) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            int villagerLevel = 3;
            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 20),
                    new ItemStack(ModItems.PRIMED_SLATE.get(), 1),
                    new ItemStack(ModItems.INACTIVE_AQUAMARINE_BASE.get()),
                    10,3,0.02F));
            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 20),
                    new ItemStack(ModItems.PRIMED_SLATE.get(), 1),
                    new ItemStack(ModItems.INACTIVE_EMERALD_BASE.get()),
                    10,3,0.02F));
            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 20),
                    new ItemStack(ModItems.PRIMED_SLATE.get(), 1),
                    new ItemStack(ModItems.INACTIVE_PERIDOT_BASE.get()),
                    10,3,0.02F));
            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 20),
                    new ItemStack(ModItems.PRIMED_SLATE.get(), 1),
                    new ItemStack(ModItems.INACTIVE_SAPPHIRE_BASE.get()),
                    10,3,0.02F));
            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 20),
                    new ItemStack(ModItems.PRIMED_SLATE.get(), 1),
                    new ItemStack(ModItems.INACTIVE_TOURMALINE_BASE.get()),
                    10,3,0.02F));
            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 20),
                    new ItemStack(ModItems.PRIMED_SLATE.get(), 1),
                    new ItemStack(ModItems.INACTIVE_ZIRCON_BASE.get()),
                    10,3,0.02F));
        }
    }


    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if(event.getObject() instanceof Player) {
            if(!event.getObject().getCapability(PlayerAuraProvider.PLAYER_AURA).isPresent()) {
                event.addCapability(new ResourceLocation(Gempire.MODID, "properties"), new PlayerAuraProvider());
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        if(event.isWasDeath()) {
            event.getOriginal().getCapability(PlayerAuraProvider.PLAYER_AURA).ifPresent(oldStore -> {
                event.getOriginal().getCapability(PlayerAuraProvider.PLAYER_AURA).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);
                });
            });
        }
    }

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(PlayerAura.class);
    }


    @SubscribeEvent
    public static void onPlayerJoinWorld(EntityJoinLevelEvent event) {
        if(!event.getLevel().isClientSide()) {
            if(event.getEntity() instanceof ServerPlayer player) {
                player.getCapability(PlayerAuraProvider.PLAYER_AURA).ifPresent(aura -> {
                    ModMessages.sendToPlayer(new AuraDataSyncS2C(0), player);
                    //ClientAuraData.set(0);
                });
            }
        }
    }

    @SubscribeEvent
    public static void cancelAura(TickEvent.PlayerTickEvent event) {
        if (!hasAura && (event.player.hasEffect(ModEffects.PINK_AURA.get())) || event.player.hasEffect(ModEffects.WHITE_AURA.get()) || event.player.hasEffect(ModEffects.BLUE_AURA.get()) || event.player.hasEffect(ModEffects.YELLOW_AURA.get())) {
            hasAura = true;
        }

        if (hasAura && !(event.player.hasEffect(ModEffects.PINK_AURA.get()))
                && !(event.player.hasEffect(ModEffects.BLUE_AURA.get()))
                && !(event.player.hasEffect(ModEffects.YELLOW_AURA.get()))
                && !(event.player.hasEffect(ModEffects.WHITE_AURA.get()))) {
            ClientAuraData.set(0);
        }
    }

    @SubscribeEvent
    public void WanderingTraderTrades(WandererTradesEvent event) {
        event.getGenericTrades().add(new BasicItemListing(new ItemStack(Items.EMERALD, 20), new ItemStack(ModItems.PINK_CHROMA.get(), 1), new ItemStack(ModItems.PINK_DESTABILIZER.get(), 1), 1, 10, 10));
        event.getGenericTrades().add(new BasicItemListing(new ItemStack(Items.EMERALD, 20), new ItemStack(ModItems.YELLOW_CHROMA.get(), 1), new ItemStack(ModItems.YELLOW_DESTABILIZER.get(), 1), 1, 10, 10));
        event.getGenericTrades().add(new BasicItemListing(new ItemStack(Items.EMERALD, 20), new ItemStack(ModItems.BLUE_CHROMA.get(), 1), new ItemStack(ModItems.BLUE_DESTABILIZER.get(), 1), 1, 10, 10));
        event.getGenericTrades().add(new BasicItemListing(new ItemStack(Items.EMERALD, 20), new ItemStack(ModItems.WHITE_CHROMA.get(), 1), new ItemStack(ModItems.WHITE_DESTABILIZER.get(), 1), 1, 10, 10));

        event.getGenericTrades().add(new BasicItemListing(new ItemStack(Items.DIAMOND, 3), new ItemStack(Items.EMERALD, 0), new ItemStack(ModItems.PINK_ESSENCE_BUCKET.get(), 1), 3, 10, 10));
        event.getGenericTrades().add(new BasicItemListing(new ItemStack(Items.DIAMOND, 3), new ItemStack(Items.EMERALD, 0), new ItemStack(ModItems.BLUE_ESSENCE_BUCKET.get(), 1), 3, 10, 10));
        event.getGenericTrades().add(new BasicItemListing(new ItemStack(Items.DIAMOND, 3), new ItemStack(Items.EMERALD, 0), new ItemStack(ModItems.WHITE_ESSENCE_BUCKET.get(), 1), 3, 10, 10));
        event.getGenericTrades().add(new BasicItemListing(new ItemStack(Items.DIAMOND, 3), new ItemStack(Items.EMERALD, 0), new ItemStack(ModItems.YELLOW_ESSENCE_BUCKET.get(), 1), 3, 10, 10));

        event.getGenericTrades().add(new BasicItemListing(new ItemStack(Items.EMERALD, 15), new ItemStack(Items.EMERALD, 0), new ItemStack(ModItems.RED_CHROMA.get(), 1), 6, 10, 10));
        event.getGenericTrades().add(new BasicItemListing(new ItemStack(Items.EMERALD, 15), new ItemStack(Items.EMERALD, 0), new ItemStack(ModItems.ORANGE_CHROMA.get(), 1), 6, 10, 10));
        event.getGenericTrades().add(new BasicItemListing(new ItemStack(Items.EMERALD, 15), new ItemStack(Items.EMERALD, 0), new ItemStack(ModItems.YELLOW_CHROMA.get(), 1), 6, 10, 10));
        event.getGenericTrades().add(new BasicItemListing(new ItemStack(Items.EMERALD, 15), new ItemStack(Items.EMERALD, 0), new ItemStack(ModItems.LIME_CHROMA.get(), 1), 6, 10, 10));
        event.getGenericTrades().add(new BasicItemListing(new ItemStack(Items.EMERALD, 15), new ItemStack(Items.EMERALD, 0), new ItemStack(ModItems.GREEN_CHROMA.get(), 1), 6, 10, 10));
        event.getGenericTrades().add(new BasicItemListing(new ItemStack(Items.EMERALD, 15), new ItemStack(Items.EMERALD, 0), new ItemStack(ModItems.LIGHT_BLUE_CHROMA.get(), 1), 6, 10, 10));
        event.getGenericTrades().add(new BasicItemListing(new ItemStack(Items.EMERALD, 15), new ItemStack(Items.EMERALD, 0), new ItemStack(ModItems.BLUE_CHROMA.get(), 1), 6, 10, 10));
        event.getGenericTrades().add(new BasicItemListing(new ItemStack(Items.EMERALD, 15), new ItemStack(Items.EMERALD, 0), new ItemStack(ModItems.PURPLE_CHROMA.get(), 1), 6, 10, 10));
        event.getGenericTrades().add(new BasicItemListing(new ItemStack(Items.EMERALD, 15), new ItemStack(Items.EMERALD, 0), new ItemStack(ModItems.MAGENTA_CHROMA.get(), 1), 6, 10, 10));
        event.getGenericTrades().add(new BasicItemListing(new ItemStack(Items.EMERALD, 15), new ItemStack(Items.EMERALD, 0), new ItemStack(ModItems.PINK_CHROMA.get(), 1), 6, 10, 10));
        event.getGenericTrades().add(new BasicItemListing(new ItemStack(Items.EMERALD, 15), new ItemStack(Items.EMERALD, 0), new ItemStack(ModItems.BROWN_CHROMA.get(), 1), 6, 10, 10));
        event.getGenericTrades().add(new BasicItemListing(new ItemStack(Items.EMERALD, 15), new ItemStack(Items.EMERALD, 0), new ItemStack(ModItems.WHITE_CHROMA.get(), 1), 6, 10, 10));
        event.getGenericTrades().add(new BasicItemListing(new ItemStack(Items.EMERALD, 15), new ItemStack(Items.EMERALD, 0), new ItemStack(ModItems.LIGHT_GRAY_CHROMA.get(), 1), 6, 10, 10));
        event.getGenericTrades().add(new BasicItemListing(new ItemStack(Items.EMERALD, 15), new ItemStack(Items.EMERALD, 0), new ItemStack(ModItems.GRAY_CHROMA.get(), 1), 6, 10, 10));
        event.getGenericTrades().add(new BasicItemListing(new ItemStack(Items.EMERALD, 15), new ItemStack(Items.EMERALD, 0), new ItemStack(ModItems.BLACK_CHROMA.get(), 1), 6, 10, 10));
        event.getRareTrades().add(new BasicItemListing(new ItemStack(Items.EMERALD, 25), new ItemStack(Items.EMERALD, 0), new ItemStack(ModItems.SPECIAL_CHROMA.get(), 1), 3, 10, 10));

        event.getGenericTrades().add(new BasicItemListing(new ItemStack(Items.DIAMOND, 4), new ItemStack(Items.EMERALD, 0), new ItemStack(ModItems.GEM_SCRAP.get(), 1), 6, 10, 10));
        event.getRareTrades().add(new BasicItemListing(new ItemStack(Items.DIAMOND, 6), new ItemStack(Items.EMERALD, 0), new ItemStack(ModItems.GEM_ALLOY.get(), 2), 3, 10, 10));

        event.getGenericTrades().add(new BasicItemListing(new ItemStack(Items.DIAMOND, 15), new ItemStack(ModItems.PRISMATIC_BLOCK.get(), 1), new ItemStack(ModItems.CONFRACTOR_BODY.get(), 1), 1, 10, 10));
        event.getGenericTrades().add(new BasicItemListing(new ItemStack(Items.DIAMOND, 10), new ItemStack(ModItems.PRISMATIC_BLOCK.get(), 1), new ItemStack(ModItems.CONFRACTOR_TIP.get(), 1), 1, 10, 10));
    }
}
