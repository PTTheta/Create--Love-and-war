package net.pttheta.loveandwar.recipe;

import com.simibubi.create.content.processing.recipe.ProcessingRecipeBuilder;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeSerializer;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.pttheta.loveandwar.LoveAndWarMod;
import net.pttheta.loveandwar.recipe.drawing.DrawingRecipe;
import net.pttheta.loveandwar.recipe.drawing.DrawingRecipeProcessingFactory;
import net.pttheta.loveandwar.recipe.drawing.SequencedAssemblyDrawingRecipeSerializer;
import net.pttheta.loveandwar.recipe.stamping.SequencedAssemblyStampingRecipeSerializer;
import net.pttheta.loveandwar.recipe.stamping.StampingRecipe;
import net.pttheta.loveandwar.recipe.stamping.StampingRecipeProcessingFactory;

import java.util.function.Supplier;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, LoveAndWarMod.MODID);
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, LoveAndWarMod.MODID);

    private static <T extends Recipe<?>> Supplier<RecipeType<T>> register(String id) {
        return RECIPE_TYPES.register(id, () -> new RecipeType<>() {
            public String toString() {
                return id;
            }
        });
    }
    public static final Supplier<RecipeType<DrawingRecipe>> DRAWING_TYPE = register("drawing");
    public static final Supplier<RecipeType<StampingRecipe>> STAMPING_TYPE = register("stamping");

    public static RegistryObject<RecipeSerializer<?>> DRAWING = SERIALIZERS.register("drawing", () ->
            new SequencedAssemblyDrawingRecipeSerializer(new DrawingRecipeProcessingFactory()));
    public static RegistryObject<RecipeSerializer<?>> STAMPING = SERIALIZERS.register("stamping", () ->
            new SequencedAssemblyStampingRecipeSerializer(new StampingRecipeProcessingFactory()));


    public static void register(IEventBus event) {
        SERIALIZERS.register(event);
        RECIPE_TYPES.register(event);
    }
}
