package io.papermc.paper.event.player;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

public class PlayerNameEntityEvent extends org.bukkit.event.player.PlayerEvent {
    private final Entity entity;
    private String name;
    public PlayerNameEntityEvent(Player player, Entity entity, String name) {
        super(player);
        this.entity = entity;
        this.name = name;
    }
    public Entity getEntity() { return entity; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    @Override public HandlerList getHandlers() { return new HandlerList(); }
    public static HandlerList getHandlerList() { return new HandlerList(); }
}
