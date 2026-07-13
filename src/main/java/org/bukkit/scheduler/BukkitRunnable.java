package org.bukkit.scheduler;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public abstract class BukkitRunnable implements Runnable {

    private BukkitTask task;

    public abstract void run();

    public synchronized BukkitTask runTask(Plugin plugin) {
        task = Bukkit.getScheduler().runTask(plugin, this);
        return task;
    }

    public synchronized BukkitTask runTaskAsynchronously(Plugin plugin) {
        task = Bukkit.getScheduler().runTaskAsynchronously(plugin, this);
        return task;
    }

    public synchronized BukkitTask runTaskLater(Plugin plugin, long delay) {
        task = Bukkit.getScheduler().runTaskLater(plugin, this, delay);
        return task;
    }

    public synchronized BukkitTask runTaskLaterAsynchronously(Plugin plugin, long delay) {
        task = Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, this, delay);
        return task;
    }

    public synchronized BukkitTask runTaskTimer(Plugin plugin, long delay, long period) {
        task = Bukkit.getScheduler().runTaskTimer(plugin, this, delay, period);
        return task;
    }

    public synchronized BukkitTask runTaskTimerAsynchronously(Plugin plugin, long delay, long period) {
        task = Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, this, delay, period);
        return task;
    }

    public synchronized boolean isCancelled() {
        return task != null && task.isCancelled();
    }

    public synchronized void cancel() {
        if (task != null) task.cancel();
        task = null;
    }

    public synchronized BukkitTask getTask() {
        return task;
    }
}
