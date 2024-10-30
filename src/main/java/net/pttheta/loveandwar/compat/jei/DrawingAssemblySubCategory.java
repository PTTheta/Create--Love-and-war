package net.pttheta.loveandwar.compat.jei;

import com.simibubi.create.compat.jei.category.sequencedAssembly.SequencedAssemblySubCategory;
import com.simibubi.create.content.processing.sequenced.SequencedRecipe;
import net.minecraft.client.gui.GuiGraphics;

public class DrawingAssemblySubCategory extends SequencedAssemblySubCategory {

    public DrawingAssemblySubCategory() {
        super(20);
        //mill = new AnimatedRollingMill(false);
    }
    @Override
    public void draw(SequencedRecipe<?> sequencedRecipe, GuiGraphics gg, double mouseX, double mouseY, int index) {
        var ms = gg.pose();
        ms.pushPose();
        ms.translate(0, 51.5f, 0);
        ms.scale(.6f, .6f, .6f);
        //mill.draw(gg, getWidth() / 2, 30);
        ms.popPose();
    }
}
