package net.pttheta.loveandwar.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.pttheta.loveandwar.LoveAndWarMod;

public class ModTags {

    public static class Blocks{

        public static final TagKey<Block> SULPHUR = tag("sulphur");
        public static final TagKey<Block> TUNGSTEN = tag("tungsten");
        public static final TagKey<Block> PLASTIC = tag("plastic");
        public static final TagKey<Block> COLORED_PLASTIC = tag("colored_plastic");
        public static final TagKey<Block> NEEDS_TUNGSTEN_TOOL = tag("needs_tungsten_tool");

        private static TagKey<Block> tag(String name){
            return BlockTags.create(new ResourceLocation(LoveAndWarMod.MODID,name));
        }

        private static TagKey<Block> tagFlan(String name){
            return BlockTags.create(new ResourceLocation("flansmod",name));
        }
    }

    public static class Items{

        public static final TagKey<Item> STAMPING_TEMPLATE = tag("stamping_template");
        public static final TagKey<Item> PAPER_CARTRIDGE = tag("black_powder");
        public static final TagKey<Item> PISTOL_BULLET = tagFlanItem("pistol_bullet");
        public static final TagKey<Item> RIFLE_BULLET = tagFlanItem("rifle_bullet");
        public static final TagKey<Item> SHOTGUN_SHELL = tagFlanItem("shotgun_shell");
        public static final TagKey<Item> GRENADE_ROUND = tagFlanItem("launchable_grenade");
        public static final TagKey<Item> ARTILLERY_SHELL = tagFlanItem("artillery_shell");

        private static TagKey<Item> tag(String name){
            return ItemTags.create(new ResourceLocation(LoveAndWarMod.MODID,name));
        }

        private static TagKey<Item> tagFlanItem(String name){
            return ItemTags.create(new ResourceLocation("flansmod",name));
        }
    }
}
