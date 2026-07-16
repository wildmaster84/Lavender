package net.minecraft.server.network;

import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundSystemChatPacket;

public class ServerGamePacketListenerImpl {
    private final net.minestom.server.entity.Player minestomPlayer;

    public ServerGamePacketListenerImpl(net.minestom.server.entity.Player minestomPlayer) {
        this.minestomPlayer = minestomPlayer;
    }

    public void send(Packet<?> packet) {
        if (minestomPlayer == null || !minestomPlayer.isOnline()) return;
        try {
            if (packet instanceof ClientboundSystemChatPacket chatPacket) {
                net.minecraft.network.chat.Component component = chatPacket.getText();
                if (component != null) {
                    minestomPlayer.sendMessage(component.getAdventureComponent());
                }
            }
        } catch (Throwable t) {
            // Don't let packet sending errors crash the tick thread
        }
    }
}
