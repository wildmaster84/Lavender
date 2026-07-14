package org.bukkit;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.scheduler.BukkitScheduler;
import org.slf4j.Logger;

import me.wildmaster84.lavender.event.EventManager;

import java.util.Collection;
import java.util.UUID;

public interface Server {

    String getName();
    void setName(String name);

    Player getPlayer(String name);
    Player getPlayer(UUID id);
    Collection<? extends Player> getOnlinePlayers();

    Logger getLogger();

    EventManager getEventManager();

    double[] getTPS();

    PluginManager getPluginManager();
    BukkitScheduler getScheduler();
    org.bukkit.command.CommandMap getCommandMap();
    org.bukkit.help.HelpMap getHelpMap();

    World getWorld(String name);
    World getWorld(UUID uid);
    java.util.List<World> getWorlds();
    World createWorld(WorldCreator creator);

    boolean dispatchCommand(CommandSender sender, String commandLine);

    java.util.Map<String, String[]> getCommandAliases();

    int getPort();
    String getIp();
    String getServerName();
    String getMotd();
    int getMaxPlayers();
    String getVersion();
    String getBukkitVersion();

    boolean isPrimaryThread();
    boolean getOnlineMode();

    long getWorldTickTime();

    void reload();
    void shutdown();

    org.bukkit.command.ConsoleCommandSender getConsoleSender();

    org.bukkit.OfflinePlayer getOfflinePlayer(String name);
    org.bukkit.OfflinePlayer getOfflinePlayer(java.util.UUID uuid);
    org.bukkit.OfflinePlayer[] getOfflinePlayers();

    java.io.File getWorldContainer();

    //io.papermc.paper.threadedregions.scheduler.AsyncScheduler getAsyncScheduler();

    String getMinecraftVersion();

    org.bukkit.packs.ResourcePack getServerResourcePack();
    int getSimulationDistance();
    int getViewDistance();
    org.bukkit.scoreboard.ScoreboardManager getScoreboardManager();
    java.util.Iterator<org.bukkit.boss.BossBar> getBossBars();
    boolean isResourcePackRequired();
    org.bukkit.inventory.ItemFactory getItemFactory();

    org.bukkit.plugin.ServicesManager getServicesManager();
    org.bukkit.UnsafeValues getUnsafe();
    org.bukkit.plugin.messaging.Messenger getMessenger();

    org.bukkit.inventory.Inventory createInventory(org.bukkit.inventory.InventoryHolder owner, org.bukkit.inventory.InventoryType type);
    org.bukkit.inventory.Inventory createInventory(org.bukkit.inventory.InventoryHolder owner, org.bukkit.inventory.InventoryType type, String title);
    org.bukkit.inventory.Inventory createInventory(org.bukkit.inventory.InventoryHolder owner, org.bukkit.inventory.InventoryType type, net.kyori.adventure.text.Component title);
    org.bukkit.inventory.Inventory createInventory(org.bukkit.inventory.InventoryHolder owner, int size);
    org.bukkit.inventory.Inventory createInventory(org.bukkit.inventory.InventoryHolder owner, int size, String title);
    org.bukkit.inventory.Inventory createInventory(org.bukkit.inventory.InventoryHolder owner, int size, net.kyori.adventure.text.Component title);

    void sendActionBar(net.kyori.adventure.text.Component message);
    void sendMessage(net.kyori.adventure.text.Component message);
    void sendMessage(java.lang.Iterable<? extends net.kyori.adventure.text.Component> messages);

    org.bukkit.WorldBorder createWorldBorder();

    org.bukkit.packs.DataPackManager getDataPackManager();

    boolean unloadWorld(World world);
}
