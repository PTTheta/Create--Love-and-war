package net.pttheta.loveandwar.blocks;

import com.simibubi.create.AllCreativeModeTabs;
import com.simibubi.create.content.kinetics.BlockStressDefaults;
import com.simibubi.create.content.kinetics.press.MechanicalPressBlock;
import com.simibubi.create.content.processing.AssemblyOperatorBlockItem;
import com.simibubi.create.foundation.data.BlockStateGen;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.data.SharedProperties;
import com.simibubi.create.foundation.data.TagGen;
import com.tterrag.registrate.builders.BlockBuilder;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.pttheta.loveandwar.LoveAndWarMod;
import net.pttheta.loveandwar.blocks.drawing.DrawingPressBlock;
import net.pttheta.loveandwar.blocks.petrochem.DistillationColumnBlock;
import net.pttheta.loveandwar.blocks.petrochem.DistillationColumnModel;
import net.pttheta.loveandwar.blocks.stamping.StampingPressBlock;
import net.pttheta.loveandwar.item.ModCreativeModeTabs;

import static com.simibubi.create.foundation.data.ModelGen.customItemModel;
import static com.simibubi.create.foundation.data.TagGen.axeOrPickaxe;


public class LAWBlocks {
    static {
        LoveAndWarMod.LAWREGISTRATE.setCreativeTab(ModCreativeModeTabs.LOVE_AND_WAR_TAB);
    }

    public static final BlockEntry<DrawingPressBlock> DRAWING_PRESS =
            LoveAndWarMod.LAWREGISTRATE.block("drawing_press", DrawingPressBlock::new)
                    .initialProperties(SharedProperties::stone)
                    .properties(p -> p.noOcclusion().mapColor(MapColor.PODZOL))
                    .transform(axeOrPickaxe())
                    .blockstate(BlockStateGen.horizontalBlockProvider(true))
                    .transform(BlockStressDefaults.setImpact(8.0))
                    .item(AssemblyOperatorBlockItem::new)
                    .transform(customItemModel())
                    .register();

    public static final BlockEntry<StampingPressBlock> STAMPING_PRESS =
            LoveAndWarMod.LAWREGISTRATE.block("stamping_press", StampingPressBlock::new)
                    .initialProperties(SharedProperties::stone)
                    .properties(p -> p.noOcclusion().mapColor(MapColor.PODZOL))
                    .transform(axeOrPickaxe())
                    .blockstate(BlockStateGen.horizontalBlockProvider(true))
                    .transform(BlockStressDefaults.setImpact(8.0))
                    .item(AssemblyOperatorBlockItem::new)
                    .transform(customItemModel())
                    .register();

    public static final BlockEntry<DistillationColumnBlock> DISTILLATION_COLUMN =
            LoveAndWarMod.LAWREGISTRATE.block("distillation_column", DistillationColumnBlock::new)
            .initialProperties(SharedProperties::copperMetal)
            .properties(BlockBehaviour.Properties::noOcclusion)
            .properties(p -> p.isRedstoneConductor((p1, p2, p3) -> true))
            .transform(axeOrPickaxe())
            .onRegister(CreateRegistrate.blockModel(() -> DistillationColumnModel::new))
            .register();

    public static void register() {
        LoveAndWarMod.LAWREGISTRATE.setCreativeTab(ModCreativeModeTabs.LOVE_AND_WAR_TAB);
    }
}
