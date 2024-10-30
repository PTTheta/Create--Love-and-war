package net.pttheta.loveandwar;

//import com.flansmod.client.render.effects.EffectRenderer;
//import com.flansmod.client.render.models.FlansModelRegistry;
import com.mojang.logging.LogUtils;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.item.ItemDescription;
import com.simibubi.create.foundation.item.KineticStats;
import com.simibubi.create.foundation.item.TooltipHelper;
import com.simibubi.create.foundation.item.TooltipModifier;
import net.minecraft.ChatFormatting;
import net.minecraft.SharedConstants;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.PathPackResources;
import net.minecraft.server.packs.metadata.pack.PackMetadataSection;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackCompatibility;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AddPackFindersEvent;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.pttheta.loveandwar.blocks.LAWBlockEntityTypes;
import net.pttheta.loveandwar.blocks.LAWBlocks;
import net.pttheta.loveandwar.blocks.LAWPartialModels;
import net.pttheta.loveandwar.blocks.ModBlocks;
import net.pttheta.loveandwar.blocks.entity.ModBlockEntities;
import net.pttheta.loveandwar.entity.ModEntities;
import net.pttheta.loveandwar.entity.client.RobotdogRenderer;
import net.pttheta.loveandwar.entity.client.ShotRenderer;

import net.pttheta.loveandwar.fluid.ModFluidTypes;
import net.pttheta.loveandwar.fluid.ModFluids;
import net.pttheta.loveandwar.item.ModCreativeModeTabs;
import net.pttheta.loveandwar.item.ModItems;
import net.pttheta.loveandwar.loot.ModLootModifiers;
import net.pttheta.loveandwar.network.ModMessages;
import net.pttheta.loveandwar.ponder.LAWPonder;
import net.pttheta.loveandwar.recipe.ModRecipes;
import net.pttheta.loveandwar.recipe.RecipeRegister;
import net.pttheta.loveandwar.screen.ModMenuTypes;
import net.pttheta.loveandwar.sound.ModSounds;
import org.slf4j.Logger;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.function.UnaryOperator;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(LoveAndWarMod.MODID)
public class LoveAndWarMod
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "createloveandwar";
    public static final CreateRegistrate LAWREGISTRATE = CreateRegistrate.create(MODID);
    static {
        LAWREGISTRATE.setTooltipModifierFactory(item -> {
            return new ItemDescription.Modifier(item, TooltipHelper.Palette.STANDARD_CREATE)
                    .andThen(TooltipModifier.mapNull(KineticStats.create(item)));
        });
    }

    private static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Blocks which will all be registered under the "examplemod" namespace

    public LoveAndWarMod()
    {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientSetup);
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::packSetup);
        ModCreativeModeTabs.register(modEventBus);
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        ModLootModifiers.register(modEventBus);
        ModSounds.register(modEventBus);
        ModEntities.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModMenuTypes.register(modEventBus);
        ModFluids.register(modEventBus);
        ModFluidTypes.register(modEventBus);
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);
        LAWREGISTRATE.setCreativeTab(ModCreativeModeTabs.LOVE_AND_WAR_TAB);
        LAWREGISTRATE.registerEventListeners(modEventBus);
        LAWREGISTRATE.setCreativeTab(ModCreativeModeTabs.LOVE_AND_WAR_TAB);
        LAWBlocks.register();
        LAWREGISTRATE.setCreativeTab(ModCreativeModeTabs.LOVE_AND_WAR_TAB);
        LAWBlockEntityTypes.register();
        ModRecipes.register(modEventBus);
        RecipeRegister.register(modEventBus);
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);
        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> LAWPartialModels::init);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");


        event.enqueueWork(()-> {
            ModMessages.register();

        });

    }

    public void packSetup(AddPackFindersEvent event) {

        //Resource packs
        this.setupVenderArt(event);

        // Data Packs
        this.setupVenderData(event);
        this.setupNoOilPack(event);
        this.setupSimpleBrass(event);
    }

    private void setupNoOilPack(AddPackFindersEvent event) {
        if (event.getPackType() == PackType.SERVER_DATA) {
            Path resourcePath = ModList.get().getModFileById(LoveAndWarMod.MODID).getFile().findResource("packs/no_oil");
            PathPackResources pack = new PathPackResources(ModList.get().getModFileById(LoveAndWarMod.MODID).getFile().getFileName() + ":" + resourcePath, resourcePath, true);
            PackMetadataSection metadata = new PackMetadataSection(Component.translatable("pack.createloveandwar.no_oil.description"), SharedConstants.getCurrentVersion().getPackVersion(PackType.SERVER_DATA));
            event.addRepositorySource((source) ->
                    source.accept(Pack.create(
                            "builtin/loveandwar_no_oil",
                            Component.translatable("pack.createloveandwar.no_oil.title"),
                            false,
                            (string) -> pack,
                            new Pack.Info(metadata.getDescription(), metadata.getPackFormat(PackType.SERVER_DATA), metadata.getPackFormat(PackType.CLIENT_RESOURCES), FeatureFlagSet.of(), pack.isHidden()),
                            PackType.SERVER_DATA,
                            Pack.Position.TOP,
                            false,
                            create(decorateWithSource("pack.source.builtin"), Config.no_oil))
                    )
            );
        }
    }

    private void setupSimpleBrass(AddPackFindersEvent event) {
        if (event.getPackType() == PackType.SERVER_DATA) {
            Path resourcePath = ModList.get().getModFileById(LoveAndWarMod.MODID).getFile().findResource("packs/simple_brass");
            PathPackResources pack = new PathPackResources(ModList.get().getModFileById(LoveAndWarMod.MODID).getFile().getFileName() + ":" + resourcePath, resourcePath, true);
            PackMetadataSection metadata = new PackMetadataSection(Component.translatable("pack.createloveandwar.simple_brass.description"), SharedConstants.getCurrentVersion().getPackVersion(PackType.SERVER_DATA));
            event.addRepositorySource((source) ->
                    source.accept(Pack.create(
                            "builtin/loveandwar_simple_brass",
                            Component.translatable("pack.createloveandwar.simple_brass.title"),
                            false,
                            (string) -> pack,
                            new Pack.Info(metadata.getDescription(), metadata.getPackFormat(PackType.SERVER_DATA), metadata.getPackFormat(PackType.CLIENT_RESOURCES), FeatureFlagSet.of(), pack.isHidden()),
                            PackType.SERVER_DATA,
                            Pack.Position.TOP,
                            false,
                            create(decorateWithSource("pack.source.builtin"), Config.simple_brass))
                    )
            );
        }
    }

    private void setupVenderData(AddPackFindersEvent event) {
        if (event.getPackType() == PackType.SERVER_DATA) {
            Path resourcePath = ModList.get().getModFileById(LoveAndWarMod.MODID).getFile().findResource("packs/vender_compat_data");
            PathPackResources pack = new PathPackResources(ModList.get().getModFileById(LoveAndWarMod.MODID).getFile().getFileName() + ":" + resourcePath, resourcePath, true);
            PackMetadataSection metadata = new PackMetadataSection(Component.translatable("pack.createloveandwar.vender_data.description"), SharedConstants.getCurrentVersion().getPackVersion(PackType.SERVER_DATA));
            event.addRepositorySource((source) ->
                    source.accept(Pack.create(
                            "builtin/loveandwar_vender_data",
                            Component.translatable("pack.createloveandwar.vender_data.title"),
                            false,
                            (string) -> pack,
                            new Pack.Info(metadata.getDescription(), metadata.getPackFormat(PackType.SERVER_DATA), metadata.getPackFormat(PackType.CLIENT_RESOURCES), FeatureFlagSet.of(), pack.isHidden()),
                            PackType.SERVER_DATA,
                            Pack.Position.TOP,
                            false,
                            create(decorateWithSource("pack.source.builtin"), Config.vender_data))
                    )
            );
        }
    }

    private void setupVenderArt(AddPackFindersEvent event) {
        if (event.getPackType() == PackType.CLIENT_RESOURCES) {
            Path resourcePath = ModList.get().getModFileById(LoveAndWarMod.MODID).getFile().findResource("packs/vender_compat_art");
            PathPackResources pack = new PathPackResources(ModList.get().getModFileById(LoveAndWarMod.MODID).getFile().getFileName() + ":" + resourcePath, resourcePath, true);
            PackMetadataSection metadata = new PackMetadataSection(Component.translatable("pack.createloveandwar.vender_art.description"), SharedConstants.getCurrentVersion().getPackVersion(PackType.CLIENT_RESOURCES));
            event.addRepositorySource((source) ->
                    source.accept(Pack.create(
                            "builtin/loveandwar_vender_art",
                            Component.translatable("pack.createloveandwar.vender_art.title"),
                            Config.vender_art,
                            (string) -> pack,
                            new Pack.Info(metadata.getDescription(), metadata.getPackFormat(PackType.SERVER_DATA), metadata.getPackFormat(PackType.CLIENT_RESOURCES), FeatureFlagSet.of(), pack.isHidden()),
                            PackType.CLIENT_RESOURCES,
                            Pack.Position.TOP,
                            false,
                            create(decorateWithSource("pack.source.builtin"), Config.vender_art))
                    )
            );
        }
    }

    static PackSource create(final UnaryOperator<Component> decorator, final boolean shouldAddAutomatically) {
    return new PackSource() {
        public Component decorate(Component component) {
            return decorator.apply(component);
        }

        public boolean shouldAddAutomatically() {
            return shouldAddAutomatically;
        }
    };
}

    private static UnaryOperator<Component> decorateWithSource(String translationKey) {
        Component component = Component.translatable(translationKey);
        return (name) -> Component.translatable("pack.nameAndSource", name, component).withStyle(ChatFormatting.GRAY);
    }

            // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS){
            event.accept(ModItems.SULPHUR);
            event.accept(ModItems.RAW_SULPHUR);
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            EntityRenderers.register(ModEntities.ROBOT_DOG.get(), RobotdogRenderer::new);
            EntityRenderers.register(ModEntities.SHOT_PROJECTILE.get(), ShotRenderer::new);
            //LAWPartialModels.init();

            ItemBlockRenderTypes.setRenderLayer(ModFluids.SOURCE_BITUMEN.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModFluids.FLOWING_BITUMEN.get(), RenderType.translucent());
            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());

            if(Config.vender_art){
                if(!Minecraft.getInstance().options.resourcePacks.contains("builtin/loveandwar_vender_art")) {
                    Minecraft.getInstance().options.resourcePacks.add("builtin/loveandwar_vender_art");
                    Minecraft.getInstance().reloadResourcePacks();
                }
            }
        }
    }

    private void doClientSetup(final FMLClientSetupEvent event){
        event.enqueueWork(LAWPonder::register);

    }

    public static ResourceLocation asResource(String path) {
        return new ResourceLocation(MODID, path);
    }
}
