package net.pttheta.loveandwar.recipe.stamping;

import com.simibubi.create.content.processing.recipe.ProcessingRecipeBuilder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

public class StampingRecipeProcessingFactory implements ProcessingRecipeBuilder.ProcessingRecipeFactory<StampingRecipe> {
    @Override
    public StampingRecipe create(ProcessingRecipeBuilder.ProcessingRecipeParams processingRecipeParams) {
        var params = (StampingRecipeParams) processingRecipeParams;
        Ingredient ingredient = Ingredient.EMPTY;
        ItemStack result = ItemStack.EMPTY;
        ItemStack template = ItemStack.EMPTY;
        if (!params.getIngredients().isEmpty()) {
            ingredient = params.getIngredients().get(0);
        }
        if (!params.getResults().isEmpty()) {
            result = params.getResults().get(0).getStack();
        }
        if (!params.getTemplate().isEmpty()) {
            template = params.getTemplate().get(0);
        }
        return new StampingRecipe(ingredient, result, template, params.getID());
    }
}
