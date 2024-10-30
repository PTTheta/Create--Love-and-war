package net.pttheta.loveandwar.recipe.drawing;

import com.simibubi.create.content.processing.recipe.HeatCondition;
import com.simibubi.create.content.processing.recipe.ProcessingOutput;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeBuilder;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.pttheta.loveandwar.Config;

//ref https://github.com/mrh0/createaddition/blob/1.19.2/src/main/java/com/mrh0/createaddition/recipe/rolling/RollingMillRecipeParams.java
public class DrawingRecipeParams extends ProcessingRecipeBuilder.ProcessingRecipeParams{
    protected DrawingRecipeParams(ResourceLocation id, Ingredient input, ProcessingOutput output) {
        super(id);
        if(ingredients == null) {
            ingredients =  NonNullList.create();
        }
        ingredients.add(input);
        if(results == null) {
            results = NonNullList.create();
        }
        results.add(output);
        if(fluidIngredients == null) {
            fluidIngredients = NonNullList.create();
        }
        fluidIngredients.clear();
        if(fluidResults == null) {
            fluidResults = NonNullList.create();
        }
        fluidResults.clear();
        processingDuration = Config.DRAWING_PROCESSING_DURATION.get();
        requiredHeat = HeatCondition.NONE;
        keepHeldItem = false;
    }

    public NonNullList<Ingredient> getIngredients() {
        return ingredients;
    }

    public NonNullList<ProcessingOutput> getResults() {
        return results;
    }

    public int getProcessingDuration() {
        return processingDuration;
    }

    public ResourceLocation getID() {
        return id;
    }
}
