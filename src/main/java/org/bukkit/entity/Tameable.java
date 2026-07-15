package org.bukkit.entity;

public interface Tameable extends Entity {
    boolean isTamed();
    void setTamed(boolean tamed);
    AnimalTamer getOwner();
    void setOwner(AnimalTamer owner);
}
