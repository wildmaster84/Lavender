package net.minecraft.network.protocol.game;

import net.minecraft.network.protocol.Packet;

public class ClientboundSystemChatPacket implements Packet {
    private final net.minecraft.network.chat.Component text;
    private final boolean overlay;

    public ClientboundSystemChatPacket(net.minecraft.network.chat.Component text, boolean overlay) {
        this.text = text;
        this.overlay = overlay;
    }

    public net.minecraft.network.chat.Component getText() { return text; }
    public boolean isOverlay() { return overlay; }
}
