package com.gempire.blocks;

import com.gempire.init.ModTE;
import com.gempire.tileentities.GemSeedTE;
import com.gempire.tileentities.InjectorTE;
import com.gempire.tileentities.PedistalTE;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class PedistalBlock extends HorizontalDirectionalBlock implements EntityBlock {
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    private final boolean sensitive;
    private UUID owner;
    protected static final VoxelShape Z_AXIS_AABB = Block.box(5.0D, 0.0D, 4.0D, 11.0D, 16.0D, 11.0D);


    public PedistalBlock(Properties p_49795_, Boolean sensitive) {
        super(p_49795_);
        this.registerDefaultState(this.stateDefinition.any().setValue(POWERED, Boolean.FALSE).setValue(FACING, Direction.NORTH));
        this.sensitive = sensitive;
    }
    public boolean mayPlaceOn(BlockState p_51042_, BlockGetter p_51043_, BlockPos p_51044_) {
        return p_51042_.is(BlockTags.DIRT) || p_51042_.is(Blocks.FARMLAND);
    }
    private int getPressDuration() {
        return this.sensitive ? 60 : 20;
    }
    public InteractionResult use(BlockState blockState, Level level, BlockPos pos, Player player, InteractionHand interactionHand, BlockHitResult hitResult) {
        if (!level.isClientSide) {
            if (level.getBlockEntity(pos) instanceof PedistalTE toto) {
                if (player.isCrouching()) {
                    if (toto.owner == null) {
                        toto.owner = player.getUUID();
                        player.sendSystemMessage(Component.translatable("block.gempire.pedistal.setprivate"));
                    } else if (toto.owner == player.getUUID()) {
                        toto.owner = null;
                        player.sendSystemMessage(Component.translatable("block.gempire.pedistal.setpublic"));
                    }
                }
                else if (toto.owner == null || player.getUUID() == toto.owner) {
                    if (blockState.getValue(POWERED)) {
                        return InteractionResult.CONSUME;
                    } else {
                        this.press(blockState, level, pos);
                        this.playSound(player, level, pos, true);
                        level.gameEvent(player, GameEvent.BLOCK_ACTIVATE, pos);
                        return InteractionResult.sidedSuccess(true);
                    }
                } else {
                    player.sendSystemMessage(Component.translatable("block.gempire.pedistal.notowner"));
                }
            }
        }
        return InteractionResult.sidedSuccess(true);
    }
    public VoxelShape getShape(BlockState p_154346_, BlockGetter p_154347_, BlockPos p_154348_, CollisionContext p_154349_) {
        return Z_AXIS_AABB;
    }

    public void press(BlockState p_51117_, Level p_51118_, BlockPos p_51119_) {
        p_51118_.setBlock(p_51119_, p_51117_.setValue(POWERED, Boolean.TRUE), 3);
        this.updateNeighbours(p_51117_, p_51118_, p_51119_);
        p_51118_.scheduleTick(p_51119_, this, this.getPressDuration());
    }

    protected void playSound(@Nullable Player p_51068_, LevelAccessor p_51069_, BlockPos p_51070_, boolean p_51071_) {
        p_51069_.playSound(p_51071_ ? p_51068_ : null, p_51070_, this.getSound(p_51071_), SoundSource.BLOCKS, 0.3F, p_51071_ ? 0.6F : 0.5F);
    }
    public BlockState getStateForPlacement(BlockPlaceContext p_51377_) {
        if(p_51377_.getClickedFace().equals(Direction.UP)) {
            return this.defaultBlockState().setValue(FACING, p_51377_.getHorizontalDirection().getOpposite());
        }
        else
        {
            return null;
        }
    }
    protected SoundEvent getSound(boolean p_51102_) {
        return null;
    }

    public void onRemove(BlockState p_51095_, Level p_51096_, BlockPos p_51097_, BlockState p_51098_, boolean p_51099_) {
        if (!p_51099_ && !p_51095_.is(p_51098_.getBlock())) {
            if (p_51095_.getValue(POWERED)) {
                this.updateNeighbours(p_51095_, p_51096_, p_51097_);
            }

            super.onRemove(p_51095_, p_51096_, p_51097_, p_51098_, p_51099_);
        }
    }

    public int getSignal(BlockState p_51078_, BlockGetter p_51079_, BlockPos p_51080_, Direction p_51081_) {
        return p_51078_.getValue(POWERED) ? 15 : 0;
    }

    public int getDirectSignal(BlockState p_51109_, BlockGetter p_51110_, BlockPos p_51111_, Direction p_51112_) {
        return p_51109_.getValue(POWERED) ? 15 : 0;
    }

    public boolean isSignalSource(BlockState p_51114_) {
        return true;
    }

    public void tick(BlockState p_220903_, ServerLevel p_220904_, BlockPos p_220905_, RandomSource p_220906_) {
        if (p_220903_.getValue(POWERED)) {
            if (this.sensitive) {
                this.checkPressed(p_220903_, p_220904_, p_220905_);
            } else {
                p_220904_.setBlock(p_220905_, p_220903_.setValue(POWERED, Boolean.valueOf(false)), 3);
                this.updateNeighbours(p_220903_, p_220904_, p_220905_);
                this.playSound((Player)null, p_220904_, p_220905_, false);
                p_220904_.gameEvent((Entity)null, GameEvent.BLOCK_DEACTIVATE, p_220905_);
            }

        }
    }

    public void entityInside(BlockState p_51083_, Level p_51084_, BlockPos p_51085_, Entity p_51086_) {
        if (!p_51084_.isClientSide && this.sensitive && !p_51083_.getValue(POWERED)) {
            this.checkPressed(p_51083_, p_51084_, p_51085_);
        }
    }

    private void checkPressed(BlockState p_51121_, Level p_51122_, BlockPos p_51123_) {
        List<? extends Entity> list = p_51122_.getEntitiesOfClass(EnderDragon.class, p_51121_.getShape(p_51122_, p_51123_).bounds().move(p_51123_));
        boolean flag = !list.isEmpty();
        boolean flag1 = p_51121_.getValue(POWERED);
        if (flag != flag1) {
            p_51122_.setBlock(p_51123_, p_51121_.setValue(POWERED, Boolean.valueOf(flag)), 3);
            this.updateNeighbours(p_51121_, p_51122_, p_51123_);
            this.playSound((Player)null, p_51122_, p_51123_, flag);
            p_51122_.gameEvent(list.stream().findFirst().orElse(null), flag ? GameEvent.BLOCK_ACTIVATE : GameEvent.BLOCK_DEACTIVATE, p_51123_);
        }

        if (flag) {
            p_51122_.scheduleTick(new BlockPos(p_51123_), this, this.getPressDuration());
        }

    }

    private void updateNeighbours(BlockState p_51125_, Level p_51126_, BlockPos p_51127_) {
        p_51126_.updateNeighborsAt(p_51127_, this);
        p_51126_.updateNeighborsAt(p_51127_.below(), this);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_51101_) {
        p_51101_.add(POWERED);
        p_51101_.add(FACING);
    }
    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new PedistalTE(pos, state);
    }
}
