package net.pttheta.loveandwar.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.SpiderEyesLayer;
import net.minecraft.resources.ResourceLocation;
import net.pttheta.loveandwar.LoveAndWarMod;
import net.pttheta.loveandwar.entity.custom.RobotdogEntity;

public class RobotdogRenderer extends MobRenderer<RobotdogEntity,RobotdogModel<RobotdogEntity>> {

    private static final ResourceLocation YELLOW = new ResourceLocation(LoveAndWarMod.MODID,"textures/entity/robotdog.png");
    private static final ResourceLocation RED = new ResourceLocation(LoveAndWarMod.MODID,"textures/entity/robotdog_red.png");
    private static final ResourceLocation WHITE = new ResourceLocation(LoveAndWarMod.MODID,"textures/entity/robotdog_white.png");
    private static final ResourceLocation LIGHT_GREY = new ResourceLocation(LoveAndWarMod.MODID,"textures/entity/robotdog_light_grey.png");
    private static final ResourceLocation GREY = new ResourceLocation(LoveAndWarMod.MODID,"textures/entity/robotdog_grey.png");
    private static final ResourceLocation BLACK = new ResourceLocation(LoveAndWarMod.MODID,"textures/entity/robotdog_black.png");
    private static final ResourceLocation BROWN = new ResourceLocation(LoveAndWarMod.MODID,"textures/entity/robotdog_brown.png");
    private static final ResourceLocation ORANGE = new ResourceLocation(LoveAndWarMod.MODID,"textures/entity/robotdog_orange.png");
    private static final ResourceLocation LIME = new ResourceLocation(LoveAndWarMod.MODID,"textures/entity/robotdog_lime.png");
    private static final ResourceLocation GREEN = new ResourceLocation(LoveAndWarMod.MODID,"textures/entity/robotdog_green.png");
    private static final ResourceLocation CYAN = new ResourceLocation(LoveAndWarMod.MODID,"textures/entity/robotdog_cyan.png");
    private static final ResourceLocation LIGHT_BLUE = new ResourceLocation(LoveAndWarMod.MODID,"textures/entity/robotdog_light_blue.png");
    private static final ResourceLocation BLUE = new ResourceLocation(LoveAndWarMod.MODID,"textures/entity/robotdog_blue.png");
    private static final ResourceLocation PURPLE = new ResourceLocation(LoveAndWarMod.MODID,"textures/entity/robotdog_purple.png");
    private static final ResourceLocation MAGENTA = new ResourceLocation(LoveAndWarMod.MODID,"textures/entity/robotdog_magenta.png");
    private static final ResourceLocation PINK = new ResourceLocation(LoveAndWarMod.MODID,"textures/entity/robotdog_pink.png");
    private static final ResourceLocation VALKYRIE = new ResourceLocation(LoveAndWarMod.MODID,"textures/entity/robotdog_valkyrie.png");
    private static final ResourceLocation BUBBLEGUM = new ResourceLocation(LoveAndWarMod.MODID,"textures/entity/robotdog_bubblegum.png");
    private static final ResourceLocation RACING = new ResourceLocation(LoveAndWarMod.MODID,"textures/entity/robotdog_racing.png");

    public RobotdogRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new RobotdogModel<>(pContext.bakeLayer(ModModelLayers.ROBOT_DOG_LAYER)), 1f);
        this.addLayer(new RobotDogEyeLayer(this));
        this.addLayer(new RobotDogArmorLayer(this));
    }

    @Override
    public ResourceLocation getTextureLocation(RobotdogEntity pEntity) {

        switch(pEntity.getPaintjob()){
            case 0: return YELLOW;
            case 1: return RED;
            case 2: return WHITE;
            case 3: return LIGHT_GREY;
            case 4: return GREY;
            case 5: return BLACK;
            case 6: return BROWN;
            case 7: return ORANGE;
            case 8: return LIME;
            case 9: return GREEN;
            case 10: return CYAN;
            case 11: return LIGHT_BLUE;
            case 12: return BLUE;
            case 13: return PURPLE;
            case 14: return MAGENTA;
            case 15: return PINK;
            case 16: return VALKYRIE;
            case 17: return BUBBLEGUM;
            case 18: return RACING;
            default: return YELLOW;
        }
    }

    @Override
    public void render(RobotdogEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        if(pEntity.isBaby()){
            pPoseStack.scale(0.5f,0.5f,0.5f);
        }

        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }
}
