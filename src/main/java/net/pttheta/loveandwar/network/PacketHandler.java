package net.pttheta.loveandwar.network;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import net.pttheta.loveandwar.LoveAndWarMod;

public class PacketHandler {
    private static final String PROTOCOL_VERSION = "1";
    //private static final SimpleChannel INSTANCE = NetworkRegistry.ChannelBuilder.named(
                    //new ResourceLocation(LoveAndWarMod.MODID, "main"))
            //.serverAcceptedVersions((version) -> true)
            //.clientAcceptedVersions((version) -> true)
            //.networkProtocolVersion(()-> new String("1"))
            //.simpleChannel();
    private static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(LoveAndWarMod.MODID, "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );


    public static void register(){
        INSTANCE.messageBuilder(SSendMessage.class, NetworkDirection.PLAY_TO_SERVER.ordinal())
                .encoder(SSendMessage::encode)
                .decoder(SSendMessage::new)
                .consumerMainThread(SSendMessage::handle)
                .add();

    }

    public static void sendToServer(Object message){
        INSTANCE.send(PacketDistributor.SERVER.noArg(),message);
    }

    public static void sendToPlayer(Object message, ServerPlayer player){
        INSTANCE.send(PacketDistributor.PLAYER.with(()->player),message);
    }

    public static void sendToNearbyPlayers(Object message, LevelChunk chunk){
        INSTANCE.send(PacketDistributor.TRACKING_CHUNK.with(()->chunk),message);
    }

    public static void sendToNearbyPlayers(Object message, PacketDistributor.TargetPoint point){
        INSTANCE.send(PacketDistributor.NEAR.with(()->point),message);
    }
    public static void sendToAllClients(Object message){
        INSTANCE.send(PacketDistributor.ALL.noArg(),message);
    }

}
