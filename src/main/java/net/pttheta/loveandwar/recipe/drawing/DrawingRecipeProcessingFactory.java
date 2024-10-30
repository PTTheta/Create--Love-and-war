package net.pttheta.loveandwar.recipe.drawing;

import com.simibubi.create.content.processing.recipe.ProcessingRecipeBuilder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

public class DrawingRecipeProcessingFactory implements ProcessingRecipeBuilder.ProcessingRecipeFactory<DrawingRecipe> {
    @Override
    public DrawingRecipe create(ProcessingRecipeBuilder.ProcessingRecipeParams processingRecipeParams) {
        var params = (DrawingRecipeParams) processingRecipeParams;
        Ingredient ingredient = Ingredient.EMPTY;
        ItemStack result = ItemStack.EMPTY;
        if (!params.getIngredients().isEmpty()) {
            ingredient = params.getIngredients().get(0);
        }
        if (!params.getResults().isEmpty()) {
            result = params.getResults().get(0).getStack();
        }
        return new DrawingRecipe(ingredient, result, params.getID());
    }
}
