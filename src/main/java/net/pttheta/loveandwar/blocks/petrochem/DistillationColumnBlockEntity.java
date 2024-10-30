//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//
package net.pttheta.loveandwar.blocks.petrochem;

import com.simibubi.create.api.connectivity.ConnectivityHandler;
import com.simibubi.create.content.equipment.goggles.IHaveGoggleInformation;
import com.simibubi.create.content.fluids.tank.BoilerHeaters;
import com.simibubi.create.content.fluids.tank.FluidTankBlock;
import com.simibubi.create.content.fluids.tank.FluidTankBlock.Shape;
import com.simibubi.create.content.processing.recipe.HeatCondition;
import com.simibubi.create.foundation.blockEntity.IMultiBlockEntityContainer;
import com.simibubi.create.foundation.blockEntity.SmartBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import com.simibubi.create.foundation.fluid.FluidIngredient;
import com.simibubi.create.foundation.fluid.SmartFluidTank;
import com.simibubi.create.foundation.recipe.RecipeFinder;
import com.simibubi.create.foundation.utility.animation.LerpedFloat;
import com.simibubi.create.foundation.utility.animation.LerpedFloat.Chaser;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler.FluidAction;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.pttheta.loveandwar.recipe.RecipeRegister;
import net.pttheta.loveandwar.recipe.petrochemical.FractionalDistillationRecipe;

public class DistillationColumnBlockEntity extends SmartBlockEntity implements IMultiBlockEntityContainer.Fluid, IHaveGoggleInformation {
    
    public int FRACTIONAL_HEIGHT = 1;
    public boolean SPEED_BOOST = true;
    private static final int MAX_SIZE = 3;
    public float progress;
    public int heat;
    protected LazyOptional<IFluidHandler> fluidCapability = LazyOptional.of(() -> {
        return this.tankInventory;
    });
    protected boolean forceFluidLevelUpdate = true;
    public FluidTank tankInventory = this.createInventory();
    protected BlockPos controller;
    protected BlockPos lastKnownPos;
    protected boolean updateConnectivity = false;
    public boolean window = false;
    protected int luminosity;
    protected int width = 1;
    protected int height = 1;
    protected BlockPos bottomCPos;
    private static final int SYNC_RATE = 8;
    protected int syncCooldown;
    protected boolean queuedSync;
    private LerpedFloat fluidLevel;
    public boolean hasDistillationC;
    int processingTime = -1;
    FractionalDistillationRecipe currentRecipe;

    public DistillationColumnBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.refreshCapability();
    }

    private BlockPos getBottomControllerPos() {
        if (this.isBottom()) {
            return this.getController();
        } else {
            BlockEntity block = this.level.getBlockEntity(this.getBlockPos().below());
            if (block instanceof DistillationColumnBlockEntity) {
                DistillationColumnBlockEntity be = (DistillationColumnBlockEntity)block;
                return be.getBottomControllerPos();
            } else {
                return this.getController();
            }
        }
    }

    protected SmartFluidTank createInventory() {
        return new SmartFluidTank(getCapacityMultiplier(), this::onFluidStackChanged);
    }

    public int updateTemperature() {
        if(this.getControllerBE() == null) return 0;
        int width = this.getControllerBE().width;
        int heatN = 0;
        if (this.getController() != null && width != 0) {
            for(int xOffset = 0; xOffset < width; ++xOffset) {
                for(int zOffset = 0; zOffset < width; ++zOffset) {
                    BlockPos pos = this.getController().offset(xOffset, -1, zOffset);
                    BlockState blockState = this.level.getBlockState(pos);
                    float heat = BoilerHeaters.getActiveHeat(this.level, pos, blockState);
                    heatN = (int)((float)heatN + heat);
                }
            }

            return heatN / (width * width);
        } else {
            return 0;
        }
    }

    public void updateConnectivity() {
        this.updateConnectivity = false;
        if (!this.level.isClientSide) {
            if (this.isController()) {
                ConnectivityHandler.formMulti(this);
            }
        }
    }

    private void startProcessing() {
        if (this.currentRecipe != null) {
            this.processingTime = this.currentRecipe.getProcessingDuration();
        }
    }

    public void tick() {
        this.bottomCPos = this.getBottomControllerPos();
        if (this.isController() && this.isBottom()) {
            int oldHeat = this.heat;
            this.heat = this.updateTemperature();
            if (oldHeat != this.heat && this.processingTime <= -1) {
                List<Recipe<?>> r = this.getMatchingRecipes();
                if (!r.isEmpty()) {
                    this.currentRecipe = (FractionalDistillationRecipe)r.get(0);
                    if (this.getHeat(this.currentRecipe.getRequiredHeat()) <= this.heat) {
                        this.startProcessing();
                    } else {
                        this.currentRecipe = null;
                    }
                } else {
                    this.currentRecipe = null;
                }
            }

            if (this.processingTime > -1 && this.currentRecipe != null) {
                boolean canFill = true;
                int i = 0;

                while(i < this.currentRecipe.getFluidResults().size() * FRACTIONAL_HEIGHT) {
                    BlockEntity block = this.level.getBlockEntity(this.getBlockPos().above(i + 1));
                    if (block instanceof DistillationColumnBlockEntity) {
                        DistillationColumnBlockEntity be = (DistillationColumnBlockEntity)block;
                        if (be.getControllerBE().width != this.getControllerBE().width) {
                            canFill = false;
                            break;
                        }

                        if (i % FRACTIONAL_HEIGHT == 0 && be.getTank(0).getFluidAmount() <= getCapacityMultiplier() * this.width * this.width - ((FluidStack)this.currentRecipe.getFluidResults().get(i / FRACTIONAL_HEIGHT)).getAmount()) {
                            ++i;
                            continue;
                        }

                        canFill = false;
                        break;
                    }

                    canFill = false;
                    break;
                }

                if (canFill) {
                    this.processingTime -= Mth.clamp(SPEED_BOOST ? this.width * this.width : 1, 1, this.processingTime);
                }

                if (this.tankInventory.getFluid().getAmount() < (this.currentRecipe.getFluidIngredients().get(0)).getRequiredAmount() || this.getHeat(this.currentRecipe.getRequiredHeat()) > this.heat) {
                    this.currentRecipe = null;
                    this.processingTime = -1;
                    this.onFluidStackChanged(this.tankInventory.getFluid());
                }
            }

            if (this.processingTime == 0 && this.currentRecipe != null) {
                if (this.tankInventory.getFluid().getAmount() >= (this.currentRecipe.getFluidIngredients().get(0)).getRequiredAmount() && this.getHeat(this.currentRecipe.getRequiredHeat()) <= this.heat) {
                    this.tankInventory.drain((this.currentRecipe.getFluidIngredients().get(0)).getRequiredAmount(), FluidAction.EXECUTE);
                    if (this.currentRecipe != null) {
                        for(int i = 0; i < this.currentRecipe.getFluidResults().size() * FRACTIONAL_HEIGHT; ++i) {
                            BlockEntity block = this.level.getBlockEntity(this.getBlockPos().above(i + 1));
                            if (!(block instanceof DistillationColumnBlockEntity)) {
                                break;
                            }

                            DistillationColumnBlockEntity be = (DistillationColumnBlockEntity)block;
                            if (be.getControllerBE().width != this.getControllerBE().width) {
                                break;
                            }

                            if (i % FRACTIONAL_HEIGHT == 0) {
                                be.tankInventory.fill(this.currentRecipe.getFluidResults().get(i / FRACTIONAL_HEIGHT), FluidAction.EXECUTE);
                            }
                        }
                    }
                }

                this.currentRecipe = null;
                this.processingTime = -1;
                this.onFluidStackChanged(this.tankInventory.getFluid());
            }

            if (this.currentRecipe == null || this.width != 0 && this.currentRecipe.getProcessingDuration() != 0) {
                this.progress = this.currentRecipe != null ? (float)this.processingTime / ((float)this.currentRecipe.getProcessingDuration() / (float)(this.width * this.width)) : 0.0F;
            }
        }

        super.tick();
        if (this.syncCooldown > 0) {
            --this.syncCooldown;
            if (this.syncCooldown == 0 && this.queuedSync) {
                this.sendData();
            }
        }

        if (this.lastKnownPos == null) {
            this.lastKnownPos = this.getBlockPos();
        } else if (!this.lastKnownPos.equals(this.worldPosition) && this.worldPosition != null) {
            this.onPositionChanged();
            return;
        }

        if (this.updateConnectivity) {
            this.updateConnectivity();
        }

        if (this.fluidLevel != null) {
            this.fluidLevel.tickChaser();
        }

    }

    public BlockPos getLastKnownPos() {
        return this.lastKnownPos;
    }

    public boolean isController() {
        return this.controller == null || this.worldPosition.getX() == this.controller.getX() && this.worldPosition.getY() == this.controller.getY() && this.worldPosition.getZ() == this.controller.getZ();
    }

    public void initialize() {
        super.initialize();
        this.sendData();
        if (this.level.isClientSide) {
            this.invalidateRenderBoundingBox();
        }

    }

    private void onPositionChanged() {
        this.removeController(true);
        this.lastKnownPos = this.worldPosition;
    }

    protected List<Recipe<?>> getMatchingRecipes() {
        List<Recipe<?>> list = RecipeFinder.get(new Object(), this.level, (recipe) -> {
            return recipe.getType() == RecipeRegister.FRACTIONAL_DISTILLATION.getType();
        });
        return (List)list.stream().filter((r) -> {
            return !((FractionalDistillationRecipe)r).getFluidIngredients().isEmpty() ? ((FluidIngredient)((FractionalDistillationRecipe)r).getFluidIngredients().get(0)).getMatchingFluidStacks().contains(this.tankInventory.getFluid()) && this.getHeat(((FractionalDistillationRecipe)r).getRequiredHeat()) <= this.heat : false;
        }).collect(Collectors.toList());
    }

    int getHeat(HeatCondition heatCondition) {
        if (heatCondition == HeatCondition.SUPERHEATED) {
            return 2;
        } else {
            return heatCondition == HeatCondition.HEATED ? 1 : 0;
        }
    }

    protected void onFluidStackChanged(FluidStack newFluidStack) {

        if(!this.hasLevel()) return;
        if (this.processingTime <= -1) {
            List<Recipe<?>> r = this.getMatchingRecipes();
            if (!r.isEmpty()) {
                this.currentRecipe = (FractionalDistillationRecipe)r.get(0);
                this.startProcessing();
            } else {
                this.currentRecipe = null;
            }
        }

        FluidType attributes = newFluidStack.getFluid().getFluidType();
        int luminosity = (int)((float)attributes.getLightLevel(newFluidStack) / 1.2F);
        boolean reversed = attributes.isLighterThanAir();
        int maxY = (int)(this.getFillState() * (float)this.height + 1.0F);

        for(int yOffset = 0; yOffset < this.height; ++yOffset) {
            boolean isBright = reversed ? this.height - yOffset <= maxY : yOffset < maxY;
            int actualLuminosity = isBright ? luminosity : (luminosity > 0 ? 1 : 0);

            for(int xOffset = 0; xOffset < this.width; ++xOffset) {
                for(int zOffset = 0; zOffset < this.width; ++zOffset) {
                    BlockPos pos = this.worldPosition.offset(xOffset, yOffset, zOffset);
                    DistillationColumnBlockEntity tankAt = (DistillationColumnBlockEntity)ConnectivityHandler.partAt(this.getType(), this.level, pos);
                    if (tankAt != null) {
                        this.level.updateNeighbourForOutputSignal(pos, tankAt.getBlockState().getBlock());
                        if (tankAt.luminosity != actualLuminosity) {
                            tankAt.setLuminosity(actualLuminosity);
                        }
                    }
                }
            }
        }

        if (!this.level.isClientSide) {
            this.setChanged();
            this.sendData();
        }

        if (this.isVirtual()) {
            if (this.fluidLevel == null) {
                this.fluidLevel = LerpedFloat.linear().startWithValue((double)this.getFillState());
            }

            this.fluidLevel.chase((double)this.getFillState(), 0.5, Chaser.EXP);
        }
    }

    protected void setLuminosity(int luminosity) {
        if (!this.level.isClientSide) {
            if (this.luminosity != luminosity) {
                this.luminosity = luminosity;
                this.sendData();
            }
        }
    }

    public DistillationColumnBlockEntity getControllerBE() {
        if (this.isController()) {
            return this;
        } else {
            BlockEntity blockEntity = this.level.getBlockEntity(this.controller);
            return blockEntity instanceof DistillationColumnBlockEntity ? (DistillationColumnBlockEntity)blockEntity : null;
        }
    }

    public void applyFluidTankSize(int blocks) {
        this.tankInventory.setCapacity(blocks * getCapacityMultiplier());
        int overflow = this.tankInventory.getFluidAmount() - this.tankInventory.getCapacity();
        if (overflow > 0) {
            this.tankInventory.drain(overflow, FluidAction.EXECUTE);
        }

        this.forceFluidLevelUpdate = true;
    }

    public void removeController(boolean keepFluids) {
        if (!this.level.isClientSide) {
            this.updateConnectivity = true;
            if (!keepFluids) {
                this.applyFluidTankSize(1);
            }

            this.controller = null;
            this.width = 1;
            this.height = 1;
            this.onFluidStackChanged(this.tankInventory.getFluid());
            BlockState state = this.getBlockState();
            if (DistillationColumnBlock.isTank(state)) {
                state = (BlockState)state.setValue(DistillationColumnBlock.BOTTOM, true);
                state = (BlockState)state.setValue(DistillationColumnBlock.TOP, true);
                state = (BlockState)state.setValue(DistillationColumnBlock.SHAPE, this.window ? Shape.WINDOW : Shape.PLAIN);
                this.getLevel().setBlock(this.worldPosition, state, 6);
            }

            this.refreshCapability();
            this.setChanged();
            this.sendData();
        }
    }

    public void toggleWindows() {
        DistillationColumnBlockEntity be = this.getControllerBE();
        if (be != null) {
            be.setWindows(!be.window);
        }
    }

    public void sendData() {
        if (this.syncCooldown > 0) {
            this.queuedSync = true;
        } else {
            super.sendData();
            this.queuedSync = false;
            this.syncCooldown = 8;
        }
    }

    public void setWindows(boolean window) {
        if (!window || FRACTIONAL_HEIGHT == 1 || (this.getBottomControllerPos().getY() + 1 - this.worldPosition.getY()) % FRACTIONAL_HEIGHT == 0) {
            this.window = window;

            for(int yOffset = 0; yOffset < this.height; ++yOffset) {
                for(int xOffset = 0; xOffset < this.width; ++xOffset) {
                    for(int zOffset = 0; zOffset < this.width; ++zOffset) {
                        BlockPos pos = this.worldPosition.offset(xOffset, yOffset, zOffset);
                        BlockState blockState = this.level.getBlockState(pos);
                        if (DistillationColumnBlock.isTank(blockState)) {
                            FluidTankBlock.Shape shape = Shape.PLAIN;
                            if (window) {
                                if (this.width == 1) {
                                    shape = Shape.WINDOW;
                                }

                                if (this.width == 2) {
                                    shape = xOffset == 0 ? (zOffset == 0 ? Shape.WINDOW_NW : Shape.WINDOW_SW) : (zOffset == 0 ? Shape.WINDOW_NE : Shape.WINDOW_SE);
                                }

                                if (this.width == 3 && Math.abs(Math.abs(xOffset) - Math.abs(zOffset)) == 1) {
                                    shape = Shape.WINDOW;
                                }
                            }

                            this.level.setBlock(pos, (BlockState)blockState.setValue(DistillationColumnBlock.SHAPE, shape), 22);
                            this.level.getChunkSource().getLightEngine().checkBlock(pos);
                        }
                    }
                }
            }

        }
    }

    public void setController(BlockPos controller) {
        if (!this.level.isClientSide || this.isVirtual()) {
            if (!controller.equals(this.controller)) {
                this.controller = controller;
                this.refreshCapability();
                this.setChanged();
                this.sendData();
            }
        }
    }

    private void refreshCapability() {
        LazyOptional<IFluidHandler> oldCap = this.fluidCapability;
        this.fluidCapability = LazyOptional.of(() -> {
            return this.handlerForCapability();
        });
        oldCap.invalidate();
    }

    private IFluidHandler handlerForCapability() {
        return (IFluidHandler)(this.isController() ? this.tankInventory : (this.getControllerBE() != null ? this.getControllerBE().handlerForCapability() : new FluidTank(0)));
    }

    public BlockPos getController() {
        return this.isController() ? this.worldPosition : this.controller;
    }

    protected AABB createRenderBoundingBox() {
        return this.isController() ? super.createRenderBoundingBox().expandTowards((double)(this.width - 1), (double)(this.height - 1), (double)(this.width - 1)) : super.createRenderBoundingBox();
    }

    public DistillationColumnBlockEntity getOtherDistillationColumnBlockEntity(Direction direction) {
        BlockEntity otherBE = this.level.getBlockEntity(this.worldPosition.relative(direction));
        return otherBE instanceof DistillationColumnBlockEntity ? (DistillationColumnBlockEntity)otherBE : null;
    }

    public boolean addToGoggleTooltip(List<Component> tooltip, boolean isPlayerSneaking) {
        if (FRACTIONAL_HEIGHT != 1 && (this.getBottomControllerPos().getY() + 1 - this.worldPosition.getY()) % FRACTIONAL_HEIGHT != 0) {
            return false;
        } else {
            DistillationColumnBlockEntity controllerBE = this.getControllerBE();
            return controllerBE == null ? false : this.containedFluidTooltip(tooltip, isPlayerSneaking, controllerBE.getCapability(ForgeCapabilities.FLUID_HANDLER));
        }
    }

    protected void read(CompoundTag compound, boolean clientPacket) {
        super.read(compound, clientPacket);
        this.hasDistillationC = compound.getBoolean("HasDistillationC");
        BlockPos controllerBefore = this.controller;
        int prevSize = this.width;
        //this.FRACTIONAL_HEIGHT = this.width;
        int prevHeight = this.height;
        int prevLum = this.luminosity;
        this.updateConnectivity = compound.contains("Uninitialized");
        this.luminosity = compound.getInt("Luminosity");
        this.controller = null;
        this.lastKnownPos = null;
        if (compound.contains("LastKnownPos")) {
            this.lastKnownPos = NbtUtils.readBlockPos(compound.getCompound("LastKnownPos"));
        }

        if (compound.contains("Controller")) {
            this.controller = NbtUtils.readBlockPos(compound.getCompound("Controller"));
        }

        if (this.isController()) {
            this.window = compound.getBoolean("Window");
            this.width = compound.getInt("Size");
            this.height = compound.getInt("Height");
            this.tankInventory.setCapacity(this.getTotalTankSize() * getCapacityMultiplier());
            this.tankInventory.readFromNBT(compound.getCompound("TankContent"));
            if (this.tankInventory.getSpace() < 0) {
                this.tankInventory.drain(-this.tankInventory.getSpace(), FluidAction.EXECUTE);
            }
        }

        if (compound.contains("ForceFluidLevel") || this.fluidLevel == null) {
            this.fluidLevel = LerpedFloat.linear().startWithValue((double)this.getFillState());
        }

        if (clientPacket) {
            boolean changeOfController = controllerBefore == null ? this.controller != null : !controllerBefore.equals(this.controller);
            if (changeOfController || prevSize != this.width || prevHeight != this.height) {
                if (this.hasLevel()) {
                    this.level.sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 16);
                }

                if (this.isController()) {
                    this.tankInventory.setCapacity(getCapacityMultiplier() * this.getTotalTankSize());
                }

                this.invalidateRenderBoundingBox();
            }

            if (this.isController()) {
                float fillState = this.getFillState();
                if (compound.contains("ForceFluidLevel") || this.fluidLevel == null) {
                    this.fluidLevel = LerpedFloat.linear().startWithValue((double)fillState);
                }

                this.fluidLevel.chase((double)fillState, 0.5, Chaser.EXP);
                this.processingTime = compound.getInt("Progress");
            }

            if (this.luminosity != prevLum && this.hasLevel()) {
                this.level.getChunkSource().getLightEngine().checkBlock(this.worldPosition);
            }

            if (compound.contains("LazySync")) {
                this.fluidLevel.chase((double)this.fluidLevel.getChaseTarget(), 0.125, Chaser.EXP);
            }

        }
    }

    public float getFillState() {
        return (float)this.tankInventory.getFluidAmount() / (float)this.tankInventory.getCapacity();
    }

    public void write(CompoundTag compound, boolean clientPacket) {
        compound.putBoolean("HasDistillationC", this.hasDistillationC);
        if (this.updateConnectivity) {
            compound.putBoolean("Uninitialized", true);
        }

        if (this.lastKnownPos != null) {
            compound.put("LastKnownPos", NbtUtils.writeBlockPos(this.lastKnownPos));
        }

        if (!this.isController()) {
            compound.put("Controller", NbtUtils.writeBlockPos(this.controller));
        }

        if (this.isController()) {
            compound.putBoolean("Window", this.window);
            compound.put("TankContent", this.tankInventory.writeToNBT(new CompoundTag()));
            compound.putInt("Size", this.width);
            compound.putInt("Height", this.height);
            compound.putInt("Progress", this.processingTime);
        }

        compound.putInt("Luminosity", this.luminosity);
        super.write(compound, clientPacket);
        if (clientPacket) {
            if (this.forceFluidLevelUpdate) {
                compound.putBoolean("ForceFluidLevel", true);
            }

            if (this.queuedSync) {
                compound.putBoolean("LazySync", true);
            }

            this.forceFluidLevelUpdate = false;
        }
    }

    @Nonnull
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (FRACTIONAL_HEIGHT != 1 && (this.getBottomControllerPos().getY() + 1 - this.worldPosition.getY()) % FRACTIONAL_HEIGHT != 0) {
            return super.getCapability(cap, side);
        } else {
            if (!this.fluidCapability.isPresent()) {
                this.refreshCapability();
            }

            return cap == ForgeCapabilities.FLUID_HANDLER ? this.fluidCapability.cast() : super.getCapability(cap, side);
        }
    }

    public void invalidate() {
        super.invalidate();
    }

    public void addBehaviours(List<BlockEntityBehaviour> behaviours) {
    }

    public int getTotalTankSize() {
        return this.width * this.width;
    }

    public static int getCapacityMultiplier() {
        return 500;
    }

    public LerpedFloat getFluidLevel() {
        return this.fluidLevel;
    }

    public void preventConnectivityUpdate() {
        this.updateConnectivity = false;
    }

    public void notifyMultiUpdated() {
        BlockState state = this.getBlockState();
        if (DistillationColumnBlock.isTank(state)) {
            state = (BlockState)state.setValue(DistillationColumnBlock.BOTTOM, this.getBottomConnectivity());
            state = (BlockState)state.setValue(DistillationColumnBlock.TOP, this.getTopConnectivity());
            this.level.setBlock(this.getBlockPos(), state, 6);
        }

        if (this.isController()) {
            this.setWindows(this.window);
        }

        this.onFluidStackChanged(this.tankInventory.getFluid());
        this.setChanged();
    }

    private boolean getBottomConnectivity() {
        BlockEntity block = this.level.getBlockEntity(this.getBlockPos().below());
        if (block instanceof DistillationColumnBlockEntity be) {
            DistillationColumnBlockEntity otherControllerBE = be.getControllerBE();
            if (otherControllerBE != null) {
                DistillationColumnBlockEntity controllerBE = this.getControllerBE();
                if (controllerBE != null && controllerBE.getBlockPos().below().equals(otherControllerBE.getBlockPos())) {
                    return controllerBE.getWidth() != otherControllerBE.getWidth();
                }
            }
        }

        return true;
    }

    private boolean getTopConnectivity() {
        BlockEntity block = this.level.getBlockEntity(this.getBlockPos().above());
        if (block instanceof DistillationColumnBlockEntity be) {
            DistillationColumnBlockEntity otherControllerBE = be.getControllerBE();
            if (otherControllerBE != null) {
                DistillationColumnBlockEntity controllerBE = this.getControllerBE();
                if (controllerBE != null && controllerBE.getBlockPos().above().equals(otherControllerBE.getBlockPos())) {
                    return controllerBE.getWidth() != otherControllerBE.getWidth();
                }
            }
        }

        return true;
    }

    public void setExtraData(@Nullable Object data) {
        if (data instanceof Boolean) {
            this.window = (Boolean)data;
        }

    }

    @Nullable
    public Object getExtraData() {
        return this.window;
    }

    public Object modifyExtraData(Object data) {
        if (data instanceof Boolean windows) {
            windows = windows | this.window;
            return windows;
        } else {
            return data;
        }
    }

    public Direction.Axis getMainConnectionAxis() {
        return Axis.Y;
    }

    public int getMaxLength(Direction.Axis longAxis, int width) {
        return longAxis == Axis.Y ? 1 : this.getMaxWidth();
    }

    public int getMaxWidth() {
        return 3;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int width) {
        this.width = width;
        //this.FRACTIONAL_HEIGHT = width;
    }

    public boolean hasTank() {
        return true;
    }

    public int getTankSize(int tank) {
        return getCapacityMultiplier();
    }

    public void setTankSize(int tank, int blocks) {
        this.applyFluidTankSize(blocks);
    }

    public IFluidTank getTank(int tank) {
        return this.tankInventory;
    }

    public FluidStack getFluid(int tank) {
        return this.tankInventory.getFluid().copy();
    }

    public void updateVerticalMulti() {
        BlockState state = this.getBlockState();
        if (DistillationColumnBlock.isTank(state)) {
            state = (BlockState)state.setValue(DistillationColumnBlock.BOTTOM, this.getBottomConnectivity());
            state = (BlockState)state.setValue(DistillationColumnBlock.TOP, this.getTopConnectivity());
            if (state != this.getBlockState()) {
                this.level.setBlock(this.getBlockPos(), state, 3);
            }
        }

        BlockEntity block = this.level.getBlockEntity(this.getBlockPos().below());
        if (block instanceof DistillationColumnBlockEntity be) {
            be.updateVerticalMulti();
        }

    }

    public boolean isBottom() {
        return !(this.level.getBlockEntity(this.getBlockPos().below()) instanceof DistillationColumnBlockEntity);
    }
}
