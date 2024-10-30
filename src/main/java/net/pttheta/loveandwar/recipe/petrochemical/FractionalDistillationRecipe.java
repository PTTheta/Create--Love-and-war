package net.pttheta.loveandwar.recipe.petrochemical;

import com.simibubi.create.content.processing.recipe.ProcessingRecipe;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeBuilder;
import com.simibubi.create.foundation.item.SmartInventory;
import net.minecraft.world.level.Level;
import net.pttheta.loveandwar.recipe.RecipeRegister;

public class FractionalDistillationRecipe extends ProcessingRecipe<SmartInventory> {

    public FractionalDistillationRecipe(ProcessingRecipeBuilder.ProcessingRecipeParams params) {
        super(RecipeRegister.FRACTIONAL_DISTILLATION, params);
    }

    protected int getMaxInputCount() {
        return 0;
    }

    protected int getMaxOutputCount() {
        return 0;
    }

    protected int getMaxFluidInputCount() {
        return 1;
    }

    protected int getMaxFluidOutputCount() {
        return 4;
    }

    protected boolean canRequireHeat() {
        return true;
    }

    public boolean matches(SmartInventory p_44002_, Level p_44003_) {
        return false;
    }
}
