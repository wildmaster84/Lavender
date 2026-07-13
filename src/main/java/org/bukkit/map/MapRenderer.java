package org.bukkit.map;

import org.bukkit.entity.Player;

public abstract class MapRenderer {

    private final boolean contextual;

    public MapRenderer() {
        this(false);
    }

    public MapRenderer(boolean contextual) {
        this.contextual = contextual;
    }

    public final boolean isContextual() {
        return contextual;
    }

    public void initialize(MapView map) {}

    public abstract void render(MapView map, MapCanvas canvas, Player player);
}
