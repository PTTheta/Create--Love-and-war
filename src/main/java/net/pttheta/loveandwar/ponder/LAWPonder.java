package net.pttheta.loveandwar.ponder;

import com.simibubi.create.foundation.ponder.PonderRegistrationHelper;
import com.simibubi.create.foundation.ponder.PonderRegistry;
import com.simibubi.create.infrastructure.ponder.AllPonderTags;
import com.tterrag.registrate.util.entry.ItemProviderEntry;
import net.minecraftforge.fml.common.Mod;
import net.pttheta.loveandwar.LoveAndWarMod;
import net.pttheta.loveandwar.blocks.LAWBlocks;
import net.pttheta.loveandwar.item.ModItems;

public class LAWPonder {

    static final PonderRegistrationHelper HELPER = new PonderRegistrationHelper(LoveAndWarMod.MODID);

    public static void register(){
        HELPER.addStoryBoard(LAWBlocks.DRAWING_PRESS, "drawing_press", LAWPonderScenes::drawing, AllPonderTags.KINETIC_APPLIANCES);
        HELPER.addStoryBoard(LAWBlocks.STAMPING_PRESS, "stamping_press", LAWPonderScenes::stamping, AllPonderTags.KINETIC_APPLIANCES);
        HELPER.addStoryBoard(new ItemProviderEntry<>(LoveAndWarMod.LAWREGISTRATE, ModItems.THERMOSTAT), "distillation_column", LAWPonderScenes::distillation, AllPonderTags.FLUIDS);


        PonderRegistry.TAGS.forTag(AllPonderTags.KINETIC_APPLIANCES)
                .add(LAWBlocks.DRAWING_PRESS);
        PonderRegistry.TAGS.forTag(AllPonderTags.KINETIC_APPLIANCES)
                .add(LAWBlocks.STAMPING_PRESS);
        PonderRegistry.TAGS.forTag(AllPonderTags.FLUIDS)
                .add(new ItemProviderEntry<>(LoveAndWarMod.LAWREGISTRATE, ModItems.THERMOSTAT));
    }
}
