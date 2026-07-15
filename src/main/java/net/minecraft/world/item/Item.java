package net.minecraft.world.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class Item {
    private final net.minestom.server.item.Material material;

    public Item(net.minestom.server.item.Material material) {
        this.material = material;
    }

    public net.minestom.server.item.Material getMaterial() {
        return material;
    }

    public String getDescriptionId() {
        return material.name();
    }

    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        return null;
    }
}
