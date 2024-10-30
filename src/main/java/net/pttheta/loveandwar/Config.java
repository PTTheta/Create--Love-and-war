package net.pttheta.loveandwar;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Forge's config APIs
@Mod.EventBusSubscriber(modid = LoveAndWarMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config
{
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    private static final ForgeConfigSpec.BooleanValue NO_OIL= BUILDER
            .comment("Whether to disable oil generation on world creation")
            .define("addNoOilDatapackAutomatically", false);

    private static final ForgeConfigSpec.BooleanValue SIMPLE_BRASS= BUILDER
            .comment("Automatically add simple brass datapack on world creation")
            .define("addSimpleBrassDatapackAutomatically", true);

    private static final ForgeConfigSpec.BooleanValue VENDER_DATA= BUILDER
            .comment("Automatically add Vender's game Data compat")
            .define("vendersGameDataCompat", true);

    private static final ForgeConfigSpec.BooleanValue VENDER_RESOURCE= BUILDER
            .comment("Automatically add Vender's game Resource compat")
            .define("vendersGameArtCompat", true);

    public final String CATEGORY_DRAWING = "drawing";
    public static final ForgeConfigSpec.IntValue DRAWING_PROCESSING_DURATION = BUILDER
            .comment("Drawing machine duration in ticks")
            .defineInRange("drawing_processing_duration", 100, 0, Integer.MAX_VALUE);
    public static final ForgeConfigSpec.IntValue STAMPING_PROCESSING_DURATION = BUILDER
            .comment("Stamping machine duration in ticks")
            .defineInRange("stamping_processing_duration", 100, 0, Integer.MAX_VALUE);


    // a list of strings that are treated as resource locations for items
    //private static final ForgeConfigSpec.ConfigValue<List<? extends String>> ITEM_STRINGS = BUILDER
            //.comment("A list of items to log on common setup.")
            //.defineListAllowEmpty("items", List.of("minecraft:iron_ingot"), Config::validateItemName);

    static final ForgeConfigSpec SPEC = BUILDER.build();

    //public static boolean logDirtBlock;
    public static int drawing_processing_time;
    public static boolean no_oil;
    public static boolean simple_brass;
    public static boolean vender_data;
    public static boolean vender_art;
    //public static String magicNumberIntroduction;
    //public static Set<Item> items;

    private static boolean validateItemName(final Object obj)
    {
        return obj instanceof final String itemName && ForgeRegistries.ITEMS.containsKey(new ResourceLocation(itemName));
    }

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
        //logDirtBlock = LOG_DIRT_BLOCK.get();
        drawing_processing_time = DRAWING_PROCESSING_DURATION.get();
        no_oil = NO_OIL.get();
        simple_brass = SIMPLE_BRASS.get();
        vender_data = VENDER_DATA.get();
        vender_art = VENDER_RESOURCE.get();
        //magicNumberIntroduction = MAGIC_NUMBER_INTRODUCTION.get();

        // convert the list of strings into a set of items
        //items = ITEM_STRINGS.get().stream()
                //.map(itemName -> ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemName)))
                //.collect(Collectors.toSet());
    }
}
