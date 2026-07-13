package org.bukkit.map;

import org.bukkit.World;

import java.util.List;

public interface MapView {

    int getId();
    boolean isVirtual();
    World getWorld();
    void setWorld(World world);
    int getCenterX();
    int getCenterZ();
    void setCenterX(int x);
    void setCenterZ(int z);
    int getScale();
    void setScale(int scale);
    boolean isUnlimitedTracking();
    void setUnlimitedTracking(boolean unlimited);

    void addRenderer(MapRenderer renderer);
    void removeRenderer(MapRenderer renderer);
    List<MapRenderer> getRenderers();

    enum Scale {
        CLOSEST(0),
        CLOSE(1),
        NORMAL(2),
        FAR(3),
        FARTHEST(4);

        private final int value;
        Scale(int value) { this.value = value; }
        public int getValue() { return value; }
    }
}
