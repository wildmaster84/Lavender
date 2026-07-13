package me.wildmaster84.lavender.event;

import me.wildmaster84.adapter.inventory.LavenderInventory;
import me.wildmaster84.adapter.inventory.LavenderInventoryView;
import me.wildmaster84.adapter.player.LavenderPlayer;
import me.wildmaster84.adapter.server.LavenderServer;
import me.wildmaster84.adapter.world.LavenderWorld;
import net.minestom.server.MinecraftServer;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.instance.Instance;
import net.minestom.server.instance.Chunk;
import net.minestom.server.entity.GameMode;
import net.minestom.server.event.inventory.InventoryCloseEvent;
import net.minestom.server.event.inventory.InventoryPreClickEvent;
import net.minestom.server.event.player.*;

import net.minestom.server.event.server.ServerListPingEvent;
import net.minestom.server.inventory.InventoryClickHandler;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;

import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;

import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.InventoryType;

import java.util.HashSet;

public class EventBridge {

    private final LavenderServer server;
    private final me.wildmaster84.lavender.util.PlayerDataStore playerData;
    private final Pos spawnPoint;

    public EventBridge(LavenderServer server, me.wildmaster84.lavender.util.PlayerDataStore playerData, Pos spawnPoint) {
        this.server = server;
        this.playerData = playerData;
        this.spawnPoint = spawnPoint;
    }

    public void registerAll() {
        registerChatEvent();
        registerPlayerConfigEvent();
        registerJoinEvent();
        registerQuitEvent();
        registerMoveEvent();
        registerBlockBreakEvent();
        registerDamageEvent();
        registerUseItemEvent();
        registerBlockInteractEvent();
        registerEntityInteractEvent();
        registerInventoryClickEvent();
        registerInventoryCloseEvent();
        registerServerPingEvent();
    }

    private void registerChatEvent() {
        MinecraftServer.getGlobalEventHandler().addListener(PlayerChatEvent.class, event -> {
            LavenderPlayer bukkitPlayer = LavenderPlayer.wrap(event.getPlayer(), server);
            AsyncPlayerChatEvent bukkitEvent = new AsyncPlayerChatEvent(false, bukkitPlayer, event.getRawMessage(), new HashSet<>());
            Component chatComponent = LegacyComponentSerializer.legacySection().deserialize(event.getRawMessage());
            io.papermc.paper.event.player.AsyncChatEvent paperEvent = new io.papermc.paper.event.player.AsyncChatEvent(bukkitPlayer, chatComponent);

            server.getPluginManager().callEvent(bukkitEvent);
            if (bukkitEvent.isCancelled()) event.setCancelled(true);

            server.getPluginManager().callEvent(paperEvent);
            if (paperEvent.isCancelled()) event.setCancelled(true);

            if (!event.isCancelled()) {
                String displayName = bukkitPlayer.getDisplayName();
                String chatMessage = bukkitEvent.getMessage();
                String consoleMsg = String.format(bukkitEvent.getFormat(), displayName, chatMessage);
                server.getLogger().info(me.wildmaster84.adapter.player.LavenderConsoleSender.convertColorCodes(consoleMsg));

                Component formatted;
                if (paperEvent.message() != null && !paperEvent.message().equals(chatComponent)) {
                    formatted = Component.empty()
                        .append(LegacyComponentSerializer.legacySection().deserialize(displayName))
                        .append(Component.text(": "))
                        .append(paperEvent.message());
                } else {
                    String format = bukkitEvent.getFormat();
                    if (format != null && !format.equals("<%1$s> %2$s")) {
                        String formattedStr = String.format(format, displayName, chatMessage);
                        formatted = LegacyComponentSerializer.legacySection().deserialize(formattedStr);
                    } else {
                        formatted = Component.empty()
                            .append(LegacyComponentSerializer.legacySection().deserialize(displayName))
                            .append(Component.text(": "))
                            .append(parseLinks(chatMessage));
                    }
                }
                event.setFormattedMessage(formatted);
            }
        });
    }

    private Component buildChatMessage(net.minestom.server.entity.Player player, String rawMessage) {
        Component name = player.getName();
        Component message = parseLinks(rawMessage);
        return Component.empty().append(name).append(Component.text(": ")).append(message);
    }

    private Component parseLinks(String text) {
        Component result = Component.empty();
        java.util.regex.Pattern urlPattern = java.util.regex.Pattern.compile("(https?://[\\w\\-._~:/?#\\[\\]@!$&'()*+,;=%]+)");
        java.util.regex.Matcher matcher = urlPattern.matcher(text);
        int lastEnd = 0;
        while (matcher.find()) {
            if (matcher.start() > lastEnd) {
                result = result.append(Component.text(text.substring(lastEnd, matcher.start())));
            }
            String url = matcher.group();
            result = result.append(Component.text(url)
                .clickEvent(net.kyori.adventure.text.event.ClickEvent.openUrl(url))
                .color(net.kyori.adventure.text.format.TextColor.color(0x5555FF)));
            lastEnd = matcher.end();
        }
        if (lastEnd < text.length()) {
            result = result.append(Component.text(text.substring(lastEnd)));
        }
        return result;
    }

    private void registerPlayerConfigEvent() {
        MinecraftServer.getGlobalEventHandler().addListener(AsyncPlayerConfigurationEvent.class, event -> {
            event.setSpawningInstance(server.getInstanceContainer());
            event.getPlayer().setRespawnPoint(spawnPoint);
            event.getPlayer().setGameMode(parseGameMode(server.getGamemode()));
        });
    }

    private void registerJoinEvent() {
        MinecraftServer.getGlobalEventHandler().addListener(PlayerSpawnEvent.class, event -> {
            if (!event.isFirstSpawn()) return;

            net.minestom.server.entity.Player msPlayer = event.getPlayer();
            LavenderPlayer bukkitPlayer = LavenderPlayer.wrap(msPlayer, server);

            if (playerData != null && playerData.hasData(msPlayer.getUuid())) {
                String savedWorld = playerData.getWorld(msPlayer.getUuid());
                LavenderWorld world = (LavenderWorld) server.getWorld(savedWorld);
                if (world != null) {
                    double[] pos = playerData.getPosition(msPlayer.getUuid());
                    if (pos != null) {
                        Location loc = new Location(world, pos[0], pos[1], pos[2], (float) pos[3], (float) pos[4]);
                        Instance targetInstance = world.getInstance();
                        int chunkX = (int) Math.floor(pos[0] / 16);
                        int chunkZ = (int) Math.floor(pos[2] / 16);
                        java.util.List<java.util.concurrent.CompletableFuture<Chunk>> futures = new java.util.ArrayList<>();
                        for (int dx = -1; dx <= 1; dx++) {
                            for (int dz = -1; dz <= 1; dz++) {
                                if (!targetInstance.isChunkLoaded(chunkX + dx, chunkZ + dz)) {
                                    futures.add(targetInstance.loadChunk(chunkX + dx, chunkZ + dz));
                                }
                            }
                        }
                        if (futures.isEmpty()) {
                            MinecraftServer.getSchedulerManager().scheduleNextTick(() -> bukkitPlayer.teleportAsync(loc));
                        } else {
                            java.util.concurrent.CompletableFuture.allOf(futures.toArray(new java.util.concurrent.CompletableFuture[0])).thenRun(() -> {
                                MinecraftServer.getSchedulerManager().scheduleNextTick(() -> bukkitPlayer.teleportAsync(loc));
                            });
                        }
                    }
                }
            }

            PlayerLoginEvent loginEvent = new PlayerLoginEvent(bukkitPlayer);
            server.getPluginManager().callEvent(loginEvent);

            PlayerJoinEvent joinEvent = new PlayerJoinEvent(bukkitPlayer, "");
            server.getPluginManager().callEvent(joinEvent);
        });
    }

    private void registerQuitEvent() {
        MinecraftServer.getGlobalEventHandler().addListener(PlayerDisconnectEvent.class, event -> {
            net.minestom.server.entity.Player msPlayer = event.getPlayer();
            LavenderPlayer bukkitPlayer = LavenderPlayer.wrap(msPlayer, server);

            if (playerData != null) {
                World world = server.getWorld(msPlayer.getInstance());
                Pos pos = msPlayer.getPosition();
                String worldName = world != null ? world.getName() : "world";
                playerData.savePlayer(msPlayer.getUuid(), worldName, pos.x(), pos.y(), pos.z(), pos.yaw(), pos.pitch());
            }

            PlayerQuitEvent quitEvent = new PlayerQuitEvent(bukkitPlayer, "");
            server.getPluginManager().callEvent(quitEvent);

            LavenderPlayer.invalidate(msPlayer.getUuid());
        });
    }

    private void registerMoveEvent() {
        MinecraftServer.getGlobalEventHandler().addListener(net.minestom.server.event.player.PlayerMoveEvent.class, event -> {
            LavenderPlayer bukkitPlayer = LavenderPlayer.wrap(event.getPlayer(), server);
            Pos from = event.getPlayer().getPosition();
            Pos to = event.getNewPosition();
            World world = server.getWorld(event.getPlayer().getInstance());
            Location bukkitFrom = new Location(world, from.x(), from.y(), from.z(), from.yaw(), from.pitch());
            Location bukkitTo = new Location(world, to.x(), to.y(), to.z(), to.yaw(), to.pitch());
            org.bukkit.event.player.PlayerMoveEvent moveEvent = new org.bukkit.event.player.PlayerMoveEvent(bukkitPlayer, bukkitFrom, bukkitTo);
            server.getPluginManager().callEvent(moveEvent);
            if (moveEvent.isCancelled()) event.setCancelled(true);
        });
    }

    private void registerBlockBreakEvent() {
        MinecraftServer.getGlobalEventHandler().addListener(PlayerBlockBreakEvent.class, event -> {
            LavenderPlayer bukkitPlayer = LavenderPlayer.wrap(event.getPlayer(), server);
            net.minestom.server.coordinate.Point pos = event.getBlockPosition();
            World world = server.getWorld(event.getPlayer().getInstance());
            Block bukkitBlock = world.getBlockAt(pos.blockX(), pos.blockY(), pos.blockZ());
            BlockBreakEvent breakEvent = new BlockBreakEvent(bukkitBlock, bukkitPlayer);
            server.getPluginManager().callEvent(breakEvent);
            if (breakEvent.isCancelled()) event.setCancelled(true);
        });
    }

    private void registerDamageEvent() {
        MinecraftServer.getGlobalEventHandler().addListener(net.minestom.server.event.entity.EntityDamageEvent.class, event -> {
            if (event.getEntity() instanceof net.minestom.server.entity.Player p) {
                LavenderPlayer bukkitPlayer = LavenderPlayer.wrap(p, server);
                org.bukkit.event.entity.EntityDamageEvent damageEvent = new org.bukkit.event.entity.EntityDamageEvent(
                    bukkitPlayer, org.bukkit.event.entity.EntityDamageEvent.DamageCause.CUSTOM, 0);
                server.getPluginManager().callEvent(damageEvent);
                if (damageEvent.isCancelled()) event.setCancelled(true);
            }
        });
    }

    private void registerUseItemEvent() {
        MinecraftServer.getGlobalEventHandler().addListener(PlayerUseItemEvent.class, event -> {
            LavenderPlayer bukkitPlayer = LavenderPlayer.wrap(event.getPlayer(), server);
            ItemStack bukkitItem = convertMsItem(event.getItemStack());
            World world = server.getWorld(event.getPlayer().getInstance());
            Location loc = new Location(world,
                event.getPlayer().getPosition().x(),
                event.getPlayer().getPosition().y(),
                event.getPlayer().getPosition().z());
            PlayerInteractEvent interactEvent = new PlayerInteractEvent(
                bukkitPlayer, Action.RIGHT_CLICK_AIR, bukkitItem,
                loc.getBlock(), BlockFace.SELF);
            server.getPluginManager().callEvent(interactEvent);
            if (interactEvent.isCancelled()) event.setCancelled(true);
        });
    }

    private void registerBlockInteractEvent() {
        MinecraftServer.getGlobalEventHandler().addListener(PlayerBlockInteractEvent.class, event -> {
            LavenderPlayer bukkitPlayer = LavenderPlayer.wrap(event.getPlayer(), server);
            ItemStack bukkitItem = convertMsItem(event.getPlayer().getItemInMainHand());
            World world = server.getWorld(event.getPlayer().getInstance());
            net.minestom.server.coordinate.Point pos = event.getBlockPosition();
            Block bukkitBlock = world.getBlockAt(pos.blockX(), pos.blockY(), pos.blockZ());
            PlayerInteractEvent interactEvent = new PlayerInteractEvent(
                bukkitPlayer, Action.RIGHT_CLICK_BLOCK, bukkitItem,
                bukkitBlock, BlockFace.UP);
            server.getPluginManager().callEvent(interactEvent);
            if (interactEvent.isCancelled()) event.setCancelled(true);
        });
    }

    private void registerEntityInteractEvent() {
        MinecraftServer.getGlobalEventHandler().addListener(PlayerEntityInteractEvent.class, event -> {
            LavenderPlayer bukkitPlayer = LavenderPlayer.wrap(event.getPlayer(), server);
            org.bukkit.event.player.PlayerInteractEntityEvent bukkitEvent = new org.bukkit.event.player.PlayerInteractEntityEvent(bukkitPlayer, null);
            server.getPluginManager().callEvent(bukkitEvent);
        });
    }

    private void registerInventoryClickEvent() {
        MinecraftServer.getGlobalEventHandler().addListener(InventoryPreClickEvent.class, event -> {
            LavenderPlayer bukkitPlayer = LavenderPlayer.wrap(event.getPlayer(), server);
            InventoryClickHandler msInv = event.getInventory();
            LavenderInventory topInv = null;
            if (msInv instanceof net.minestom.server.inventory.Inventory inv) {
                topInv = LavenderInventory.wrap(inv);
                if (topInv == null) {
                    String title = inv.getTitle() != null
                        ? PlainTextComponentSerializer.plainText().serialize(inv.getTitle())
                        : "Inventory";
                    topInv = new LavenderInventory(inv, title, null, InventoryType.CHEST);
                }
            }
            if (topInv == null) return;

            InventoryType bukkitType = topInv.getType();
            LavenderInventoryView view = new LavenderInventoryView(bukkitPlayer, topInv, bukkitType);
            int slot = event.getSlot();
            ClickType bukkitClick = mapClickFromClick(event.getClick());
            InventoryAction bukkitAction = InventoryAction.PICKUP_ALL;
            ItemStack bukkitItem = topInv.getItem(slot);
            InventoryClickEvent clickEvent = new InventoryClickEvent(
                view, bukkitAction, bukkitClick, slot, slot, bukkitItem);
            if (slot >= 0 && slot < topInv.getSize()) {
                clickEvent.setClickedInventory(topInv);
            } else {
                clickEvent.setClickedInventory(bukkitPlayer.getInventory());
            }
            server.getPluginManager().callEvent(clickEvent);
            if (clickEvent.isCancelled()) event.setCancelled(true);
        });
    }

    private void registerInventoryCloseEvent() {
        MinecraftServer.getGlobalEventHandler().addListener(net.minestom.server.event.inventory.InventoryCloseEvent.class, event -> {
            LavenderPlayer bukkitPlayer = LavenderPlayer.wrap(event.getPlayer(), server);
            InventoryClickHandler msInv = event.getInventory();
            if (msInv instanceof net.minestom.server.inventory.Inventory inv) {
                LavenderInventory topInv = LavenderInventory.wrap(inv);
                if (topInv == null) {
                    String title = inv.getTitle() != null
                        ? PlainTextComponentSerializer.plainText().serialize(inv.getTitle())
                        : "Inventory";
                    topInv = new LavenderInventory(inv, title, null, InventoryType.CHEST);
                }
                org.bukkit.event.inventory.InventoryCloseEvent closeEvent = new org.bukkit.event.inventory.InventoryCloseEvent(topInv, bukkitPlayer);
                server.getPluginManager().callEvent(closeEvent);
            }
        });
    }

    private void registerServerPingEvent() {
        MinecraftServer.getGlobalEventHandler().addListener(ServerListPingEvent.class, event -> {
            int online = MinecraftServer.getConnectionManager().getOnlinePlayers().size();
            int max = server.getMaxPlayers();
            event.setStatus(net.minestom.server.ping.Status.builder()
                .description(net.kyori.adventure.text.Component.text(server.getMotd()))
                .playerInfo(online, max)
                .build());
        });
    }

    private static GameMode parseGameMode(String name) {
        if (name == null) return GameMode.CREATIVE;
        return switch (name.toLowerCase().trim()) {
            case "survival", "0" -> GameMode.SURVIVAL;
            case "creative", "1" -> GameMode.CREATIVE;
            case "adventure", "2" -> GameMode.ADVENTURE;
            case "spectator", "3" -> GameMode.SPECTATOR;
            default -> GameMode.CREATIVE;
        };
    }

    static ItemStack convertMsItem(net.minestom.server.item.ItemStack msItem) {
        if (msItem == null || msItem.isAir()) return new ItemStack();
        Material mat = Material.matchMaterial(msItem.material().name());
        if (mat == null) mat = Material.AIR;
        return new ItemStack(mat, msItem.amount());
    }

    static ClickType mapClickFromClick(net.minestom.server.inventory.click.Click click) {
        if (click == null) return ClickType.UNKNOWN;
        String typeName = click.getClass().getSimpleName();
        return switch (typeName) {
            case "Left" -> ClickType.LEFT;
            case "Right" -> ClickType.RIGHT;
            case "LeftShift" -> ClickType.SHIFT_LEFT;
            case "RightShift" -> ClickType.SHIFT_RIGHT;
            case "Middle" -> ClickType.MIDDLE;
            case "Double" -> ClickType.DOUBLE_CLICK;
            case "LeftDropCursor" -> ClickType.DROP;
            case "RightDropCursor" -> ClickType.DROP;
            case "HotbarSwap" -> ClickType.NUMBER_KEY;
            case "OffhandSwap" -> ClickType.SWAP_OFFHAND;
            default -> ClickType.UNKNOWN;
        };
    }
}
