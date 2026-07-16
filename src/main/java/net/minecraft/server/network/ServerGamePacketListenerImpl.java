package net.minecraft.server.network;

import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundSystemChatPacket;

public class ServerGamePacketListenerImpl {
    private final net.minestom.server.entity.Player minestomPlayer;
    public Connection connection;

    public ServerGamePacketListenerImpl(net.minestom.server.entity.Player minestomPlayer) {
        this.minestomPlayer = minestomPlayer;
        this.connection = new Connection();
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
        }
    }
}
