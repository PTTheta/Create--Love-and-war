package net.pttheta.loveandwar.datagen;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.pttheta.loveandwar.LoveAndWarMod;
import net.pttheta.loveandwar.blocks.ModBlocks;
import net.pttheta.loveandwar.item.ModItems;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {

    private static final List<ItemLike> SULPHUR_SMELTABLES = List.of(ModItems.RAW_SULPHUR.get(),
            ModBlocks.SULPHUR_ORE.get(),ModBlocks.DEEPSLATE_SULPHUR_ORE.get(),ModBlocks.NETHER_SULPHUR_ORE.get());
    private static final List<ItemLike> TUNGSTEN_SMELTABLES = List.of(ModItems.RAW_TUNGSTEN.get(),
            ModBlocks.DEEPSLATE_TUNGSTEN_ORE.get());
    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {

        sulphurRecipes(pWriter);
        tungstenRecipes(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,Items.GUNPOWDER,3)
                .requires(ModItems.SULPHUR.get())
                .requires(Items.COAL)
                .requires(Items.BONE_MEAL)
                .unlockedBy(getHasName(ModItems.SULPHUR.get()),has(ModItems.SULPHUR.get()))
                .save(pWriter);


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ROBOT_DOG_SPAWN_EGG.get())
                .pattern(" RB")
                .pattern("BPB")
                .pattern("T T")
                .define('T',ModItems.TUNGSTEN.get())
                .define('P', AllItems.PRECISION_MECHANISM.get())
                .define('B', AllItems.BRASS_INGOT.get())
                .define('R',Items.REDSTONE_BLOCK)
                .unlockedBy(getHasName(AllItems.PRECISION_MECHANISM.get()),has(AllItems.PRECISION_MECHANISM.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ARMOR_UPGRADE.get())
                .pattern("   ")
                .pattern("TIT")
                .pattern("TIT")
                .define('T',ModItems.TUNGSTEN.get())
                .define('I',Items.IRON_INGOT)
                .unlockedBy(getHasName(ModItems.ROBOT_DOG_SPAWN_EGG.get()),has(ModItems.ROBOT_DOG_SPAWN_EGG.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SCANNER_UPGRADE.get())
                .pattern(" T ")
                .pattern("GRG")
                .pattern(" T ")
                .define('T',ModItems.TUNGSTEN.get())
                .define('R',Items.REDSTONE)
                .define('G',Items.GLASS)
                .unlockedBy(getHasName(ModItems.ROBOT_DOG_SPAWN_EGG.get()),has(ModItems.ROBOT_DOG_SPAWN_EGG.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.TURRET_UPGRADE.get())
                .pattern("   ")
                .pattern("RII")
                .pattern(" T ")
                .define('T',ModItems.TUNGSTEN.get())
                .define('R',Items.REDSTONE)
                .define('I',Items.IRON_INGOT)
                .unlockedBy(getHasName(ModItems.ROBOT_DOG_SPAWN_EGG.get()),has(ModItems.ROBOT_DOG_SPAWN_EGG.get()))
                .save(pWriter);


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.GRENADE.get())
                .pattern("IRI")
                .pattern("IGI")
                .pattern("III")
                .define('R',Items.REDSTONE)
                .define('G', Items.GUNPOWDER)
                .define('I', Items.IRON_NUGGET)
                .unlockedBy(getHasName(Items.GUNPOWDER),has(Items.GUNPOWDER))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.BALL_BEARINGS.get(),1)
                .requires(Items.IRON_NUGGET)
                .requires(Items.IRON_NUGGET)
                .requires(Items.IRON_NUGGET)
                .unlockedBy(getHasName(Items.IRON_NUGGET),has(Items.IRON_NUGGET))
                .save(pWriter);
    }

    private void sulphurRecipes(Consumer<FinishedRecipe> pWriter){
        oreBlasting(pWriter, SULPHUR_SMELTABLES, RecipeCategory.MISC,ModItems.SULPHUR.get(),0.25f,100,"sulphur");
        oreSmelting(pWriter, SULPHUR_SMELTABLES, RecipeCategory.MISC,ModItems.SULPHUR.get(),0.25f,200,"sulphur");
        blockFromItem(ModBlocks.RAW_SULPHUR_BLOCK.get(),ModItems.RAW_SULPHUR.get(),pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SULPHUR_BLOCK.get())
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S',ModItems.SULPHUR.get())
                .unlockedBy(getHasName(ModItems.SULPHUR.get()),has(ModItems.SULPHUR.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.SULPHUR.get(),9)
                .requires(ModBlocks.SULPHUR_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.SULPHUR_BLOCK.get()),has(ModBlocks.SULPHUR_BLOCK.get()))
                .save(pWriter);

    }

    private void tungstenRecipes(Consumer<FinishedRecipe> pWriter){
        oreProcessing(pWriter,TUNGSTEN_SMELTABLES,ModItems.TUNGSTEN.get(),"tungsten");
        blockFromItem(ModBlocks.RAW_TUNGSTEN_BLOCK.get(),ModItems.RAW_TUNGSTEN.get(),pWriter);
        blockFromItem(ModBlocks.TUNGSTEN_BLOCK.get(),ModItems.TUNGSTEN.get(),pWriter);

        swordItem(ModItems.TUNGSTEN_SWORD.get(), ModItems.TUNGSTEN.get(), pWriter);
        pickaxeItem(ModItems.TUNGSTEN_PICKAXE.get(), ModItems.TUNGSTEN.get(), pWriter);
        axeItem(ModItems.TUNGSTEN_AXE.get(), ModItems.TUNGSTEN.get(), pWriter);
        shovelItem(ModItems.TUNGSTEN_SHOVEL.get(), ModItems.TUNGSTEN.get(), pWriter);
        hoeItem(ModItems.TUNGSTEN_HOE.get(), ModItems.TUNGSTEN.get(), pWriter);

        helmetItem(ModItems.TUNGSTEN_HELMET.get(), ModItems.TUNGSTEN.get(), pWriter);
        chestplateItem(ModItems.TUNGSTEN_CHESTPLATE.get(), ModItems.TUNGSTEN.get(), pWriter);
        leggingsItem(ModItems.TUNGSTEN_LEGGINGS.get(), ModItems.TUNGSTEN.get(), pWriter);
        bootsItem(ModItems.TUNGSTEN_BOOTS.get(), ModItems.TUNGSTEN.get(), pWriter);

        /**
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.TUNGSTEN.get())
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S',ModItems.TUNGSTEN_NUGGET.get())
                .unlockedBy(getHasName(ModItems.TUNGSTEN_NUGGET.get()),has(ModItems.TUNGSTEN_NUGGET.get()))
                .save(pWriter);
         */

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.TUNGSTEN_NUGGET.get(),9)
                .requires(ModItems.TUNGSTEN.get())
                .unlockedBy(getHasName(ModItems.TUNGSTEN.get()),has(ModItems.TUNGSTEN.get()))
                .save(pWriter);
    }

    protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult,
                            pExperience, pCookingTime, pCookingSerializer)
                    .group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pFinishedRecipeConsumer, LoveAndWarMod.MODID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }

    protected static void oreProcessing(Consumer<FinishedRecipe> pWriter, List<ItemLike> input,Item output, String name){
        oreBlasting(pWriter, input, RecipeCategory.MISC,output,0.25f,100,name);
        oreSmelting(pWriter, input, RecipeCategory.MISC,output,0.25f,200,name);

    }

    protected static void blockFromItem(Block block, Item item,Consumer<FinishedRecipe> pWriter){
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, block)
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S',item)
                .unlockedBy(getHasName(item),has(item))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,item,9)
                .requires(block)
                .unlockedBy(getHasName(block),has(block))
                .save(pWriter);
    }

    protected static void swordItem(Item output, Item ingredient, Consumer<FinishedRecipe> pWriter){
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, output)
                .pattern(" T ")
                .pattern(" T ")
                .pattern(" S ")
                .define('S', Items.STICK)
                .define('T', ingredient)
                .unlockedBy(getHasName(ingredient),has(ingredient))
                .save(pWriter);
    }


    protected static void pickaxeItem(Item output, Item ingredient, Consumer<FinishedRecipe> pWriter){
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, output)
                .pattern("TTT")
                .pattern(" S ")
                .pattern(" S ")
                .define('S', Items.STICK)
                .define('T', ingredient)
                .unlockedBy(getHasName(ingredient),has(ingredient))
                .save(pWriter);
    }

    protected static void axeItem(Item output, Item ingredient, Consumer<FinishedRecipe> pWriter){
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, output)
                .pattern("TT ")
                .pattern("TS ")
                .pattern(" S ")
                .define('S', Items.STICK)
                .define('T', ingredient)
                .unlockedBy(getHasName(ingredient),has(ingredient))
                .save(pWriter);
    }

    protected static void shovelItem(Item output, Item ingredient, Consumer<FinishedRecipe> pWriter){
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, output)
                .pattern(" T ")
                .pattern(" S ")
                .pattern(" S ")
                .define('S', Items.STICK)
                .define('T', ingredient)
                .unlockedBy(getHasName(ingredient),has(ingredient))
                .save(pWriter);
    }

    protected static void hoeItem(Item output, Item ingredient, Consumer<FinishedRecipe> pWriter){
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, output)
                .pattern("TT ")
                .pattern(" S ")
                .pattern(" S ")
                .define('S', Items.STICK)
                .define('T', ingredient)
                .unlockedBy(getHasName(ingredient),has(ingredient))
                .save(pWriter);
    }

    protected static void helmetItem(Item output, Item ingredient, Consumer<FinishedRecipe> pWriter){
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, output)
                .pattern("TTT")
                .pattern("T T")
                .pattern("   ")
                .define('T', ingredient)
                .unlockedBy(getHasName(ingredient),has(ingredient))
                .save(pWriter);
    }

    protected static void chestplateItem(Item output, Item ingredient, Consumer<FinishedRecipe> pWriter){
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, output)
                .pattern("T T")
                .pattern("TTT")
                .pattern("TTT")
                .define('T', ingredient)
                .unlockedBy(getHasName(ingredient),has(ingredient))
                .save(pWriter);
    }

    protected static void leggingsItem(Item output, Item ingredient, Consumer<FinishedRecipe> pWriter){
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, output)
                .pattern("TTT")
                .pattern("T T")
                .pattern("T T")
                .define('T', ingredient)
                .unlockedBy(getHasName(ingredient),has(ingredient))
                .save(pWriter);
    }

    protected static void bootsItem(Item output, Item ingredient, Consumer<FinishedRecipe> pWriter){
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, output)
                .pattern("   ")
                .pattern("T T")
                .pattern("T T")
                .define('T', ingredient)
                .unlockedBy(getHasName(ingredient),has(ingredient))
                .save(pWriter);
    }
}
