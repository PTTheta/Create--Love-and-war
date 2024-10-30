package net.pttheta.loveandwar.blocks.stamping;

import com.jozufozu.flywheel.backend.Backend;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.simibubi.create.AllPartialModels;
import com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer;
import com.simibubi.create.foundation.render.CachedBufferer;
import com.simibubi.create.foundation.render.SuperByteBuffer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.state.BlockState;

import static net.minecraft.world.level.block.state.properties.BlockStateProperties.HORIZONTAL_FACING;

public class StampingPressRenderer extends KineticBlockEntityRenderer<StampingPressBlockEntity> {
    public StampingPressRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public boolean shouldRenderOffScreen(StampingPressBlockEntity be) {
        return true;
    }

    @Override
    protected void renderSafe(StampingPressBlockEntity be, float partialTicks, PoseStack ms, MultiBufferSource buffer,
                              int light, int overlay) {
        super.renderSafe(be, partialTicks, ms, buffer, light, overlay);

        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        ItemStack itemStack = be.getRenderStack();

        ms.pushPose();
        ms.translate(0.5f, -be.getStampingBehaviour().getRenderedHeadOffset(partialTicks) * be.getStampingBehaviour().mode.headOffset, 0.5f);
        ms.scale(0.5f, 0.5f, 0.5f);
        ms.mulPose(Axis.XP.rotationDegrees(270));

        itemRenderer.renderStatic(itemStack, ItemDisplayContext.FIXED, getLightLevel(be.getLevel(), be.getBlockPos()),
                OverlayTexture.NO_OVERLAY, ms, buffer, be.getLevel(), 1);
        ms.popPose();

        if (Backend.canUseInstancing(be.getLevel()))
            return;


        BlockState blockState = be.getBlockState();
        StampingBehaviour drawingBehaviour = be.getStampingBehaviour();
        float renderedHeadOffset =
                drawingBehaviour.getRenderedHeadOffset(partialTicks) * drawingBehaviour.mode.headOffset;

        SuperByteBuffer headRender = CachedBufferer.partialFacing(AllPartialModels.MECHANICAL_PRESS_HEAD, blockState,
                blockState.getValue(HORIZONTAL_FACING));
        headRender.translate(0, -renderedHeadOffset, 0)
                .light(light)
                .renderInto(ms, buffer.getBuffer(RenderType.solid()));
    }

    @Override
    protected BlockState getRenderedBlockState(StampingPressBlockEntity be) {
        return shaft(getRotationAxisOf(be));
    }

    private int getLightLevel(Level level, BlockPos pos) {
        int bLight = level.getBrightness(LightLayer.BLOCK, pos);
        int sLight = level.getBrightness(LightLayer.SKY, pos);
        return LightTexture.pack(bLight, sLight);
    }
}
