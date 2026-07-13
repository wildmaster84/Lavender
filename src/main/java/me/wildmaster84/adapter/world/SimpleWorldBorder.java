package me.wildmaster84.adapter.world;

import org.bukkit.WorldBorder;
import org.bukkit.Location;

public class SimpleWorldBorder implements WorldBorder {
    private double size = 59999968.0;
    private double damageAmount = 0.2;
    private double damageBuffer = 5.0;
    private int warningTime = 15;
    private int warningDistance = 5;
    private double centerX = 0.0;
    private double centerZ = 0.0;

    @Override public void setSize(double size) { this.size = size; }
    @Override public double getSize() { return size; }
    @Override public void setLocation(Location location) { setCenter(location); }
    @Override public Location getLocation() { return getCenter(); }
    @Override public Location getCenter() { return new Location(null, centerX, 0, centerZ); }
    @Override public void setCenter(Location location) { centerX = location.getX(); centerZ = location.getZ(); }
    @Override public void setCenter(double x, double z) { centerX = x; centerZ = z; }
    @Override public void setDamageAmount(double damage) { this.damageAmount = damage; }
    @Override public double getDamageAmount() { return damageAmount; }
    @Override public void setDamageBuffer(double buffer) { this.damageBuffer = buffer; }
    @Override public double getDamageBuffer() { return damageBuffer; }
    @Override public void setWarningTime(int time) { this.warningTime = time; }
    @Override public int getWarningTime() { return warningTime; }
    @Override public void setWarningDistance(int distance) { this.warningDistance = distance; }
    @Override public int getWarningDistance() { return warningDistance; }
    @Override public void reset() { size = 59999968.0; centerX = 0; centerZ = 0; }
    @Override public boolean isInside(Location location) { return true; }
}
