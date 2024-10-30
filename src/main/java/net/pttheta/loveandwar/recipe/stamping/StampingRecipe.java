package net.pttheta.loveandwar.recipe.stamping;

import com.simibubi.create.compat.jei.category.sequencedAssembly.SequencedAssemblySubCategory;
import com.simibubi.create.content.processing.recipe.ProcessingOutput;
import com.simibubi.create.content.processing.recipe.ProcessingRecipe;
import com.simibubi.create.content.processing.sequenced.IAssemblyRecipe;
import net.minecraft.ChatFormatting;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.wrapper.RecipeWrapper;
import net.pttheta.loveandwar.compat.jei.StampingAssemblySubCategory;
import net.pttheta.loveandwar.recipe.ModRecipes;

import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

//ref https://github.com/mrh0/createaddition/blob/1.19.2/src/main/java/com/mrh0/createaddition/recipe/rolling/RollingRecipe.java
public class StampingRecipe extends ProcessingRecipe<RecipeWrapper> implements IAssemblyRecipe{

    protected final ItemStack output;
    protected final ItemStack template;
    protected final ResourceLocation id;
    protected final Ingredient ingredient;

    protected StampingRecipe(Ingredient ingredient, ItemStack output, ItemStack template, ResourceLocation id) {
        super(new StampingRecipeInfo(id, (SequencedAssemblyStampingRecipeSerializer) ModRecipes.STAMPING.get(), ModRecipes.STAMPING_TYPE.get()), new StampingRecipeParams(id, ingredient, template, new ProcessingOutput(output, 1f)));
        this.output = output;
        this.template = template;
        this.id = id;
        this.ingredient = ingredient;
    }

    public static void register() {
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public ItemStack getTemplate() {
        return template;
    }
    @Override
    public boolean matches(RecipeWrapper inv, Level worldIn) {
        if (inv.isEmpty())
            return false;
        return ingredient.test(inv.getItem(0));
    }
    @Override
    protected int getMaxInputCount() {
        return 1;
    }

    @Override
    protected int getMaxOutputCount() {
        return 32;
    }

    @Override
    public ItemStack assemble(RecipeWrapper inv, RegistryAccess registryAccess) {
        return this.output;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess pRegistryAccess) {
        return this.output;
    }

    public ItemStack getResultItem() {
        return this.output;
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.STAMPING.get();
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.STAMPING_TYPE.get();
    }

    @Override
    public ItemStack getToastSymbol() {
        return this.output;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }
    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height > 0;
    }

    @Override
    public Component getDescriptionForAssembly() {
        return Component.translatable("createloveandwar.recipe.stamping.sequence").withStyle(ChatFormatting.DARK_GREEN);
    }

    @Override
    public void addRequiredMachines(Set<ItemLike> list) {

    }

    @Override

    public void addAssemblyIngredients(List<Ingredient> list) {

    }
    @Override
    public Supplier<Supplier<SequencedAssemblySubCategory>> getJEISubCategory() {
        return () -> StampingAssemblySubCategory::new;
    }
}
