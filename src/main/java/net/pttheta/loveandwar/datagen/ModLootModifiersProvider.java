package net.pttheta.loveandwar.datagen;

import com.mojang.serialization.Codec;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootTableIdCondition;
import net.pttheta.loveandwar.LoveAndWarMod;
import net.pttheta.loveandwar.item.ModItems;
import net.pttheta.loveandwar.loot.AddItemModifier;
import net.pttheta.loveandwar.loot.AddSuspiciousSandModifier;

public class ModLootModifiersProvider  extends GlobalLootModifierProvider {
    public ModLootModifiersProvider(PackOutput output) {
        super(output, LoveAndWarMod.MODID);
    }

    @Override
    protected void start() {
        //add("cigarette_from_grass", new AddItemModifier(new LootItemCondition[]{
                //LootItemBlockStatePropertyCondition.hasBlockStateProperties.build(),
                //LootItemRandomChanceCondition.randomChance(0.1f).build()
        //}, ModItems.CIGARETTE.get()));

        add("sulphur_from_creeper", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("entities/creeper")).build()
        }, ModItems.RAW_SULPHUR.get()));

        add("cigarette_from_mineshaft", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("chests/abandoned_mineshaft")).build()
        }, ModItems.CIGARETTE.get()));

        add("cigarette_from_chest", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("chests/simple_dungeon")).build()
        }, ModItems.CIGARETTE.get()));

        add("cigarette_from_suspicious_sand", new AddSuspiciousSandModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("archaeology/desert_pyramid")).build()
        }, ModItems.CIGARETTE.get()));
    }
}
