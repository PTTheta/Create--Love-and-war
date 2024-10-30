package net.pttheta.loveandwar.blocks.petrochem;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.api.connectivity.ConnectivityHandler;
import com.simibubi.create.content.equipment.wrench.IWrenchable;
import com.simibubi.create.content.fluids.tank.FluidTankBlock;
import com.simibubi.create.content.fluids.tank.FluidTankBlockEntity;
import com.simibubi.create.content.schematics.requirement.ISpecialBlockItemRequirement;
import com.simibubi.create.content.schematics.requirement.ItemRequirement;
import com.simibubi.create.foundation.block.IBE;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.pttheta.loveandwar.blocks.LAWBlockEntityTypes;
import net.pttheta.loveandwar.item.ModItems;

import java.util.ArrayList;
import java.util.List;

public class DistillationColumnBlock extends Block implements IBE<DistillationColumnBlockEntity>, IWrenchable, ISpecialBlockItemRequirement {

    public static final BooleanProperty TOP = BooleanProperty.create("top");
    public static final BooleanProperty BOTTOM = BooleanProperty.create("bottom");
    public static final EnumProperty<FluidTankBlock.Shape> SHAPE = EnumProperty.create("shape", FluidTankBlock.Shape.class);


    public DistillationColumnBlock(Properties pProperties) {
        super(pProperties);
    }
    public static boolean isTank(BlockState state) {
        return state.getBlock() instanceof DistillationColumnBlock;
    }

    @Override
    public InteractionResult onSneakWrenched(BlockState state, UseOnContext context) {
        if(context.getLevel().getBlockEntity(context.getClickedPos()) instanceof DistillationColumnBlockEntity dtbe){
            int width = dtbe.getControllerBE().getWidth();
            BlockPos pos = dtbe.getController();
            FluidStack stackInTank = dtbe.getCapability(ForgeCapabilities.FLUID_HANDLER).orElse(new FluidTank(1)).getFluidInTank(0).copy();

            for (int x = 0; x < width; x++) {
                for (int z = 0; z < width; z++) {
                    context.getLevel().setBlock(pos.offset(x, 0, z), AllBlocks.FLUID_TANK.getDefaultState(), 3);
                    context.getLevel().updateNeighborsAt(pos.offset(x, 0, z), AllBlocks.FLUID_TANK.get());
                }
            }
            if(!stackInTank.isEmpty() && context.getLevel().getBlockEntity(pos) instanceof FluidTankBlockEntity be){
                IFluidHandler tank = be.getCapability(ForgeCapabilities.FLUID_HANDLER).orElse(new FluidTank(1));
                tank.fill(stackInTank, IFluidHandler.FluidAction.EXECUTE);
            }
            if(!context.getPlayer().isCreative())
                context.getPlayer().getInventory().placeItemBackInInventory(new ItemStack(ModItems.THERMOSTAT.get().asItem(),1));
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighbourState, LevelAccessor level, BlockPos pos, BlockPos neighbourPos) {
        if (direction == Direction.DOWN && neighbourState.getBlock() != this)
            withBlockEntityDo(level, pos, DistillationColumnBlockEntity::updateTemperature);
        return super.updateShape(state, direction, neighbourState, level, pos, neighbourPos);
    }

    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos otherPos, boolean p_60514_) {
        super.neighborChanged(state, level, pos, block, otherPos, p_60514_);
        withBlockEntityDo(level, pos, DistillationColumnBlockEntity::updateVerticalMulti);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(TOP, BOTTOM, SHAPE);
    }


    @Override
    public ItemRequirement getRequiredItems(BlockState state, BlockEntity blockEntity) {
        List<ItemStack> list = new ArrayList<>();
        list.add(AllBlocks.FLUID_TANK.asStack());
        list.add(new ItemStack(ModItems.THERMOSTAT.get()));
        return new ItemRequirement(ItemRequirement.ItemUseType.CONSUME, list);
    }

    public Class<DistillationColumnBlockEntity> getBlockEntityClass() {
        return DistillationColumnBlockEntity.class;
    }

    @Override
    public InteractionResult onWrenched(BlockState state, UseOnContext context) {
        withBlockEntityDo(context.getLevel(), context.getClickedPos(), DistillationColumnBlockEntity::toggleWindows);
        return InteractionResult.SUCCESS;
    }
    public BlockEntityType<? extends DistillationColumnBlockEntity> getBlockEntityType() {
        return (BlockEntityType) LAWBlockEntityTypes.DISTILLATION_COLUMN.get();
    }

    @Override
    public void onRemove(BlockState state, Level world, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.hasBlockEntity() && (state.getBlock() != newState.getBlock() || !newState.hasBlockEntity())) {
            BlockEntity be = world.getBlockEntity(pos);
            if (!(be instanceof DistillationColumnBlockEntity))
                return;
            DistillationColumnBlockEntity tankBE = (DistillationColumnBlockEntity) be;
            world.removeBlockEntity(pos);
            ConnectivityHandler.splitMulti(tankBE);
        }
    }

    @Override
    public ItemStack getCloneItemStack(BlockGetter getter, BlockPos pos, BlockState state) {
        return AllBlocks.FLUID_TANK.asStack();
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean moved) {
        if (oldState.getBlock() == state.getBlock())
            return;
        if (moved)
            return;
        withBlockEntityDo(level, pos, DistillationColumnBlockEntity::updateConnectivity);
        withBlockEntityDo(level, pos, DistillationColumnBlockEntity::updateVerticalMulti);
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        if (mirror == Mirror.NONE)
            return state;
        boolean x = mirror == Mirror.FRONT_BACK;
        return switch (state.getValue(SHAPE)) {
            case WINDOW_NE -> state.setValue(SHAPE, x ? FluidTankBlock.Shape.WINDOW_NW : FluidTankBlock.Shape.WINDOW_SE);
            case WINDOW_NW -> state.setValue(SHAPE, x ? FluidTankBlock.Shape.WINDOW_NE : FluidTankBlock.Shape.WINDOW_SW);
            case WINDOW_SE -> state.setValue(SHAPE, x ? FluidTankBlock.Shape.WINDOW_SW : FluidTankBlock.Shape.WINDOW_NE);
            case WINDOW_SW -> state.setValue(SHAPE, x ? FluidTankBlock.Shape.WINDOW_SE : FluidTankBlock.Shape.WINDOW_NW);
            default -> state;
        };
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        for (int i = 0; i < rotation.ordinal(); i++)
            state = rotateOnce(state);
        return state;
    }

    private BlockState rotateOnce(BlockState state) {
        return switch (state.getValue(SHAPE)) {
            case WINDOW_NE -> state.setValue(SHAPE, FluidTankBlock.Shape.WINDOW_SE);
            case WINDOW_NW -> state.setValue(SHAPE, FluidTankBlock.Shape.WINDOW_NE);
            case WINDOW_SE -> state.setValue(SHAPE, FluidTankBlock.Shape.WINDOW_SW);
            case WINDOW_SW -> state.setValue(SHAPE, FluidTankBlock.Shape.WINDOW_NW);
            default -> state;
        };
    }
}
