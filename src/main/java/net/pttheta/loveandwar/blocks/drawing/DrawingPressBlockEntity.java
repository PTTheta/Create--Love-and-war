package net.pttheta.loveandwar.blocks.drawing;

import com.simibubi.create.AllRecipeTypes;
import com.simibubi.create.AllTags;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.belt.transport.TransportedItemStack;
import com.simibubi.create.content.kinetics.crafter.MechanicalCraftingRecipe;
import com.simibubi.create.content.processing.sequenced.SequencedAssemblyRecipe;
import com.simibubi.create.foundation.advancement.AdvancementBehaviour;
import com.simibubi.create.foundation.advancement.AllAdvancements;
import com.simibubi.create.foundation.advancement.CreateAdvancement;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import com.simibubi.create.foundation.item.ItemHelper;
import com.simibubi.create.foundation.recipe.RecipeApplier;
import com.simibubi.create.foundation.utility.VecHelper;
import com.simibubi.create.infrastructure.config.AllConfigs;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Container;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.RecipeWrapper;
import net.pttheta.loveandwar.recipe.ModRecipes;
import net.pttheta.loveandwar.recipe.drawing.DrawingRecipe;
import net.pttheta.loveandwar.blocks.drawing.DrawingBehaviour.DrawingBehaviourSpecifics;

import java.util.List;
import java.util.Optional;

public class DrawingPressBlockEntity extends KineticBlockEntity implements DrawingBehaviourSpecifics {

    private static final Object drawingRecipesKey = new Object();
    public DrawingBehaviour drawingBehaviour;
    private int tracksCreated;
    public DrawingPressBlockEntity(BlockEntityType<? extends DrawingPressBlockEntity> typeIn, BlockPos pos, BlockState state) {
        super(typeIn, pos, state);
    }

    @Override

    public void addBehaviours(List<BlockEntityBehaviour> behaviours) {
        super.addBehaviours(behaviours);
        drawingBehaviour = new DrawingBehaviour(this);
        behaviours.add(drawingBehaviour);

       registerAwardables(behaviours, AllAdvancements.PRESS,
                AllAdvancements.TRACK_CRAFTING);
    }

    @Override
    protected AABB createRenderBoundingBox() {
        return new AABB(worldPosition).expandTowards(0, -1.5, 0)
                .expandTowards(0, 1, 0);
    }

    public void onItemDrawn(ItemStack result) {
        //award(AllAdvancements.PRESS);
        if (AllTags.AllBlockTags.TRACKS.matches(result))
            tracksCreated += result.getCount();
        if (tracksCreated >= 1000) {
            award(AllAdvancements.TRACK_CRAFTING);
            tracksCreated = 0;
        }
    }

    @Override
    protected void write(CompoundTag compound, boolean clientPacket) {
        super.write(compound, clientPacket);
        if (getBehaviour(AdvancementBehaviour.TYPE).isOwnerPresent())
            compound.putInt("TracksCreated", tracksCreated);
    }

    @Override
    protected void read(CompoundTag compound, boolean clientPacket) {
        super.read(compound, clientPacket);
        tracksCreated = compound.getInt("TracksCreated");
    }

    public DrawingBehaviour getDrawingBehaviour() {
        return drawingBehaviour;
    }

    public boolean tryProcessInWorld(ItemEntity itemEntity, boolean simulate) {
        ItemStack item = itemEntity.getItem();
        Optional<DrawingRecipe> recipe = getRecipe(item);
        if (!recipe.isPresent())
            return false;
        if (simulate)
            return true;

        ItemStack itemCreated = ItemStack.EMPTY;
        drawingBehaviour.particleItems.add(item);
        if (canProcessInBulk() || item.getCount() == 1) {
            RecipeApplier.applyRecipeOn(itemEntity, recipe.get());
            itemCreated = itemEntity.getItem()
                    .copy();
        } else {
            for (ItemStack result : RecipeApplier.applyRecipeOn(level, ItemHandlerHelper.copyStackWithSize(item, 1),
                    recipe.get())) {
                if (itemCreated.isEmpty())
                    itemCreated = result.copy();
                ItemEntity created =
                        new ItemEntity(level, itemEntity.getX(), itemEntity.getY(), itemEntity.getZ(), result);
                created.setDefaultPickUpDelay();
                created.setDeltaMovement(VecHelper.offsetRandomly(Vec3.ZERO, level.random, .05f));
                level.addFreshEntity(created);
            }
            item.shrink(1);
        }

        if (!itemCreated.isEmpty())
            onItemDrawn(itemCreated);
        return true;
    }

    @Override
    public boolean tryProcessOnBelt(TransportedItemStack input, List<ItemStack> outputList, boolean simulate) {
        Optional<DrawingRecipe> recipe = getRecipe(input.stack);
        if (!recipe.isPresent())
            return false;
        if (simulate)
            return true;
        drawingBehaviour.particleItems.add(input.stack);
        List<ItemStack> outputs = RecipeApplier.applyRecipeOn(level,
                canProcessInBulk() ? input.stack : ItemHandlerHelper.copyStackWithSize(input.stack, 1), recipe.get());

        for (ItemStack created : outputs) {
            if (!created.isEmpty()) {
                onItemDrawn(created);
                break;
            }
        }

        outputList.addAll(outputs);
        return true;
    }

    @Override
    public boolean canProcessInBulk() {
        return AllConfigs.server().recipes.bulkPressing.get();
    }

    @Override
    public void onDrawingCompleted() {

    }

    public static <C extends Container> boolean canCompress(Recipe<C> recipe) {
        if (!(recipe instanceof CraftingRecipe) || !AllConfigs.server().recipes.allowShapedSquareInPress.get())
            return false;
        NonNullList<Ingredient> ingredients = recipe.getIngredients();
        return (ingredients.size() == 4 || ingredients.size() == 9) && ItemHelper.matchAllIngredients(ingredients);
    }

    @Override
    public int getParticleAmount() {
        return 15;
    }
    @Override
    public float getKineticSpeed() {
        return getSpeed();
    }

    @Override
    public void tick() {
        super.tick();
    }


    private static final RecipeWrapper pressingInv = new RecipeWrapper(new ItemStackHandler(1));
    public Optional<DrawingRecipe> getRecipe(ItemStack item) {
        Optional<DrawingRecipe> assemblyRecipe =
                SequencedAssemblyRecipe.getRecipe(level, item, ModRecipes.DRAWING_TYPE.get(), DrawingRecipe.class);
        if (assemblyRecipe.isPresent())
            return assemblyRecipe;

        pressingInv.setItem(0, item);
        return find(pressingInv, level);
    }

    public Optional<DrawingRecipe> find(RecipeWrapper inv, Level world) {
        var sequenced = SequencedAssemblyRecipe.getRecipe(level, inv.getItem(0), ModRecipes.DRAWING_TYPE.get(), DrawingRecipe.class);
        if(sequenced.isPresent()) {
            return sequenced;
        }
        return world.getRecipeManager().getRecipeFor(ModRecipes.DRAWING_TYPE.get(), inv, world);
    }

    //@Override
    //protected boolean isRunning() {
        //return drawingBehaviour.running;
    //}

    protected Object getRecipeCacheKey() {
        return drawingRecipesKey;
    }

}
