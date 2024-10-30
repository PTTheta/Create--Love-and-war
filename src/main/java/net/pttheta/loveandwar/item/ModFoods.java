package net.pttheta.loveandwar.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static final FoodProperties CIGARETTE = new FoodProperties.Builder().alwaysEat().nutrition(-1)
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION,100),1).build();
}
