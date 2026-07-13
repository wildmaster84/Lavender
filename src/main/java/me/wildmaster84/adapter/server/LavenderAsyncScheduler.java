package me.wildmaster84.adapter.server;

import io.papermc.paper.threadedregions.scheduler.AsyncScheduler;
import io.papermc.paper.threadedregions.scheduler.ScheduledTask;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class LavenderAsyncScheduler implements AsyncScheduler {

private final ScheduledExecutorService asyncPool;
    
    public LavenderAsyncScheduler(ScheduledExecutorService pool) {
    	this.asyncPool = pool;
    }


    private final Map<Plugin, Set<ScheduledTaskImpl>> pluginTasks = new ConcurrentHashMap<>();

//    private ScheduledTaskImpl createTask(Plugin plugin, Consumer<ScheduledTask> task, Runnable wrapped) {
//        ScheduledTaskImpl st = new ScheduledTaskImpl(plugin);
//        pluginTasks.computeIfAbsent(plugin, k -> new CopyOnWriteArraySet<>()).add(st);
//        return st;
//    }

    @Override
    public ScheduledTask runNow(Plugin plugin, Consumer<ScheduledTask> task) {
        ScheduledTaskImpl st = new ScheduledTaskImpl(plugin);
        pluginTasks.computeIfAbsent(plugin, k -> new CopyOnWriteArraySet<>()).add(st);
        asyncPool.submit(() -> task.accept(st));
        return st;
    }

    @Override
    public ScheduledTask runDelayed(Plugin plugin, Consumer<ScheduledTask> task, long delayTicks) {
        ScheduledTaskImpl st = new ScheduledTaskImpl(plugin);
        pluginTasks.computeIfAbsent(plugin, k -> new CopyOnWriteArraySet<>()).add(st);
        long delayMs = (delayTicks <= 0) ? 0 : (delayTicks * 50);
        ScheduledFuture<?> f = asyncPool.schedule(() -> task.accept(st), delayMs, TimeUnit.MILLISECONDS);
        st.setFuture(f);
        return st;
    }

    @Override
    public ScheduledTask runDelayed(Plugin plugin, Consumer<ScheduledTask> task, long delay, TimeUnit unit) {
        ScheduledTaskImpl st = new ScheduledTaskImpl(plugin);
        pluginTasks.computeIfAbsent(plugin, k -> new CopyOnWriteArraySet<>()).add(st);
        ScheduledFuture<?> f = asyncPool.schedule(() -> task.accept(st), delay, unit);
        st.setFuture(f);
        return st;
    }

    @Override
    public ScheduledTask runAtFixedRate(Plugin plugin, Consumer<ScheduledTask> task, long initialDelayTicks, long periodTicks) {
        ScheduledTaskImpl st = new ScheduledTaskImpl(plugin);
        pluginTasks.computeIfAbsent(plugin, k -> new CopyOnWriteArraySet<>()).add(st);
        long delayMs = (initialDelayTicks <= 0) ? 0 : (initialDelayTicks * 50);
        long periodMs = Math.max(1, periodTicks) * 50;
        ScheduledFuture<?> f = asyncPool.scheduleAtFixedRate(() -> task.accept(st), delayMs, periodMs, TimeUnit.MILLISECONDS);
        st.setFuture(f);
        return st;
    }

    @Override
    public ScheduledTask runAtFixedRate(Plugin plugin, Consumer<ScheduledTask> task, long initialDelay, long period, TimeUnit unit) {
        ScheduledTaskImpl st = new ScheduledTaskImpl(plugin);
        pluginTasks.computeIfAbsent(plugin, k -> new CopyOnWriteArraySet<>()).add(st);
        ScheduledFuture<?> f = asyncPool.scheduleAtFixedRate(() -> task.accept(st), initialDelay, period, unit);
        st.setFuture(f);
        return st;
    }

    @Override
    public void cancelTasks(Plugin plugin) {
        Set<ScheduledTaskImpl> tasks = pluginTasks.remove(plugin);
        if (tasks != null) {
            for (ScheduledTaskImpl st : tasks) {
                st.cancel();
            }
        }
    }

    @Override
    public List<ScheduledTask> getActiveTasks() {
        List<ScheduledTask> result = new ArrayList<>();
        for (Set<ScheduledTaskImpl> tasks : pluginTasks.values()) {
            for (ScheduledTaskImpl st : tasks) {
                if (!st.cancelled) result.add(st);
            }
        }
        return result;
    }

    private static class ScheduledTaskImpl implements ScheduledTask {
        private final Plugin plugin;
        private volatile boolean cancelled = false;
        private ScheduledFuture<?> future;

        ScheduledTaskImpl(Plugin plugin) { this.plugin = plugin; }
        void setFuture(ScheduledFuture<?> f) { this.future = f; }

        @Override
        public CancelledState getCancelledState() {
            return cancelled ? CancelledState.CANCELLED_BY_CALLER : CancelledState.RUNNING;
        }

        @Override
        public ExecutionState getExecutionState() {
            return cancelled ? ExecutionState.CANCELLED : ExecutionState.SCHEDULED;
        }

        @Override
        public void cancel() {
            cancelled = true;
            if (future != null) future.cancel(false);
        }

        @Override
        public Object getOwningPlugin() { return plugin; }

        @Override
        public long getDelayTicks() { return 0; }

        @Override
        public long getPeriodTicks() { return 0; }
    }
}
