package net.pttheta.loveandwar.worldgen;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.pttheta.loveandwar.LoveAndWarMod;
import net.pttheta.loveandwar.blocks.ModBlocks;

import java.util.List;

public class ModConfiguredFeatures {

    public static ResourceKey<ConfiguredFeature<?,?>> OVERWORLD_SULPHUR_ORE_KEY = registerKey("sulphur_ore");
    public static ResourceKey<ConfiguredFeature<?,?>> NETHER_SULPHUR_ORE_KEY = registerKey("nether_sulphur_ore");
    public static ResourceKey<ConfiguredFeature<?,?>> OVERWORLD_TUNGSTEN_ORE_KEY = registerKey("tungsten_ore");
    public static ResourceKey<ConfiguredFeature<?,?>> OVERWORLD_OIL_SAND_KEY = registerKey("oil_sand");

    public static void bootstrap(BootstapContext<ConfiguredFeature<?,?>> context){
        RuleTest stoneReplacables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplacables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest netherReplacables = new BlockMatchTest(Blocks.NETHERRACK);
        RuleTest sandstoneReplacables = new BlockMatchTest(Blocks.SANDSTONE);

        List<OreConfiguration.TargetBlockState> overworldSulphurOres = List.of(OreConfiguration.target(stoneReplacables,
                ModBlocks.SULPHUR_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplacables,ModBlocks.DEEPSLATE_SULPHUR_ORE.get().defaultBlockState()));

        register(context,OVERWORLD_SULPHUR_ORE_KEY,Feature.ORE,new OreConfiguration(overworldSulphurOres,8));
        register(context,NETHER_SULPHUR_ORE_KEY,Feature.ORE,new OreConfiguration(netherReplacables,
                ModBlocks.NETHER_SULPHUR_ORE.get().defaultBlockState(),8));

        register(context,OVERWORLD_TUNGSTEN_ORE_KEY,Feature.ORE,new OreConfiguration(deepslateReplacables,
                ModBlocks.DEEPSLATE_TUNGSTEN_ORE.get().defaultBlockState(),5));

        register(context,OVERWORLD_OIL_SAND_KEY,Feature.ORE,new OreConfiguration(stoneReplacables,
                ModBlocks.OIL_SAND.get().defaultBlockState(),18));
    }

    public static ResourceKey<ConfiguredFeature<?,?>> registerKey(String name){
        return ResourceKey.create(Registries.CONFIGURED_FEATURE,new ResourceLocation(LoveAndWarMod.MODID,name));
    }


    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?,?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?,?>> key, F feature, FC configuration){
        context.register(key,new ConfiguredFeature<>(feature,configuration));
    }
}
