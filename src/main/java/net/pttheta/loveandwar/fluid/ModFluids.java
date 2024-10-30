package net.pttheta.loveandwar.fluid;

import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.pttheta.loveandwar.LoveAndWarMod;
import net.pttheta.loveandwar.blocks.ModBlocks;
import net.pttheta.loveandwar.item.ModItems;

public class ModFluids {

    public static final DeferredRegister<Fluid> FLUIDS =
            DeferredRegister.create(ForgeRegistries.FLUIDS, LoveAndWarMod.MODID);

    public static final RegistryObject<FlowingFluid> SOURCE_BITUMEN = FLUIDS.register("bitumen_fluid",
            () -> new ForgeFlowingFluid.Source(ModFluids.BITUMEN_FLUID_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_BITUMEN = FLUIDS.register("flowing_bitumen",
            () -> new ForgeFlowingFluid.Flowing(ModFluids.BITUMEN_FLUID_PROPERTIES));
    
    public static final ForgeFlowingFluid.Properties BITUMEN_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(
            ModFluidTypes.BITUMEN_FLUID_TYPE, SOURCE_BITUMEN, FLOWING_BITUMEN)
            .slopeFindDistance(2).levelDecreasePerBlock(2).block(ModBlocks.BITUMEN_FLUID_BLOCK)
            .bucket(ModItems.BITUMEN_BUCKET);
    

    public static final RegistryObject<FlowingFluid> SOURCE_CRUDE_OIL = FLUIDS.register("crude_oil",
            () -> new ForgeFlowingFluid.Source(ModFluids.CRUDE_OIL_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_CRUDE_OIL = FLUIDS.register("flowing_crude_oil",
            () -> new ForgeFlowingFluid.Flowing(ModFluids.CRUDE_OIL_PROPERTIES));
    public static final ForgeFlowingFluid.Properties CRUDE_OIL_PROPERTIES = new ForgeFlowingFluid.Properties(
            ModFluidTypes.CRUDE_OIL_TYPE, SOURCE_CRUDE_OIL, FLOWING_CRUDE_OIL)
            .slopeFindDistance(2).levelDecreasePerBlock(2).block(ModBlocks.CRUDE_OIL_BLOCK)
            .bucket(ModItems.CRUDE_OIL_BUCKET);

    public static final RegistryObject<FlowingFluid> SOURCE_DIESEL = FLUIDS.register("diesel",
            () -> new ForgeFlowingFluid.Source(ModFluids.DIESEL_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_DIESEL = FLUIDS.register("flowing_diesel",
            () -> new ForgeFlowingFluid.Flowing(ModFluids.DIESEL_PROPERTIES));
    public static final ForgeFlowingFluid.Properties DIESEL_PROPERTIES = new ForgeFlowingFluid.Properties(
            ModFluidTypes.DIESEL_TYPE, SOURCE_DIESEL, FLOWING_DIESEL)
            .slopeFindDistance(2).levelDecreasePerBlock(2).block(ModBlocks.DIESEL_BLOCK)
            .bucket(ModItems.DIESEL_BUCKET);

    public static final RegistryObject<FlowingFluid> SOURCE_KEROSENE = FLUIDS.register("kerosene",
            () -> new ForgeFlowingFluid.Source(ModFluids.KEROSENE_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_KEROSENE = FLUIDS.register("flowing_kerosene",
            () -> new ForgeFlowingFluid.Flowing(ModFluids.KEROSENE_PROPERTIES));
    public static final ForgeFlowingFluid.Properties KEROSENE_PROPERTIES = new ForgeFlowingFluid.Properties(
            ModFluidTypes.KEROSENE_TYPE, SOURCE_KEROSENE, FLOWING_KEROSENE)
            .slopeFindDistance(2).levelDecreasePerBlock(2).block(ModBlocks.KEROSENE_BLOCK)
            .bucket(ModItems.KEROSENE_BUCKET);

    public static final RegistryObject<FlowingFluid> SOURCE_ETHYLENE = FLUIDS.register("ethylene",
            () -> new ForgeFlowingFluid.Source(ModFluids.ETHYLENE_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_ETHYLENE = FLUIDS.register("flowing_ethylene",
            () -> new ForgeFlowingFluid.Flowing(ModFluids.ETHYLENE_PROPERTIES));
    public static final ForgeFlowingFluid.Properties ETHYLENE_PROPERTIES = new ForgeFlowingFluid.Properties(
            ModFluidTypes.ETHYLENE_TYPE, SOURCE_ETHYLENE, FLOWING_ETHYLENE)
            .slopeFindDistance(2).levelDecreasePerBlock(2).block(ModBlocks.ETHYLENE_BLOCK)
            .bucket(ModItems.ETHYLENE_BUCKET);

    public static final RegistryObject<FlowingFluid> SOURCE_POLYMER = FLUIDS.register("polymer",
            () -> new ForgeFlowingFluid.Source(ModFluids.POLYMER_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_POLYMER = FLUIDS.register("flowing_polymer",
            () -> new ForgeFlowingFluid.Flowing(ModFluids.POLYMER_PROPERTIES));
    public static final ForgeFlowingFluid.Properties POLYMER_PROPERTIES = new ForgeFlowingFluid.Properties(
            ModFluidTypes.POLYMER_TYPE, SOURCE_POLYMER, FLOWING_POLYMER)
            .slopeFindDistance(2).levelDecreasePerBlock(2).block(ModBlocks.POLYMER_BLOCK)
            .bucket(ModItems.POLYMER_BUCKET);

    public static final RegistryObject<FlowingFluid> SOURCE_DURAPLAS = FLUIDS.register("duraplas",
            () -> new ForgeFlowingFluid.Source(ModFluids.DURAPLAS_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_DURAPLAS = FLUIDS.register("flowing_duraplas",
            () -> new ForgeFlowingFluid.Flowing(ModFluids.DURAPLAS_PROPERTIES));
    public static final ForgeFlowingFluid.Properties DURAPLAS_PROPERTIES = new ForgeFlowingFluid.Properties(
            ModFluidTypes.DURAPLAS_TYPE, SOURCE_DURAPLAS, FLOWING_DURAPLAS)
            .slopeFindDistance(2).levelDecreasePerBlock(2).block(ModBlocks.DURAPLAS_BLOCK)
            .bucket(ModItems.DURAPLAS_BUCKET);

    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }

}
