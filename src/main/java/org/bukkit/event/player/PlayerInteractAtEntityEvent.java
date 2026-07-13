package org.bukkit.event.player;

import org.bukkit.entity.Player;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.EquipmentSlot;

public class PlayerInteractAtEntityEvent extends PlayerInteractEntityEvent {

    private static final org.bukkit.event.HandlerList handlers = new org.bukkit.event.HandlerList();

    public PlayerInteractAtEntityEvent(Player player) {
        super(player);
    }

    public PlayerInteractAtEntityEvent(Player player, Entity clickedEntity) {
        super(player, clickedEntity);
    }

    public PlayerInteractAtEntityEvent(Player player, Entity clickedEntity, EquipmentSlot hand) {
        super(player, clickedEntity, hand);
    }

    @Override
    public org.bukkit.event.HandlerList getHandlers() { return handlers; }
    public static org.bukkit.event.HandlerList getHandlerList() { return handlers; }
}
