package io.papermc.paper.threadedregions.scheduler;

public interface ScheduledTask {
    enum CancelledState {
        CANCELLED_BY_CALLER, CANCELLED_BY_PLUGIN_DISABLE, CANCELLED_BY_SERVER_SHUTDOWN, RUNNING, FINISHED
    }
    enum ExecutionState {
        IDLE, SCHEDULED, EXECUTING, FINISHED, CANCELLED
    }
    ScheduledTask.CancelledState getCancelledState();
    ScheduledTask.ExecutionState getExecutionState();
    void cancel();
    Object getOwningPlugin();
    long getDelayTicks();
    long getPeriodTicks();
}
