package me.wildmaster84.lavender.plugin;

import net.minestom.server.MinecraftServer;
import net.minestom.server.timer.Task.Builder;
import net.minestom.server.timer.TaskSchedule;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class LavenderScheduler implements BukkitScheduler {
    private final Map<Integer, Object> tasks = new ConcurrentHashMap<>();
    private final Map<Integer, Plugin> taskOwners = new ConcurrentHashMap<>();
    private final AtomicInteger nextId = new AtomicInteger(0);

    private final ScheduledExecutorService timer;
    private final ExecutorService executor;

    public LavenderScheduler(ScheduledExecutorService timer, ExecutorService executor) {
        this.timer = timer;
        this.executor = executor;
    }

    @Override
    public BukkitTask runTask(Plugin plugin, Runnable task) {
        return scheduleSync(plugin, task, 0, -1);
    }

    @Override
    public BukkitTask runTaskAsynchronously(Plugin plugin, Runnable task) {
        return scheduleAsync(plugin, task, 0, -1);
    }

    @Override
    public BukkitTask runTaskLater(Plugin plugin, Runnable task, long delayTicks) {
        return scheduleSync(plugin, task, delayTicks, -1);
    }

    @Override
    public BukkitTask runTaskLaterAsynchronously(Plugin plugin, Runnable task, long delayTicks) {
        return scheduleAsync(plugin, task, delayTicks, -1);
    }

    @Override
    public BukkitTask runTaskTimer(Plugin plugin, Runnable task, long delayTicks, long periodTicks) {
        return scheduleSync(plugin, task, delayTicks, periodTicks);
    }

    @Override
    public BukkitTask runTaskTimerAsynchronously(Plugin plugin, Runnable task, long delayTicks, long periodTicks) {
        return scheduleAsync(plugin, task, delayTicks, periodTicks);
    }

    private BukkitTask scheduleSync(Plugin plugin, Runnable task, long delayTicks, long periodTicks) {
        int id = nextId.incrementAndGet();
        Runnable wrappedTask;
        if (periodTicks > 0) {
            wrappedTask = task;
        } else {
            wrappedTask = () -> {
                try { task.run(); }
                finally { tasks.remove(id); taskOwners.remove(id); }
            };
        }

        Builder builder = MinecraftServer.getSchedulerManager().buildTask(wrappedTask);
        TaskSchedule delay = delayTicks <= 0 ? TaskSchedule.immediate() : TaskSchedule.tick((int) delayTicks);

        net.minestom.server.timer.Task minestomTask;
        if (periodTicks > 0) {
            TaskSchedule repeat = TaskSchedule.tick((int) Math.max(1, periodTicks));
            minestomTask = builder.delay(delay).repeat(repeat).schedule();
        } else {
            minestomTask = builder.delay(delay).schedule();
        }

        tasks.put(id, minestomTask);
        taskOwners.put(id, plugin);
        return new LavenderTask(id, plugin, minestomTask);
    }

    private BukkitTask scheduleAsync(Plugin plugin, Runnable task, long delayTicks, long periodTicks) {
        int id = nextId.incrementAndGet();
        long delayMs = (delayTicks <= 0) ? 0 : (delayTicks * 50);
        long periodMs = (periodTicks > 0) ? (Math.max(1, periodTicks) * 50) : -1;

        Runnable wrappedTask;
        if (periodTicks > 0) {
            wrappedTask = () -> {
                try { task.run(); }
                catch (Throwable e) { org.slf4j.LoggerFactory.getLogger("LavenderScheduler").error("Async task error", e); }
            };
        } else {
            wrappedTask = () -> {
                try { task.run(); }
                catch (Throwable e) { org.slf4j.LoggerFactory.getLogger("LavenderScheduler").error("Async task error", e); }
                finally { tasks.remove(id); taskOwners.remove(id); }
            };
        }

        Runnable dispatchToVirtual = () -> executor.submit(wrappedTask);

        ScheduledFuture<?> future;
        if (periodMs > 0) {
            future = timer.scheduleAtFixedRate(dispatchToVirtual, delayMs, periodMs, TimeUnit.MILLISECONDS);
        } else {
            future = timer.schedule(dispatchToVirtual, delayMs, TimeUnit.MILLISECONDS);
        }

        tasks.put(id, future);
        taskOwners.put(id, plugin);
        return new LavenderTask(id, plugin, future);
    }

    @Override
    public void cancelTasks(Plugin plugin) {
        tasks.entrySet().removeIf(entry -> {
            if (taskOwners.get(entry.getKey()) == plugin) {
                cancelTask(entry.getKey(), entry.getValue());
                taskOwners.remove(entry.getKey());
                return true;
            }
            return false;
        });
    }

    @Override
    public void cancelAllTasks() {
        for (Map.Entry<Integer, Object> entry : tasks.entrySet()) {
            cancelTask(entry.getKey(), entry.getValue());
        }
        tasks.clear();
        taskOwners.clear();
    }

    @SuppressWarnings("unchecked")
    private void cancelTask(int id, Object task) {
        if (task instanceof net.minestom.server.timer.Task mt) {
            mt.cancel();
        } else if (task instanceof ScheduledFuture<?> sf) {
            sf.cancel(false);
        }
    }

    @Override
    public boolean isCurrentlyRunning(int taskId) {
        return tasks.containsKey(taskId);
    }

    private static class LavenderTask implements BukkitTask {
        private final int taskId;
        private final Plugin owner;
        private volatile boolean cancelled = false;
        private final Object task;

        LavenderTask(int taskId, Plugin owner, Object task) {
            this.taskId = taskId;
            this.owner = owner;
            this.task = task;
        }

        @Override
        public int getTaskId() { return taskId; }

        @Override
        public void cancel() {
            cancelled = true;
            if (task instanceof net.minestom.server.timer.Task mt) {
                mt.cancel();
            } else if (task instanceof ScheduledFuture<?> sf) {
                sf.cancel(false);
            }
        }

        @Override
        public boolean isCancelled() { return cancelled; }

        @Override
        public Plugin getOwner() { return owner; }
    }
}
