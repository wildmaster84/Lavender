package net.minecraft.server.dedicated;

import me.wildmaster84.adapter.registry.LavenderRegistryAccess;
import net.minecraft.core.RegistryAccess;
import net.minecraft.server.MinecraftServer;

public class DedicatedServer extends MinecraftServer {
    private static DedicatedServer instance;
    private static LavenderRegistryAccess registryAccess;

    public static MinecraftServer getServer() {
        return instance;
    }

    public static void setInstance(DedicatedServer server) {
        instance = server;
    }

    public static void initRegistryAccess() {
        registryAccess = new LavenderRegistryAccess();
    }

    @Override
    public RegistryAccess.Frozen registryAccess() {
        if (registryAccess == null) registryAccess = new LavenderRegistryAccess();
        return registryAccess;
    }
}
