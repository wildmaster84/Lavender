package org.bukkit.event.entity;

import org.bukkit.entity.Item;
import org.bukkit.event.HandlerList;

public class ItemMergeEvent extends EntityEvent {
    private final Item target;
    public ItemMergeEvent(Item item, Item target) {
        super(item);
        this.target = target;
    }
    public Item getTarget() { return target; }
    @Override public HandlerList getHandlers() { return new HandlerList(); }
    public static HandlerList getHandlerList() { return new HandlerList(); }
}
