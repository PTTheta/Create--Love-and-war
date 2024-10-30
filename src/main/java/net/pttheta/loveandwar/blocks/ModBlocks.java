package net.pttheta.loveandwar.blocks;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.pttheta.loveandwar.LoveAndWarMod;
import net.pttheta.loveandwar.fluid.ModFluids;
import net.pttheta.loveandwar.item.ModItems;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, LoveAndWarMod.MODID);

    public static final RegistryObject<Block> SULPHUR_BLOCK = registerBlock("sulphur_block",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.COAL_BLOCK).sound(SoundType.CALCITE)));
    public static final RegistryObject<Block> RAW_SULPHUR_BLOCK = registerBlock("raw_sulphur_block",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.COAL_BLOCK).sound(SoundType.CALCITE)));

    public static final RegistryObject<Block> SULPHUR_ORE = registerBlock("sulphur_ore",
            ()-> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .strength(2f).requiresCorrectToolForDrops(), UniformInt.of(1,2)
            ));
    public static final RegistryObject<Block> DEEPSLATE_SULPHUR_ORE = registerBlock("deepslate_sulphur_ore",
            ()-> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE)
                    .strength(3f).requiresCorrectToolForDrops(), UniformInt.of(3,4)
            ));
    public static final RegistryObject<Block> NETHER_SULPHUR_ORE = registerBlock("nether_sulphur_ore",
            ()-> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.NETHERRACK)
                    .strength(1f).requiresCorrectToolForDrops(), UniformInt.of(3,4)
            ));
    public static final RegistryObject<LiquidBlock> BITUMEN_FLUID_BLOCK = registerBlock("bitumen_liquid_block",
            ()-> new LiquidBlock(ModFluids.SOURCE_BITUMEN,BlockBehaviour.Properties.copy(Blocks.WATER)));
    public static final RegistryObject<LiquidBlock> CRUDE_OIL_BLOCK = registerBlock("crude_oil_block",
            ()-> new LiquidBlock(ModFluids.SOURCE_CRUDE_OIL,BlockBehaviour.Properties.copy(Blocks.WATER).randomTicks()));
    public static final RegistryObject<LiquidBlock> DIESEL_BLOCK = registerBlock("diesel_block",
            ()-> new LiquidBlock(ModFluids.SOURCE_DIESEL,BlockBehaviour.Properties.copy(Blocks.WATER)));
    public static final RegistryObject<LiquidBlock> KEROSENE_BLOCK = registerBlock("kerosene_block",
            ()-> new LiquidBlock(ModFluids.SOURCE_KEROSENE,BlockBehaviour.Properties.copy(Blocks.WATER)));
    public static final RegistryObject<LiquidBlock> ETHYLENE_BLOCK = registerBlock("ethylene_block",
            ()-> new LiquidBlock(ModFluids.SOURCE_ETHYLENE,BlockBehaviour.Properties.copy(Blocks.WATER)));
    public static final RegistryObject<LiquidBlock> POLYMER_BLOCK = registerBlock("polymer_block",
            ()-> new LiquidBlock(ModFluids.SOURCE_POLYMER,BlockBehaviour.Properties.copy(Blocks.WATER)));
    public static final RegistryObject<LiquidBlock> DURAPLAS_BLOCK = registerBlock("duraplas_block",
            ()-> new LiquidBlock(ModFluids.SOURCE_DURAPLAS,BlockBehaviour.Properties.copy(Blocks.WATER)));
    public static final RegistryObject<Block> OIL_SAND = registerBlock("oil_sand",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.SANDSTONE)));

    public static final RegistryObject<Block> SLIME_RESIN_BLOCK = registerBlock("slime_resin_block",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE)));

    public static final RegistryObject<Block> POLYMER_BLOCK_SOLID = registerBlock("block_of_polymer",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE)));

    public static final RegistryObject<Block> DURAPLAS_BLOCK_SOLID = registerBlock("block_of_duraplas",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE)));
    public static final RegistryObject<Block> BITUMEN_BLOCK = registerBlock("bitumen",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE)));
    public static final RegistryObject<Block> SMOOTH_ASPHALT = registerBlock("smooth_asphalt",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE)));

    public static final RegistryObject<Block> BLACK_PLASTIC = registerBlock("black_plastic",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE)));
    public static final RegistryObject<Block> BLUE_PLASTIC = registerBlock("blue_plastic",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE)));
    public static final RegistryObject<Block> BROWN_PLASTIC = registerBlock("brown_plastic",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE)));
    public static final RegistryObject<Block> CYAN_PLASTIC = registerBlock("cyan_plastic",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE)));
    public static final RegistryObject<Block> GRAY_PLASTIC = registerBlock("gray_plastic",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE)));
    public static final RegistryObject<Block> GREEN_PLASTIC = registerBlock("green_plastic",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE)));
    public static final RegistryObject<Block> LIGHT_BLUE_PLASTIC = registerBlock("light_blue_plastic",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE)));
    public static final RegistryObject<Block> LIGHT_GRAY_PLASTIC = registerBlock("light_gray_plastic",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE)));
    public static final RegistryObject<Block> LIME_PLASTIC = registerBlock("lime_plastic",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE)));
    public static final RegistryObject<Block> MAGENTA_PLASTIC = registerBlock("magenta_plastic",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE)));
    public static final RegistryObject<Block> ORANGE_PLASTIC = registerBlock("orange_plastic",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE)));
    public static final RegistryObject<Block> PINK_PLASTIC = registerBlock("pink_plastic",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE)));
    public static final RegistryObject<Block> PURPLE_PLASTIC = registerBlock("purple_plastic",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE)));
    public static final RegistryObject<Block> RED_PLASTIC = registerBlock("red_plastic",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE)));
    public static final RegistryObject<Block> WHITE_PLASTIC = registerBlock("white_plastic",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE)));
    public static final RegistryObject<Block> YELLOW_PLASTIC = registerBlock("yellow_plastic",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE)));

    public static final RegistryObject<Block> SMOOTH_ASPHALT_SLAB = registerBlock("smooth_asphalt_slab",
            ()-> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE)));
    public static final RegistryObject<Block> TUNGSTEN_BLOCK = registerBlock("tungsten_block",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final RegistryObject<Block> RAW_TUNGSTEN_BLOCK = registerBlock("raw_tungsten_block",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.COAL_BLOCK).sound(SoundType.CALCITE)));
    public static final RegistryObject<Block> DEEPSLATE_TUNGSTEN_ORE = registerBlock("deepslate_tungsten_ore",
            ()-> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE)
                    .strength(3f).requiresCorrectToolForDrops(), UniformInt.of(3,4)
            ));
    public static final RegistryObject<Block> TUNGSTEN_STAIRS = registerBlock("tungsten_stairs",
            ()-> new StairBlock(() -> ModBlocks.TUNGSTEN_BLOCK.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final RegistryObject<Block> TUNGSTEN_SLAB = registerBlock("tungsten_slab",
            ()-> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));

    public static final RegistryObject<Block> TUNGSTEN_BUTTON = registerBlock("tungsten_button",
            ()-> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BUTTON),
                    BlockSetType.IRON, 10, true));
    public static final RegistryObject<Block> TUNGSTEN_PRESSURE_PLATE = registerBlock("tungsten_pressure_plate",
            ()-> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK),
            BlockSetType.IRON));

    public static final RegistryObject<Block> TUNGSTEN_FENCE = registerBlock("tungsten_fence",
            ()-> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final RegistryObject<Block> TUNGSTEN_GATE = registerBlock("tungsten_gate",
            ()-> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK), SoundEvents.CHAIN_PLACE,SoundEvents.ANVIL_BREAK));
    public static final RegistryObject<Block> TUNGSTEN_WALL = registerBlock("tungsten_wall",
            ()-> new WallBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));

    public static final RegistryObject<Block> TUNGSTEN_DOOR = registerBlock("tungsten_door",
            ()-> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion(),BlockSetType.IRON));
    public static final RegistryObject<Block> TUNGSTEN_TRAPDOOR = registerBlock("tungsten_trapdoor",
            ()-> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion(),BlockSetType.IRON));

    private static<T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name,block);
        registerBlockItem(name,toReturn);
        return toReturn;
    }
    private static<T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block){
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }
    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
