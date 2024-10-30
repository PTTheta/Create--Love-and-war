package net.pttheta.loveandwar.recipe.stamping;

import com.simibubi.create.foundation.recipe.IRecipeTypeInfo;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

public class StampingRecipeInfo implements IRecipeTypeInfo {
    private ResourceLocation id;
    private SequencedAssemblyStampingRecipeSerializer serializer;
    private RecipeType<StampingRecipe> type;

    public StampingRecipeInfo(ResourceLocation id, SequencedAssemblyStampingRecipeSerializer serializer, RecipeType<StampingRecipe> type) {
        this.id = id;
        this.serializer = serializer;
        this.type = type;
    }
    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public <T extends RecipeSerializer<?>> T getSerializer() {
        return (T) serializer;
    }

    @Override
    public <T extends RecipeType<?>> T getType() {
        return (T) type;
    }
}
