package org.bukkit.event.entity;

import org.bukkit.entity.Item;
import org.bukkit.event.HandlerList;

public class ItemDespawnEvent extends EntityEvent {
    public ItemDespawnEvent(Item item) { super(item); }
    @Override public HandlerList getHandlers() { return new HandlerList(); }
    public static HandlerList getHandlerList() { return new HandlerList(); }
}
