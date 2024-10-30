package net.pttheta.loveandwar;

import com.flansmod.client.render.effects.EffectRenderer;
import com.flansmod.client.render.models.FlansModelRegistry;
import com.flansmod.common.FlansMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.pttheta.loveandwar.ponder.LAWPonder;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD, modid = LoveAndWarMod.MODID)
public class LoveAndWarClient {

    static
    {
        FlansModelRegistry.PreRegisterRenderer(new ResourceLocation(LoveAndWarMod.MODID, "effects/muzzle_flash"), new EffectRenderer());
    }
}
