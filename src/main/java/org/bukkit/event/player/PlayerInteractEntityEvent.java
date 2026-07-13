package org.bukkit.event.player;

import org.bukkit.entity.Player;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.EquipmentSlot;

public class PlayerInteractEntityEvent extends PlayerEvent implements org.bukkit.event.Cancellable {

    private final Entity clickedEntity;
    private final EquipmentSlot hand;
    private boolean cancelled = false;

    private static final org.bukkit.event.HandlerList handlers = new org.bukkit.event.HandlerList();

    public PlayerInteractEntityEvent(Player player) {
        super(player);
        this.clickedEntity = null;
        this.hand = EquipmentSlot.HAND;
    }

    public PlayerInteractEntityEvent(Player player, Entity clickedEntity) {
        super(player);
        this.clickedEntity = clickedEntity;
        this.hand = EquipmentSlot.HAND;
    }

    public PlayerInteractEntityEvent(Player player, Entity clickedEntity, EquipmentSlot hand) {
        super(player);
        this.clickedEntity = clickedEntity;
        this.hand = hand;
    }

    public Entity getRightClicked() { return clickedEntity; }
    public EquipmentSlot getHand() { return hand; }

    @Override
    public boolean isCancelled() { return cancelled; }
    @Override
    public void setCancelled(boolean cancel) { this.cancelled = cancel; }

    @Override
    public org.bukkit.event.HandlerList getHandlers() { return handlers; }
    public static org.bukkit.event.HandlerList getHandlerList() { return handlers; }
}
