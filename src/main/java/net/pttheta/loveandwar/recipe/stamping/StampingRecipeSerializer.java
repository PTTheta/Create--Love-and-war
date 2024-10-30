package net.pttheta.loveandwar.recipe.stamping;

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

public class StampingRecipeSerializer extends LAWRecipeSerializer<StampingRecipe> {
    public StampingRecipeSerializer() {}
    @Override
    public StampingRecipe readFromJson(ResourceLocation recipeId, JsonObject json, ICondition.IContext context) {
        ItemStack output = readOutput(json.get("result"));
        Ingredient input = Ingredient.fromJson(json.get("input"));
        ItemStack template = readTemplate(json.get("template"));
        return new StampingRecipe(input, output, template, recipeId);
    }

    @Override
    public ItemStack getIcon() {
        return LAWBlocks.STAMPING_PRESS.get().asItem().getDefaultInstance();
    }

    @Override
    public @Nullable StampingRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
        ItemStack output = buffer.readItem();
        Ingredient input = Ingredient.fromNetwork(buffer);
        ItemStack template = buffer.readItem();
        return new StampingRecipe(input, output, template, recipeId);
    }
    @Override
    public void toNetwork(FriendlyByteBuf buffer, StampingRecipe recipe) {
        buffer.writeItem(recipe.output);
        recipe.ingredient.toNetwork(buffer);
    }
}
