package org.bukkit.event.entity;

import org.bukkit.entity.Item;
import org.bukkit.event.HandlerList;

public class ItemDespawnEvent extends EntityEvent {
    private static final org.bukkit.event.HandlerList HANDLER_LIST = new org.bukkit.event.HandlerList();
    public ItemDespawnEvent(Item item) { super(item); }
    @Override public HandlerList getHandlers() { return HANDLER_LIST; }
    public static HandlerList getHandlerList() { return HANDLER_LIST; }
}
