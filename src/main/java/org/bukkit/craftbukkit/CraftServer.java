package org.bukkit.craftbukkit;

import java.util.Map;
import org.bukkit.World;

public class CraftServer {
    public Map<String, World> worlds;

    public net.minecraft.server.dedicated.DedicatedServer getServer() {
        return (net.minecraft.server.dedicated.DedicatedServer) net.minecraft.server.dedicated.DedicatedServer.getServer();
    }
}
