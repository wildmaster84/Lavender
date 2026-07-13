package io.papermc.paper.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PufferFishStateChangeEvent extends Event {
    private static final org.bukkit.event.HandlerList HANDLER_LIST = new org.bukkit.event.HandlerList();
    private final Entity entity;
    private final int newPuffState;
    private final int oldPuffState;
    public PufferFishStateChangeEvent(Entity entity, int oldPuffState, int newPuffState) {
        this.entity = entity;
        this.oldPuffState = oldPuffState;
        this.newPuffState = newPuffState;
    }
    public Entity getEntity() { return entity; }
    public int getNewPuffState() { return newPuffState; }
    public int getOldPuffState() { return oldPuffState; }
    @Override public HandlerList getHandlers() { return HANDLER_LIST; }
    public static HandlerList getHandlerList() { return HANDLER_LIST; }
}
