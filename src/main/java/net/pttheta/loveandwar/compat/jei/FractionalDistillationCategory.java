package net.pttheta.loveandwar.compat.jei;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllItems;
import com.simibubi.create.compat.jei.category.CreateRecipeCategory;
import com.simibubi.create.compat.jei.category.animations.AnimatedBlazeBurner;
import com.simibubi.create.content.processing.burner.BlazeBurnerBlock;
import com.simibubi.create.content.processing.recipe.HeatCondition;
import com.simibubi.create.foundation.fluid.FluidIngredient;
import com.simibubi.create.foundation.gui.AllGuiTextures;
import com.simibubi.create.foundation.utility.Lang;
import mezz.jei.api.forge.ForgeTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.builder.IRecipeSlotBuilder;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraftforge.fluids.FluidStack;
import net.pttheta.loveandwar.compat.jei.animations.AnimatedFractionalColumn;
import net.pttheta.loveandwar.recipe.petrochemical.FractionalDistillationRecipe;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Iterator;

@ParametersAreNonnullByDefault
public class FractionalDistillationCategory extends CreateRecipeCategory<FractionalDistillationRecipe> {
    private final AnimatedFractionalColumn fractionatingColumn = new AnimatedFractionalColumn();
    private final AnimatedBlazeBurner heater = new AnimatedBlazeBurner();
    public FractionalDistillationCategory(Info<FractionalDistillationRecipe> info) {
        super(info);
    }


    public int shift = 60;
    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, FractionalDistillationRecipe recipe, IFocusGroup focuses) {

        if(recipe.getFluidIngredients().isEmpty())
            return;
        FluidIngredient fluidIngredient = recipe.getFluidIngredients().get(0);
        builder
                .addSlot(RecipeIngredientRole.INPUT, 17, 145-shift)
                .setBackground(getRenderedSlot(), -1, -1)
                .addIngredients(ForgeTypes.FLUID_STACK, withImprovedVisibility(fluidIngredient.getMatchingFluidStacks()))
                .addTooltipCallback(addFluidTooltip(fluidIngredient.getRequiredAmount()));


        int i = 1;

        int size = recipe.getRollableResults().size() + recipe.getFluidResults().size();
        for (FluidStack fluidResult : recipe.getFluidResults()) {
            int yPosition = -23 * i + 150-shift;
            builder
                    .addSlot(RecipeIngredientRole.OUTPUT, 130, yPosition)
                    .setBackground(getRenderedSlot(), -1, -1)
                    .addIngredient(ForgeTypes.FLUID_STACK, withImprovedVisibility(fluidResult))
                    .addTooltipCallback(addFluidTooltip(fluidResult.getAmount()));
            i++;
        }

        HeatCondition requiredHeat = recipe.getRequiredHeat();
        if (!requiredHeat.testBlazeBurner(BlazeBurnerBlock.HeatLevel.NONE)) {
            builder
                    .addSlot(RecipeIngredientRole.RENDER_ONLY, 134, 171-shift)
                    .addItemStack(AllBlocks.BLAZE_BURNER.asStack());
        }
        if (!requiredHeat.testBlazeBurner(BlazeBurnerBlock.HeatLevel.KINDLED)) {
            builder
                    .addSlot(RecipeIngredientRole.CATALYST, 153, 171-shift)
                    .addItemStack(AllItems.BLAZE_CAKE.asStack());
        }
    }
    int height = 0;
    @Override
    public void draw(FractionalDistillationRecipe recipe, IRecipeSlotsView iRecipeSlotsView, GuiGraphics graphics, double mouseX, double mouseY) {


        HeatCondition requiredHeat = recipe.getRequiredHeat();
        boolean noHeat = requiredHeat == HeatCondition.NONE;
        AllGuiTextures.JEI_ARROW.render(graphics, 40, 150-shift);
        AllGuiTextures shadow = noHeat ? AllGuiTextures.JEI_SHADOW : AllGuiTextures.JEI_LIGHT;
        shadow.render(graphics, 81, 153 + (noHeat ? 10 : 30)-shift);
        fractionatingColumn.draw(graphics, 91, 142-shift, recipe.getFluidResults().size());

        if(!noHeat)
            heater.withHeat(requiredHeat.visualizeAsBlazeBurner()).draw(graphics, 91, 150-shift);

        AllGuiTextures heatBar = noHeat ? AllGuiTextures.JEI_NO_HEAT_BAR : AllGuiTextures.JEI_HEAT_BAR;

        heatBar.render(graphics, 4, 170);
        graphics.drawString(Minecraft.getInstance().font, Lang.translateDirect(requiredHeat.getTranslationKey()), 9,
                176-shift, requiredHeat.getColor(), false);
    }
}