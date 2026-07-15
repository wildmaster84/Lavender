package org.bukkit;

import java.util.Set;

public interface Tag<T extends Keyed> {
    NamespacedKey getKey();
    boolean isTagged(T item);
    Set<T> getValues();
    String getName();
}
