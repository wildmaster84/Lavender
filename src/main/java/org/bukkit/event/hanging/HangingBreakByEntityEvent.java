package org.bukkit.event.hanging;

import org.bukkit.entity.Entity;
import org.bukkit.entity.HangingEntity;
import org.bukkit.event.HandlerList;

public class HangingBreakByEntityEvent extends HangingBreakEvent {
    private static final org.bukkit.event.HandlerList HANDLER_LIST = new org.bukkit.event.HandlerList();
    private final Entity remover;
    public HangingBreakByEntityEvent(HangingEntity hanging, Entity remover) {
        super(hanging);
        this.remover = remover;
    }
    public Entity getRemover() { return remover; }
    @Override public HandlerList getHandlers() { return HANDLER_LIST; }
    public static HandlerList getHandlerList() { return HANDLER_LIST; }
}
