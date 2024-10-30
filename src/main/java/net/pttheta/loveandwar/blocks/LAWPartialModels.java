package net.pttheta.loveandwar.blocks;

import com.jozufozu.flywheel.core.PartialModel;
import com.simibubi.create.Create;
import com.simibubi.create.foundation.utility.Couple;
import net.minecraft.resources.ResourceLocation;
import net.pttheta.loveandwar.LoveAndWarMod;

public class LAWPartialModels {
    public static final PartialModel
            DRAWING_PRESS_HEAD = block("drawing_press/head"),
            STAMPING_PRESS_HEAD = block("stamping_press/head")
    ;

    public static final PartialModel DISTILLATION_GAUGE        = new PartialModel(new ResourceLocation("createloveandwar:block/distillation_column/gauge"));
    public static final PartialModel DISTILLATION_GAUGE_DIAL = new PartialModel(new ResourceLocation("createloveandwar:block/distillation_column/gauge_dial"));
    private static PartialModel block(String path) {
        return new PartialModel(LoveAndWarMod.asResource("block/" + path));
    }

    public static void init() {
        // init static fields
    }

}
