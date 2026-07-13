package org.bukkit.event.inventory;

import org.bukkit.inventory.Inventory;

public class BrewingStandFuelEvent extends InventoryEvent {
    private static final org.bukkit.event.HandlerList HANDLER_LIST = new org.bukkit.event.HandlerList();
    private final int fuelPower;
    private final int brewingTime;
    public BrewingStandFuelEvent(Inventory inventory, int fuelPower, int brewingTime) {
        super(inventory);
        this.fuelPower = fuelPower;
        this.brewingTime = brewingTime;
    }
    public int getFuelPower() { return fuelPower; }
    public int getBrewingTime() { return brewingTime; }
    @Override public org.bukkit.event.HandlerList getHandlers() { return HANDLER_LIST; }
    public static org.bukkit.event.HandlerList getHandlerList() { return HANDLER_LIST; }
}
