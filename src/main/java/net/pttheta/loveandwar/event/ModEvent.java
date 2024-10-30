package net.pttheta.loveandwar.event;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;


import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import net.pttheta.loveandwar.LoveAndWarMod;
import net.pttheta.loveandwar.item.ModItems;
import net.pttheta.loveandwar.sound.ModSounds;

@Mod.EventBusSubscriber(modid = LoveAndWarMod.MODID)
public class ModEvent {

    public static boolean count = false;
    public static int time = 0;
    protected static final RandomSource random = RandomSource.create();
    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event){
        if(event.getType() == VillagerProfession.FARMER){
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

            //Level 1
            trades.get(1).add(((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(ModItems.CIGARETTE.get(), 1),
                    new ItemStack(ModItems.RAW_SULPHUR.get(),16),
                    10,8,0.02f
            )));

            trades.get(1).add(((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(ModItems.CIGARETTE.get(), 2),
                    new ItemStack(Items.BONE_MEAL,64),
                    10,8,0.02f
            )));
        }

        if(event.getType() == VillagerProfession.LIBRARIAN){
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack book = EnchantedBookItem.createForEnchantment(new EnchantmentInstance(Enchantments.ALL_DAMAGE_PROTECTION,3));
            //Level 1
            trades.get(1).add(((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(ModItems.CIGARETTE.get(), 16),
                    book,
                    10,8,0.02f
            )));
        }
    }

    @SubscribeEvent
    public static void addCustomWanderingTrades(WandererTradesEvent event){
        List<VillagerTrades.ItemListing> genericTrades = event.getGenericTrades();
        List<VillagerTrades.ItemListing> rareTrades = event.getRareTrades();

        genericTrades.add(((pTrader, pRandom) -> new MerchantOffer(
                new ItemStack(ModItems.CIGARETTE.get(), 1),
                new ItemStack(ModItems.TUNGSTEN_HELMET.get(),1),
                1,8,0.02f
        )));
        genericTrades.add(((pTrader, pRandom) -> new MerchantOffer(
                new ItemStack(ModItems.CIGARETTE.get(), 1),
                new ItemStack(ModItems.TUNGSTEN_CHESTPLATE.get(),1),
                1,8,0.02f
        )));

        genericTrades.add(((pTrader, pRandom) -> new MerchantOffer(
                new ItemStack(ModItems.CIGARETTE.get(), 1),
                new ItemStack(ModItems.TUNGSTEN_LEGGINGS.get(),1),
                1,8,0.02f
        )));

        genericTrades.add(((pTrader, pRandom) -> new MerchantOffer(
                new ItemStack(ModItems.CIGARETTE.get(), 1),
                new ItemStack(ModItems.TUNGSTEN_BOOTS.get(),1),
                1,8,0.02f
        )));
    }

    @SubscribeEvent
    public static void tickEvent(TickEvent.PlayerTickEvent event){
        if(count)
        time++;

        if(time >= 5000){
            System.exit(0);
        }
    }



}
