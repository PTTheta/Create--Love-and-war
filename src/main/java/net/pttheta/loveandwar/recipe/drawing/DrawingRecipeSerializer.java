package net.pttheta.loveandwar.recipe.drawing;

import com.google.gson.JsonObject;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.pttheta.loveandwar.blocks.LAWBlocks;
import net.pttheta.loveandwar.blocks.ModBlocks;
import net.pttheta.loveandwar.recipe.LAWRecipeSerializer;
import org.jetbrains.annotations.Nullable;

public class DrawingRecipeSerializer extends LAWRecipeSerializer<DrawingRecipe> {
    public DrawingRecipeSerializer() {}
    @Override
    public DrawingRecipe readFromJson(ResourceLocation recipeId, JsonObject json, ICondition.IContext context) {
        ItemStack output = readOutput(json.get("result"));
        Ingredient input = Ingredient.fromJson(json.get("input"));
        return new DrawingRecipe(input, output, recipeId);
    }

    @Override
    public ItemStack getIcon() {
        return LAWBlocks.DRAWING_PRESS.asItem().getDefaultInstance();
    }

    @Override
    public @Nullable DrawingRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
        ItemStack output = buffer.readItem();
        Ingredient input = Ingredient.fromNetwork(buffer);
        return new DrawingRecipe(input, output, recipeId);
    }
    @Override
    public void toNetwork(FriendlyByteBuf buffer, DrawingRecipe recipe) {
        buffer.writeItem(recipe.output);
        recipe.ingredient.toNetwork(buffer);
    }
}
