package org.bukkit.event.entity;

import org.bukkit.entity.ThrownExpBottle;
import org.bukkit.event.HandlerList;

public class ExpBottleEvent extends ProjectileHitEvent {
    private static final HandlerList HANDLER_LIST = new HandlerList();
    private int exp;
    private boolean showEffect = true;

    public ExpBottleEvent(ThrownExpBottle bottle, int exp) {
        super(bottle);
        this.exp = exp;
    }

    public ThrownExpBottle getEntity() { return (ThrownExpBottle) entity; }
    public int getExp() { return exp; }
    public void setExp(int exp) { this.exp = exp; }
    public boolean getShowEffect() { return showEffect; }
    public void setShowEffect(boolean show) { this.showEffect = show; }
    @Override public HandlerList getHandlers() { return HANDLER_LIST; }
    public static HandlerList getHandlerList() { return HANDLER_LIST; }
}
