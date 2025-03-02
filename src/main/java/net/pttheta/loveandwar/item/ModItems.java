package net.pttheta.loveandwar.item;


import com.flansmod.common.FlansMod;
import com.flansmod.common.crafting.WorkbenchBlockEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.pttheta.loveandwar.LoveAndWarMod;
import net.pttheta.loveandwar.fluid.ModFluids;
import net.pttheta.loveandwar.item.custom.*;
import net.pttheta.loveandwar.util.ModTags;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, LoveAndWarMod.MODID);
    public static final DeferredRegister<BlockEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, LoveAndWarMod.MODID);

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, LoveAndWarMod.MODID);
    public static final RegistryObject<Item> SULPHUR = ITEMS.register("sulphur", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_SULPHUR = ITEMS.register("raw_sulphur", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PROPELLANT = ITEMS.register("propellant", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PROPELLANT_SHEET = ITEMS.register("propellant_sheet", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PROPELLANT_MOULDED = ITEMS.register("propellant_moulded", () -> new Item(new Item.Properties()));


    public static final RegistryObject<Item> TUNGSTEN = ITEMS.register("tungsten", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> TUNGSTEN_NUGGET = ITEMS.register("tungsten_nugget", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> TUNGSTEN_SHEET = ITEMS.register("tungsten_sheet", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> NETHERITE_SHEET = ITEMS.register("netherite_sheet", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CUT_NETHERITE_SHEET = ITEMS.register("cut_netherite_sheet", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_TUNGSTEN = ITEMS.register("raw_tungsten", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CIGARETTE = ITEMS.register("cigarette", () -> new Item(new Item.Properties().food(ModFoods.CIGARETTE)));
    public static final RegistryObject<Item> FORGOTTEN_GUNSMITH_PARTS = ITEMS.register("forgotten_gunsmith_parts", () -> new Item(new Item.Properties().food(ModFoods.CIGARETTE)));


    public static final RegistryObject<Item> TUNGSTEN_SWORD = ITEMS.register("tungsten_sword", () -> new SwordItem(ModToolTiers.TUNGSTEN, 3,1.2f,new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> TUNGSTEN_PICKAXE = ITEMS.register("tungsten_pickaxe", () -> new PickaxeItem(ModToolTiers.TUNGSTEN, 1,1,new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> TUNGSTEN_AXE = ITEMS.register("tungsten_axe", () -> new AxeItem(ModToolTiers.TUNGSTEN, 5,1,new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> TUNGSTEN_SHOVEL = ITEMS.register("tungsten_shovel", () -> new ShovelItem(ModToolTiers.TUNGSTEN, 4,1,new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> TUNGSTEN_HOE = ITEMS.register("tungsten_hoe", () -> new HoeItem(ModToolTiers.TUNGSTEN, 1,1,new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> TUNGSTEN_HELMET = ITEMS.register("tungsten_helmet", () -> new ModArmorItem(ModArmorMaterials.TUNGSTEN,ArmorItem.Type.HELMET, new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> TUNGSTEN_CHESTPLATE = ITEMS.register("tungsten_chestplate", () -> new ArmorItem(ModArmorMaterials.TUNGSTEN,ArmorItem.Type.CHESTPLATE, new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> TUNGSTEN_LEGGINGS = ITEMS.register("tungsten_leggings", () -> new ArmorItem(ModArmorMaterials.TUNGSTEN,ArmorItem.Type.LEGGINGS, new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> TUNGSTEN_BOOTS = ITEMS.register("tungsten_boots", () -> new ArmorItem(ModArmorMaterials.TUNGSTEN,ArmorItem.Type.BOOTS, new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> STEEL_INGOT = ITEMS.register("steel_ingot", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> STEEL_NUGGET = ITEMS.register("steel_nugget", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> STEEL_SHEET = ITEMS.register("steel_sheet", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BRASS_MIX= ITEMS.register("brass_mix", () -> new Item(new Item.Properties()));


    public static final RegistryObject<Item> ROBOT_DOG_SPAWN_EGG = ITEMS.register("robot_dog_spawn_egg",
            () -> new RobotDogItem(new Item.Properties()));
    public static final RegistryObject<Item> TURRET_UPGRADE = ITEMS.register("turret", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ARMOR_UPGRADE = ITEMS.register("robot_dog_armor", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SCANNER_UPGRADE = ITEMS.register("scanner", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ROBOT_DOG_VALKYRIE_SKIN = ITEMS.register("robot_dog_valkyrie_skin", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ROBOT_DOG_BUBBLEGUM_SKIN = ITEMS.register("robot_dog_bubblegum_skin", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ROBOT_DOG_RACING_SKIN = ITEMS.register("robot_dog_racing_skin", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BITUMEN_BUCKET = ITEMS.register("bitumen_bucket", () -> new BucketItem(ModFluids.SOURCE_BITUMEN, new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));
    public static final RegistryObject<Item> CRUDE_OIL_BUCKET = ITEMS.register("crude_oil_bucket", () -> new BucketItem(ModFluids.SOURCE_CRUDE_OIL, new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));

    public static final RegistryObject<Item> DIESEL_BUCKET = ITEMS.register("diesel_bucket", () -> new BucketItem(ModFluids.SOURCE_DIESEL, new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));

    public static final RegistryObject<Item> KEROSENE_BUCKET = ITEMS.register("kerosene_bucket", () -> new BucketItem(ModFluids.SOURCE_KEROSENE, new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));



    public static final RegistryObject<Item> ETHYLENE_BUCKET = ITEMS.register("ethylene_bucket", () -> new BucketItem(ModFluids.SOURCE_ETHYLENE, new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));

    public static final RegistryObject<Item> POLYMER_BUCKET = ITEMS.register("polymer_bucket", () -> new BucketItem(ModFluids.SOURCE_POLYMER, new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));
    public static final RegistryObject<Item> DURAPLAS_BUCKET = ITEMS.register("duraplas_bucket", () -> new BucketItem(ModFluids.SOURCE_DURAPLAS, new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));

    public static final RegistryObject<Item> TAR_CLUMP = ITEMS.register("tar_clump", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> THERMOSTAT = ITEMS.register("thermostat", () -> new ThermostatItem(new Item.Properties()));
    public static final RegistryObject<Item> SEEKER_UNIT = ITEMS.register("seeker_unit", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MAS62_BLUEPRINT = ITEMS.register("mas62blueprint", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> EM2_BLUEPRINT = ITEMS.register("em2blueprint", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> STG57_BLUEPRINT = ITEMS.register("stg57blueprint", () -> new Item(new Item.Properties()));




    public static final RegistryObject<Item> SLIME_RESIN = ITEMS.register("slime_resin", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SLIME_RESIN_SHEET = ITEMS.register("slime_resin_sheet", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CATALYST = ITEMS.register("catalyst", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> GLASS_LENS = ITEMS.register("glass_lens", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> OPTICAL_SCOPE = ITEMS.register("optical_scope", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> LAUNCHER_BARREL = ITEMS.register("launcher_barrel", () -> new Item(new Item.Properties()));


    public static final RegistryObject<Item> POLYMER_INGOT = ITEMS.register("polymer_ingot", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> POLYMER_SHEET = ITEMS.register("polymer_sheet", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DURAPLAS_INGOT = ITEMS.register("duraplas_ingot", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DURAPLAS_SHEET = ITEMS.register("duraplas_sheet", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> FUEL_PELLET = ITEMS.register("fuel_pellet", () -> new FuelItem(new Item.Properties(),2000));

    //Flans compat
    public static final RegistryObject<Item> COPPER_CUP = ITEMS.register("copper_cup", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> GYROJET_CASE = ITEMS.register("gyrojet_case", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> STEEL_CUP = ITEMS.register("steel_cup", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> HEAVY_RIFLE_CASING = ITEMS.register("heavy_rifle_casing", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> FILLED_HEAVY_RIFLE_CASING = ITEMS.register("filled_heavy_rifle_casing", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BRASS_CUP = ITEMS.register("brass_cup", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> WIDE_BRASS_CUP = ITEMS.register("wide_brass_cup", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PISTOL_CASING = ITEMS.register("pistol_casing", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RIFLE_CASING = ITEMS.register("rifle_casing", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> GRENADE_CASING = ITEMS.register("grenade_casing", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ARTILLERY_CASING = ITEMS.register("artillery_casing", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SHOTGUN_CASING = ITEMS.register("shotgun_casing", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> FILLED_PISTOL_CASING = ITEMS.register("filled_pistol_casing", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> FILLED_RIFLE_CASING = ITEMS.register("filled_rifle_casing", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> FILLED_GRENADE_CASING = ITEMS.register("filled_grenade_casing", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> FILLED_ARTILLERY_CASING = ITEMS.register("filled_artillery_casing", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> FILLED_SHOTGUN_CASING = ITEMS.register("filled_shotgun_casing", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PISTOL_BULLET = FlansMod.Bullet(ITEMS, LoveAndWarMod.MODID, "pistol_bullet");
    public static final RegistryObject<Item> PISTOL_BULLET_SOFT = FlansMod.Bullet(ITEMS, LoveAndWarMod.MODID, "pistol_bullet_soft");
    public static final RegistryObject<Item> PISTOL_BULLET_AP = FlansMod.Bullet(ITEMS, LoveAndWarMod.MODID, "pistol_bullet_ap");

    public static final RegistryObject<Item> RIFLE_BULLET = FlansMod.Bullet(ITEMS, LoveAndWarMod.MODID, "rifle_bullet");
    public static final RegistryObject<Item> RIFLE_BULLET_SOFT = FlansMod.Bullet(ITEMS, LoveAndWarMod.MODID, "rifle_bullet_soft");
    public static final RegistryObject<Item> RIFLE_BULLET_AP = FlansMod.Bullet(ITEMS, LoveAndWarMod.MODID, "rifle_bullet_ap");

    public static final RegistryObject<Item> HEAVY_RIFLE_BULLET = FlansMod.Bullet(ITEMS, LoveAndWarMod.MODID, "heavy_rifle_bullet");

    public static final RegistryObject<Item> GYROJET_BASIC = FlansMod.Bullet(ITEMS, LoveAndWarMod.MODID, "gyrojet_basic");
    public static final RegistryObject<Item> GYROJET_SEEKER = FlansMod.Bullet(ITEMS, LoveAndWarMod.MODID, "gyrojet_heatseeker");
    public static final RegistryObject<Item> GYROJET_SEEKER_TOP = FlansMod.Bullet(ITEMS, LoveAndWarMod.MODID, "gyrojet_heatseeker_top");
    public static final RegistryObject<Item> GYROJET_PEN = FlansMod.Bullet(ITEMS, LoveAndWarMod.MODID, "gyrojet_pen");
    public static final RegistryObject<Item> GYROJET_HE = FlansMod.Bullet(ITEMS, LoveAndWarMod.MODID, "gyrojet_he");

    public static final RegistryObject<Item> CASELESS_BULLET = FlansMod.Bullet(ITEMS, LoveAndWarMod.MODID, "caseless_bullet");
    public static final RegistryObject<Item> CASELESS_BULLET_AP = FlansMod.Bullet(ITEMS, LoveAndWarMod.MODID, "caseless_bullet_ap");
    public static final RegistryObject<Item> CASELESS_BULLET_SOFT = FlansMod.Bullet(ITEMS, LoveAndWarMod.MODID, "caseless_bullet_soft");


    public static final RegistryObject<Item> GRENADE = ITEMS.register("grenade_he", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> GRENADE_ROUND = FlansMod.Bullet(ITEMS, LoveAndWarMod.MODID, "grenade_round_he");
    public static final RegistryObject<Item> GRENADE_ROUND_BUCKSHOT = FlansMod.Bullet(ITEMS, LoveAndWarMod.MODID, "grenade_round_buckshot");

    public static final RegistryObject<Item> BLACK_POWDER_CARTRIDGE = FlansMod.Bullet(ITEMS, LoveAndWarMod.MODID, "black_powder_cartridge");

    public static final RegistryObject<Item> FLECHETTES = ITEMS.register("flechettes", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BALL_BEARINGS = ITEMS.register("ball_bearings", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BUCKSHOT_SHELL= FlansMod.Bullet(ITEMS, LoveAndWarMod.MODID, "buckshot_shell");
    public static final RegistryObject<Item> SLUG_SHELL= FlansMod.Bullet(ITEMS, LoveAndWarMod.MODID, "slug_shell");
    public static final RegistryObject<Item> FLECHETTE_SHELL= FlansMod.Bullet(ITEMS, LoveAndWarMod.MODID, "flechette_shell");
    public static final RegistryObject<Item> EXPLOSIVE_SHELL= FlansMod.Bullet(ITEMS, LoveAndWarMod.MODID, "explosive_shell");

    public static final RegistryObject<Item> ARTILLERY_SHELL= FlansMod.Bullet(ITEMS, LoveAndWarMod.MODID, "artillery_shell");
    public static final RegistryObject<Item> ARTILLERY_SHELL_AP= FlansMod.Bullet(ITEMS, LoveAndWarMod.MODID, "artillery_shell_ap");
    public static final RegistryObject<Item> ARTILLERY_SHELL_HE= FlansMod.Bullet(ITEMS, LoveAndWarMod.MODID, "artillery_shell_he");


    public static final RegistryObject<Item> TEMPLATE_UPPER_RECEIVER = ITEMS.register("template_upper_receiver", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> TEMPLATE_LOWER_RECEIVER = ITEMS.register("template_lower_receiver", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> TEMPLATE_STOCK = ITEMS.register("template_stock", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> TEMPLATE_BARREL= ITEMS.register("template_barrel", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> TEMPLATE_GRIP = ITEMS.register("template_grip", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> TEMPLATE_HANDGUARD = ITEMS.register("template_handguard", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> TEMPLATE_BOLT_ACTION = ITEMS.register("template_bolt_action", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> TEMPLATE_REVOLVER_ACTION = ITEMS.register("template_revolver_action", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> TEMPLATE_PROPELLANT_BLOCK = ITEMS.register("template_propellant_block", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> AMMO_POUCH = ITEMS.register("ammopouch", () -> new AmmoPouchItem(new Item.Properties().stacksTo(1),64, ModTags.Items.PAPER_CARTRIDGE));
    public static final RegistryObject<Item> AMMO_POUCH_PISTOL = ITEMS.register("ammopouch_pistol", () -> new AmmoPouchItem(new Item.Properties().stacksTo(1),192, ModTags.Items.PISTOL_BULLET));
    public static final RegistryObject<Item> AMMO_POUCH_RIFLE = ITEMS.register("ammopouch_rifle", () -> new AmmoPouchItem(new Item.Properties().stacksTo(1),96, ModTags.Items.RIFLE_BULLET));
    public static final RegistryObject<Item> AMMO_POUCH_SHOTGUN = ITEMS.register("ammopouch_shotgun", () -> new AmmoPouchItem(new Item.Properties().stacksTo(1),64, ModTags.Items.SHOTGUN_SHELL));

    public static final RegistryObject<Item> UPPER_RECEIVER_IRON = FlansMod.Part(ITEMS, LoveAndWarMod.MODID, "upper_receiver_iron");
    public static final RegistryObject<Item> LOWER_RECEIVER_IRON = FlansMod.Part(ITEMS, LoveAndWarMod.MODID, "lower_receiver_iron");
    public static final RegistryObject<Item> STOCK_IRON = FlansMod.Part(ITEMS, LoveAndWarMod.MODID, "stock_iron");
    public static final RegistryObject<Item> BARREL_IRON = FlansMod.Part(ITEMS, LoveAndWarMod.MODID, "barrel_iron");
    public static final RegistryObject<Item> GRIP_IRON = FlansMod.Part(ITEMS, LoveAndWarMod.MODID, "grip_iron");
    public static final RegistryObject<Item> HANDGUARD_IRON = FlansMod.Part(ITEMS, LoveAndWarMod.MODID, "handguard_iron");
    public static final RegistryObject<Item> REVOLVER_ACTION_IRON = FlansMod.Part(ITEMS, LoveAndWarMod.MODID, "revolver_action_iron");
    public static final RegistryObject<Item> BOLT_ACTION_IRON = FlansMod.Part(ITEMS, LoveAndWarMod.MODID, "bolt_action_iron");
    public static final RegistryObject<Item> INCOMPLETE_IRON_RECEIVER = FlansMod.Part(ITEMS, LoveAndWarMod.MODID, "incomplete_iron_receiver");

    public static final RegistryObject<Item> UPPER_RECEIVER_BRASS = FlansMod.Part(ITEMS, LoveAndWarMod.MODID, "upper_receiver_brass");
    public static final RegistryObject<Item> LOWER_RECEIVER_BRASS = FlansMod.Part(ITEMS, LoveAndWarMod.MODID, "lower_receiver_brass");
    public static final RegistryObject<Item> STOCK_BRASS = FlansMod.Part(ITEMS, LoveAndWarMod.MODID, "stock_brass");
    public static final RegistryObject<Item> BARREL_BRASS = FlansMod.Part(ITEMS, LoveAndWarMod.MODID, "barrel_brass");
    public static final RegistryObject<Item> GRIP_BRASS = FlansMod.Part(ITEMS, LoveAndWarMod.MODID, "grip_brass");
    public static final RegistryObject<Item> HANDGUARD_BRASS = FlansMod.Part(ITEMS, LoveAndWarMod.MODID, "handguard_brass");
    public static final RegistryObject<Item> REVOLVER_ACTION_BRASS = FlansMod.Part(ITEMS, LoveAndWarMod.MODID, "revolver_action_brass");
    public static final RegistryObject<Item> BOLT_ACTION_BRASS = FlansMod.Part(ITEMS, LoveAndWarMod.MODID, "bolt_action_brass");
    public static final RegistryObject<Item> INCOMPLETE_BRASS_RECEIVER = FlansMod.Part(ITEMS, LoveAndWarMod.MODID, "incomplete_brass_receiver");

    public static final RegistryObject<Item> UPPER_RECEIVER_TUNGSTEN = FlansMod.Part(ITEMS, LoveAndWarMod.MODID, "upper_receiver_tungsten");
    public static final RegistryObject<Item> LOWER_RECEIVER_TUNGSTEN = FlansMod.Part(ITEMS, LoveAndWarMod.MODID, "lower_receiver_tungsten");
    public static final RegistryObject<Item> STOCK_TUNGSTEN = FlansMod.Part(ITEMS, LoveAndWarMod.MODID, "stock_tungsten");
    public static final RegistryObject<Item> BARREL_TUNGSTEN = FlansMod.Part(ITEMS, LoveAndWarMod.MODID, "barrel_tungsten");
    public static final RegistryObject<Item> GRIP_TUNGSTEN = FlansMod.Part(ITEMS, LoveAndWarMod.MODID, "grip_tungsten");
    public static final RegistryObject<Item> HANDGUARD_TUNGSTEN = FlansMod.Part(ITEMS, LoveAndWarMod.MODID, "handguard_tungsten");
    public static final RegistryObject<Item> REVOLVER_ACTION_TUNGSTEN = FlansMod.Part(ITEMS, LoveAndWarMod.MODID, "revolver_action_tungsten");
    public static final RegistryObject<Item> BOLT_ACTION_TUNGSTEN = FlansMod.Part(ITEMS, LoveAndWarMod.MODID, "bolt_action_tungsten");
    public static final RegistryObject<Item> INCOMPLETE_TUNGSTEN_RECEIVER = FlansMod.Part(ITEMS, LoveAndWarMod.MODID, "incomplete_tungsten_receiver");

    public static final RegistryObject<Item> UPPER_RECEIVER_STURDY = FlansMod.Part(ITEMS, LoveAndWarMod.MODID, "upper_receiver_sturdy");
    public static final RegistryObject<Item> LOWER_RECEIVER_STURDY = FlansMod.Part(ITEMS, LoveAndWarMod.MODID, "lower_receiver_sturdy");
    public static final RegistryObject<Item> STOCK_STURDY = FlansMod.Part(ITEMS, LoveAndWarMod.MODID, "stock_sturdy");
    public static final RegistryObject<Item> BARREL_STURDY = FlansMod.Part(ITEMS, LoveAndWarMod.MODID, "barrel_sturdy");
    public static final RegistryObject<Item> GRIP_STURDY = FlansMod.Part(ITEMS, LoveAndWarMod.MODID, "grip_sturdy");
    public static final RegistryObject<Item> HANDGUARD_STURDY = FlansMod.Part(ITEMS, LoveAndWarMod.MODID, "handguard_sturdy");
    public static final RegistryObject<Item> REVOLVER_ACTION_STURDY = FlansMod.Part(ITEMS, LoveAndWarMod.MODID, "revolver_action_sturdy");
    public static final RegistryObject<Item> BOLT_ACTION_STURDY = FlansMod.Part(ITEMS, LoveAndWarMod.MODID, "bolt_action_sturdy");
    public static final RegistryObject<Item> INCOMPLETE_STURDY_RECEIVER = FlansMod.Part(ITEMS, LoveAndWarMod.MODID, "incomplete_sturdy_receiver");

    public static final RegistryObject<Item> UPPER_RECEIVER_NETHERITE = FlansMod.Part(ITEMS, LoveAndWarMod.MODID, "upper_receiver_netherite");
    public static final RegistryObject<Item> LOWER_RECEIVER_NETHERITE = FlansMod.Part(ITEMS, LoveAndWarMod.MODID, "lower_receiver_netherite");
    public static final RegistryObject<Item> STOCK_NETHERITE = FlansMod.Part(ITEMS, LoveAndWarMod.MODID, "stock_netherite");
    public static final RegistryObject<Item> BARREL_NETHERITE = FlansMod.Part(ITEMS, LoveAndWarMod.MODID, "barrel_netherite");
    public static final RegistryObject<Item> GRIP_NETHERITE = FlansMod.Part(ITEMS, LoveAndWarMod.MODID, "grip_netherite");
    public static final RegistryObject<Item> HANDGUARD_NETHERITE = FlansMod.Part(ITEMS, LoveAndWarMod.MODID, "handguard_netherite");
    public static final RegistryObject<Item> REVOLVER_ACTION_NETHERITE = FlansMod.Part(ITEMS, LoveAndWarMod.MODID, "revolver_action_netherite");
    public static final RegistryObject<Item> BOLT_ACTION_NETHERITE = FlansMod.Part(ITEMS, LoveAndWarMod.MODID, "bolt_action_netherite");
    public static final RegistryObject<Item> INCOMPLETE_NETHERITE_RECEIVER = FlansMod.Part(ITEMS, LoveAndWarMod.MODID, "incomplete_netherite_receiver");

    public static final RegistryObject<Item> UPPER_RECEIVER_RESIN = FlansMod.Part(ITEMS, LoveAndWarMod.MODID, "upper_receiver_resin");
    public static final RegistryObject<Item> LOWER_RECEIVER_RESIN = FlansMod.Part(ITEMS, LoveAndWarMod.MODID, "lower_receiver_resin");
    public static final RegistryObject<Item> STOCK_RESIN = FlansMod.Part(ITEMS, LoveAndWarMod.MODID, "stock_resin");
    public static final RegistryObject<Item> BARREL_RESIN = FlansMod.Part(ITEMS, LoveAndWarMod.MODID, "barrel_resin");
    public static final RegistryObject<Item> GRIP_RESIN = FlansMod.Part(ITEMS, LoveAndWarMod.MODID, "grip_resin");
    public static final RegistryObject<Item> HANDGUARD_RESIN = FlansMod.Part(ITEMS, LoveAndWarMod.MODID, "handguard_resin");
    public static final RegistryObject<Item> REVOLVER_ACTION_RESIN = FlansMod.Part(ITEMS, LoveAndWarMod.MODID, "revolver_action_resin");
    public static final RegistryObject<Item> BOLT_ACTION_RESIN = FlansMod.Part(ITEMS, LoveAndWarMod.MODID, "bolt_action_resin");
    public static final RegistryObject<Item> INCOMPLETE_RESIN_RECEIVER = FlansMod.Part(ITEMS, LoveAndWarMod.MODID, "incomplete_resin_receiver");

    public static final RegistryObject<Item> UPPER_RECEIVER_POLYMER = FlansMod.Part(ITEMS, LoveAndWarMod.MODID, "upper_receiver_polymer");
    public static final RegistryObject<Item> LOWER_RECEIVER_POLYMER = FlansMod.Part(ITEMS, LoveAndWarMod.MODID, "lower_receiver_polymer");
    public static final RegistryObject<Item> STOCK_POLYMER = FlansMod.Part(ITEMS, LoveAndWarMod.MODID, "stock_polymer");
    public static final RegistryObject<Item> BARREL_POLYMER = FlansMod.Part(ITEMS, LoveAndWarMod.MODID, "barrel_polymer");
    public static final RegistryObject<Item> GRIP_POLYMER = FlansMod.Part(ITEMS, LoveAndWarMod.MODID, "grip_polymer");
    public static final RegistryObject<Item> HANDGUARD_POLYMER = FlansMod.Part(ITEMS, LoveAndWarMod.MODID, "handguard_polymer");
    public static final RegistryObject<Item> REVOLVER_ACTION_POLYMER = FlansMod.Part(ITEMS, LoveAndWarMod.MODID, "revolver_action_polymer");
    public static final RegistryObject<Item> BOLT_ACTION_POLYMER = FlansMod.Part(ITEMS, LoveAndWarMod.MODID, "bolt_action_polymer");
    public static final RegistryObject<Item> INCOMPLETE_POLYMER_RECEIVER = FlansMod.Part(ITEMS, LoveAndWarMod.MODID, "incomplete_polymer_receiver");

    public static final RegistryObject<Item> UPPER_RECEIVER_DURAPLAS = FlansMod.Part(ITEMS, LoveAndWarMod.MODID, "upper_receiver_duraplas");
    public static final RegistryObject<Item> LOWER_RECEIVER_DURAPLAS = FlansMod.Part(ITEMS, LoveAndWarMod.MODID, "lower_receiver_duraplas");
    public static final RegistryObject<Item> STOCK_DURAPLAS = FlansMod.Part(ITEMS, LoveAndWarMod.MODID, "stock_duraplas");
    public static final RegistryObject<Item> BARREL_DURAPLAS = FlansMod.Part(ITEMS, LoveAndWarMod.MODID, "barrel_duraplas");
    public static final RegistryObject<Item> GRIP_DURAPLAS = FlansMod.Part(ITEMS, LoveAndWarMod.MODID, "grip_duraplas");
    public static final RegistryObject<Item> HANDGUARD_DURAPLAS = FlansMod.Part(ITEMS, LoveAndWarMod.MODID, "handguard_duraplas");
    public static final RegistryObject<Item> REVOLVER_ACTION_DURAPLAS = FlansMod.Part(ITEMS, LoveAndWarMod.MODID, "revolver_action_duraplas");
    public static final RegistryObject<Item> BOLT_ACTION_DURAPLAS = FlansMod.Part(ITEMS, LoveAndWarMod.MODID, "bolt_action_duraplas");
    public static final RegistryObject<Item> INCOMPLETE_DURAPLAS_RECEIVER = FlansMod.Part(ITEMS, LoveAndWarMod.MODID, "incomplete_duraplas_receiver");


    public static final RegistryObject<Item> UPPER_RECEIVER_STEEL = FlansMod.Part(ITEMS, LoveAndWarMod.MODID, "upper_receiver_steel");
    public static final RegistryObject<Item> LOWER_RECEIVER_STEEL = FlansMod.Part(ITEMS, LoveAndWarMod.MODID, "lower_receiver_steel");
    public static final RegistryObject<Item> STOCK_STEEL = FlansMod.Part(ITEMS, LoveAndWarMod.MODID, "stock_steel");
    public static final RegistryObject<Item> BARREL_STEEL = FlansMod.Part(ITEMS, LoveAndWarMod.MODID, "barrel_steel");
    public static final RegistryObject<Item> GRIP_STEEL = FlansMod.Part(ITEMS, LoveAndWarMod.MODID, "grip_steel");
    public static final RegistryObject<Item> HANDGUARD_STEEL = FlansMod.Part(ITEMS, LoveAndWarMod.MODID, "handguard_steel");
    public static final RegistryObject<Item> REVOLVER_ACTION_STEEL = FlansMod.Part(ITEMS, LoveAndWarMod.MODID, "revolver_action_steel");
    public static final RegistryObject<Item> BOLT_ACTION_STEEL = FlansMod.Part(ITEMS, LoveAndWarMod.MODID, "bolt_action_steel");
    public static final RegistryObject<Item> INCOMPLETE_STEEL_RECEIVER = FlansMod.Part(ITEMS, LoveAndWarMod.MODID, "incomplete_steel_receiver");


    public static final RegistryObject<Item> STOCK_WOOD = FlansMod.Part(ITEMS, LoveAndWarMod.MODID, "stock_wood");
    public static final RegistryObject<Item> GRIP_WOOD = FlansMod.Part(ITEMS, LoveAndWarMod.MODID, "grip_wood");
    public static final RegistryObject<Item> HANDGUARD_WOOD = FlansMod.Part(ITEMS, LoveAndWarMod.MODID, "handguard_wood");


    public static final RegistryObject<Item> REDSTONE_CIRCUIT = FlansMod.Part(ITEMS, LoveAndWarMod.MODID, "redstone_circuit");
    public static final RegistryObject<Item> PRECISION_CIRCUIT = FlansMod.Part(ITEMS, LoveAndWarMod.MODID, "precision_circuit");
    public static final RegistryObject<Item> QUANTUM_CIRCUIT = FlansMod.Part(ITEMS, LoveAndWarMod.MODID, "quantum_circuit");


    public static final RegistryObject<Item> LEBEL = FlansMod.Gun(ITEMS, LoveAndWarMod.MODID, "lebel");
    public static final RegistryObject<Item> LEBEL_SCOPE = FlansMod.Gun(ITEMS, LoveAndWarMod.MODID, "lebel_scope");
    public static final RegistryObject<Item> BERTHIER = FlansMod.Gun(ITEMS, LoveAndWarMod.MODID, "berthier");
    public static final RegistryObject<Item> MODEL1887 = FlansMod.Gun(ITEMS, LoveAndWarMod.MODID, "1887");
    public static final RegistryObject<Item> ALOF = FlansMod.Gun(ITEMS, LoveAndWarMod.MODID, "alof");
    public static final RegistryObject<Item> WALKER = FlansMod.Gun(ITEMS, LoveAndWarMod.MODID, "walker");
    public static final RegistryObject<Item> NAVY_CARBINE = FlansMod.Gun(ITEMS, LoveAndWarMod.MODID, "navycarbine");
    public static final RegistryObject<Item> PERRIN = FlansMod.Gun(ITEMS, LoveAndWarMod.MODID, "perrin");

    public static final RegistryObject<Item> MARTINI = FlansMod.Gun(ITEMS, LoveAndWarMod.MODID, "martini_henry");
    public static final RegistryObject<Item> GREENER = FlansMod.Gun(ITEMS, LoveAndWarMod.MODID, "greener");

    public static final RegistryObject<Item> MARTINI_CARBINE = FlansMod.Gun(ITEMS, LoveAndWarMod.MODID, "martinicarbine");
    public static final RegistryObject<Item> RSC17 = FlansMod.Gun(ITEMS, LoveAndWarMod.MODID, "rsc17");
    public static final RegistryObject<Item> WZ38 = FlansMod.Gun(ITEMS, LoveAndWarMod.MODID, "wz38");
    public static final RegistryObject<Item> MARS = FlansMod.Gun(ITEMS, LoveAndWarMod.MODID, "mars");
    public static final RegistryObject<Item> GYROJET = FlansMod.Gun(ITEMS, LoveAndWarMod.MODID, "gyrojet");
    public static final RegistryObject<Item> GYROJET_CARBINE = FlansMod.Gun(ITEMS, LoveAndWarMod.MODID, "gyrojet_carbine");
    public static final RegistryObject<Item> KRAG = FlansMod.Gun(ITEMS, LoveAndWarMod.MODID, "krag");
    public static final RegistryObject<Item> KRAG_CLEAN = FlansMod.Gun(ITEMS, LoveAndWarMod.MODID, "krag_clean");

    public static final RegistryObject<Item> CHAUCHAT = FlansMod.Gun(ITEMS, LoveAndWarMod.MODID, "chauchat");
    public static final RegistryObject<Item> MONITOR = FlansMod.Gun(ITEMS, LoveAndWarMod.MODID, "monitor");
    public static final RegistryObject<Item> THOMPSON = FlansMod.Gun(ITEMS, LoveAndWarMod.MODID, "thompson");
    public static final RegistryObject<Item> MAT49 = FlansMod.Gun(ITEMS, LoveAndWarMod.MODID, "mat49");
    public static final RegistryObject<Item> MAT4954 = FlansMod.Gun(ITEMS, LoveAndWarMod.MODID, "mat4954");
    public static final RegistryObject<Item> SJOGREN = FlansMod.Gun(ITEMS, LoveAndWarMod.MODID, "sjogren");


    public static final RegistryObject<Item> WF54 = FlansMod.Gun(ITEMS, LoveAndWarMod.MODID, "wf54");
    public static final RegistryObject<Item> EM2 = FlansMod.Gun(ITEMS, LoveAndWarMod.MODID, "em2");
    public static final RegistryObject<Item> MAS62 = FlansMod.Gun(ITEMS, LoveAndWarMod.MODID, "mas62");
    public static final RegistryObject<Item> STG57 = FlansMod.Gun(ITEMS, LoveAndWarMod.MODID, "stg57");
    public static final RegistryObject<Item> G11K2 = FlansMod.Gun(ITEMS, LoveAndWarMod.MODID, "g11k2");
    public static final RegistryObject<Item> SA81 = FlansMod.Gun(ITEMS, LoveAndWarMod.MODID, "sa81");
    public static final RegistryObject<Item> AAI_ACR = FlansMod.Gun(ITEMS, LoveAndWarMod.MODID, "aai_acr");
    public static final RegistryObject<Item> CAWS = FlansMod.Gun(ITEMS, LoveAndWarMod.MODID, "caws");


    public static final RegistryObject<Item> VETTERLI = FlansMod.Gun(ITEMS, LoveAndWarMod.MODID, "vetterli");
    public static final RegistryObject<Item> VETTERLIVITALI = FlansMod.Gun(ITEMS, LoveAndWarMod.MODID, "vetterlivitali");
    public static final RegistryObject<Item> CHASSEPOT = FlansMod.Gun(ITEMS, LoveAndWarMod.MODID, "chassepot");
    public static final RegistryObject<Item> DREYSE = FlansMod.Gun(ITEMS, LoveAndWarMod.MODID, "dreyse");
    public static final RegistryObject<Item> DREYSEPISTOL = FlansMod.Gun(ITEMS, LoveAndWarMod.MODID, "dreysepistol");
    public static final RegistryObject<Item> P53_ENFIELD = FlansMod.Gun(ITEMS, LoveAndWarMod.MODID, "p53_enfield");
    public static final RegistryObject<Item> P61_ENFIELD = FlansMod.Gun(ITEMS, LoveAndWarMod.MODID, "p61_enfield");
    public static final RegistryObject<Item> SNIDER_ENFIELD = FlansMod.Gun(ITEMS, LoveAndWarMod.MODID, "snider_enfield");
    public static final RegistryObject<Item> SNIDER_ENFIELD_CARBINE = FlansMod.Gun(ITEMS, LoveAndWarMod.MODID, "snider_enfield_carbine");


    public static final RegistryObject<Item> MARTINI_REPEATER = FlansMod.Gun(ITEMS, LoveAndWarMod.MODID, "martini_repeater");
    public static final RegistryObject<Item> PVG43 = FlansMod.Gun(ITEMS, LoveAndWarMod.MODID, "pvg43");
    public static final RegistryObject<Item> LEE_METFORD = FlansMod.Gun(ITEMS, LoveAndWarMod.MODID, "leemetford");
    public static final RegistryObject<Item> MARTINILAUNCHER = FlansMod.Gun(ITEMS, LoveAndWarMod.MODID, "martinilauncher");
    public static final RegistryObject<Item> TANKGEWEHR = FlansMod.Gun(ITEMS, LoveAndWarMod.MODID, "tankgewehr");
    public static final RegistryObject<Item> WZ35 = FlansMod.Gun(ITEMS, LoveAndWarMod.MODID, "wz35");
    public static final RegistryObject<Item> GEPARD_M3 = FlansMod.Gun(ITEMS, LoveAndWarMod.MODID, "gepard_m3");


    public static final RegistryObject<Item> ATTACHMENT_WOOD_STOCK = FlansMod.Attachment(ITEMS, LoveAndWarMod.MODID, "attachment_wood_stock");
    public static final RegistryObject<Item> ATTACHMENT_IMPACT_STOCK = FlansMod.Attachment(ITEMS, LoveAndWarMod.MODID, "attachment_impact_stock");
    public static final RegistryObject<Item> ATTACHMENT_RED_DOT = FlansMod.Attachment(ITEMS, LoveAndWarMod.MODID, "attachment_red_dot");
    public static final RegistryObject<Item> ATTACHMENT_TELESCOPIC_SIGHT = FlansMod.Attachment(ITEMS, LoveAndWarMod.MODID, "attachment_telescopic_sight");
    public static final RegistryObject<Item> ATTACHMENT_LASER_SCOPE = FlansMod.Attachment(ITEMS, LoveAndWarMod.MODID, "attachment_laser_scope");
    public static final RegistryObject<Item> ATTACHMENT_EXTENDED_BARREL = FlansMod.Attachment(ITEMS, LoveAndWarMod.MODID, "attachment_extended_barrel");
    public static final RegistryObject<Item> ATTACHMENT_SILENCER = FlansMod.Attachment(ITEMS, LoveAndWarMod.MODID, "attachment_silencer");
    public static final RegistryObject<Item> ATTACHMENT_TANK_BRAKE = FlansMod.Attachment(ITEMS, LoveAndWarMod.MODID, "attachment_tank_brake");
    public static final RegistryObject<Item> ATTACHMENT_WOOD_GRIP = FlansMod.Attachment(ITEMS, LoveAndWarMod.MODID, "attachment_wood_grip");
    public static final RegistryObject<Item> ATTACHMENT_POLYMER_GRIP = FlansMod.Attachment(ITEMS, LoveAndWarMod.MODID, "attachment_polymer_grip");
    public static final RegistryObject<Item> ATTACHMENT_SUIT_SIGHT = FlansMod.Attachment(ITEMS, LoveAndWarMod.MODID, "attachment_suit_sight");


    public static final RegistryObject<Block> WORKBENCH_BLOCK_LAW = FlansMod.Workbench_Block(BLOCKS, LoveAndWarMod.MODID, "law_workbench");
    public static final RegistryObject<Item> WORKBENCH_ITEM_LAW = FlansMod.Workbench_Item(ITEMS, LoveAndWarMod.MODID, "law_workbench", WORKBENCH_BLOCK_LAW);
    public static final RegistryObject<BlockEntityType<WorkbenchBlockEntity>> WORKBENCH_TILE_ENTITY_LAW =
            FlansMod.Workbench_TileEntityType(TILE_ENTITIES, LoveAndWarMod.MODID, "law_workbench");
    public static final RegistryObject<Block> WORKBENCH_BLOCK_ADV = FlansMod.Workbench_Block(BLOCKS, LoveAndWarMod.MODID, "advanced_workbench");
    public static final RegistryObject<Item> WORKBENCH_ITEM_ADV = FlansMod.Workbench_Item(ITEMS, LoveAndWarMod.MODID, "advanced_workbench", WORKBENCH_BLOCK_ADV);
    public static final RegistryObject<BlockEntityType<WorkbenchBlockEntity>> WORKBENCH_TILE_ENTITY_ADV =
            FlansMod.Workbench_TileEntityType(TILE_ENTITIES, LoveAndWarMod.MODID, "advanced_workbench");

    public static final RegistryObject<Block> WORKBENCH_BLOCK_EXP = FlansMod.Workbench_Block(BLOCKS, LoveAndWarMod.MODID, "expert_workbench");
    public static final RegistryObject<Item> WORKBENCH_ITEM_EXP = FlansMod.Workbench_Item(ITEMS, LoveAndWarMod.MODID, "expert_workbench", WORKBENCH_BLOCK_EXP);
    public static final RegistryObject<BlockEntityType<WorkbenchBlockEntity>> WORKBENCH_TILE_ENTITY_EXP =
            FlansMod.Workbench_TileEntityType(TILE_ENTITIES, LoveAndWarMod.MODID, "expert_workbench");



    public static final RegistryObject<Block> WORKBENCH_BLOCK_EM2 = FlansMod.Workbench_Block(BLOCKS, LoveAndWarMod.MODID, "em2_workbench");
    public static final RegistryObject<Item> WORKBENCH_ITEM_EM2 = FlansMod.Workbench_Item(ITEMS, LoveAndWarMod.MODID, "em2_workbench", WORKBENCH_BLOCK_EM2);
    public static final RegistryObject<BlockEntityType<WorkbenchBlockEntity>> WORKBENCH_TILE_ENTITY_EM2 =
            FlansMod.Workbench_TileEntityType(TILE_ENTITIES, LoveAndWarMod.MODID, "em2_workbench");

    public static final RegistryObject<Block> WORKBENCH_BLOCK_MAS62 = FlansMod.Workbench_Block(BLOCKS, LoveAndWarMod.MODID, "mas62_workbench");
    public static final RegistryObject<Item> WORKBENCH_ITEM_MAS62 = FlansMod.Workbench_Item(ITEMS, LoveAndWarMod.MODID, "mas62_workbench", WORKBENCH_BLOCK_MAS62);
    public static final RegistryObject<BlockEntityType<WorkbenchBlockEntity>> WORKBENCH_TILE_ENTITY_MAS62 =
            FlansMod.Workbench_TileEntityType(TILE_ENTITIES, LoveAndWarMod.MODID, "mas62_workbench");


    public static final RegistryObject<Block> WORKBENCH_BLOCK_STG57 = FlansMod.Workbench_Block(BLOCKS, LoveAndWarMod.MODID, "stg57_workbench");
    public static final RegistryObject<Item> WORKBENCH_ITEM_STG57 = FlansMod.Workbench_Item(ITEMS, LoveAndWarMod.MODID, "stg57_workbench", WORKBENCH_BLOCK_STG57);
    public static final RegistryObject<BlockEntityType<WorkbenchBlockEntity>> WORKBENCH_TILE_ENTITY_STG57 =
            FlansMod.Workbench_TileEntityType(TILE_ENTITIES, LoveAndWarMod.MODID, "stg57_workbench");

    public static void register(IEventBus eventBus){

        ITEMS.register(eventBus);
        BLOCKS.register(eventBus);
        TILE_ENTITIES.register(eventBus);
    }
}
