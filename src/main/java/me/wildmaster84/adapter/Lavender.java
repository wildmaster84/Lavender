package me.wildmaster84.adapter;

import me.wildmaster84.adapter.server.LavenderServer;
import me.wildmaster84.adapter.world.LavenderWorld;
import net.minestom.server.MinecraftServer;
import net.minestom.server.coordinate.*;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.instance.LightingChunk;
import net.minestom.server.instance.anvil.AnvilLoader;
import net.minestom.server.instance.block.Block;
import net.minestom.server.instance.generator.Generator;
import net.minestom.server.registry.RegistryKey;
import net.minestom.server.timer.*;
import net.minestom.server.world.DimensionType;

import org.bukkit.Bukkit;
import org.bukkit.Server;

import me.wildmaster84.lavender.command.LavenderCommands;
import me.wildmaster84.lavender.event.EventBridge;
import me.wildmaster84.lavender.rcon.RCONServer;
import me.wildmaster84.lavender.util.*;

import java.io.*;
import java.util.concurrent.*;

import me.lucko.spark.minestom.SparkMinestom;

import org.slf4j.*;

public final class Lavender {

    private static final Logger log = LoggerFactory.getLogger("Lavender");
    private final MinecraftServer minecraft;
    private final Server server;
    private final InstanceContainer world;
    private final ServerProperties properties;
    private me.wildmaster84.lavender.util.PlayerDataStore playerData;
    private RCONServer rconServer;
    private SparkMinestom spark;
    private net.minestom.server.coordinate.Pos spawnPoint = new net.minestom.server.coordinate.Pos(0, 100, 0);
    private final ScheduledExecutorService timer = Executors.newScheduledThreadPool(1, r -> {
        Thread t = new Thread(r, "Lavender-Timer");
        t.setDaemon(true);
        return t;
    });
    private final ExecutorService virtualExecutor = Executors.newVirtualThreadPerTaskExecutor();
    private final java.util.List<LavenderWorld> pendingWorlds = new java.util.ArrayList<>();

    public ScheduledExecutorService getTimer() { return timer; }
    public ExecutorService getVirtualExecutor() { return virtualExecutor; }
    public net.minestom.server.coordinate.Pos getSpawnPoint() { return spawnPoint; }

    public Lavender() {
        org.fusesource.jansi.AnsiConsole.systemInstall();

        properties = new ServerProperties(new File("server.properties"));

        minecraft = properties.isOnlineMode()
            ? MinecraftServer.init(new net.minestom.server.Auth.Online())
            : MinecraftServer.init();

        Logger exLogger = LoggerFactory.getLogger("Lavender");
        MinecraftServer.getExceptionManager().setExceptionHandler(t -> exLogger.error("Uncaught exception in server task", t));

        Thread.setDefaultUncaughtExceptionHandler((thread, throwable) -> {
            if (throwable instanceof OutOfMemoryError) {
                exLogger.error("Out of memory! Forcing shutdown...");
                Runtime.getRuntime().halt(1);
            } else {
                exLogger.error("Uncaught exception in thread " + thread.getName(), throwable);
            }
        });

        installJULBridge();

        // TODO: fix this hot garbage ._.
        net.minestom.server.instance.block.BlockManager blockManager = MinecraftServer.getBlockManager();
        for (String block : new String[]{"minecraft:chest", "minecraft:barrel", "minecraft:hopper",
                "minecraft:decorated_pot", "minecraft:structure_block", "minecraft:shulker_box",
                "minecraft:dispenser", "minecraft:dropper", "minecraft:furnace", "minecraft:blast_furnace",
                "minecraft:smoker", "minecraft:brewing_stand", "minecraft:beacon", "minecraft:chiseled_bookshelf",
                "minecraft:lectern", "minecraft:campfire", "minecraft:soul_campfire"}) {
            String key = block;
            blockManager.registerHandler(key, () -> new net.minestom.server.instance.block.BlockHandler() {
                @Override
                public net.kyori.adventure.key.Key getKey() { return net.kyori.adventure.key.Key.key(key); }
            });
        }

        File worldDir = new File("world");
        if (!worldDir.exists()) {
            worldDir.mkdirs();
        }
        RegistryKey<DimensionType> dimType = MinecraftServer.getDimensionTypeRegistry().getKey(net.kyori.adventure.key.Key.key("minecraft", "overworld"));
        world = MinecraftServer.getInstanceManager().createInstanceContainer(dimType, new AnvilLoader(worldDir.toPath(), net.kyori.adventure.key.Key.key("minecraft", "overworld")));
        world.enableAutoChunkLoad(true);
        createWorld();

        server = new LavenderServer(this);
        server.setName("Lavender");
        Bukkit.setServer(server);

        net.minecraft.server.dedicated.DedicatedServer.setInstance(new net.minecraft.server.dedicated.DedicatedServer());

        pendingWorlds.add(new LavenderWorld(world, (LavenderServer) server, "world", properties.getLevelSeed(), org.bukkit.World.Environment.NORMAL));

        createDimensionWorld("world_nether", "the_nether", org.bukkit.World.Environment.NETHER);
        createDimensionWorld("world_the_end", "the_end", org.bukkit.World.Environment.THE_END);

        playerData = new me.wildmaster84.lavender.util.PlayerDataStore(new File("."));

        new LavenderCommands(server, this).registerAll();

        startConsole();

        if (properties.isRconEnabled()) {
            rconServer = new RCONServer(properties.getRconPort(), properties.getRconPassword());
            rconServer.start();
        }
    }

    public void start() {
        ((LavenderServer) server).loadPlugins();

        for (LavenderWorld w : pendingWorlds) {
            ((LavenderServer) server).registerWorld(w);
        }
        pendingWorlds.clear();
        log.info("Registered {} worlds after plugin load", ((LavenderServer) server).getWorlds().size());

        try {
            spark = SparkMinestom.builder(java.nio.file.Path.of("spark")).commands(true).permissionHandler((sender, permission) -> true).enable();
            log.info("Spark profiler enabled");
        } catch (Throwable t) {
            log.warn("Failed to enable Spark: {}", t.getMessage());
        }

        log.info("Loading spawn chunks...");
        try {
            for (int cx = -1; cx <= 1; cx++) {
                for (int cz = -1; cz <= 1; cz++) {
                    world.loadChunk(cx, cz).join();
                }
            }
            int surfaceY = 100;
            for (int y = 319; y >= -64; y--) {
                Block b = world.getBlock(0, y, 0);
                if (b != null && !b.isAir() && !b.isLiquid()) {
                    surfaceY = y + 1;
                    break;
                }
            }
            spawnPoint = new Pos(0, surfaceY, 0);
            world.saveChunksToStorage().join();
            log.info("Spawn chunks saved to disk");
        } catch (Throwable e) {
            log.warn("Failed to pre-generate spawn chunks: {}", e.getMessage());
        }

        new EventBridge((LavenderServer) server, playerData, spawnPoint).registerAll();

        SchedulerManager scheduler = MinecraftServer.getSchedulerManager();

        scheduler.buildTask(this::saveAllWorlds)
                .repeat(TaskSchedule.tick(2400))
                .delay(TaskSchedule.tick(2400))
                .schedule();

        Runtime.getRuntime().addShutdownHook(Thread.ofVirtual().name("Lavender-Shutdown").unstarted(() -> {
            try { saveAllWorlds(); } catch (Throwable t) { log.error("Error saving worlds: {}", t.getMessage()); }
            try { ((LavenderServer) server).disableAllPlugins(); } catch (Throwable t) { log.error("Error disabling plugins: {}", t.getMessage()); }
            try { if (spark != null) spark.shutdown(); } catch (Throwable t) {}
            try { if (rconServer != null) rconServer.stop(); } catch (Throwable t) {}
            try { timer.shutdownNow(); } catch (Throwable t) {}
            try { virtualExecutor.shutdownNow(); } catch (Throwable t) {}
            Runtime.getRuntime().halt(0);
        }));

        minecraft.start(properties.getServerIp(), properties.getServerPort());
    }

    private void installJULBridge() {
        java.util.logging.Logger root = java.util.logging.Logger.getLogger("");
        for (java.util.logging.Handler h : root.getHandlers()) {
            root.removeHandler(h);
        }
        root.addHandler(new java.util.logging.Handler() {
            @Override
            public void publish(java.util.logging.LogRecord record) {
                try {
                    String name = record.getLoggerName();
                    if (name == null) name = "lavender";
                    Logger l = LoggerFactory.getLogger(name);
                    String msg = record.getMessage();
                    Throwable thrown = record.getThrown();
                    if (record.getLevel().intValue() >= java.util.logging.Level.SEVERE.intValue()) {
                        l.error(msg, thrown);
                    } else if (record.getLevel().intValue() >= java.util.logging.Level.WARNING.intValue()) {
                        l.warn(msg, thrown);
                    } else if (record.getLevel().intValue() >= java.util.logging.Level.INFO.intValue()) {
                        l.info(msg, thrown);
                    } else {
                        l.debug(msg, thrown);
                    }
                } catch (Throwable t) {
                    System.err.println("[JUL-Bridge] " + record.getMessage());
                }
            }
            @Override public void flush() {}
            @Override public void close() {}
        });
    }

    public void saveAllWorlds() {
        try {
            for (org.bukkit.World w : ((LavenderServer) server).getWorlds()) {
                if (w instanceof LavenderWorld mw) {
                    try {
                        mw.getInstance().saveChunksToStorage().get(5, java.util.concurrent.TimeUnit.SECONDS);
                    } catch (java.util.concurrent.TimeoutException e) {
                        log.warn("Timed out saving world {}, forcing continue", mw.getName());
                    } catch (java.util.concurrent.ExecutionException e) {
                        log.warn("Error saving world {}: {}", mw.getName(), e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        log.warn("Interrupted while saving world {}", mw.getName());
                    }
                    log.info("Saved world: {}", mw.getName());
                }
            }
            if (playerData != null) {
                for (net.minestom.server.entity.Player p : MinecraftServer.getConnectionManager().getOnlinePlayers()) {
                    LavenderWorld world = ((LavenderServer) server).getWorld(p.getInstance());
                    net.minestom.server.coordinate.Pos pos = p.getPosition();
                    String worldName = world != null ? world.getName() : "world";
                    playerData.savePlayer(p.getUuid(), worldName, pos.x(), pos.y(), pos.z(), pos.yaw(), pos.pitch());
                }
            }
        } catch (Exception e) {
            log.warn("Failed to save worlds: {}", e.getMessage());
        }
    }

    public void stop() {
        log.info("Stopping server...");
        System.exit(0);
    }

    public MinecraftServer getMinecraftServer() { return minecraft; }
    public Server getServer() { return server; }
    public InstanceContainer getWorld() { return world; }
    public Generator getGenerator() { return world.generator(); }
    public ServerProperties getProperties() { return properties; }
    public me.wildmaster84.lavender.util.PlayerDataStore getPlayerData() { return playerData; }

    private void createDimensionWorld(String name, String dimTypeName, org.bukkit.World.Environment env) {
        File worldDir = new File("world");
        if (!worldDir.exists()) worldDir.mkdirs();
        RegistryKey<DimensionType> dimType = MinecraftServer.getDimensionTypeRegistry()
                .getKey(net.kyori.adventure.key.Key.key("minecraft", dimTypeName));
        InstanceContainer instance = MinecraftServer.getInstanceManager()
                .createInstanceContainer(dimType, new AnvilLoader(worldDir.toPath(), net.kyori.adventure.key.Key.key("minecraft", dimTypeName)));
        instance.enableAutoChunkLoad(true);
        try {
            rocks.minestom.worldgen.WorldGenerators worldGens = new rocks.minestom.worldgen.WorldGenerators(
                new File(".").toPath(), properties.getLevelSeed());
            Generator dimGen = switch (env) {
                case NETHER -> worldGens.nether();
                case THE_END -> worldGens.end();
                default -> worldGens.overworld();
            };
            instance.setGenerator(unit -> {
                try { dimGen.generate(unit); }
                catch (Throwable e) { log.warn("World generation error in {}: {}", name, e.getMessage()); }
            });
            log.info("Vanilla worldgen enabled for {}", name);
        } catch (Exception e) {
            log.warn("Failed to initialize worldgen for {}, falling back to stone: {}", name, e.getMessage());
            instance.setGenerator(unit -> {
                final Point start = unit.absoluteStart();
                final Point size = unit.size();
                for (int x = 0; x < size.blockX(); x++) {
                    for (int z = 0; z < size.blockZ(); z++) {
                        for (int y = 0; y < Math.min(40 - start.blockY(), size.blockY()); y++) {
                            unit.modifier().setBlock(start.add(x, y, z), Block.STONE);
                        }
                    }
                }
            });
        }
        instance.setChunkSupplier(LightingChunk::new);
        pendingWorlds.add(new LavenderWorld(instance, (LavenderServer) server, name, properties.getLevelSeed(), env));
        log.info("Created {} world: {}", env, name);
    }

    private void createWorld() {
        new VanillaDataPack(new File(".").toPath()).ensureExtracted();

        try {
            rocks.minestom.worldgen.WorldGenerators worldGens = new rocks.minestom.worldgen.WorldGenerators(
                new File(".").toPath(), properties.getLevelSeed());
            Generator originalGen = worldGens.overworld();
            world.setGenerator(unit -> {
                try {
                    originalGen.generate(unit);
                } catch (Throwable e) {
                    log.warn("World generation error in chunk: {}", e.getMessage());
                }
            });
            log.info("Vanilla world generation enabled (seed={})", properties.getLevelSeed());
        } catch (Exception e) {
            log.warn("Failed to initialize vanilla worldgen, falling back to stone platform: " + e.getMessage());
            world.setGenerator(unit -> {
                final Point start = unit.absoluteStart();
                final Point size = unit.size();
                for (int x = 0; x < size.blockX(); x++) {
                    for (int z = 0; z < size.blockZ(); z++) {
                        for (int y = 0; y < Math.min(40 - start.blockY(), size.blockY()); y++) {
                            unit.modifier().setBlock(start.add(x, y, z), Block.STONE);
                        }
                    }
                }
            });
        }
        world.setChunkSupplier(LightingChunk::new);
    }

    private void startConsole() {
        Thread.ofVirtual().name("Lavender-Console").start(() -> {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                try {
                    String line = br.readLine();
                    if (line == null) break;
                    if (!line.isBlank()) {
                        line = line.trim();
                        String[] parts = line.split(" ");
                        String cmdName = parts[0];
                        String[] args = parts.length > 1 ? java.util.Arrays.copyOfRange(parts, 1, parts.length) : new String[0];
                        org.bukkit.command.Command cmd = ((me.wildmaster84.adapter.server.LavenderServer) server).getCommandMap().getCommand(cmdName);
                        if (cmd != null) {
                            cmd.execute(server.getConsoleSender(), cmdName, args);
                        } else {
                            MinecraftServer.getCommandManager().execute(MinecraftServer.getCommandManager().getConsoleSender(), line);
                        }
                    }
                } catch (IOException e) {
                    break;
                }
            }
        });
    }
}
