package net.pttheta.loveandwar.event;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.pttheta.loveandwar.LoveAndWarMod;

import net.pttheta.loveandwar.entity.client.ModModelLayers;
import net.pttheta.loveandwar.entity.client.RobotdogModel;


@Mod.EventBusSubscriber(modid = LoveAndWarMod.MODID,bus = Mod.EventBusSubscriber.Bus.MOD,value = Dist.CLIENT)
public class ModEventBusClientEvents {
    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event){
        event.registerLayerDefinition(ModModelLayers.ROBOT_DOG_LAYER, RobotdogModel::createBodyLayer);
    }
}
