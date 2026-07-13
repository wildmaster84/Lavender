package org.bukkit.event.vehicle;

import org.bukkit.entity.Vehicle;

public class VehicleDestroyEvent extends VehicleEvent {
    private static final org.bukkit.event.HandlerList HANDLER_LIST = new org.bukkit.event.HandlerList();
    public VehicleDestroyEvent(Vehicle vehicle) { super(vehicle); }
    @Override public org.bukkit.event.HandlerList getHandlers() { return HANDLER_LIST; }
    public static org.bukkit.event.HandlerList getHandlerList() { return HANDLER_LIST; }
}