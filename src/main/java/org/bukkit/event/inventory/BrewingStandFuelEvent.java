package org.bukkit.event.inventory;

import org.bukkit.inventory.Inventory;

public class BrewingStandFuelEvent extends InventoryEvent {
    private final int fuelPower;
    private final int brewingTime;
    public BrewingStandFuelEvent(Inventory inventory, int fuelPower, int brewingTime) {
        super(inventory);
        this.fuelPower = fuelPower;
        this.brewingTime = brewingTime;
    }
    public int getFuelPower() { return fuelPower; }
    public int getBrewingTime() { return brewingTime; }
    @Override public org.bukkit.event.HandlerList getHandlers() { return new org.bukkit.event.HandlerList(); }
    public static org.bukkit.event.HandlerList getHandlerList() { return new org.bukkit.event.HandlerList(); }
}
