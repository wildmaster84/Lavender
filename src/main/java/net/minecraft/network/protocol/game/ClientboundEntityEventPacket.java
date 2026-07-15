package net.minecraft.network.protocol.game;

import net.minecraft.network.protocol.Packet;
import net.minecraft.world.entity.Entity;

public class ClientboundEntityEventPacket implements Packet<Object> {
    public static ClientboundEntityEventPacket addBadge(Entity entity) {
        return new ClientboundEntityEventPacket();
    }
}
