package org.bukkit.event.vehicle;

import org.bukkit.entity.Vehicle;

public class VehicleMoveEvent extends VehicleEvent {
    public VehicleMoveEvent(Vehicle vehicle) { super(vehicle); }
    @Override public org.bukkit.event.HandlerList getHandlers() { return new org.bukkit.event.HandlerList(); }
    public static org.bukkit.event.HandlerList getHandlerList() { return new org.bukkit.event.HandlerList(); }
}