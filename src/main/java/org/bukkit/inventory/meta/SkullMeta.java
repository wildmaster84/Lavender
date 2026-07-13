package org.bukkit.inventory.meta;

import org.bukkit.OfflinePlayer;

public interface SkullMeta extends ItemMeta {
    String getOwner();
    boolean setOwner(String owner);
    OfflinePlayer getOwningPlayer();
    boolean setOwningPlayer(OfflinePlayer player);
    boolean hasOwner();
}
