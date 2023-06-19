package com.gempire.fluids;

import com.gempire.blocks.PinkEssenceBlock;
import com.gempire.init.ModBlocks;
import com.gempire.init.ModFluids;
import com.gempire.init.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.*;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.fluids.FluidInteractionRegistry;
import net.minecraftforge.fluids.ForgeFlowingFluid;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.function.Supplier;

import static net.minecraftforge.fluids.FluidInteractionRegistry.addInteraction;

public abstract class PinkEssenceFluid extends ForgeFlowingFluid {
    protected PinkEssenceFluid(Properties properties) {
        super(properties);
    }

    public Fluid getFlowing() {
        return ModFluids.PINK_ESSENCE_FLOWING.get();
    }

    public Fluid getSource() {
        return ModFluids.PINK_ESSENCE.get();
    }

    public Item getBucket() {
        return ModItems.PINK_ESSENCE_BUCKET.get();
    }

    public void animateTick(Level p_230606_, BlockPos p_230607_, FluidState p_230608_, RandomSource p_230609_) {
        if (!p_230608_.isSource() && !p_230608_.getValue(FALLING)) {
            if (p_230609_.nextInt(64) == 0) {
                //p_230606_.playLocalSound((double)p_230607_.getX() + 0.5D, (double)p_230607_.getY() + 0.5D, (double)p_230607_.getZ() + 0.5D, SoundEvents.WATER_AMBIENT, SoundSource.BLOCKS, p_230609_.nextFloat() * 0.25F + 0.75F, p_230609_.nextFloat() + 0.5F, false);
            }
        } else if (p_230609_.nextInt(10) == 0) {
            //p_230606_.addParticle(ParticleTypes.UNDERWATER, (double)p_230607_.getX() + p_230609_.nextDouble(), (double)p_230607_.getY() + p_230609_.nextDouble(), (double)p_230607_.getZ() + p_230609_.nextDouble(), 0.0D, 0.0D, 0.0D);
        }

    }

    @Nullable
    public ParticleOptions getDripParticle() {
        return ParticleTypes.DRIPPING_WATER;
    }

    protected boolean canConvertToSource() {
        return false;
    }

    protected void beforeDestroyingBlock(LevelAccessor p_76450_, BlockPos p_76451_, BlockState p_76452_) {
        BlockEntity blockentity = p_76452_.hasBlockEntity() ? p_76450_.getBlockEntity(p_76451_) : null;
        Block.dropResources(p_76452_, p_76450_, p_76451_, blockentity);
    }

    public int getSlopeFindDistance(LevelReader p_76464_) {
        return 4;
    }

    public BlockState createLegacyBlock(FluidState p_76466_) {
        return ModBlocks.PINK_ESSENCE_BLOCK.get().defaultBlockState().setValue(LiquidBlock.LEVEL, Integer.valueOf(getLegacyLevel(p_76466_)));
    }

    public boolean isSame(Fluid p_76456_) {
        return p_76456_ == ModFluids.PINK_ESSENCE.get() || p_76456_ == ModFluids.PINK_ESSENCE_FLOWING.get();
    }

    public int getDropOff(LevelReader p_76469_) {
        return 1;
    }

    @Override
    public int getAmount(FluidState p_164509_) {
        return 0;
    }

    public int getTickDelay(LevelReader p_76454_) {
        return 5;
    }

    public boolean canBeReplacedWith(FluidState p_76458_, BlockGetter p_76459_, BlockPos p_76460_, Fluid p_76461_, Direction p_76462_) {
        return p_76462_ == Direction.DOWN && !p_76461_.is(ModFluidTags.PINK_ESSENCE);
    }

    protected float getExplosionResistance() {
        return 100.0F;
    }

    public Optional<SoundEvent> getPickupSound() {
        return Optional.of(SoundEvents.BUCKET_FILL);
    }

    public static class Flowing extends PinkEssenceFluid {
        public Flowing(Properties properties) {
            super(properties);
        }

        protected void createFluidStateDefinition(StateDefinition.Builder<Fluid, FluidState> p_76476_) {
            super.createFluidStateDefinition(p_76476_);
            p_76476_.add(LEVEL);
        }

        public int getAmount(FluidState p_76480_) {
            return p_76480_.getValue(LEVEL);
        }

        public boolean isSource(FluidState p_76478_) {
            return false;
        }
    }

    public static class Source extends PinkEssenceFluid {
        public Source(Properties properties) {
            super(properties);
        }

        public int getAmount(FluidState p_76485_) {
            return 8;
        }

        public boolean isSource(FluidState p_76483_) {
            return true;
        }
    }
}
