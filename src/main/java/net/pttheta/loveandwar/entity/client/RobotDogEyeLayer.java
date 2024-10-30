package net.pttheta.loveandwar.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.SpiderModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.pttheta.loveandwar.LoveAndWarMod;
import net.pttheta.loveandwar.entity.custom.RobotdogEntity;

public class RobotDogEyeLayer<T extends Entity, M extends RobotdogModel<T>> extends EyesLayer<T, M> {

    private static final ResourceLocation BLUE = new ResourceLocation(LoveAndWarMod.MODID,"textures/entity/robotdog_eye_blue.png");
    private static final ResourceLocation YELLOW = new ResourceLocation(LoveAndWarMod.MODID,"textures/entity/robotdog_eye_yellow.png");
    private static final ResourceLocation RED = new ResourceLocation(LoveAndWarMod.MODID,"textures/entity/robotdog_eye_red.png");

    public ResourceLocation getEyeColor(RobotdogEntity e){

        switch(e.getPaintjob()){
            case 0: return BLUE;
            case 7: return BLUE;
            case 8: return BLUE;
            case 9: return BLUE;
            case 10: return RED;
            case 11: return RED;
            case 12: return RED;
            case 13: return BLUE;
            case 14: return BLUE;
            case 15: return BLUE;
            case 16: return BLUE;
            default: return YELLOW;
        }
    }
    public RobotDogEyeLayer(RenderLayerParent<T, M> pRenderer) {
        super(pRenderer);
    }
    @Override
    public RenderType renderType() {

        return null;
    }

    @Override
    public void render(PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, T pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTicks, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        ResourceLocation resourcelocation = getEyeColor((RobotdogEntity) pLivingEntity);
        if (resourcelocation != null && !pLivingEntity.isInvisible()) {
            VertexConsumer vertexconsumer = pBuffer.getBuffer(RenderType.eyes(resourcelocation));
            this.getParentModel().renderToBuffer(pPoseStack, vertexconsumer, pPackedLight, LivingEntityRenderer.getOverlayCoords((LivingEntity) pLivingEntity, 0.0F), 1.0F, 1.0F, 1.0F, 1.0F);
        }
    }
}
