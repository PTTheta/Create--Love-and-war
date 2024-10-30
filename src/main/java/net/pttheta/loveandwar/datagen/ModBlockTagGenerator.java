package net.pttheta.loveandwar.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.pttheta.loveandwar.LoveAndWarMod;
import net.pttheta.loveandwar.blocks.ModBlocks;
import net.pttheta.loveandwar.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {

    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, LoveAndWarMod.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(ModTags.Blocks.SULPHUR)
                .add(ModBlocks.SULPHUR_BLOCK.get())
                .add(ModBlocks.SULPHUR_ORE.get())
                .add(ModBlocks.DEEPSLATE_SULPHUR_ORE.get())
                .add(ModBlocks.NETHER_SULPHUR_ORE.get())
                .add(ModBlocks.RAW_SULPHUR_BLOCK.get());

        this.tag(ModTags.Blocks.TUNGSTEN)
                .add(ModBlocks.TUNGSTEN_BLOCK.get())
                .add(ModBlocks.DEEPSLATE_TUNGSTEN_ORE.get())
                .add(ModBlocks.RAW_TUNGSTEN_BLOCK.get());

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)

                //SULPHUR
                .add(ModBlocks.SULPHUR_BLOCK.get())
                .add(ModBlocks.SULPHUR_ORE.get())
                .add(ModBlocks.DEEPSLATE_SULPHUR_ORE.get())
                .add(ModBlocks.NETHER_SULPHUR_ORE.get())
                .add(ModBlocks.RAW_SULPHUR_BLOCK.get())

                //TUNGSTEN
                .add(ModBlocks.TUNGSTEN_BLOCK.get())
                .add(ModBlocks.RAW_TUNGSTEN_BLOCK.get())
                .add(ModBlocks.DEEPSLATE_TUNGSTEN_ORE.get());



        this.tag(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.SULPHUR_BLOCK.get())
                .add(ModBlocks.SULPHUR_ORE.get())
                .add(ModBlocks.DEEPSLATE_SULPHUR_ORE.get())
                .add(ModBlocks.NETHER_SULPHUR_ORE.get())
                .add(ModBlocks.RAW_SULPHUR_BLOCK.get());

        this.tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.TUNGSTEN_BLOCK.get())
                .add(ModBlocks.DEEPSLATE_TUNGSTEN_ORE.get())
                .add(ModBlocks.RAW_TUNGSTEN_BLOCK.get()); //add tungsten here

        this.tag(BlockTags.FENCES)
                .add(ModBlocks.TUNGSTEN_FENCE.get()); //add tungsten here

        this.tag(BlockTags.FENCE_GATES)
                .add(ModBlocks.TUNGSTEN_GATE.get()); //add tungsten here

        this.tag(BlockTags.WALLS)
                .add(ModBlocks.TUNGSTEN_WALL.get()); //add tungsten here
    }
}
