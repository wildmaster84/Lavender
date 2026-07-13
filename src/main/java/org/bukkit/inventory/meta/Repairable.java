package org.bukkit.inventory.meta;

public interface Repairable extends ItemMeta {
    int getRepairCost();
    void setRepairCost(int cost);
    boolean hasRepairCost();
}
