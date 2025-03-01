package net.pttheta.loveandwar.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.pttheta.loveandwar.LoveAndWarMod;
import net.pttheta.loveandwar.blocks.LAWBlocks;
import net.pttheta.loveandwar.blocks.ModBlocks;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB,LoveAndWarMod.MODID);

    public static final RegistryObject<CreativeModeTab> LOVE_AND_WAR_TAB = CREATIVE_MODE_TABS.register("love_and_war_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.RIFLE_BULLET.get()))
                    .title(Component.translatable("creativetab.loveandwartab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.SULPHUR.get());
                        pOutput.accept(ModItems.RAW_SULPHUR.get());

                        pOutput.accept(ModBlocks.SULPHUR_BLOCK.get());
                        pOutput.accept(ModBlocks.RAW_SULPHUR_BLOCK.get());

                        pOutput.accept(ModBlocks.SULPHUR_ORE.get());
                        pOutput.accept(ModBlocks.DEEPSLATE_SULPHUR_ORE.get());
                        pOutput.accept(ModBlocks.NETHER_SULPHUR_ORE.get());

                        pOutput.accept(ModItems.CIGARETTE.get());

                        pOutput.accept(ModItems.NETHERITE_SHEET.get());
                        pOutput.accept(ModItems.CUT_NETHERITE_SHEET.get());

                        pOutput.accept(ModItems.STEEL_INGOT.get());
                        pOutput.accept(ModItems.STEEL_NUGGET.get());
                        pOutput.accept(ModItems.STEEL_SHEET.get());

                        pOutput.accept(ModItems.BRASS_MIX.get());

                        pOutput.accept(ModItems.TUNGSTEN.get());
                        pOutput.accept(ModItems.TUNGSTEN_NUGGET.get());
                        pOutput.accept(ModItems.TUNGSTEN_SHEET.get());
                        pOutput.accept(ModItems.RAW_TUNGSTEN.get());

                        pOutput.accept(ModItems.TUNGSTEN_SWORD.get());
                        pOutput.accept(ModItems.TUNGSTEN_PICKAXE.get());
                        pOutput.accept(ModItems.TUNGSTEN_AXE.get());
                        pOutput.accept(ModItems.TUNGSTEN_SHOVEL.get());
                        pOutput.accept(ModItems.TUNGSTEN_HOE.get());

                        pOutput.accept(ModItems.TUNGSTEN_HELMET.get());
                        pOutput.accept(ModItems.TUNGSTEN_CHESTPLATE.get());
                        pOutput.accept(ModItems.TUNGSTEN_LEGGINGS.get());
                        pOutput.accept(ModItems.TUNGSTEN_BOOTS.get());
                        pOutput.accept(ModBlocks.TUNGSTEN_BLOCK.get());
                        pOutput.accept(ModBlocks.RAW_TUNGSTEN_BLOCK.get());
                        pOutput.accept(ModBlocks.DEEPSLATE_TUNGSTEN_ORE.get());

                        pOutput.accept(ModBlocks.TUNGSTEN_STAIRS.get());
                        pOutput.accept(ModBlocks.TUNGSTEN_SLAB.get());
                        pOutput.accept(ModBlocks.TUNGSTEN_BUTTON.get());
                        pOutput.accept(ModBlocks.TUNGSTEN_PRESSURE_PLATE.get());

                        pOutput.accept(ModBlocks.TUNGSTEN_FENCE.get());
                        pOutput.accept(ModBlocks.TUNGSTEN_GATE.get());
                        pOutput.accept(ModBlocks.TUNGSTEN_WALL.get());

                        pOutput.accept(ModBlocks.TUNGSTEN_DOOR.get());
                        pOutput.accept(ModBlocks.TUNGSTEN_TRAPDOOR.get());


                        pOutput.accept(ModItems.ROBOT_DOG_SPAWN_EGG.get());
                        pOutput.accept(ModItems.TURRET_UPGRADE.get());
                        pOutput.accept(ModItems.ARMOR_UPGRADE.get());
                        pOutput.accept(ModItems.SCANNER_UPGRADE.get());
                        pOutput.accept(ModItems.ROBOT_DOG_VALKYRIE_SKIN.get());
                        pOutput.accept(ModItems.ROBOT_DOG_BUBBLEGUM_SKIN.get());
                        pOutput.accept(ModItems.ROBOT_DOG_RACING_SKIN.get());
                        //petrochem
                        pOutput.accept(ModItems.BITUMEN_BUCKET.get());
                        pOutput.accept(ModBlocks.BITUMEN_BLOCK.get());
                        pOutput.accept(ModBlocks.SMOOTH_ASPHALT.get());
                        pOutput.accept(ModBlocks.SMOOTH_ASPHALT_SLAB.get());

                        pOutput.accept(ModBlocks.BLACK_PLASTIC.get());
                        pOutput.accept(ModBlocks.BLUE_PLASTIC.get());
                        pOutput.accept(ModBlocks.BROWN_PLASTIC.get());
                        pOutput.accept(ModBlocks.CYAN_PLASTIC.get());
                        pOutput.accept(ModBlocks.GRAY_PLASTIC.get());
                        pOutput.accept(ModBlocks.GREEN_PLASTIC.get());
                        pOutput.accept(ModBlocks.LIGHT_BLUE_PLASTIC.get());
                        pOutput.accept(ModBlocks.LIGHT_GRAY_PLASTIC.get());
                        pOutput.accept(ModBlocks.LIME_PLASTIC.get());
                        pOutput.accept(ModBlocks.MAGENTA_PLASTIC.get());
                        pOutput.accept(ModBlocks.ORANGE_PLASTIC.get());
                        pOutput.accept(ModBlocks.PINK_PLASTIC.get());
                        pOutput.accept(ModBlocks.PURPLE_PLASTIC.get());
                        pOutput.accept(ModBlocks.RED_PLASTIC.get());
                        pOutput.accept(ModBlocks.YELLOW_PLASTIC.get());
                        pOutput.accept(ModBlocks.WHITE_PLASTIC.get());
                        pOutput.accept(ModBlocks.YELLOW_PLASTIC.get());

                        pOutput.accept(ModBlocks.OIL_SAND.get());
                        pOutput.accept(ModItems.TAR_CLUMP.get());
                        pOutput.accept(ModItems.CRUDE_OIL_BUCKET.get());
                        pOutput.accept(ModItems.DIESEL_BUCKET.get());
                        pOutput.accept(ModItems.KEROSENE_BUCKET.get());
                        pOutput.accept(ModItems.ETHYLENE_BUCKET.get());
                        pOutput.accept(ModItems.POLYMER_BUCKET.get());
                        pOutput.accept(ModItems.DURAPLAS_BUCKET.get());

                        pOutput.accept(ModItems.SLIME_RESIN.get());
                        pOutput.accept(ModItems.SLIME_RESIN_SHEET.get());
                        pOutput.accept(ModItems.CATALYST.get());
                        pOutput.accept(ModItems.GLASS_LENS.get());
                        pOutput.accept(ModItems.OPTICAL_SCOPE.get());
                        pOutput.accept(ModItems.LAUNCHER_BARREL.get());

                        pOutput.accept(ModItems.POLYMER_INGOT.get());
                        pOutput.accept(ModItems.POLYMER_SHEET.get());
                        pOutput.accept(ModItems.DURAPLAS_INGOT.get());
                        pOutput.accept(ModItems.DURAPLAS_SHEET.get());

                        pOutput.accept(ModBlocks.SLIME_RESIN_BLOCK.get());
                        pOutput.accept(ModBlocks.POLYMER_BLOCK_SOLID.get());
                        pOutput.accept(ModBlocks.DURAPLAS_BLOCK_SOLID.get());

                        pOutput.accept(ModItems.FUEL_PELLET.get());

                        pOutput.accept(ModItems.PROPELLANT.get());
                        pOutput.accept(ModItems.PROPELLANT_SHEET.get());
                        pOutput.accept(ModItems.SEEKER_UNIT.get());

                        //Flans stuff
                        pOutput.accept(LAWBlocks.DRAWING_PRESS);
                        pOutput.accept(LAWBlocks.STAMPING_PRESS);
                        pOutput.accept(ModItems.THERMOSTAT.get());

                        pOutput.accept(ModItems.COPPER_CUP.get());
                        pOutput.accept(ModItems.GYROJET_CASE.get());
                        pOutput.accept(ModItems.BRASS_CUP.get());
                        pOutput.accept(ModItems.WIDE_BRASS_CUP.get());
                        pOutput.accept(ModItems.PISTOL_CASING.get());
                        pOutput.accept(ModItems.RIFLE_CASING.get());
                        pOutput.accept(ModItems.GRENADE_CASING.get());
                        pOutput.accept(ModItems.ARTILLERY_CASING.get());
                        pOutput.accept(ModItems.SHOTGUN_CASING.get());
                        pOutput.accept(ModItems.FILLED_PISTOL_CASING.get());
                        pOutput.accept(ModItems.FILLED_RIFLE_CASING.get());
                        pOutput.accept(ModItems.FILLED_GRENADE_CASING.get());
                        pOutput.accept(ModItems.FILLED_ARTILLERY_CASING.get());
                        pOutput.accept(ModItems.FILLED_SHOTGUN_CASING.get());
                        pOutput.accept(ModItems.PROPELLANT_MOULDED.get());
                        pOutput.accept(ModItems.STEEL_CUP.get());
                        pOutput.accept(ModItems.HEAVY_RIFLE_CASING.get());
                        pOutput.accept(ModItems.FILLED_HEAVY_RIFLE_CASING.get());

                        pOutput.accept(ModItems.BALL_BEARINGS.get());
                        pOutput.accept(ModItems.FLECHETTES.get());
                        pOutput.accept(ModItems.BUCKSHOT_SHELL.get());
                        pOutput.accept(ModItems.SLUG_SHELL.get());
                        pOutput.accept(ModItems.EXPLOSIVE_SHELL.get());
                        pOutput.accept(ModItems.FLECHETTE_SHELL.get());

                        pOutput.accept(ModItems.ARTILLERY_SHELL.get());
                        pOutput.accept(ModItems.ARTILLERY_SHELL_AP.get());
                        pOutput.accept(ModItems.ARTILLERY_SHELL_HE.get());

                        pOutput.accept(ModItems.PISTOL_BULLET.get());
                        pOutput.accept(ModItems.PISTOL_BULLET_SOFT.get());
                        pOutput.accept(ModItems.PISTOL_BULLET_AP.get());

                        pOutput.accept(ModItems.RIFLE_BULLET.get());
                        pOutput.accept(ModItems.RIFLE_BULLET_SOFT.get());
                        pOutput.accept(ModItems.RIFLE_BULLET_AP.get());

                        pOutput.accept(ModItems.CASELESS_BULLET.get());
                        pOutput.accept(ModItems.CASELESS_BULLET_SOFT.get());
                        pOutput.accept(ModItems.CASELESS_BULLET_AP.get());

                        pOutput.accept(ModItems.HEAVY_RIFLE_BULLET.get());

                        pOutput.accept(ModItems.GYROJET_BASIC.get());
                        pOutput.accept(ModItems.GYROJET_SEEKER.get());
                        pOutput.accept(ModItems.GYROJET_SEEKER_TOP.get());
                        pOutput.accept(ModItems.GYROJET_PEN.get());
                        pOutput.accept(ModItems.GYROJET_HE.get());

                        pOutput.accept(ModItems.GRENADE.get());
                        pOutput.accept(ModItems.GRENADE_ROUND.get());
                        pOutput.accept(ModItems.GRENADE_ROUND_BUCKSHOT.get());
                        pOutput.accept(ModItems.BLACK_POWDER_CARTRIDGE.get());

                        pOutput.accept(ModItems.TEMPLATE_UPPER_RECEIVER.get());
                        pOutput.accept(ModItems.TEMPLATE_LOWER_RECEIVER.get());
                        pOutput.accept(ModItems.TEMPLATE_STOCK.get());
                        pOutput.accept(ModItems.TEMPLATE_BARREL.get());
                        pOutput.accept(ModItems.TEMPLATE_GRIP.get());
                        pOutput.accept(ModItems.TEMPLATE_HANDGUARD.get());
                        pOutput.accept(ModItems.TEMPLATE_REVOLVER_ACTION.get());
                        pOutput.accept(ModItems.TEMPLATE_BOLT_ACTION.get());
                        pOutput.accept(ModItems.TEMPLATE_PROPELLANT_BLOCK.get());

                        pOutput.accept(ModItems.UPPER_RECEIVER_IRON.get());
                        pOutput.accept(ModItems.LOWER_RECEIVER_IRON.get());
                        pOutput.accept(ModItems.STOCK_IRON.get());
                        pOutput.accept(ModItems.BARREL_IRON.get());
                        pOutput.accept(ModItems.GRIP_IRON.get());
                        pOutput.accept(ModItems.HANDGUARD_IRON.get());
                        pOutput.accept(ModItems.BOLT_ACTION_IRON.get());
                        pOutput.accept(ModItems.REVOLVER_ACTION_IRON.get());
                        pOutput.accept(ModItems.INCOMPLETE_IRON_RECEIVER.get());

                        pOutput.accept(ModItems.UPPER_RECEIVER_BRASS.get());
                        pOutput.accept(ModItems.LOWER_RECEIVER_BRASS.get());
                        pOutput.accept(ModItems.STOCK_BRASS.get());
                        pOutput.accept(ModItems.BARREL_BRASS.get());
                        pOutput.accept(ModItems.GRIP_BRASS.get());
                        pOutput.accept(ModItems.HANDGUARD_BRASS.get());
                        pOutput.accept(ModItems.BOLT_ACTION_BRASS.get());
                        pOutput.accept(ModItems.REVOLVER_ACTION_BRASS.get());
                        pOutput.accept(ModItems.INCOMPLETE_BRASS_RECEIVER.get());

                        pOutput.accept(ModItems.UPPER_RECEIVER_TUNGSTEN.get());
                        pOutput.accept(ModItems.LOWER_RECEIVER_TUNGSTEN.get());
                        pOutput.accept(ModItems.STOCK_TUNGSTEN.get());
                        pOutput.accept(ModItems.BARREL_TUNGSTEN.get());
                        pOutput.accept(ModItems.GRIP_TUNGSTEN.get());
                        pOutput.accept(ModItems.HANDGUARD_TUNGSTEN.get());
                        pOutput.accept(ModItems.BOLT_ACTION_TUNGSTEN.get());
                        pOutput.accept(ModItems.REVOLVER_ACTION_TUNGSTEN.get());
                        pOutput.accept(ModItems.INCOMPLETE_TUNGSTEN_RECEIVER.get());

                        pOutput.accept(ModItems.UPPER_RECEIVER_STEEL.get());
                        pOutput.accept(ModItems.LOWER_RECEIVER_STEEL.get());
                        pOutput.accept(ModItems.STOCK_STEEL.get());
                        pOutput.accept(ModItems.BARREL_STEEL.get());
                        pOutput.accept(ModItems.GRIP_STEEL.get());
                        pOutput.accept(ModItems.HANDGUARD_STEEL.get());
                        pOutput.accept(ModItems.BOLT_ACTION_STEEL.get());
                        pOutput.accept(ModItems.REVOLVER_ACTION_STEEL.get());
                        pOutput.accept(ModItems.INCOMPLETE_STEEL_RECEIVER.get());

                        pOutput.accept(ModItems.UPPER_RECEIVER_STURDY.get());
                        pOutput.accept(ModItems.LOWER_RECEIVER_STURDY.get());
                        pOutput.accept(ModItems.STOCK_STURDY.get());
                        pOutput.accept(ModItems.BARREL_STURDY.get());
                        pOutput.accept(ModItems.GRIP_STURDY.get());
                        pOutput.accept(ModItems.HANDGUARD_STURDY.get());
                        pOutput.accept(ModItems.BOLT_ACTION_STURDY.get());
                        pOutput.accept(ModItems.REVOLVER_ACTION_STURDY.get());
                        pOutput.accept(ModItems.INCOMPLETE_STURDY_RECEIVER.get());

                        pOutput.accept(ModItems.UPPER_RECEIVER_NETHERITE.get());
                        pOutput.accept(ModItems.LOWER_RECEIVER_NETHERITE.get());
                        pOutput.accept(ModItems.STOCK_NETHERITE.get());
                        pOutput.accept(ModItems.BARREL_NETHERITE.get());
                        pOutput.accept(ModItems.GRIP_NETHERITE.get());
                        pOutput.accept(ModItems.HANDGUARD_NETHERITE.get());
                        pOutput.accept(ModItems.BOLT_ACTION_NETHERITE.get());
                        pOutput.accept(ModItems.REVOLVER_ACTION_NETHERITE.get());
                        pOutput.accept(ModItems.INCOMPLETE_NETHERITE_RECEIVER.get());

                        pOutput.accept(ModItems.UPPER_RECEIVER_RESIN.get());
                        pOutput.accept(ModItems.LOWER_RECEIVER_RESIN.get());
                        pOutput.accept(ModItems.STOCK_RESIN.get());
                        pOutput.accept(ModItems.BARREL_RESIN.get());
                        pOutput.accept(ModItems.GRIP_RESIN.get());
                        pOutput.accept(ModItems.HANDGUARD_RESIN.get());
                        pOutput.accept(ModItems.BOLT_ACTION_RESIN.get());
                        pOutput.accept(ModItems.REVOLVER_ACTION_RESIN.get());
                        pOutput.accept(ModItems.INCOMPLETE_RESIN_RECEIVER.get());

                        pOutput.accept(ModItems.UPPER_RECEIVER_POLYMER.get());
                        pOutput.accept(ModItems.LOWER_RECEIVER_POLYMER.get());
                        pOutput.accept(ModItems.STOCK_POLYMER.get());
                        pOutput.accept(ModItems.BARREL_POLYMER.get());
                        pOutput.accept(ModItems.GRIP_POLYMER.get());
                        pOutput.accept(ModItems.HANDGUARD_POLYMER.get());
                        pOutput.accept(ModItems.BOLT_ACTION_POLYMER.get());
                        pOutput.accept(ModItems.REVOLVER_ACTION_POLYMER.get());
                        pOutput.accept(ModItems.INCOMPLETE_POLYMER_RECEIVER.get());

                        pOutput.accept(ModItems.UPPER_RECEIVER_DURAPLAS.get());
                        pOutput.accept(ModItems.LOWER_RECEIVER_DURAPLAS.get());
                        pOutput.accept(ModItems.STOCK_DURAPLAS.get());
                        pOutput.accept(ModItems.BARREL_DURAPLAS.get());
                        pOutput.accept(ModItems.GRIP_DURAPLAS.get());
                        pOutput.accept(ModItems.HANDGUARD_DURAPLAS.get());
                        pOutput.accept(ModItems.BOLT_ACTION_DURAPLAS.get());
                        pOutput.accept(ModItems.REVOLVER_ACTION_DURAPLAS.get());
                        pOutput.accept(ModItems.INCOMPLETE_DURAPLAS_RECEIVER.get());

                        pOutput.accept(ModItems.STOCK_WOOD.get());
                        pOutput.accept(ModItems.GRIP_WOOD.get());
                        pOutput.accept(ModItems.HANDGUARD_WOOD.get());

                        pOutput.accept(ModItems.REDSTONE_CIRCUIT.get());
                        pOutput.accept(ModItems.PRECISION_CIRCUIT.get());
                        pOutput.accept(ModItems.QUANTUM_CIRCUIT.get());

                        pOutput.accept(ModItems.LEBEL.get());
                        pOutput.accept(ModItems.LEBEL_SCOPE.get());
                        pOutput.accept(ModItems.BERTHIER.get());
                        pOutput.accept(ModItems.MODEL1887.get());
                        pOutput.accept(ModItems.ALOF.get());
                        pOutput.accept(ModItems.WALKER.get());
                        pOutput.accept(ModItems.NAVY_CARBINE.get());
                        pOutput.accept(ModItems.PERRIN.get());
                        pOutput.accept(ModItems.KRAG.get());
                        pOutput.accept(ModItems.KRAG_CLEAN.get());
                        pOutput.accept(ModItems.RSC17.get());
                        pOutput.accept(ModItems.WZ38.get());
                        pOutput.accept(ModItems.MARS.get());
                        pOutput.accept(ModItems.GYROJET.get());
                        pOutput.accept(ModItems.GYROJET_CARBINE.get());
                        pOutput.accept(ModItems.CHAUCHAT.get());
                        pOutput.accept(ModItems.MONITOR.get());
                        pOutput.accept(ModItems.THOMPSON.get());
                        pOutput.accept(ModItems.MAT49.get());
                        pOutput.accept(ModItems.MAT4954.get());
                        pOutput.accept(ModItems.SJOGREN.get());

                        pOutput.accept(ModItems.WF54.get());
                        pOutput.accept(ModItems.EM2.get());
                        pOutput.accept(ModItems.MAS62.get());
                        pOutput.accept(ModItems.STG57.get());
                        pOutput.accept(ModItems.G11K2.get());
                        pOutput.accept(ModItems.AAI_ACR.get());
                        pOutput.accept(ModItems.SA81.get());
                        pOutput.accept(ModItems.VETTERLI.get());
                        pOutput.accept(ModItems.VETTERLIVITALI.get());
                        pOutput.accept(ModItems.P53_ENFIELD.get());
                        pOutput.accept(ModItems.P61_ENFIELD.get());
                        pOutput.accept(ModItems.SNIDER_ENFIELD.get());
                        pOutput.accept(ModItems.SNIDER_ENFIELD_CARBINE.get());
                        pOutput.accept(ModItems.CHASSEPOT.get());
                        pOutput.accept(ModItems.DREYSE.get());
                        pOutput.accept(ModItems.DREYSEPISTOL.get());
                        pOutput.accept(ModItems.MARTINI.get());
                        pOutput.accept(ModItems.MARTINI_CARBINE.get());
                        pOutput.accept(ModItems.GREENER.get());
                        pOutput.accept(ModItems.MARTINI_REPEATER.get());
                        pOutput.accept(ModItems.MARTINILAUNCHER.get());
                        pOutput.accept(ModItems.LEE_METFORD.get());
                        pOutput.accept(ModItems.PVG43.get());
                        pOutput.accept(ModItems.TANKGEWEHR.get());
                        pOutput.accept(ModItems.WZ35.get());
                        pOutput.accept(ModItems.GEPARD_M3.get());

                        pOutput.accept(ModItems.ATTACHMENT_WOOD_STOCK.get());
                        pOutput.accept(ModItems.ATTACHMENT_IMPACT_STOCK.get());
                        pOutput.accept(ModItems.ATTACHMENT_RED_DOT.get());
                        pOutput.accept(ModItems.ATTACHMENT_TELESCOPIC_SIGHT.get());
                        pOutput.accept(ModItems.ATTACHMENT_SUIT_SIGHT.get());
                        pOutput.accept(ModItems.ATTACHMENT_LASER_SCOPE.get());
                        pOutput.accept(ModItems.ATTACHMENT_EXTENDED_BARREL.get());
                        pOutput.accept(ModItems.ATTACHMENT_SILENCER.get());
                        pOutput.accept(ModItems.ATTACHMENT_TANK_BRAKE.get());
                        pOutput.accept(ModItems.ATTACHMENT_WOOD_GRIP.get());
                        pOutput.accept(ModItems.ATTACHMENT_POLYMER_GRIP.get());

                        pOutput.accept(ModItems.AMMO_POUCH.get());
                        pOutput.accept(ModItems.AMMO_POUCH_PISTOL.get());
                        pOutput.accept(ModItems.AMMO_POUCH_RIFLE.get());
                        pOutput.accept(ModItems.AMMO_POUCH_SHOTGUN.get());

                        pOutput.accept(ModItems.WORKBENCH_ITEM_LAW.get());
                        pOutput.accept(ModItems.WORKBENCH_ITEM_ADV.get());
                        pOutput.accept(ModItems.WORKBENCH_ITEM_EXP.get());
                        pOutput.accept(ModItems.WORKBENCH_ITEM_EM2.get());
                        pOutput.accept(ModItems.WORKBENCH_ITEM_MAS62.get());
                        pOutput.accept(ModItems.WORKBENCH_ITEM_STG57.get());

                        pOutput.accept(ModItems.EM2_BLUEPRINT.get());
                        pOutput.accept(ModItems.MAS62_BLUEPRINT.get());
                        pOutput.accept(ModItems.STG57_BLUEPRINT.get());
                        pOutput.accept(ModItems.FORGOTTEN_GUNSMITH_PARTS.get());
                    })
                    .build());
    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
