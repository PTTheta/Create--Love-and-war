package net.pttheta.loveandwar.fluid;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.common.SoundAction;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.pttheta.loveandwar.LoveAndWarMod;
import org.joml.Vector3f;

public class ModFluidTypes {

    public static final ResourceLocation WATER_STILL_RL = new ResourceLocation("block/water_still");
    public static final ResourceLocation WATER_FLOWING_RL = new ResourceLocation("block/water_flow");
    public static final ResourceLocation OVERLAY = new ResourceLocation("misc/in_oil");

    public static final DeferredRegister<FluidType> FLUID_TYPES =
            DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, LoveAndWarMod.MODID);


    public static final RegistryObject<FluidType> BITUMEN_FLUID_TYPE = register("bitumen_fluid",
            FluidType.Properties.create().lightLevel(2).density(2).viscosity(2).sound(SoundAction.get("drink"),
                    SoundEvents.HONEY_DRINK),0xff412f2f,new Vector3f(65f / 255f, 47f / 255f, 47f / 255f));

    public static final RegistryObject<FluidType> CRUDE_OIL_TYPE = register("crude_oil",
            FluidType.Properties.create().lightLevel(2).density(5).viscosity(5).sound(SoundAction.get("drink"),
                    SoundEvents.HONEY_DRINK),0xff201818,new Vector3f(32f / 255f, 24f / 255f, 24f / 255f));

    public static final RegistryObject<FluidType> DIESEL_TYPE = register("diesel",
            FluidType.Properties.create().lightLevel(2).density(10).viscosity(10).sound(SoundAction.get("drink"),
                    SoundEvents.HONEY_DRINK),0xff4e2b2b,new Vector3f(78f / 255f, 43f / 255f, 43f / 255f));

    public static final RegistryObject<FluidType> KEROSENE_TYPE = register("kerosene",
            FluidType.Properties.create().lightLevel(2).density(15).viscosity(15).sound(SoundAction.get("drink"),
                    SoundEvents.HONEY_DRINK),0xff665c3f,new Vector3f(102f / 255f, 92f / 255f, 63f / 255f));

    public static final RegistryObject<FluidType> ETHYLENE_TYPE = register("ethylene",
            FluidType.Properties.create().lightLevel(2).density(5).viscosity(15).sound(SoundAction.get("drink"),
                    SoundEvents.HONEY_DRINK),0xffb8ba9a,new Vector3f(184f / 255f, 186f / 255f, 154f / 255f));

    public static final RegistryObject<FluidType> POLYMER_TYPE = register("polymer",
            FluidType.Properties.create().lightLevel(2).density(5).viscosity(15).sound(SoundAction.get("drink"),
                    SoundEvents.HONEY_DRINK),0xffba9ab0,new Vector3f(186f / 255f, 154f / 255f, 176f / 255f));


    public static final RegistryObject<FluidType> DURAPLAS_TYPE = register("duraplas",
            FluidType.Properties.create().lightLevel(2).density(5).viscosity(15).sound(SoundAction.get("drink"),
                    SoundEvents.HONEY_DRINK),0xff897097,new Vector3f(137f / 255f, 112f / 255f, 151f / 255f));



    private static RegistryObject<FluidType> register(String name, FluidType.Properties properties,int tintColor,Vector3f colour) {
        return FLUID_TYPES.register(name, () -> new BaseFluidType(WATER_STILL_RL, WATER_FLOWING_RL, OVERLAY,
                tintColor, colour, properties));
    }
    public static void register(IEventBus eventBus) {
        FLUID_TYPES.register(eventBus);
    }
}
