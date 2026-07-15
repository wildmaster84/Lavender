package net.minecraft.util.thread;

import java.util.concurrent.Executor;

public interface BlockableEventLoop extends Executor {
    void executeIfPossible(Runnable runnable);
}
