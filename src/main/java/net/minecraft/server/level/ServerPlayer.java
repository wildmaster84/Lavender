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
        this.connection = new ServerGamePacketListenerImpl(minestomPlayer);
    }

    public ServerLevel level() {
        net.minestom.server.entity.Player msPlayer = (net.minestom.server.entity.Player) getMinestomEntity();
        net.minestom.server.instance.Instance instance = msPlayer.getInstance();
        if (instance == null) return null;
        org.bukkit.Server bukkitServer = org.bukkit.Bukkit.getServer();
        if (bukkitServer instanceof me.wildmaster84.adapter.server.LavenderServer ls) {
            me.wildmaster84.adapter.world.LavenderWorld lw = ls.getWorld(instance);
            if (lw != null) return lw.getHandle();
        }
        return new ServerLevel(instance);
    }

    public org.bukkit.entity.Player getBukkitEntity() {
        net.minestom.server.entity.Player msPlayer = (net.minestom.server.entity.Player) getMinestomEntity();
        org.bukkit.Server bukkitServer = org.bukkit.Bukkit.getServer();
        if (bukkitServer instanceof me.wildmaster84.adapter.server.LavenderServer ls) {
            return me.wildmaster84.adapter.player.LavenderPlayer.wrap(msPlayer, ls);
        }
        return null;
    }

    public void setItemInHand(InteractionHand hand, ItemStack stack) {
        net.minestom.server.entity.Player msPlayer = (net.minestom.server.entity.Player) getMinestomEntity();
        net.minestom.server.entity.EquipmentSlot slot = hand == InteractionHand.MAIN_HAND
                ? net.minestom.server.entity.EquipmentSlot.MAIN_HAND
                : net.minestom.server.entity.EquipmentSlot.OFF_HAND;
        msPlayer.getInventory().setEquipment(slot, (byte) 0, stack != null ? stack.getMinestomStack() : net.minestom.server.item.ItemStack.AIR);
    }

    public void absSnapTo(double x, double y, double z, float yRot, float xRot) {
        net.minestom.server.entity.Player msPlayer = (net.minestom.server.entity.Player) getMinestomEntity();
        msPlayer.teleport(new net.minestom.server.coordinate.Pos(x, y, z, yRot, xRot));
    }

    public void updateOptions(ClientInformation information) {
    }
}
