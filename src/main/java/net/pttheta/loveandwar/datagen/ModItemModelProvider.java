package net.pttheta.loveandwar.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.pttheta.loveandwar.LoveAndWarMod;
import net.pttheta.loveandwar.blocks.ModBlocks;
import net.pttheta.loveandwar.item.ModItems;

import java.util.LinkedHashMap;

public class ModItemModelProvider extends ItemModelProvider {
    private static LinkedHashMap<ResourceKey<TrimMaterial>, Float> trimMaterials = new LinkedHashMap<>();
    static {
        trimMaterials.put(TrimMaterials.QUARTZ, 0.1F);
        trimMaterials.put(TrimMaterials.IRON, 0.2F);
        trimMaterials.put(TrimMaterials.NETHERITE, 0.3F);
        trimMaterials.put(TrimMaterials.REDSTONE, 0.4F);
        trimMaterials.put(TrimMaterials.COPPER, 0.5F);
        trimMaterials.put(TrimMaterials.GOLD, 0.6F);
        trimMaterials.put(TrimMaterials.EMERALD, 0.7F);
        trimMaterials.put(TrimMaterials.DIAMOND, 0.8F);
        trimMaterials.put(TrimMaterials.LAPIS, 0.9F);
        trimMaterials.put(TrimMaterials.AMETHYST, 1.0F);
    }
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, LoveAndWarMod.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {

        simpleItem(ModItems.CIGARETTE);
        simpleItem(ModItems.RAW_SULPHUR);
        simpleItem(ModItems.SULPHUR);
        simpleItem(ModItems.RAW_TUNGSTEN);
        simpleItem(ModItems.TUNGSTEN);
        simpleItem(ModItems.TUNGSTEN_NUGGET);
        simpleItem(ModItems.TUNGSTEN_SHEET);

        //simpleBlockItem(ModBlocks.NEXUS_BLOCK);
        //simpleBlockItem(ModBlocks.SKY_BLOCK);
        simpleBlockItem(ModBlocks.TUNGSTEN_DOOR);

        fenceItem(ModBlocks.TUNGSTEN_FENCE, ModBlocks.TUNGSTEN_BLOCK);
        buttonItem(ModBlocks.TUNGSTEN_BUTTON, ModBlocks.TUNGSTEN_BLOCK);
        wallItem(ModBlocks.TUNGSTEN_WALL, ModBlocks.TUNGSTEN_BLOCK);

        evenSimplerBlockItem(ModBlocks.TUNGSTEN_STAIRS);
        evenSimplerBlockItem(ModBlocks.TUNGSTEN_SLAB);
        evenSimplerBlockItem(ModBlocks.TUNGSTEN_PRESSURE_PLATE);
        evenSimplerBlockItem(ModBlocks.TUNGSTEN_GATE);

        trapdoorItem(ModBlocks.TUNGSTEN_TRAPDOOR);

        handHeldItem(ModItems.TUNGSTEN_SWORD);
        handHeldItem(ModItems.TUNGSTEN_PICKAXE);
        handHeldItem(ModItems.TUNGSTEN_AXE);
        handHeldItem(ModItems.TUNGSTEN_SHOVEL);
        handHeldItem(ModItems.TUNGSTEN_HOE);

        trimmedArmorItem(ModItems.TUNGSTEN_HELMET);
        trimmedArmorItem(ModItems.TUNGSTEN_CHESTPLATE);
        trimmedArmorItem(ModItems.TUNGSTEN_LEGGINGS);
        trimmedArmorItem(ModItems.TUNGSTEN_BOOTS);

        //withExistingParent(ModItems.ROBOT_DOG_SPAWN_EGG.getId().getPath(),mcLoc("item/template_spawn_egg"));
        simpleItem(ModItems.TURRET_UPGRADE);
        simpleItem(ModItems.ARMOR_UPGRADE);
        simpleItem(ModItems.SCANNER_UPGRADE);
        simpleItem(ModItems.ROBOT_DOG_SPAWN_EGG);

        //Flans stuff
        simpleItem(ModItems.BRASS_CUP);
        simpleItem(ModItems.WIDE_BRASS_CUP);
        simpleItem(ModItems.PISTOL_CASING);
        simpleItem(ModItems.RIFLE_CASING);
        simpleItem(ModItems.GRENADE_CASING);
        simpleItem(ModItems.ARTILLERY_CASING);
        simpleItem(ModItems.SHOTGUN_CASING);
        simpleItem(ModItems.FILLED_PISTOL_CASING);
        simpleItem(ModItems.FILLED_RIFLE_CASING);
        simpleItem(ModItems.FILLED_GRENADE_CASING);
        simpleItem(ModItems.FILLED_ARTILLERY_CASING);
        simpleItem(ModItems.FILLED_SHOTGUN_CASING);

        simpleItem(ModItems.BALL_BEARINGS);
        //simpleItem(ModItems.BUCKSHOT_SHELL);
        //simpleItem(ModItems.SLUG_SHELL);
        //simpleItem(ModItems.FLECHETTES);
        //simpleItem(ModItems.EXPLOSIVE_SHELL);
        //simpleItem(ModItems.FLECHETTE_SHELL);

        //simpleItem(ModItems.PISTOL_BULLET);
        //simpleItem(ModItems.PISTOL_BULLET_SOFT);
        //simpleItem(ModItems.PISTOL_BULLET_AP);

        //simpleItem(ModItems.RIFLE_BULLET);
        //simpleItem(ModItems.RIFLE_BULLET_SOFT);
        //simpleItem(ModItems.RIFLE_BULLET_AP);

        //simpleItem(ModItems.ARTILLERY_SHELL);
        //simpleItem(ModItems.ARTILLERY_SHELL_AP);
        //simpleItem(ModItems.ARTILLERY_SHELL_HE);

        simpleItem(ModItems.GRENADE);
        //simpleItem(ModItems.GRENADE_ROUND);

        simpleItem(ModItems.TEMPLATE_UPPER_RECEIVER);
        simpleItem(ModItems.TEMPLATE_LOWER_RECEIVER);
        simpleItem(ModItems.TEMPLATE_STOCK);
        simpleItem(ModItems.TEMPLATE_BARREL);
        simpleItem(ModItems.TEMPLATE_GRIP);
        simpleItem(ModItems.TEMPLATE_HANDGUARD);

        simpleItem(ModItems.UPPER_RECEIVER_IRON);
        simpleItem(ModItems.LOWER_RECEIVER_IRON);
        simpleItem(ModItems.STOCK_IRON);
        simpleItem(ModItems.BARREL_IRON);
        simpleItem(ModItems.GRIP_IRON);

        simpleItem(ModItems.UPPER_RECEIVER_BRASS);
        simpleItem(ModItems.LOWER_RECEIVER_BRASS);
        simpleItem(ModItems.STOCK_BRASS);
        simpleItem(ModItems.BARREL_BRASS);
        simpleItem(ModItems.GRIP_BRASS);

        simpleItem(ModItems.UPPER_RECEIVER_TUNGSTEN);
        simpleItem(ModItems.LOWER_RECEIVER_TUNGSTEN);
        simpleItem(ModItems.STOCK_TUNGSTEN);
        simpleItem(ModItems.BARREL_TUNGSTEN);
        simpleItem(ModItems.GRIP_TUNGSTEN);

        simpleItem(ModItems.UPPER_RECEIVER_STURDY);
        simpleItem(ModItems.LOWER_RECEIVER_STURDY);
        simpleItem(ModItems.STOCK_STURDY);
        simpleItem(ModItems.BARREL_STURDY);
        simpleItem(ModItems.GRIP_STURDY);

        simpleItem(ModItems.STOCK_WOOD);
        simpleItem(ModItems.GRIP_WOOD);
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item){
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(LoveAndWarMod.MODID,"item/" + item.getId().getPath()));
    }

    public void evenSimplerBlockItem(RegistryObject<Block> block) {
        this.withExistingParent(LoveAndWarMod.MODID + ":" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),
                modLoc("block/" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath()));
    }

    public void trapdoorItem(RegistryObject<Block> block) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),
                modLoc("block/" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath() + "_bottom"));
    }

    public void fenceItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/fence_inventory"))
                .texture("texture",  new ResourceLocation(LoveAndWarMod.MODID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    public void buttonItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/button_inventory"))
                .texture("texture",  new ResourceLocation(LoveAndWarMod.MODID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    public void wallItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/wall_inventory"))
                .texture("wall",  new ResourceLocation(LoveAndWarMod.MODID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    private ItemModelBuilder simpleBlockItem(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(LoveAndWarMod.MODID,"item/" + item.getId().getPath()));
    }

    private ItemModelBuilder handHeldItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(LoveAndWarMod.MODID,"item/" + item.getId().getPath()));
    }

    private void trimmedArmorItem(RegistryObject<Item> itemRegistryObject) {
        final String MOD_ID = LoveAndWarMod.MODID; // Change this to your mod id

        if(itemRegistryObject.get() instanceof ArmorItem armorItem) {
            trimMaterials.entrySet().forEach(entry -> {

                ResourceKey<TrimMaterial> trimMaterial = entry.getKey();
                float trimValue = entry.getValue();

                String armorType = switch (armorItem.getEquipmentSlot()) {
                    case HEAD -> "helmet";
                    case CHEST -> "chestplate";
                    case LEGS -> "leggings";
                    case FEET -> "boots";
                    default -> "";
                };

                String armorItemPath = "item/" + armorItem;
                String trimPath = "trims/items/" + armorType + "_trim_" + trimMaterial.location().getPath();
                String currentTrimName = armorItemPath + "_" + trimMaterial.location().getPath() + "_trim";
                ResourceLocation armorItemResLoc = new ResourceLocation(MOD_ID, armorItemPath);
                ResourceLocation trimResLoc = new ResourceLocation(trimPath); // minecraft namespace
                ResourceLocation trimNameResLoc = new ResourceLocation(MOD_ID, currentTrimName);

                // This is used for making the ExistingFileHelper acknowledge that this texture exist, so this will
                // avoid an IllegalArgumentException
                existingFileHelper.trackGenerated(trimResLoc, PackType.CLIENT_RESOURCES, ".png", "textures");

                // Trimmed armorItem files
                getBuilder(currentTrimName)
                        .parent(new ModelFile.UncheckedModelFile("item/generated"))
                        .texture("layer0", armorItemResLoc)
                        .texture("layer1", trimResLoc);

                // Non-trimmed armorItem file (normal variant)
                this.withExistingParent(itemRegistryObject.getId().getPath(),
                                mcLoc("item/generated"))
                        .override()
                        .model(new ModelFile.UncheckedModelFile(trimNameResLoc))
                        .predicate(mcLoc("trim_type"), trimValue).end()
                        .texture("layer0",
                                new ResourceLocation(MOD_ID,
                                        "item/" + itemRegistryObject.getId().getPath()));
            });
        }
    }
}
