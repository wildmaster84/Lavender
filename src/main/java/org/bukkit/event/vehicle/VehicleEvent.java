package org.bukkit.event.vehicle;

import org.bukkit.entity.Vehicle;
import org.bukkit.event.Event;

public abstract class VehicleEvent extends Event {
    protected Vehicle vehicle;
    public VehicleEvent(Vehicle vehicle) { this.vehicle = vehicle; }
    public Vehicle getVehicle() { return vehicle; }
}