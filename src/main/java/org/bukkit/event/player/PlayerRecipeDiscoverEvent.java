package org.bukkit.event.player;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

public class PlayerRecipeDiscoverEvent extends PlayerEvent {
    private static final HandlerList handlers = new HandlerList();
    private final NamespacedKey recipe;

    public PlayerRecipeDiscoverEvent(Player player, NamespacedKey recipe) {
        super(player);
        this.recipe = recipe;
    }

    public NamespacedKey getRecipe() { return recipe; }

    @Override
    public HandlerList getHandlers() { return handlers; }
    public static HandlerList getHandlerList() { return handlers; }
}
