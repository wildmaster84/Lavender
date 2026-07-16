package net.minecraft.network.protocol.game;

import net.minecraft.network.protocol.Packet;

public class ClientboundSetTitleTextPacket implements Packet {
    private final net.minecraft.network.chat.Component text;

    public ClientboundSetTitleTextPacket(net.minecraft.network.chat.Component text) {
        this.text = text;
    }

    public net.minecraft.network.chat.Component getText() { return text; }
}
