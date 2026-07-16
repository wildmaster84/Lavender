package net.minecraft.network.protocol.game;

import net.minecraft.network.protocol.Packet;

public class ClientboundSetSubtitleTextPacket implements Packet {
    private final net.minecraft.network.chat.Component text;

    public ClientboundSetSubtitleTextPacket(net.minecraft.network.chat.Component text) {
        this.text = text;
    }

    public net.minecraft.network.chat.Component getText() { return text; }
}
