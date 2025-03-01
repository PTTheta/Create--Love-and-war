package net.pttheta.loveandwar;

import com.flansmod.client.render.bullets.CasingRenderer;
import com.flansmod.client.render.effects.EffectRenderer;
import com.flansmod.client.render.models.FlansModelRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.Mod;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD, modid = LoveAndWarMod.MODID)
public class LoveAndWarClient {

    static
    {
        ResourceLocation smallMuzzleFlash = new ResourceLocation(LoveAndWarMod.MODID, "effects/muzzle_flash");
        FlansModelRegistry.PreRegisterRenderer(new ResourceLocation(LoveAndWarMod.MODID, "effects/muzzle_flash"), new EffectRenderer(smallMuzzleFlash));

        ResourceLocation shotgunCasing = new ResourceLocation(LoveAndWarMod.MODID, "effects/casing_shotgun");
        FlansModelRegistry.PreRegisterRenderer(new ResourceLocation(LoveAndWarMod.MODID, "effects/casing_shotgun"), new CasingRenderer(shotgunCasing));

        ResourceLocation pistolCasing = new ResourceLocation(LoveAndWarMod.MODID, "effects/casing_pistol");
        FlansModelRegistry.PreRegisterRenderer(new ResourceLocation(LoveAndWarMod.MODID, "effects/casing_pistol"), new CasingRenderer(pistolCasing));

        ResourceLocation artillery_small = new ResourceLocation(LoveAndWarMod.MODID, "effects/casing_37mm");
        FlansModelRegistry.PreRegisterRenderer(new ResourceLocation(LoveAndWarMod.MODID, "effects/casing_37mm"), new CasingRenderer(artillery_small));

        ResourceLocation heavy_rifle_casing = new ResourceLocation(LoveAndWarMod.MODID, "effects/casing_heavy_rifle");
        FlansModelRegistry.PreRegisterRenderer(new ResourceLocation(LoveAndWarMod.MODID, "effects/casing_heavy_rifle"), new CasingRenderer(heavy_rifle_casing));

    }
}
