package org.bukkit.entity;

import org.bukkit.inventory.ItemStack;
import java.util.UUID;

public interface Item extends Entity {
    ItemStack getItemStack();
    void setItemStack(ItemStack stack);
    int getPickupDelay();
    void setPickupDelay(int delay);
    void setPickupDelay(int delay, boolean infinite);
    boolean willPickupAfterDelay();
    void setThrower(UUID thrower);
    UUID getThrower();
    void setOwner(UUID owner);
    UUID getOwner();
}
