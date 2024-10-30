package net.pttheta.loveandwar.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.pttheta.loveandwar.LoveAndWarMod;
import net.pttheta.loveandwar.blocks.LAWBlocks;
import net.pttheta.loveandwar.blocks.ModBlocks;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, LoveAndWarMod.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

        //SULPHUR
        blockWithItem(ModBlocks.SULPHUR_BLOCK);
        blockWithItem(ModBlocks.RAW_SULPHUR_BLOCK);
        blockWithItem(ModBlocks.SULPHUR_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_SULPHUR_ORE);
        blockWithItem(ModBlocks.NETHER_SULPHUR_ORE);
        //TUNGSTEN
        blockWithItem(ModBlocks.TUNGSTEN_BLOCK);
        blockWithItem(ModBlocks.RAW_TUNGSTEN_BLOCK);
        blockWithItem(ModBlocks.DEEPSLATE_TUNGSTEN_ORE);

        //OTHER
        //blockWithItem(ModBlocks.SIREN_BLOCK);

        stairsBlock(((StairBlock)ModBlocks.TUNGSTEN_STAIRS.get()),blockTexture(ModBlocks.TUNGSTEN_BLOCK.get()));
        slabBlock(((SlabBlock)ModBlocks.TUNGSTEN_SLAB.get()),blockTexture(ModBlocks.TUNGSTEN_BLOCK.get()),blockTexture(ModBlocks.TUNGSTEN_BLOCK.get()));

        buttonBlock(((ButtonBlock) ModBlocks.TUNGSTEN_BUTTON.get()),blockTexture(ModBlocks.TUNGSTEN_BLOCK.get()));
        pressurePlateBlock(((PressurePlateBlock) ModBlocks.TUNGSTEN_PRESSURE_PLATE.get()),blockTexture(ModBlocks.TUNGSTEN_BLOCK.get()));

        fenceBlock(((FenceBlock) ModBlocks.TUNGSTEN_FENCE.get()),blockTexture(ModBlocks.TUNGSTEN_BLOCK.get()));
        fenceGateBlock(((FenceGateBlock) ModBlocks.TUNGSTEN_GATE.get()),blockTexture(ModBlocks.TUNGSTEN_BLOCK.get()));
        wallBlock(((WallBlock) ModBlocks.TUNGSTEN_WALL.get()),blockTexture(ModBlocks.TUNGSTEN_BLOCK.get()));

        doorBlockWithRenderType(((DoorBlock) ModBlocks.TUNGSTEN_DOOR.get()),modLoc("block/tungsten_door_bottom"),modLoc("block/tungsten_door_top"),"cutout");
        trapdoorBlockWithRenderType(((TrapDoorBlock) ModBlocks.TUNGSTEN_TRAPDOOR.get()),modLoc("block/tungsten_trapdoor"),true,"cutout");
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
