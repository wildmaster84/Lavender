package net.minecraft.server;

import me.wildmaster84.adapter.registry.LavenderRegistryAccess;
import net.minecraft.core.RegistryAccess;
import net.minecraft.server.network.ServerConnection;

public class MinecraftServer {
    public java.util.concurrent.Executor executor;
    private LavenderRegistryAccess registryAccess;
    private static final MinecraftServer INSTANCE = new MinecraftServer();
    private final ServerConnection connection = new ServerConnection();

    public static MinecraftServer getServer() { return INSTANCE; }

    public ServerConnection getConnection() { return connection; }

    public RegistryAccess.Frozen registryAccess() {
        if (registryAccess == null) registryAccess = new LavenderRegistryAccess();
        return registryAccess;
    }

    public com.mojang.datafixers.DataFixer getFixerUpper() {
        return net.minecraft.util.datafix.DataFixers.getDataFixer();
    }
}
