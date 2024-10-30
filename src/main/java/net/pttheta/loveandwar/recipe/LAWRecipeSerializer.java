package net.pttheta.loveandwar.recipe;
import javax.annotation.Nonnull;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.conditions.ICondition;
import org.jetbrains.annotations.Nullable;
import net.minecraft.world.item.crafting.RecipeSerializer;

//Ref https://github.com/mrh0/createaddition/blob/1.20.1/src/main/java/com/mrh0/createaddition/recipe/CARecipeSerializer.java
public abstract class LAWRecipeSerializer<R extends Recipe<?>> implements RecipeSerializer<R> {
    @Override
    public final R fromJson(ResourceLocation recipeId, JsonObject json, ICondition.IContext context) {
        if(CraftingHelper.processConditions(json, "conditions", context))
            return readFromJson(recipeId, json, context);
        return null;
    }

    public abstract ItemStack getIcon();
    @Override
    public R fromJson(@Nonnull ResourceLocation recipeId, @Nonnull JsonObject serializedRecipe)
    {
        return null;
    }

    protected ItemStack readOutput(JsonElement outputObject) {
        if(outputObject.isJsonObject() && outputObject.getAsJsonObject().has("item"))
            return ShapedRecipe.itemStackFromJson(outputObject.getAsJsonObject());
        return null;
    }

    protected ItemStack readTemplate(JsonElement templateObject) {
        if(templateObject.isJsonObject() && templateObject.getAsJsonObject().has("item"))
            return ShapedRecipe.itemStackFromJson(templateObject.getAsJsonObject());
        return null;
    }

    public abstract R readFromJson(ResourceLocation recipeId, JsonObject json, ICondition.IContext context);
}
