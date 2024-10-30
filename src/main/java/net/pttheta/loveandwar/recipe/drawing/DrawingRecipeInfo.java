package net.pttheta.loveandwar.recipe.drawing;

import com.simibubi.create.foundation.recipe.IRecipeTypeInfo;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

public class DrawingRecipeInfo implements IRecipeTypeInfo {
    private ResourceLocation id;
    private SequencedAssemblyDrawingRecipeSerializer serializer;
    private RecipeType<DrawingRecipe> type;

    public DrawingRecipeInfo(ResourceLocation id, SequencedAssemblyDrawingRecipeSerializer serializer, RecipeType<DrawingRecipe> type) {
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
