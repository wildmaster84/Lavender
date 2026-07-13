package org.bukkit.event.hanging;

import org.bukkit.entity.Entity;
import org.bukkit.entity.HangingEntity;
import org.bukkit.event.HandlerList;

public class HangingBreakByEntityEvent extends HangingBreakEvent {
    private final Entity remover;
    public HangingBreakByEntityEvent(HangingEntity hanging, Entity remover) {
        super(hanging);
        this.remover = remover;
    }
    public Entity getRemover() { return remover; }
    @Override public HandlerList getHandlers() { return new HandlerList(); }
    public static HandlerList getHandlerList() { return new HandlerList(); }
}
