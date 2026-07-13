package org.bukkit.event.vehicle;

import org.bukkit.entity.Vehicle;

public class VehicleEntityCollisionEvent extends VehicleEvent {
    public VehicleEntityCollisionEvent(Vehicle vehicle) { super(vehicle); }
    @Override public org.bukkit.event.HandlerList getHandlers() { return new org.bukkit.event.HandlerList(); }
    public static org.bukkit.event.HandlerList getHandlerList() { return new org.bukkit.event.HandlerList(); }
}