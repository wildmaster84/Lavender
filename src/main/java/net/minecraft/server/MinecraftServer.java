package net.minecraft.server;

import me.wildmaster84.adapter.registry.LavenderRegistryAccess;
import net.minecraft.core.RegistryAccess;

public class MinecraftServer {
    public java.util.concurrent.Executor executor;
    private LavenderRegistryAccess registryAccess;

    public RegistryAccess.Frozen registryAccess() {
        if (registryAccess == null) registryAccess = new LavenderRegistryAccess();
        return registryAccess;
    }

    public com.mojang.datafixers.DataFixer getFixerUpper() {
        return null;
    }
}
