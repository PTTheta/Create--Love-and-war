package net.pttheta.loveandwar.sound;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.pttheta.loveandwar.LoveAndWarMod;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, LoveAndWarMod.MODID);
    public static final RegistryObject<SoundEvent> SERVO = registerSoundEvents("servo");
    public static final RegistryObject<SoundEvent> ROBOT_DOG_AMBIENT = registerSoundEvents("robotdog_ambient");
    public static final RegistryObject<SoundEvent> GUNSHOT = registerSoundEvents("gunshot");


    private static RegistryObject<SoundEvent> registerSoundEvents(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(LoveAndWarMod.MODID,name)));
    }


    public static void register(IEventBus eventBus){
        SOUND_EVENTS.register(eventBus);
    }
}
