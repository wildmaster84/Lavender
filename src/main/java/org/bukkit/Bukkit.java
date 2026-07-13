package org.bukkit;

import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.command.ConsoleCommandSender;

import java.util.Collection;
import java.util.UUID;
import org.slf4j.Logger;

public final class Bukkit {

    private static Server server;

    private Bukkit() {}

    public static Server getServer() { return server; }
    public static void setServer(Server server) { Bukkit.server = server; }

    public static String getName() { return server.getName(); }
    public static Logger getLogger() { return server.getLogger(); }
    public static Player getPlayer(String name) { return server.getPlayer(name); }
    public static Player getPlayer(java.util.UUID id) { return server.getPlayer(id); }
    public static Collection<? extends Player> getOnlinePlayers() { return server.getOnlinePlayers(); }
    public static PluginManager getPluginManager() { return server.getPluginManager(); }
    public static BukkitScheduler getScheduler() { return server.getScheduler(); }
    public static double[] getTPS() { return server.getTPS(); }
    public static World getWorld(String name) { return server.getWorld(name); }
    public static World getWorld(UUID uid) { return server.getWorld(uid); }
    public static java.util.List<World> getWorlds() { return server.getWorlds(); }
    public static World createWorld(WorldCreator creator) { return server.createWorld(creator); }
    public static boolean dispatchCommand(org.bukkit.command.CommandSender sender, String commandLine) { return server.dispatchCommand(sender, commandLine); }
    public static ConsoleCommandSender getConsoleSender() { return server.getConsoleSender(); }
    public static String getBukkitVersion() { return server.getBukkitVersion(); }
    public static String getVersion() { return server.getVersion(); }
    public static int getPort() { return server.getPort(); }
    public static String getIp() { return server.getIp(); }
    public static boolean isPrimaryThread() { return server.isPrimaryThread(); }
    public static boolean getOnlineMode() { return server.getOnlineMode(); }
    public static java.io.File getWorldContainer() { return server.getWorldContainer(); }
    public static io.papermc.paper.threadedregions.scheduler.AsyncScheduler getAsyncScheduler() { return server.getAsyncScheduler(); }
    public static String getMinecraftVersion() { return server.getMinecraftVersion(); }
    public static org.bukkit.packs.ResourcePack getServerResourcePack() { return null; }
    public static int getSimulationDistance() { return 8; }
    public static int getViewDistance() { return 10; }
    public static org.bukkit.scoreboard.ScoreboardManager getScoreboardManager() { return server.getScoreboardManager(); }
    public static java.util.Iterator<org.bukkit.boss.BossBar> getBossBars() { return java.util.Collections.emptyIterator(); }
    public static org.bukkit.OfflinePlayer getOfflinePlayer(String name) { return server.getOfflinePlayer(name); }
    public static org.bukkit.OfflinePlayer getOfflinePlayer(java.util.UUID uuid) { return server.getOfflinePlayer(uuid); }
    public static org.bukkit.OfflinePlayer[] getOfflinePlayers() { return server.getOfflinePlayers(); }
    public static boolean isResourcePackRequired() { return false; }
    public static org.bukkit.inventory.ItemFactory getItemFactory() { return server.getItemFactory(); }
    public static org.bukkit.plugin.ServicesManager getServicesManager() { return server.getServicesManager(); }
    public static org.bukkit.UnsafeValues getUnsafe() { return server.getUnsafe(); }
    public static org.bukkit.plugin.messaging.Messenger getMessenger() { return server.getMessenger(); }

    public static org.bukkit.inventory.Inventory createInventory(org.bukkit.inventory.InventoryHolder owner, org.bukkit.inventory.InventoryType type) {
        return server.createInventory(owner, type);
    }
    public static org.bukkit.inventory.Inventory createInventory(org.bukkit.inventory.InventoryHolder owner, org.bukkit.inventory.InventoryType type, String title) {
        return server.createInventory(owner, type, title);
    }
    public static org.bukkit.inventory.Inventory createInventory(org.bukkit.inventory.InventoryHolder owner, org.bukkit.inventory.InventoryType type, net.kyori.adventure.text.Component title) {
        return server.createInventory(owner, type, title);
    }
    public static org.bukkit.inventory.Inventory createInventory(org.bukkit.inventory.InventoryHolder owner, int size) {
        return server.createInventory(owner, size);
    }
    public static org.bukkit.inventory.Inventory createInventory(org.bukkit.inventory.InventoryHolder owner, int size, String title) {
        return server.createInventory(owner, size, title);
    }
    public static org.bukkit.inventory.Inventory createInventory(org.bukkit.inventory.InventoryHolder owner, int size, net.kyori.adventure.text.Component title) {
        return server.createInventory(owner, size, title);
    }

    public static void sendActionBar(net.kyori.adventure.text.Component message) {
        server.sendActionBar(message);
    }
    public static void sendMessage(net.kyori.adventure.text.Component message) {
        server.sendMessage(message);
    }

    public static org.bukkit.WorldBorder createWorldBorder() {
        return server.createWorldBorder();
    }

    public static org.bukkit.packs.DataPackManager getDataPackManager() {
        return server.getDataPackManager();
    }

    public static boolean unloadWorld(World world) {
        return server.unloadWorld(world);
    }

    public static boolean unloadWorld(String name) {
        World w = server.getWorld(name);
        return w != null && server.unloadWorld(w);
    }
}
