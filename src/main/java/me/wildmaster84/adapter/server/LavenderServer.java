package me.wildmaster84.adapter.server;

import me.wildmaster84.adapter.inventory.LavenderInventory;
import me.wildmaster84.adapter.inventory.SimpleItemFactory;
import me.wildmaster84.adapter.player.LavenderConsoleSender;
import me.wildmaster84.adapter.player.LavenderPlayer;
import me.wildmaster84.adapter.player.SimpleOfflinePlayer;
import me.wildmaster84.adapter.world.LavenderWorld;
import me.wildmaster84.adapter.world.SimpleBlockData;
import me.wildmaster84.adapter.world.SimpleChunkData;
import me.wildmaster84.adapter.world.SimpleWorldBorder;
import me.wildmaster84.adapter.world.SimpleWorldInfo;
import me.wildmaster84.adapter.Lavender;
import me.wildmaster84.lavender.event.EventManager;
import me.wildmaster84.lavender.plugin.LavenderPluginManager;
import me.wildmaster84.lavender.plugin.LavenderScheduler;
import net.minestom.server.MinecraftServer;
import net.minestom.server.command.CommandManager;
import net.minestom.server.instance.Instance;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.instance.anvil.AnvilLoader;
import net.minestom.server.instance.generator.Generator;
import net.minestom.server.registry.RegistryKey;
import net.minestom.server.world.DimensionType;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.scheduler.BukkitScheduler;

import org.slf4j.Logger;

import java.io.File;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class LavenderServer extends org.bukkit.craftbukkit.CraftServer implements Server, CraftServer {

    private final Lavender lavender;
    private LavenderPluginManager pluginManager;
    private LavenderScheduler scheduler;
    private EventManager eventManager;
    private LavenderConsoleSender consoleSender;
    private me.wildmaster84.lavender.plugin.SimpleServicesManager servicesManager;
    private org.bukkit.plugin.messaging.Messenger messenger;
    public org.bukkit.command.CommandMap commandMap;
    private final org.bukkit.help.SimpleHelpMap helpMap = new org.bukkit.help.SimpleHelpMap();

    private final Map<UUID, LavenderWorld> worldsByUUID = new ConcurrentHashMap<>();

    public LavenderServer(Lavender lavender) {
        this.lavender = lavender;
        this.worlds = new ConcurrentHashMap<>();
        this.scheduler = new LavenderScheduler(lavender.getTimer(), lavender.getVirtualExecutor());
        this.eventManager = new EventManager();
        this.commandMap = new org.bukkit.command.SimpleCommandMap();
        this.pluginManager = new LavenderPluginManager(this, new File("plugins"));
        this.pluginManager.commandMap = this.commandMap;
        this.consoleSender = new LavenderConsoleSender(this);
        this.servicesManager = new me.wildmaster84.lavender.plugin.SimpleServicesManager();
        this.messenger = new org.bukkit.plugin.messaging.StandardMessenger();
    }

    @Override public String getName() { return Lavender.BRAND_NAME; }
    @Override public void setName(String name) { MinecraftServer.setBrandName(name); }
    public org.bukkit.command.SimpleCommandMap getCommandMap() { return (org.bukkit.command.SimpleCommandMap) commandMap; }

    @Override
    public org.bukkit.help.HelpMap getHelpMap() { return helpMap; }

    @Override public Logger getLogger() { return MinecraftServer.LOGGER; }

    @Override
    public Player getPlayer(String name) {
        net.minestom.server.entity.Player player = MinecraftServer.getConnectionManager().getOnlinePlayerByUsername(name);
        return player == null ? null : LavenderPlayer.wrap(player, this);
    }

    @Override
    public Player getPlayer(UUID id) {
        net.minestom.server.entity.Player player = MinecraftServer.getConnectionManager().getOnlinePlayerByUuid(id);
        return player == null ? null : LavenderPlayer.wrap(player, this);
    }

    @Override
    public Collection<? extends Player> getOnlinePlayers() {
        return MinecraftServer.getConnectionManager().getOnlinePlayers()
                .stream().map(p -> LavenderPlayer.wrap(p, this)).collect(Collectors.toList());
    }

    @Override public EventManager getEventManager() { return eventManager; }
    @Override public double[] getTPS() { return new double[3]; } // TODO: implement server tps
    @Override public PluginManager getPluginManager() { return pluginManager; }
    @Override public BukkitScheduler getScheduler() { return scheduler; }

    @Override
    public org.bukkit.World getWorld(String name) {
        return worlds.get(name.toLowerCase());
    }

    public InstanceContainer getInstanceContainer() {
        return lavender.getWorld();
    }

    public Generator getGenerator() {
        return lavender.getGenerator();
    }

    public me.wildmaster84.lavender.util.ServerProperties getProperties() {
        return lavender.getProperties();
    }

    public String getGamemode() {
        return lavender.getProperties().getGamemode();
    }

    public void registerWorld(LavenderWorld world) {
        worlds.put(world.getName().toLowerCase(), world);
        worldsByUUID.put(world.getUID(), world);
    }

    public LavenderWorld getWorld(Instance instance) {
        if (instance == null) return null;
        UUID uuid = instance.getUuid();
        if (uuid == null) return null;
        return worldsByUUID.get(uuid);
    }

    @Override
    public org.bukkit.World getWorld(UUID uid) {
        if (uid == null) return null;
        return worldsByUUID.get(uid);
    }

    @Override
    public java.util.List<org.bukkit.World> getWorlds() {
        return new ArrayList<>(worlds.values());
    }

    @Override
    public World createWorld(WorldCreator creator) {
        String name = creator.name();
        if (worlds.containsKey(name.toLowerCase())) {
            return worlds.get(name.toLowerCase());
        }

        net.minestom.server.registry.DynamicRegistry<DimensionType> dimRegistry = MinecraftServer.getDimensionTypeRegistry();
        RegistryKey<DimensionType> dimType;
        if (creator.environment() == World.Environment.NETHER) {
            dimType = dimRegistry.getKey(net.kyori.adventure.key.Key.key("minecraft", "the_nether"));
        } else if (creator.environment() == World.Environment.THE_END) {
            dimType = dimRegistry.getKey(net.kyori.adventure.key.Key.key("minecraft", "the_end"));
        } else {
            dimType = dimRegistry.getKey(net.kyori.adventure.key.Key.key("minecraft", "overworld"));
        }

        File worldDir = new File("world");
        if (!worldDir.exists()) {
            worldDir.mkdirs();
        }
        String dimName = switch (creator.environment()) {
            case NETHER -> "the_nether";
            case THE_END -> "the_end";
            default -> name;
        };
        AnvilLoader loader = new AnvilLoader(worldDir.toPath(), net.kyori.adventure.key.Key.key("minecraft", dimName));

        InstanceContainer instance = MinecraftServer.getInstanceManager().createInstanceContainer(dimType, loader);
        instance.setChunkSupplier(net.minestom.server.instance.LightingChunk::new);

        try {
            if (creator.generator() != null) {
                instance.setGenerator(wrapChunkGenerator(instance, creator.generator(), creator));
            } else {
                rocks.minestom.worldgen.WorldGenerators worldGens = new rocks.minestom.worldgen.WorldGenerators(
                    new java.io.File(".").toPath(), creator.seed());
                Generator dimGen = switch (creator.environment()) {
                    case NETHER -> worldGens.nether();
                    case THE_END -> worldGens.end();
                    default -> worldGens.overworld();
                };
                instance.setGenerator(unit -> {
                    try { dimGen.generate(unit); }
                    catch (Throwable e) { MinecraftServer.LOGGER.warn("World generation error in {}: {}", name, e.getMessage()); }
                });
                MinecraftServer.LOGGER.info("Vanilla worldgen enabled for {}", name);
            }
        } catch (Exception e) {
            MinecraftServer.LOGGER.warn("Could not set generator for world " + name + ": " + e.getMessage());
        }

        instance.enableAutoChunkLoad(true);

        LavenderWorld world = new LavenderWorld(instance, this, name, creator.seed(), creator.environment());
        registerWorld(world);
        MinecraftServer.LOGGER.info("Created world: " + name + " (seed=" + creator.seed() + ", env=" + creator.environment() + ")");

        org.bukkit.Bukkit.getPluginManager().callEvent(new org.bukkit.event.world.WorldLoadEvent(world));

        return world;
    }

    private Generator wrapChunkGenerator(InstanceContainer instance, org.bukkit.generator.ChunkGenerator bukkitGen, WorldCreator creator) {
        return unit -> {
            int chunkX = unit.absoluteStart().blockX() / 16;
            int chunkZ = unit.absoluteStart().blockZ() / 16;
            java.util.Random random = new java.util.Random(creator.seed() * chunkX * 31 + chunkZ);

            SimpleWorldInfo worldInfo = new SimpleWorldInfo(
                creator.name(), creator.seed(), creator.environment(), -64, 320);
            SimpleChunkData chunkData = new SimpleChunkData(-64, 320);

            org.bukkit.generator.ChunkGenerator.BiomeGrid biomeGrid = new org.bukkit.generator.ChunkGenerator.BiomeGrid() {
                @Override public org.bukkit.block.Biome getBiome(int x, int z) { return org.bukkit.block.Biome.PLAINS; }
                @Override public void setBiome(int x, int z, org.bukkit.block.Biome biome) {}
                @Override public org.bukkit.block.Biome getBiome(int x, int y, int z) { return org.bukkit.block.Biome.PLAINS; }
                @Override public void setBiome(int x, int y, int z, org.bukkit.block.Biome biome) {}
            };

            try {
                bukkitGen.generateNoise(worldInfo, random, chunkX, chunkZ, chunkData);
                bukkitGen.generateSurface(worldInfo, random, chunkX, chunkZ, chunkData);
                bukkitGen.generateCaves(worldInfo, random, chunkX, chunkZ, chunkData);
                bukkitGen.generateBedrock(worldInfo, random, chunkX, chunkZ, chunkData);
                bukkitGen.generateChunkData(null, random, chunkX, chunkZ, biomeGrid);
                chunkData.applyToUnit(unit, chunkX, chunkZ);
            } catch (Exception e) {
                MinecraftServer.LOGGER.warn("ChunkGenerator error for {}: {}", creator.name(), e.getMessage());
            }
        };
    }

    @Override
    public boolean dispatchCommand(org.bukkit.command.CommandSender sender, String commandLine) {
        CommandManager cm = MinecraftServer.getCommandManager();
        cm.execute(cm.getConsoleSender(), commandLine);
        return true;
    }

    @Override public java.util.Map<String, String[]> getCommandAliases() { return java.util.Collections.emptyMap(); }

    @Override public int getPort() { return lavender.getProperties().getServerPort(); }
    @Override public String getIp() { return lavender.getProperties().getServerIp(); }
    @Override public String getServerName() { return Lavender.BRAND_NAME; }
    @Override public String getMotd() { return lavender.getProperties().getMotd(); }
    @Override public int getMaxPlayers() { return lavender.getProperties().getMaxPlayers(); }
    @Override public String getVersion() { return Lavender.BRAND_NAME + " " + Lavender.VERSION_NAME + " (MC: " + Lavender.VERSION_NAME + ")"; }
    @Override public String getBukkitVersion() { return Lavender.VERSION_NAME; }

    @Override public boolean isPrimaryThread() { return Thread.currentThread().getName().contains("main"); }
    @Override public boolean getOnlineMode() { return lavender.getProperties().isOnlineMode(); }

    @Override public long getWorldTickTime() { return 0; }

    @Override public void reload() {}
    @Override public void shutdown() { MinecraftServer.stopCleanly(); }

    @Override public org.bukkit.command.ConsoleCommandSender getConsoleSender() { return consoleSender; }

    @Override public org.bukkit.OfflinePlayer getOfflinePlayer(String name) {
        for (net.minestom.server.entity.Player p : MinecraftServer.getConnectionManager().getOnlinePlayers()) {
            if (p.getUsername().equalsIgnoreCase(name)) return LavenderPlayer.wrap(p, this);
        }
        return new SimpleOfflinePlayer(name);
    }

    @Override public org.bukkit.OfflinePlayer getOfflinePlayer(java.util.UUID uuid) {
        net.minestom.server.entity.Player p = MinecraftServer.getConnectionManager().getOnlinePlayerByUuid(uuid);
        if (p != null) return LavenderPlayer.wrap(p, this);
        return new SimpleOfflinePlayer("OfflinePlayer");
    }

    @Override public org.bukkit.OfflinePlayer[] getOfflinePlayers() {
        return new org.bukkit.OfflinePlayer[0];
    }

    @Override public java.io.File getWorldContainer() { return new java.io.File("."); }

    //@Override public io.papermc.paper.threadedregions.scheduler.AsyncScheduler getAsyncScheduler() { return asyncScheduler; }

    @Override public String getMinecraftVersion() { return Lavender.VERSION_NAME; }
    @Override public org.bukkit.packs.ResourcePack getServerResourcePack() { return null; }
    @Override public int getSimulationDistance() { return lavender.getProperties().getSimulationDistance(); }
    @Override public int getViewDistance() { return lavender.getProperties().getViewDistance(); }
    @Override public org.bukkit.scoreboard.ScoreboardManager getScoreboardManager() { return new SimpleScoreboardManager(); }
    @Override public java.util.Iterator<org.bukkit.boss.BossBar> getBossBars() { return java.util.Collections.emptyIterator(); }
    @Override public boolean isResourcePackRequired() { return false; }
    @Override public org.bukkit.inventory.ItemFactory getItemFactory() { return new SimpleItemFactory(); }

    @Override public org.bukkit.plugin.ServicesManager getServicesManager() { return servicesManager; }
    @Override public org.bukkit.UnsafeValues getUnsafe() { return new org.bukkit.UnsafeValues() { @Override public int getDataVersion() { return Lavender.DATA_VERSION; } }; }
    @Override public org.bukkit.plugin.messaging.Messenger getMessenger() { return messenger; }

    @Override
    public org.bukkit.inventory.Inventory createInventory(org.bukkit.inventory.InventoryHolder owner, org.bukkit.inventory.InventoryType type) {
        return createInventory(owner, type, type.getDefaultTitle());
    }

    @Override
    public org.bukkit.inventory.Inventory createInventory(org.bukkit.inventory.InventoryHolder owner, org.bukkit.inventory.InventoryType type, String title) {
        net.minestom.server.inventory.InventoryType msType = toMinestomInventoryType(type);
        net.minestom.server.inventory.Inventory inv = new net.minestom.server.inventory.Inventory(msType, title);
        return new LavenderInventory(inv, title, owner, type);
    }

    @Override
    public org.bukkit.inventory.Inventory createInventory(org.bukkit.inventory.InventoryHolder owner, org.bukkit.inventory.InventoryType type, net.kyori.adventure.text.Component title) {
        String titleStr = net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer.plainText().serialize(title);
        return createInventory(owner, type, titleStr);
    }

    @Override
    public org.bukkit.inventory.Inventory createInventory(org.bukkit.inventory.InventoryHolder owner, int size) {
        return createInventory(owner, size, "Chest");
    }

    @Override
    public org.bukkit.inventory.Inventory createInventory(org.bukkit.inventory.InventoryHolder owner, int size, String title) {
        net.minestom.server.inventory.InventoryType msType;
        int normalized = (size % 9 == 0) ? size : 27;
        switch (normalized) {
            case 9: msType = net.minestom.server.inventory.InventoryType.CHEST_1_ROW; break;
            case 18: msType = net.minestom.server.inventory.InventoryType.CHEST_2_ROW; break;
            case 27: msType = net.minestom.server.inventory.InventoryType.CHEST_3_ROW; break;
            case 36: msType = net.minestom.server.inventory.InventoryType.CHEST_4_ROW; break;
            case 45: msType = net.minestom.server.inventory.InventoryType.CHEST_5_ROW; break;
            case 54: msType = net.minestom.server.inventory.InventoryType.CHEST_6_ROW; break;
            default: msType = net.minestom.server.inventory.InventoryType.CHEST_3_ROW; break;
        }
        net.minestom.server.inventory.Inventory inv = new net.minestom.server.inventory.Inventory(msType, title);
        return new LavenderInventory(inv, title, owner, org.bukkit.inventory.InventoryType.CHEST);
    }

    @Override
    public org.bukkit.inventory.Inventory createInventory(org.bukkit.inventory.InventoryHolder owner, int size, net.kyori.adventure.text.Component title) {
        String titleStr = net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer.plainText().serialize(title);
        return createInventory(owner, size, titleStr);
    }

    private net.minestom.server.inventory.InventoryType toMinestomInventoryType(org.bukkit.inventory.InventoryType type) {
        switch (type) {
            case HOPPER: return net.minestom.server.inventory.InventoryType.HOPPER;
            case FURNACE: return net.minestom.server.inventory.InventoryType.FURNACE;
            case DISPENSER:
            case DROPPER: return net.minestom.server.inventory.InventoryType.WINDOW_3X3;
            case ANVIL: return net.minestom.server.inventory.InventoryType.ANVIL;
            case BEACON: return net.minestom.server.inventory.InventoryType.BEACON;
            case BREWING: return net.minestom.server.inventory.InventoryType.BREWING_STAND;
            case CRAFTING:
            case WORKBENCH: return net.minestom.server.inventory.InventoryType.CRAFTING;
            case ENCHANTING: return net.minestom.server.inventory.InventoryType.ENCHANTMENT;
            case SHULKER_BOX: return net.minestom.server.inventory.InventoryType.SHULKER_BOX;
            case BARREL: return net.minestom.server.inventory.InventoryType.CHEST_3_ROW;
            case BLAST_FURNACE: return net.minestom.server.inventory.InventoryType.BLAST_FURNACE;
            case SMOKER: return net.minestom.server.inventory.InventoryType.SMOKER;
            case GRINDSTONE: return net.minestom.server.inventory.InventoryType.GRINDSTONE;
            case STONECUTTER: return net.minestom.server.inventory.InventoryType.STONE_CUTTER;
            case LECTERN: return net.minestom.server.inventory.InventoryType.LECTERN;
            case MERCHANT: return net.minestom.server.inventory.InventoryType.MERCHANT;
            case CARTOGRAPHY: return net.minestom.server.inventory.InventoryType.CARTOGRAPHY;
            case SMITHING: return net.minestom.server.inventory.InventoryType.SMITHING;
            default: return net.minestom.server.inventory.InventoryType.CHEST_3_ROW;
        }
    }

    public void loadPlugins() { pluginManager.loadPlugins(); }
    public void disableAllPlugins() { pluginManager.disableAll(); }

    @Override
    public void sendActionBar(net.kyori.adventure.text.Component message) {
        for (net.minestom.server.entity.Player player : MinecraftServer.getConnectionManager().getOnlinePlayers()) {
            player.sendActionBar(message);
        }
    }

    @Override
    public void sendMessage(net.kyori.adventure.text.Component message) {
        for (net.minestom.server.entity.Player player : MinecraftServer.getConnectionManager().getOnlinePlayers()) {
            player.sendMessage(message);
        }
    }

    @Override
    public void sendMessage(java.lang.Iterable<? extends net.kyori.adventure.text.Component> messages) {
        for (net.kyori.adventure.text.Component msg : messages) { sendMessage(msg); }
    }

    @Override
    public org.bukkit.WorldBorder createWorldBorder() {
        return new SimpleWorldBorder();
    }

    @Override
    public org.bukkit.packs.DataPackManager getDataPackManager() {
        return new SimpleDataPackManager();
    }

    @Override
    public boolean unloadWorld(World world) {
        if (world == null) return false;
        LavenderWorld mw = (LavenderWorld) world;
        try {
            mw.getInstance().saveChunksToStorage();
        } catch (Exception e) {
            MinecraftServer.LOGGER.warn("Failed to save world {} during unload: {}", world.getName(), e.getMessage());
        }
        worlds.remove(world.getName().toLowerCase());
        worldsByUUID.remove(world.getUID());
        for (net.minestom.server.entity.Player p : mw.getInstance().getPlayers()) {
            p.setInstance(lavender.getWorld());
        }
        MinecraftServer.getInstanceManager().unregisterInstance(mw.getInstance());
        MinecraftServer.LOGGER.info("Unloaded world: {}", world.getName());
        return true;
    }

    @Override
    public java.util.List<org.bukkit.Tag<?>> getTags(String registry, Class<?> clazz) {
        return java.util.Collections.emptyList();
    }

    @Override
    public <T extends org.bukkit.Keyed> org.bukkit.Tag<T> getTag(String registry, org.bukkit.NamespacedKey key, Class<T> clazz) {
        return null;
    }

    @Override
    public org.bukkit.block.data.BlockData createBlockData(String data) {
        return new SimpleBlockData(data);
    }

    @Override
    public org.bukkit.block.data.BlockData createBlockData(org.bukkit.Material material) {
        return new SimpleBlockData(material);
    }

    @Override
    public org.bukkit.block.data.BlockData createBlockData(org.bukkit.Material material, String extra) {
        return new SimpleBlockData(material, extra);
    }
}
