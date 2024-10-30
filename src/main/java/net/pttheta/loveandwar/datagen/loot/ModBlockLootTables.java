package net.pttheta.loveandwar.datagen.loot;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.pttheta.loveandwar.blocks.ModBlocks;
import net.minecraftforge.registries.RegistryObject;
import net.pttheta.loveandwar.item.ModItems;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.dropSelf(ModBlocks.SULPHUR_BLOCK.get());
        this.dropSelf(ModBlocks.RAW_SULPHUR_BLOCK.get());
        this.add(ModBlocks.SULPHUR_ORE.get(),
        block -> createCopperLikeOreDrops(ModBlocks.SULPHUR_ORE.get(), ModItems.RAW_SULPHUR.get()));
        this.add(ModBlocks.DEEPSLATE_SULPHUR_ORE.get(),
                block -> createCopperLikeOreDrops(ModBlocks.DEEPSLATE_SULPHUR_ORE.get(), ModItems.RAW_SULPHUR.get()));
        this.add(ModBlocks.NETHER_SULPHUR_ORE.get(),
                block -> createCopperLikeOreDrops(ModBlocks.NETHER_SULPHUR_ORE.get(), ModItems.RAW_SULPHUR.get()));

        this.dropSelf(ModBlocks.TUNGSTEN_BLOCK.get());
        this.dropSelf(ModBlocks.RAW_TUNGSTEN_BLOCK.get());
        this.add(ModBlocks.DEEPSLATE_TUNGSTEN_ORE.get(),
                block -> createOreDrop(ModBlocks.DEEPSLATE_TUNGSTEN_ORE.get(), ModItems.RAW_TUNGSTEN.get()));

        this.dropSelf(ModBlocks.TUNGSTEN_STAIRS.get());
        this.dropSelf(ModBlocks.TUNGSTEN_BUTTON.get());
        this.dropSelf(ModBlocks.TUNGSTEN_PRESSURE_PLATE.get());
        this.dropSelf(ModBlocks.TUNGSTEN_TRAPDOOR.get());
        this.dropSelf(ModBlocks.TUNGSTEN_FENCE.get());
        this.dropSelf(ModBlocks.TUNGSTEN_GATE.get());
        this.dropSelf(ModBlocks.TUNGSTEN_WALL.get());

        this.add(ModBlocks.TUNGSTEN_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.TUNGSTEN_SLAB.get()));
        this.add(ModBlocks.TUNGSTEN_DOOR.get(),
                block -> createDoorTable(ModBlocks.TUNGSTEN_DOOR.get()));


    }

    protected LootTable.Builder createCopperLikeOreDrops(Block pBlock, Item item) {
        return createSilkTouchDispatchTable(pBlock, this.applyExplosionDecay(pBlock,
                LootItem.lootTableItem(item)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 5.0F)))
                        .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
