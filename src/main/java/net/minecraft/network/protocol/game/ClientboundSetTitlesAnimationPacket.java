package net.minecraft.network.protocol.game;

import net.minecraft.network.protocol.Packet;

public class ClientboundSetTitlesAnimationPacket implements Packet {
    private final int fadeIn;
    private final int stay;
    private final int fadeOut;

    public ClientboundSetTitlesAnimationPacket(int fadeIn, int stay, int fadeOut) {
        this.fadeIn = fadeIn;
        this.stay = stay;
        this.fadeOut = fadeOut;
    }

    public int getFadeIn() { return fadeIn; }
    public int getStay() { return stay; }
    public int getFadeOut() { return fadeOut; }
}
