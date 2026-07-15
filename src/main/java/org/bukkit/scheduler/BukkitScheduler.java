package org.bukkit.scheduler;

import org.bukkit.plugin.Plugin;

public interface BukkitScheduler {
    BukkitTask runTask(Plugin plugin, Runnable task);
    BukkitTask runTaskAsynchronously(Plugin plugin, Runnable task);
    BukkitTask runTaskLater(Plugin plugin, Runnable task, long delayTicks);
    BukkitTask runTaskLaterAsynchronously(Plugin plugin, Runnable task, long delayTicks);
    BukkitTask runTaskTimer(Plugin plugin, Runnable task, long delayTicks, long periodTicks);
    BukkitTask runTaskTimerAsynchronously(Plugin plugin, Runnable task, long delayTicks, long periodTicks);
    int scheduleSyncDelayedTask(Plugin plugin, Runnable task);
    int scheduleSyncDelayedTask(Plugin plugin, Runnable task, long delayTicks);
    int scheduleSyncRepeatingTask(Plugin plugin, Runnable task, long delayTicks, long periodTicks);
    int scheduleAsyncDelayedTask(Plugin plugin, Runnable task);
    int scheduleAsyncDelayedTask(Plugin plugin, Runnable task, long delayTicks);
    int scheduleAsyncRepeatingTask(Plugin plugin, Runnable task, long delayTicks, long periodTicks);
    void cancelTask(int taskId);
    void cancelTasks(Plugin plugin);
    void cancelAllTasks();
    boolean isCurrentlyRunning(int taskId);
    boolean isQueued(int taskId);
}
