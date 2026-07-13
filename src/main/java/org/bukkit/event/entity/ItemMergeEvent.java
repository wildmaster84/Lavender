package org.bukkit.event.entity;

import org.bukkit.entity.Item;
import org.bukkit.event.HandlerList;

public class ItemMergeEvent extends EntityEvent {
    private static final org.bukkit.event.HandlerList HANDLER_LIST = new org.bukkit.event.HandlerList();
    private final Item target;
    public ItemMergeEvent(Item item, Item target) {
        super(item);
        this.target = target;
    }
    public Item getTarget() { return target; }
    @Override public HandlerList getHandlers() { return HANDLER_LIST; }
    public static HandlerList getHandlerList() { return HANDLER_LIST; }
}
