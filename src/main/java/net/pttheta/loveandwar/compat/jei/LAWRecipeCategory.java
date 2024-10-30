package net.pttheta.loveandwar.compat.jei;

import com.simibubi.create.compat.jei.category.CreateRecipeCategory;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.recipe.IFocusGroup;
import net.minecraft.world.item.crafting.Recipe;

public abstract class LAWRecipeCategory <T extends Recipe<?>> extends CreateRecipeCategory<T> {
    public LAWRecipeCategory(Info<T> info) {
        super(info);
    }

}
