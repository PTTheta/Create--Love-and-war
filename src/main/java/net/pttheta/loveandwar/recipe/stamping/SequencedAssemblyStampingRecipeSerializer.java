package net.pttheta.loveandwar.recipe.stamping;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeBuilder;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeSerializer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.ShapedRecipe;

public class SequencedAssemblyStampingRecipeSerializer extends ProcessingRecipeSerializer<StampingRecipe> {

    private static final String RESULT_KEY = "result";
    private static final String SEQUENCED_ASSEMBLY_RESULT_KEY = "results";
    private static final String INGREDIENT_KEY = "input";

    private static final String TEMPLATE_KEY = "template";
    private static final String SEQUENCED_ASSEMBLY_INGREDIENT_KEY = "ingredients";

    public SequencedAssemblyStampingRecipeSerializer(ProcessingRecipeBuilder.ProcessingRecipeFactory<StampingRecipe> factory) {
        super(factory);
    }

    @Override
    protected void writeToJson(JsonObject json, StampingRecipe recipe) {
        super.writeToJson(json, recipe);
    }

    protected ItemStack readOutput(JsonObject json) {
        //note, the alternative keys are needed for kubejs create integration sequenced assembly recipe compat
        if (json.has(RESULT_KEY)) {
            var result = json.get(RESULT_KEY);
            if (result.isJsonObject() && result.getAsJsonObject().has("item")) {
                return ShapedRecipe.itemStackFromJson(result.getAsJsonObject());
            }
        } else if (json.has(SEQUENCED_ASSEMBLY_RESULT_KEY)) {
            var results = json.get(SEQUENCED_ASSEMBLY_RESULT_KEY);
            if (results.isJsonArray() && !results.getAsJsonArray().isEmpty()) {
                var result = results.getAsJsonArray().get(0).getAsJsonObject();
                if (!result.has("count")) {
                    result.add("count", new JsonPrimitive(1));
                }
                return ShapedRecipe.itemStackFromJson(result);
            }
        }
        return ItemStack.EMPTY;
    }

    protected Ingredient readIngredient(JsonObject json) {
        //note, the alternative keys are needed for kubejs create integration sequenced assembly recipe compat
        if (json.has(INGREDIENT_KEY)) {
            return Ingredient.fromJson(json.get(INGREDIENT_KEY).getAsJsonObject());
        } else if (json.has(SEQUENCED_ASSEMBLY_INGREDIENT_KEY)) {
            var jsonArray = json.get(SEQUENCED_ASSEMBLY_INGREDIENT_KEY);
            if (jsonArray.isJsonArray() && !jsonArray.getAsJsonArray().isEmpty()) {
                return Ingredient.fromJson(jsonArray.getAsJsonArray().get(0));
            }
        }
        return Ingredient.EMPTY;
    }

    protected ItemStack readTemplate(JsonObject json) {
        //note, the alternative keys are needed for kubejs create integration sequenced assembly recipe compat
        if (json.has(TEMPLATE_KEY)) {
            var result = json.get(TEMPLATE_KEY);
            if (result.isJsonObject() && result.getAsJsonObject().has("item")) {
                return ShapedRecipe.itemStackFromJson(result.getAsJsonObject());
            }
        }
        return ItemStack.EMPTY;
    }

    @Override
    protected StampingRecipe readFromJson(ResourceLocation recipeId, JsonObject json) {
        ItemStack output = readOutput(json);
        Ingredient input = readIngredient(json);
        ItemStack template = readTemplate(json);
        return new StampingRecipe(input, output, template, recipeId);
    }

    @Override
    protected void writeToBuffer(FriendlyByteBuf buffer, StampingRecipe recipe) {
        buffer.writeItem(recipe.output);
        recipe.ingredient.toNetwork(buffer);
        buffer.writeItem(recipe.template);
    }

    @Override
    protected StampingRecipe readFromBuffer(ResourceLocation recipeId, FriendlyByteBuf buffer) {
        ItemStack output = buffer.readItem();
        Ingredient input = Ingredient.fromNetwork(buffer);
        ItemStack template = buffer.readItem();
        return new StampingRecipe(input, output, template, recipeId);
    }

}
