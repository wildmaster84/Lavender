package org.bukkit.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.event.HandlerList;

public class PigZombieAngerEvent extends EntityEvent {
    private final Entity target;
    private final int oldAnger;
    private final int newAnger;
    public PigZombieAngerEvent(Entity pigZombie, Entity target, int oldAnger, int newAnger) {
        super(pigZombie);
        this.target = target;
        this.oldAnger = oldAnger;
        this.newAnger = newAnger;
    }
    public Entity getEntity() { return entity; }
    public Entity getTarget() { return target; }
    public int getOldAnger() { return oldAnger; }
    public int getNewAnger() { return newAnger; }
    public void setNewAnger(int newAnger) { }
    @Override public HandlerList getHandlers() { return new HandlerList(); }
    public static HandlerList getHandlerList() { return new HandlerList(); }
}
