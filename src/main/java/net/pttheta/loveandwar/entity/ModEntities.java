package net.pttheta.loveandwar.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.pttheta.loveandwar.LoveAndWarMod;
import net.pttheta.loveandwar.entity.custom.*;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, LoveAndWarMod.MODID);

    public static final RegistryObject<EntityType<RobotdogEntity>> ROBOT_DOG =
            ENTITY_TYPES.register("robotdog", () -> EntityType.Builder.of(RobotdogEntity::new, MobCategory.CREATURE)
                    .sized(1f,1f).build("robotdog"));

    public static final RegistryObject<EntityType<ShotProjectile>> SHOT_PROJECTILE =
            ENTITY_TYPES.register("shot_projectile", () -> EntityType.Builder.<ShotProjectile>of(ShotProjectile::new, MobCategory.MISC)
                    .sized(0.25f,0.25f).build("shot_projectile"));


    public static void register(IEventBus eventBus){
        ENTITY_TYPES.register(eventBus);
    }
}
