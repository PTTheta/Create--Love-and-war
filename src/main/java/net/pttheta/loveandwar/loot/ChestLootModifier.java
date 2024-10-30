package net.pttheta.loveandwar.loot;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import net.pttheta.loveandwar.LoveAndWarMod;

import java.util.function.Supplier;

public class ChestLootModifier extends LootModifier {

    public static final Supplier<Codec<ChestLootModifier>> CODEC = Suppliers.memoize(() -> RecordCodecBuilder.create(inst -> codecStart(inst).apply(inst, ChestLootModifier::new)));
    public static final ResourceLocation INJECTABLE_LOOT_CHEST = new ResourceLocation(LoveAndWarMod.MODID, "inject/loveandwarloot");

    public ChestLootModifier(final LootItemCondition[] conditions) {
        super(conditions);
    }

    @Override
    public ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> loot, LootContext context) {
        context.getLevel().getServer().getLootData().getLootTable(INJECTABLE_LOOT_CHEST).getRandomItemsRaw(context, LootTable.createStackSplitter(context.getLevel(), loot::add));
        //context.getLootTable(INJECTABLE_LOOT_CHEST).getRandomItems(context, loot::add);
        return loot;
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }
}