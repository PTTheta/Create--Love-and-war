package net.pttheta.loveandwar.network;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraftforge.network.NetworkEvent;
import net.pttheta.loveandwar.sound.ModSounds;

import java.util.function.Supplier;

public class SSendMessage {

    private final Component toSay;


    public SSendMessage(Component message) {
        this.toSay = message;
    }

    public void encode(FriendlyByteBuf buffer){
        buffer.writeComponent(this.toSay);
    }

    public SSendMessage(FriendlyByteBuf buffer){
        this(buffer.readComponent());
    }

    public void handle(Supplier<NetworkEvent.Context> contextSupplier) {

        contextSupplier.get().enqueueWork(()-> {
                    ServerPlayer player = contextSupplier.get().getSender();
                    player.sendSystemMessage(this.toSay);
                }
                );
    }
}
