package org.bukkit.scheduler;

import org.bukkit.plugin.Plugin;

public interface BukkitTask {
    int getTaskId();
    void cancel();
    boolean isCancelled();
    Plugin getOwner();
}
