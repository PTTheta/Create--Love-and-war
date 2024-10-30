package net.pttheta.loveandwar.event;


import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.pttheta.loveandwar.LoveAndWarMod;
import net.pttheta.loveandwar.entity.ModEntities;
import net.pttheta.loveandwar.entity.custom.*;
import net.pttheta.loveandwar.network.PacketHandler;

@Mod.EventBusSubscriber(modid = LoveAndWarMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void commonSetup(FMLCommonSetupEvent event){
        event.enqueueWork(()-> {
            PacketHandler.register();

        });
    }
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event){
        event.put(ModEntities.ROBOT_DOG.get(), RobotdogEntity.createAttributes().build());
    }
}
