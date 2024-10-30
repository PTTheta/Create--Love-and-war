package net.pttheta.loveandwar.item.custom;

import com.simibubi.create.AllSoundEvents;
import com.simibubi.create.content.fluids.tank.FluidTankBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.pttheta.loveandwar.blocks.LAWBlocks;
import net.pttheta.loveandwar.blocks.petrochem.DistillationColumnBlockEntity;

public class ThermostatItem extends Item {
    public ThermostatItem(Properties properties) {
        super(properties);
    }

    public InteractionResult useOn(UseOnContext context) {
        BlockEntity var3 = context.getLevel().getBlockEntity(context.getClickedPos());
        if (!(var3 instanceof FluidTankBlockEntity ftbe)) {
            return super.useOn(context);
        } else {
            ItemStack item = context.getPlayer().getItemInHand(InteractionHand.MAIN_HAND);
            BlockPos cPos = ftbe.getController();
            int width = ftbe.getControllerBE().getWidth();
            int height = ftbe.getControllerBE().getHeight();

            int x;
            int z;
            int y;
            for(x = 0; x < width; ++x) {
                for(z = 0; z < width; ++z) {
                    for(y = 0; y < height && (item.getCount() != 0 || context.getPlayer().isCreative()); ++y) {
                        context.getLevel().setBlock(cPos.offset(x, y, z), LAWBlocks.DISTILLATION_COLUMN.getDefaultState(), 1);
                        context.getLevel().updateNeighborsAt(cPos.offset(x, y, z), LAWBlocks.DISTILLATION_COLUMN.getDefaultState().getBlock());
                    }
                }
            }

            for(x = 0; x < width; ++x) {
                for(z = 0; z < width; ++z) {
                    for(y = 0; y < height; ++y) {
                        BlockEntity var11 = context.getLevel().getBlockEntity(cPos.offset(x, y, z));
                        if (var11 instanceof DistillationColumnBlockEntity dtbe) {
                            dtbe.updateVerticalMulti();
                            dtbe.updateConnectivity();
                        }
                    }
                }
            }

            if (!context.getPlayer().isCreative()) {
                item.shrink(1);
            }

            return InteractionResult.SUCCESS;
        }
    }
}
