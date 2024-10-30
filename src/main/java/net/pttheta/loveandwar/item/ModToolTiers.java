package net.pttheta.loveandwar.item;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;
import net.pttheta.loveandwar.LoveAndWarMod;
import net.pttheta.loveandwar.util.ModTags;

import java.util.List;

public class ModToolTiers {
    public static final Tier TUNGSTEN = TierSortingRegistry.registerTier(
            new ForgeTier(3,500,4f,2.5f,12,
                    ModTags.Blocks.NEEDS_TUNGSTEN_TOOL,() -> Ingredient.of(ModItems.TUNGSTEN.get())),
            new ResourceLocation(LoveAndWarMod.MODID, "tungsten"), List.of(Tiers.IRON),List.of(Tiers.DIAMOND));


}
