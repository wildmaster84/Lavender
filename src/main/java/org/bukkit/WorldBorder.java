package org.bukkit;

public interface WorldBorder {
    void setSize(double size);
    double getSize();
    void setLocation(org.bukkit.Location location);
    org.bukkit.Location getLocation();
    org.bukkit.Location getCenter();
    void setCenter(org.bukkit.Location location);
    void setCenter(double x, double z);
    void setDamageAmount(double damage);
    double getDamageAmount();
    void setDamageBuffer(double buffer);
    double getDamageBuffer();
    void setWarningTime(int time);
    int getWarningTime();
    void setWarningDistance(int distance);
    int getWarningDistance();
    void reset();
    boolean isInside(org.bukkit.Location location);
}
