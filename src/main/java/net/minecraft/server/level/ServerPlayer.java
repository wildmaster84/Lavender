package net.minecraft.server.level;

import com.mojang.authlib.GameProfile;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class ServerPlayer extends Player {
    public ServerGamePacketListenerImpl connection;

    public ServerPlayer(
            MinecraftServer server,
            ServerLevel level,
            GameProfile profile,
            ClientInformation information
    ) {
        super(null);
    }

    public ServerPlayer(net.minestom.server.entity.Player minestomPlayer) {
        super(minestomPlayer);
    }

    public ServerLevel level() {
        return null;
    }

    public org.bukkit.entity.Player getBukkitEntity() {
        return null;
    }

    public void setItemInHand(InteractionHand hand, ItemStack stack) {
    }

    public void absSnapTo(double x, double y, double z, float yRot, float xRot) {
    }

    public void updateOptions(ClientInformation information) {
    }
}
