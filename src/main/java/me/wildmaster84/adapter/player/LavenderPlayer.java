package me.wildmaster84.adapter.player;

import java.util.UUID;

import me.wildmaster84.adapter.inventory.LavenderInventory;
import me.wildmaster84.adapter.inventory.LavenderInventoryView;
import me.wildmaster84.adapter.inventory.PlayerInventoryView;
import me.wildmaster84.adapter.Lavender;
import me.wildmaster84.adapter.server.LavenderServer;
import me.wildmaster84.adapter.world.LavenderWorld;
import net.minestom.server.MinecraftServer;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.GameMode;
import net.minestom.server.entity.Player;
import net.minestom.server.instance.Instance;
import net.minestom.server.inventory.Inventory;
import net.minestom.server.item.ItemStack;
import net.minestom.server.network.packet.server.play.ParticlePacket;
import net.minestom.server.particle.Particle;
import net.minestom.server.command.CommandManager;

public class LavenderPlayer implements org.bukkit.entity.Player, me.wildmaster84.adapter.server.entity.CraftPlayer, org.bukkit.craftbukkit.entity.CraftPlayer {

    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(Lavender.BRAND_NAME);
    private static final java.util.Map<UUID, LavenderPlayer> cache = new java.util.concurrent.ConcurrentHashMap<>();

    public static LavenderPlayer wrap(Player player, LavenderServer server) {
        LavenderPlayer existing = cache.get(player.getUuid());
        if (existing != null && existing.player == player) return existing;
        if (existing != null && !existing.player.isOnline()) {
            cache.remove(player.getUuid());
        }
        return cache.computeIfAbsent(player.getUuid(), k -> new LavenderPlayer(player, server));
    }

    public static void invalidate(UUID uuid) {
        cache.remove(uuid);
    }

    private final Player player;
    private final LavenderServer server;
    private LavenderPlayerInventory cachedInventory;
    private String displayName;
    private String playerListName;

    public LavenderPlayer(Player player, LavenderServer server) {
        this.player = player;
        this.server = server;
    }

    @Override public UUID getUniqueId() { return player.getUuid(); }
    @Override public String getName() { return player.getUsername(); }
    @Override public String getDisplayName() { return displayName != null ? displayName : player.getUsername(); }
    @Override public void setDisplayName(String name) { this.displayName = name; }
    @Override public String getPlayerListName() { return playerListName != null ? playerListName : player.getUsername(); }
    @Override public void setPlayerListName(String name) { this.playerListName = name; }

    @Override public void sendMessage(String message) { player.sendMessage(legacyToComponent(message)); }
    @Override public void sendMessage(String[] messages) { for (String m : messages) player.sendMessage(legacyToComponent(m)); }
    @Override public void sendMessage(UUID sender, String message) { player.sendMessage(legacyToComponent(message)); }
    @Override public void sendMessage(UUID sender, String[] messages) { for (String m : messages) player.sendMessage(legacyToComponent(m)); }
    @Override public void sendRawMessage(String message) { player.sendMessage(legacyToComponent(message)); }
    @Override public void sendRawMessage(UUID sender, String message) { player.sendMessage(legacyToComponent(message)); }

    private static final java.util.regex.Pattern URL_PATTERN = java.util.regex.Pattern.compile(
        "(https?://[\\w\\-._~:/?#\\[\\]@!$&'()*+,;=%]+)");

    private static net.kyori.adventure.text.Component legacyToComponent(String message) {
        if (message == null || message.isEmpty()) return net.kyori.adventure.text.Component.empty();
        java.util.regex.Matcher matcher = URL_PATTERN.matcher(message);
        if (!matcher.find()) {
            return net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer.legacySection().deserialize(message);
        }
        matcher.reset();
        net.kyori.adventure.text.Component result = net.kyori.adventure.text.Component.empty();
        int lastEnd = 0;
        while (matcher.find()) {
            if (matcher.start() > lastEnd) {
                result = result.append(net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer.legacySection()
                    .deserialize(message.substring(lastEnd, matcher.start())));
            }
            String url = matcher.group();
            result = result.append(net.kyori.adventure.text.Component.text(url)
                .clickEvent(net.kyori.adventure.text.event.ClickEvent.openUrl(url))
                .color(net.kyori.adventure.text.format.TextColor.color(0x5555FF)));
            lastEnd = matcher.end();
        }
        if (lastEnd < message.length()) {
            result = result.append(net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer.legacySection()
                .deserialize(message.substring(lastEnd)));
        }
        return result;
    }

    @Override
    public org.bukkit.World getWorld() {
        Instance instance = player.getInstance();
        if (instance != null) return server.getWorld(instance);
        return server.getWorld("world");
    }

    @Override public double getX() { return player.getPosition().x(); }
    @Override public double getY() { return player.getPosition().y(); }
    @Override public double getZ() { return player.getPosition().z(); }

    @Override
    public org.bukkit.Location getLocation() {
        Pos pos = player.getPosition();
        return new org.bukkit.Location(getWorld(), pos.x(), pos.y(), pos.z(), pos.yaw(), pos.pitch());
    }

    @Override
    public boolean teleport(org.bukkit.Location location) {
        if (location == null) {
            logger.warn("teleport() called with null location");
            return false;
        }
        if (location.getWorld() == null) {
            logger.warn("teleport() called with null world in location (x={}, y={}, z={})", location.getX(), location.getY(), location.getZ());
            return false;
        }
        Pos targetPos = new Pos(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
        if (location.getWorld() instanceof LavenderWorld mw) {
            Instance targetInstance = mw.getInstance();
            if (targetInstance == null) {
                logger.warn("teleport() - LavenderWorld has null instance");
                return false;
            }
            Instance current = player.getInstance();
            if (current != null && current == targetInstance) {
                player.teleport(targetPos);
            } else {
                try {
                    logger.info("teleport() - setInstance to {} (uuid={})", mw.getName(), targetInstance.getUuid());
                    player.setInstance(targetInstance, targetPos);
                } catch (Throwable t) {
                    logger.error("teleport() - setInstance failed", t);
                    throw t;
                }
            }
            return true;
        }
        org.bukkit.World world = location.getWorld();
        logger.warn("teleport() - world {} is not a LavenderWorld, falling back to current instance", world.getName());
        Instance instance = player.getInstance();
        if (instance != null) {
            player.teleport(targetPos);
            return true;
        }
        return false;
    }

    @Override
    public boolean teleport(org.bukkit.entity.Entity destination) {
        if (destination instanceof LavenderPlayer mp) {
            Pos pos = mp.player.getPosition();
            player.teleport(pos);
            return true;
        }
        return false;
    }

    @Override
    public java.util.concurrent.CompletableFuture<Boolean> teleportAsync(org.bukkit.Location location) {
        java.util.concurrent.CompletableFuture<Boolean> future = new java.util.concurrent.CompletableFuture<>();
        MinecraftServer.getSchedulerManager().scheduleNextTick(() -> {
            try {
                org.bukkit.World world = location.getWorld();
                logger.info("teleportAsync() - world={}, loc=({},{},{})", world != null ? world.getName() : "null", location.getX(), location.getY(), location.getZ());
                if (world == null || !(world instanceof LavenderWorld mw)) {
                    logger.warn("teleportAsync() - world is null or not LavenderWorld, completing false");
                    future.complete(false);
                    return;
                }
                Instance targetInstance = mw.getInstance();
                if (targetInstance == null) {
                    logger.warn("teleportAsync() - target instance is null, completing false");
                    future.complete(false);
                    return;
                }
                Pos targetPos = new Pos(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
                Instance current = player.getInstance();
                if (current != null && (current == targetInstance || current.getUuid().equals(targetInstance.getUuid()))) {
                    player.teleport(targetPos);
                    future.complete(true);
                } else {
                    logger.info("teleport() - setInstance to {} (uuid={})", mw.getName(), targetInstance.getUuid());
                    // Complete the future on the tick thread so the plugin's callback runs in the right thread context.
                    player.setInstance(targetInstance, targetPos).whenCompleteAsync((v, ex) -> {
                        if (ex != null) {
                            logger.error("teleport() - setInstance failed", ex);
                            future.completeExceptionally(ex);
                        } else {
                            logger.info("teleportAsync() - setInstance completed, completing future with true");
                            try {
                                future.complete(true);
                            } catch (Throwable callbackEx) {
                                logger.error("teleportAsync() - plugin callback threw", callbackEx);
                            }
                        }
                    }, runnable -> MinecraftServer.getSchedulerManager().scheduleNextTick(runnable));
                }
            } catch (Throwable t) {
                logger.error("teleportAsync() - teleport threw", t);
                future.completeExceptionally(t);
            }
        });
        return future;
    }

    @Override
    public java.util.concurrent.CompletableFuture<Boolean> teleportAsync(org.bukkit.World world, org.bukkit.Location location) {
        return teleportAsync(location);
    }
    @Override
    public org.bukkit.GameMode getGameMode() {
        GameMode gm = player.getGameMode();
        if (gm == null) return org.bukkit.GameMode.SURVIVAL;
        return switch (gm) {
            case CREATIVE -> org.bukkit.GameMode.CREATIVE;
            case ADVENTURE -> org.bukkit.GameMode.ADVENTURE;
            case SPECTATOR -> org.bukkit.GameMode.SPECTATOR;
            default -> org.bukkit.GameMode.SURVIVAL;
        };
    }

    @Override
    public void setGameMode(org.bukkit.GameMode mode) {
        GameMode gm = switch (mode) {
            case CREATIVE -> GameMode.CREATIVE;
            case ADVENTURE -> GameMode.ADVENTURE;
            case SPECTATOR -> GameMode.SPECTATOR;
            default -> GameMode.SURVIVAL;
        };
        player.setGameMode(gm);
    }

    @Override public boolean isOnline() { return player.isOnline(); }
    @Override public boolean isBanned() { return false; }
    @Override public void setBanned(boolean banned) {}
    @Override public boolean isWhitelisted() { return true; }
    @Override public void setWhitelisted(boolean value) {}

    @Override
    public org.bukkit.inventory.PlayerInventory getInventory() {
        if (cachedInventory == null) cachedInventory = new LavenderPlayerInventory(player);
        return cachedInventory;
    }
    @Override public org.bukkit.inventory.ItemStack getItemInHand() {
        return getInventory().getItemInMainHand();
    }
    @Override public void setItemInHand(org.bukkit.inventory.ItemStack item) {
        getInventory().setItemInMainHand(item);
    }
    @Override public org.bukkit.inventory.ItemStack getItemOnCursor() { return null; }
    @Override public void setItemOnCursor(org.bukkit.inventory.ItemStack item) {}
    @Override public boolean hasInventorySpace() { return true; }

    @Override public double getHealth() { return player.getHealth(); }
    @Override public void setHealth(double health) { player.setHealth((float) health); }
    @Override public double getMaxHealth() { return 20; }
    @Override public void setMaxHealth(double maxHealth) {}
    @Override public double getHealthScale() { return 20.0; }
    @Override public void setHealthScale(double scale) {}

    @Override public int getFoodLevel() { return 20; }
    @Override public void setFoodLevel(int value) {}
    @Override public float getExhaustion() { return 0; }
    @Override public void setExhaustion(float value) {}
    @Override public float getSaturation() { return 0; }
    @Override public void setSaturation(float value) {}

    @Override public int getLevel() { return player.getLevel(); }
    @Override public void setLevel(int level) { player.setLevel(level); }
    @Override public int getTotalExperience() { return 0; }
    @Override public void setTotalExperience(int xp) {}
    @Override public float getExp() { return player.getExp(); }
    @Override public void setExp(float exp) { player.setExp(exp); }
    @Override public void giveExp(int xp) { player.setExp(player.getExp() + xp / 100f); }
    @Override public void giveExpLevels(int amount) { player.setLevel(player.getLevel() + amount); }
    @Override public int getExpToLevel() { return 7; }

    @Override public boolean isSneaking() { return player.isSneaking(); }
    @Override public void setSneaking(boolean sneak) { player.setSneaking(sneak); }
    @Override public boolean isSprinting() { return player.isSprinting(); }
    @Override public void setSprinting(boolean sprint) { player.setSprinting(sprint); }
    @Override public boolean isFlying() { return getGameMode() == org.bukkit.GameMode.CREATIVE || getGameMode() == org.bukkit.GameMode.SPECTATOR; }
    @Override public void setFlying(boolean fly) {}
    @Override public boolean isGliding() { return false; }
    @Override public void setGliding(boolean glide) {}
    @Override public boolean isSwimming() { return false; }
    @Override public void setSwimming(boolean swim) {}

    @Override public void kickPlayer(String message) { player.kick(net.kyori.adventure.text.Component.text(message)); }
    @Override public void chat(String message) {}
    @Override public boolean performCommand(String command) {
        MinecraftServer.getCommandManager().execute(player, command);
        return true;
    }

    private final java.util.Set<org.bukkit.NamespacedKey> discoveredRecipes = java.util.Collections.newSetFromMap(new java.util.concurrent.ConcurrentHashMap<>());
    @Override public java.util.Set<org.bukkit.NamespacedKey> getDiscoveredRecipes() { return discoveredRecipes; }
    @Override public boolean hasDiscoveredRecipe(org.bukkit.NamespacedKey recipe) { return discoveredRecipes.contains(recipe); }
    @Override public void discoverRecipe(org.bukkit.NamespacedKey recipe) { discoveredRecipes.add(recipe); }
    @Override public void undiscoverRecipe(org.bukkit.NamespacedKey recipe) { discoveredRecipes.remove(recipe); }
    @Override public int discoverRecipes(java.util.Collection<org.bukkit.NamespacedKey> recipes) { int count = 0; for (org.bukkit.NamespacedKey r : recipes) { if (discoveredRecipes.add(r)) count++; } return count; }
    @Override public int undiscoverRecipes(java.util.Collection<org.bukkit.NamespacedKey> recipes) { int count = 0; for (org.bukkit.NamespacedKey r : recipes) { if (discoveredRecipes.remove(r)) count++; } return count; }

    @Override public int getBeeStingersInBody() { return 0; }
    @Override public void setBeeStingersInBody(int count) {}
    @Override public void setVisualFire(boolean fire) {}
    @Override public boolean isVisualFire() { return false; }
    @Override public void resetCooldown() {}
    @Override public boolean isConnected() { return player.isOnline(); }

    @Override public void updateInventory() {}
    @Override public void closeInventory() { player.closeInventory(); }
    @Override public org.bukkit.inventory.Inventory getEnderChest() { return null; }
    @Override public boolean isBlocking() { return false; }
    @Override public org.bukkit.Location getBedLocation() { return null; }
    @Override public org.bukkit.inventory.EntityEquipment getEquipment() { return null; }
    @Override public org.bukkit.inventory.ItemStack[] getArmorInventoryContents() { return new org.bukkit.inventory.ItemStack[0]; }
    @Override public float getBodyYaw() { return 0; }
    @Override public void setBodyYaw(float yaw) {}
    @Override public boolean hasLineOfSight(org.bukkit.entity.Entity other) { return false; }
    @Override public boolean hasLineOfSight(org.bukkit.Location location) { return false; }
    @Override public int getArrowsStuck() { return 0; }
    @Override public void setArrowsStuck(int arrows) {}
    @Override public int getArrowCooldown() { return 0; }
    @Override public void setArrowCooldown(int ticks) {}
    @Override public int getBeeStingerCooldown() { return 0; }
    @Override public void setBeeStingerCooldown(int ticks) {}
    @Override public int getBeeStingersStuck() { return 0; }
    @Override public void setBeeStingersStuck(int stingers) {}
    @Override public void swingMainHand() {}
    @Override public void swingOffHand() {}
    @Override public void setCollarColor(org.bukkit.DyeColor collarColor) {}
    @Override public org.bukkit.DyeColor getCollarColor() { return null; }
    @Override public org.bukkit.inventory.ItemStack getActiveItem() { return null; }
    @Override public int getActiveItemRemainingTime() { return 0; }
    @Override public void setRiptiding(boolean riptiding) {}
    @Override public boolean isRiptiding() { return false; }
    @Override public void setSleeping(boolean sleeping) {}
    @Override public boolean isClimbing() { return false; }
    @Override public boolean isRiding() { return false; }
    @Override public void setFrozenTicks(int ticks) {}
    @Override public int getFrozenTicks() { return 0; }
    @Override public boolean isHandRaised() { return false; }
    @Override public void setKiller(org.bukkit.entity.Player killer) {}
    @Override public org.bukkit.entity.Player getKiller() { return null; }
    @Override public org.bukkit.block.Block getTargetBlock(int maxDistance) { return null; }
    @Override public org.bukkit.block.BlockFace getFacing() { return null; }
    @Override public boolean isLeashed() { return false; }
    @Override public void setLeashed(boolean leashed) {}
    @Override public org.bukkit.entity.LivingEntity getLeashHolder() throws IllegalStateException { return null; }
    @Override public boolean setLeashHolder(org.bukkit.entity.Entity holder) { return false; }
    @Override public void playPickupItemAnimation(org.bukkit.entity.Item item, int count) {}
    @Override public void playPickupItemAnimation(org.bukkit.entity.Item item) {}
    @Override public org.bukkit.potion.PotionEffect getPotionEffect(org.bukkit.potion.PotionEffectType type) { return null; }
    @Override public boolean isUndead() { return false; }
    @Override public boolean isInWater() { return false; }
    @Override public boolean isInBubbleColumn() { return false; }
    @Override public boolean isInPowderSnow() { return false; }
    @Override public boolean isTameable() { return false; }
    @Override public boolean isSitting() { return false; }
    @Override public void setSitting(boolean sitting) {}
    @Override public int getExpToDrop() { return 0; }
    @Override
    public org.bukkit.inventory.InventoryView openInventory(org.bukkit.inventory.Inventory inventory) {
        if (inventory instanceof LavenderInventory minv) {
            Inventory msInv = minv.getMinestomInventory();
            try {
                // Fire InventoryCloseEvent for the old inventory (Minestom doesn't do this)
                net.minestom.server.inventory.AbstractInventory oldInv = player.getOpenInventory();
                if (oldInv instanceof Inventory oldMsInv) {
                    LavenderInventory oldTopInv = LavenderInventory.wrap(oldMsInv);
                    if (oldTopInv == null) {
                        String title = oldMsInv.getTitle() != null
                            ? net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer.plainText().serialize(oldMsInv.getTitle())
                            : "Inventory";
                        oldTopInv = new LavenderInventory(oldMsInv, title, null, org.bukkit.inventory.InventoryType.CHEST);
                    }
                    org.bukkit.event.inventory.InventoryCloseEvent closeEvent = new org.bukkit.event.inventory.InventoryCloseEvent(oldTopInv, this);
                    server.getPluginManager().callEvent(closeEvent);
                }
                player.openInventory(msInv);
            } catch (Throwable t) {
                org.slf4j.LoggerFactory.getLogger("LavenderPlayer").error("openInventory failed", t);
            }
        }
        return null;
    }
    @Override public org.bukkit.inventory.InventoryView getOpenInventory() {
        net.minestom.server.inventory.AbstractInventory msInv = player.getOpenInventory();
        if (msInv instanceof Inventory msCustomInv) {
            LavenderInventory topInv = LavenderInventory.wrap(msCustomInv);
            if (topInv == null) {
                String title = msCustomInv.getTitle() != null
                    ? net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer.plainText().serialize(msCustomInv.getTitle())
                    : "Inventory";
                topInv = new LavenderInventory(msCustomInv, title, null, org.bukkit.inventory.InventoryType.CHEST);
            }
            return new LavenderInventoryView(this, topInv, topInv.getType());
        }
        return new PlayerInventoryView(this);
    }

    @Override public void playSound(org.bukkit.Location location, org.bukkit.Sound sound, float volume, float pitch) {}
    @Override public void playSound(org.bukkit.Location location, String sound, float volume, float pitch) {}
    @Override public void stopSound(org.bukkit.Sound sound) {}
    @Override public void stopSound(String sound) {}

    @Override public void sendTitle(String title, String subtitle, int fadeIn, int stay, int fadeOut) {
        net.kyori.adventure.title.Title.Times times = net.kyori.adventure.title.Title.Times.times(
            java.time.Duration.ofMillis(fadeIn * 50L),
            java.time.Duration.ofMillis(stay * 50L),
            java.time.Duration.ofMillis(fadeOut * 50L)
        );
        player.showTitle(net.kyori.adventure.title.Title.title(
            net.kyori.adventure.text.Component.text(title),
            net.kyori.adventure.text.Component.text(subtitle),
            times
        ));
    }
    @Override public void sendTitle(String title, String subtitle) { sendTitle(title, subtitle, 10, 70, 20); }
    @Override public void resetTitle() { player.clearTitle(); }

    @Override public void setPlayerListHeaderFooter(String header, String footer) {
        player.sendPlayerListHeaderAndFooter(
            net.kyori.adventure.text.Component.text(header),
            net.kyori.adventure.text.Component.text(footer)
        );
    }

    @Override public void setResourcePack(String url) {}
    @Override public void setResourcePack(String url, byte[] hash) {}

    @Override public java.net.InetSocketAddress getAddress() {
        java.net.SocketAddress addr = player.getPlayerConnection().getRemoteAddress();
        return addr instanceof java.net.InetSocketAddress isa ? isa : null;
    }
    @Override public java.net.InetSocketAddress getVirtualHost() { return null; }

    @Override public boolean isSleeping() { return false; }
    @Override public int getSleepTicks() { return 0; }
    @Override public org.bukkit.Location getBedSpawnLocation() { return null; }
    @Override public void setBedSpawnLocation(org.bukkit.Location location) {}
    @Override public void setBedSpawnLocation(org.bukkit.Location location, boolean force) {}

    @Override public org.bukkit.WeatherType getPlayerWeather() { return null; }
    @Override public void setPlayerWeather(org.bukkit.WeatherType type) {}
    @Override public void resetPlayerWeather() {}

    @Override public long getPlayerTime() { return 0; }
    @Override public long getPlayerTimeOffset() { return 0; }
    @Override public boolean isPlayerTimeRelative() { return true; }
    @Override public void setPlayerTime(long time, boolean relative) {}
    @Override public void resetPlayerTime() {}

    @Override public void damage(double amount) { player.damage(net.minestom.server.entity.damage.Damage.fromPlayer(player, (float) amount)); }
    @Override public void damage(double amount, org.bukkit.entity.Entity source) { player.damage(net.minestom.server.entity.damage.Damage.fromPlayer(player, (float) amount)); }

    @Override public double getEyeHeight() { return 1.62; }
    @Override public double getEyeHeight(boolean ignoreSneaking) { return 1.62; }
    @Override
    public org.bukkit.Location getEyeLocation() {
        Pos pos = player.getPosition().add(0, 1.62, 0);
        return new org.bukkit.Location(getWorld(), pos.x(), pos.y(), pos.z(), pos.yaw(), pos.pitch());
    }

    @Override public org.bukkit.block.Block getTargetBlock(java.util.Set<org.bukkit.Material> transparent, int maxDistance) { return null; }
    @Override public java.util.List<org.bukkit.block.Block> getLastTwoTargetBlocks(java.util.Set<org.bukkit.Material> transparent, int maxDistance) { return java.util.Collections.emptyList(); }

    @Override
    public org.bukkit.block.Block getTargetBlockExact(int maxDistance) {
        return getTargetBlockExact(maxDistance, null);
    }

    @Override
    public org.bukkit.block.Block getTargetBlockExact(int maxDistance, java.util.Set<org.bukkit.Material> transparent) {
        Pos pos = player.getPosition();
        net.minestom.server.coordinate.Vec dir = pos.direction();
        double eyeX = pos.x();
        double eyeY = pos.y() + player.getEyeHeight();
        double eyeZ = pos.z();
        org.bukkit.World world = getWorld();
        if (world == null) return null;
        double stepSize = 0.5;
        int steps = (int) (maxDistance / stepSize);
        for (int i = 0; i < steps; i++) {
            int bx = (int) Math.floor(eyeX + dir.x() * stepSize * i);
            int by = (int) Math.floor(eyeY + dir.y() * stepSize * i);
            int bz = (int) Math.floor(eyeZ + dir.z() * stepSize * i);
            org.bukkit.block.Block block = world.getBlockAt(bx, by, bz);
            if (block == null) continue;
            if (block.isEmpty()) continue;
            if (transparent != null && transparent.contains(block.getType())) continue;
            return block;
        }
        return null;
    }

    @Override
    public void setAllowFlight(boolean flight) {
        player.setAllowFlying(flight);
    }

    @Override
    public boolean getAllowFlight() {
        return player.isAllowFlying();
    }

    @Override public void sendBlockChange(org.bukkit.Location loc, org.bukkit.Material material, byte data) {}
    @Override public void sendBlockChange(org.bukkit.Location loc, org.bukkit.block.data.BlockData blockData) {}
    @Override public void sendSignChange(org.bukkit.Location loc, String[] lines) {}

    @Override public void setCooldown(org.bukkit.Material material, int ticks) {}
    @Override public int getCooldown(org.bukkit.Material material) { return 0; }
    @Override public boolean hasCooldown(org.bukkit.Material material) { return false; }

    @Override public long getFirstPlayed() { return 0; }
    @Override public long getLastPlayed() { return 0; }
    @Override public boolean hasPlayedBefore() { return false; }
    @Override public long getLastSeen() { return 0; }
    @Override public org.bukkit.entity.Player getPlayer() { return this; }

    @Override public java.util.Map<String, Object> serialize() { return new java.util.HashMap<>(); }

    @Override
    public org.bukkit.util.Vector getVelocity() {
        net.minestom.server.coordinate.Vec v = player.getVelocity();
        return new org.bukkit.util.Vector(v.x(), v.y(), v.z());
    }

    @Override
    public void setVelocity(org.bukkit.util.Vector velocity) {
        player.setVelocity(new net.minestom.server.coordinate.Vec(velocity.getX(), velocity.getY(), velocity.getZ()));
    }

    @Override public boolean isOnGround() { return player.isOnGround(); }
    @Override public boolean isDead() { return player.isDead(); }
    @Override public boolean isValid() { return player.isOnline(); }
    @Override public void remove() { player.remove(); }
    @Override public int getEntityId() { return player.getEntityId(); }
    @Override public int getFireTicks() { return 0; }
    @Override public void setFireTicks(int ticks) {}
    @Override public int getMaxFireTicks() { return 20; }
    @Override public float getFallDistance() { return 0; }
    @Override public void setFallDistance(float distance) {}
    @Override public boolean isInsideVehicle() { return false; }
    @Override public boolean leaveVehicle() { return false; }
    @Override public org.bukkit.entity.Entity getVehicle() { return null; }

    @Override public void setCustomName(String name) {
        if (name == null || name.isEmpty()) {
            player.setCustomName(null);
        } else {
            player.setCustomName(legacyToComponent(name));
        }
    }
    @Override
    public String getCustomName() {
        net.kyori.adventure.text.Component name = player.getCustomName();
        return name != null ? net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer.plainText().serialize(name) : null;
    }
    @Override public void setCustomNameVisible(boolean flag) { player.setCustomNameVisible(flag); }
    @Override public boolean isCustomNameVisible() { return player.isCustomNameVisible(); }
    @Override public void setGlowing(boolean flag) { player.setGlowing(flag); }
    @Override public boolean isGlowing() { return player.isGlowing(); }
    @Override public void setInvulnerable(boolean flag) { player.setInvulnerable(flag); }
    @Override public boolean isInvulnerable() { return player.isInvulnerable(); }
    @Override public void setSilent(boolean flag) {}
    @Override public boolean isSilent() { return false; }
    @Override public boolean hasGravity() { return true; }
    @Override public void setGravity(boolean gravity) {}
    @Override public int getPortalCooldown() { return 0; }
    @Override public void setPortalCooldown(int cooldown) {}
    @Override public int getTicksLived() { return 0; }
    @Override public void setTicksLived(int value) {}
    @Override public boolean isOp() { return true; }
    @Override public void setOp(boolean value) {}

    @Override public void setMetadata(String metadataKey, org.bukkit.metadata.MetadataValue metadataValue) {}
    @Override public java.util.List<org.bukkit.metadata.MetadataValue> getMetadata(String metadataKey) { return java.util.Collections.emptyList(); }
    @Override public boolean hasMetadata(String metadataKey) { return false; }
    @Override public void removeMetadata(String metadataKey, org.bukkit.plugin.Plugin plugin) {}

    @Override public org.bukkit.entity.EntityType getType() { return org.bukkit.entity.EntityType.PLAYER; }

    @Override public boolean isPermissionSet(String name) { return false; }
    @Override public boolean isPermissionSet(org.bukkit.permissions.Permission perm) { return false; }
    @Override public boolean hasPermission(String name) { return true; }
    @Override public boolean hasPermission(org.bukkit.permissions.Permission perm) { return true; }
    @Override public org.bukkit.permissions.PermissionAttachment addAttachment(org.bukkit.plugin.Plugin plugin, String name, boolean value) { org.bukkit.permissions.PermissionAttachment att = new org.bukkit.permissions.PermissionAttachment(plugin); att.setPermission(name, value); return att; }
    @Override public org.bukkit.permissions.PermissionAttachment addAttachment(org.bukkit.plugin.Plugin plugin) { return new org.bukkit.permissions.PermissionAttachment(plugin); }
    @Override public org.bukkit.permissions.PermissionAttachment addAttachment(org.bukkit.plugin.Plugin plugin, String name, boolean value, int ticks) { org.bukkit.permissions.PermissionAttachment att = new org.bukkit.permissions.PermissionAttachment(plugin); att.setPermission(name, value); return att; }
    @Override public org.bukkit.permissions.PermissionAttachment addAttachment(org.bukkit.plugin.Plugin plugin, int ticks) { return new org.bukkit.permissions.PermissionAttachment(plugin); }
    @Override public void removeAttachment(org.bukkit.permissions.PermissionAttachment attachment) {}
    @Override public void recalculatePermissions() {}
    @Override public java.util.Set<org.bukkit.permissions.PermissionAttachment> getEffectivePermissions() { return java.util.Collections.emptySet(); }

    @Override public org.bukkit.Server getServer() { return server; }
    @Override public org.bukkit.command.ConsoleCommandSender getConsoleSender() { return server.getConsoleSender(); }

    @Override public boolean eject() {
        if (player.getVehicle() != null) {
            player.getVehicle().removePassenger(player);
            return true;
        }
        return false;
    }

    @Override public org.bukkit.entity.Player.Spigot spigot() { return new org.bukkit.entity.Player.Spigot(this); }

    @Override
    public java.util.Collection<org.bukkit.potion.PotionEffect> getActivePotionEffects() {
        return java.util.Collections.emptyList();
    }
    @Override public boolean addPotionEffect(org.bukkit.potion.PotionEffect effect) { return false; }
    @Override public boolean addPotionEffect(org.bukkit.potion.PotionEffect effect, boolean force) { return false; }
    @Override public boolean removePotionEffect(org.bukkit.potion.PotionEffectType effect) { return false; }
    @Override public boolean hasPotionEffect(org.bukkit.potion.PotionEffectType type) { return false; }

    @Override public void setFreezeTicks(int ticks) {}
    @Override public int getFreezeTicks() { return 0; }
    @Override public boolean isFrozen() { return false; }

    @Override public void setWalkSpeed(float speed) {}
    @Override public float getWalkSpeed() { return 0.2f; }
    @Override public void setFlySpeed(float speed) {}
    @Override public float getFlySpeed() { return 0.1f; }

    @Override public void setMaximumAir(int ticks) {}
    @Override public int getMaximumAir() { return 300; }
    @Override public void setRemainingAir(int ticks) {}
    @Override public int getRemainingAir() { return 300; }

    @Override public void setCollidable(boolean collidable) {}
    @Override public boolean isCollidable() { return true; }

    @Override public void setAI(boolean ai) {}
    @Override public boolean hasAI() { return true; }

    @Override public void setCanPickupItems(boolean pickup) {}
    @Override public boolean getCanPickupItems() { return true; }

    @Override public void setNoPhysics(boolean noPhysics) {}

    @Override public void setSimulationDistance(int distance) {}
    @Override public void setViewDistance(int distance) {}
    @Override public int getClientViewDistance() { return 10; }

    @Override public void setScoreboard(org.bukkit.scoreboard.Scoreboard scoreboard) {}
    @Override public org.bukkit.scoreboard.Scoreboard getScoreboard() { return null; }

    @Override public void setWorldBorder(org.bukkit.WorldBorder border) {}

    @Override public void setNoDamageTicks(int ticks) {}
    @Override public int getNoDamageTicks() { return 0; }
    @Override public void setMaximumNoDamageTicks(int ticks) {}
    @Override public int getMaximumNoDamageTicks() { return 20; }

    @Override public void setArrowsInBody(int arrows) {}
    @Override public int getArrowsInBody() { return 0; }

    @Override public void setRespawnLocation(org.bukkit.Location location) {}
    @Override public org.bukkit.Location getRespawnLocation() { return null; }

    @Override public org.bukkit.attribute.AttributeInstance getAttribute(org.bukkit.attribute.Attribute attribute) { return null; }

    @Override public void hidePlayer(org.bukkit.plugin.Plugin plugin, org.bukkit.entity.Player player) {}
    @Override public void showPlayer(org.bukkit.plugin.Plugin plugin, org.bukkit.entity.Player player) {}
    @Override public void showEntity(org.bukkit.plugin.Plugin plugin, org.bukkit.entity.Entity entity) {}
    @Override public void hideEntity(org.bukkit.plugin.Plugin plugin, org.bukkit.entity.Entity entity) {}
    @Override public boolean canSee(org.bukkit.entity.Player player) { return true; }

    @Override public void spawnParticle(org.bukkit.Particle particle, org.bukkit.Location location, int count) {
        spawnParticle(particle, location.getX(), location.getY(), location.getZ(), count, 0, 0, 0, 0, null);
    }
    @Override public void spawnParticle(org.bukkit.Particle particle, double x, double y, double z, int count) {
        spawnParticle(particle, x, y, z, count, 0, 0, 0, 0, null);
    }
    @Override public void spawnParticle(org.bukkit.Particle particle, org.bukkit.Location location, int count, double offsetX, double offsetY, double offsetZ) {
        spawnParticle(particle, location.getX(), location.getY(), location.getZ(), count, offsetX, offsetY, offsetZ, 0, null);
    }
    @Override public void spawnParticle(org.bukkit.Particle particle, double x, double y, double z, int count, double offsetX, double offsetY, double offsetZ) {
        spawnParticle(particle, x, y, z, count, offsetX, offsetY, offsetZ, 0, null);
    }
    @Override public void spawnParticle(org.bukkit.Particle particle, org.bukkit.Location location, int count, double offsetX, double offsetY, double offsetZ, double extra) {
        spawnParticle(particle, location.getX(), location.getY(), location.getZ(), count, offsetX, offsetY, offsetZ, extra, null);
    }
    @Override public void spawnParticle(org.bukkit.Particle particle, double x, double y, double z, int count, double offsetX, double offsetY, double offsetZ, double extra) {
        spawnParticle(particle, x, y, z, count, offsetX, offsetY, offsetZ, extra, null);
    }
    @Override public <T> void spawnParticle(org.bukkit.Particle particle, org.bukkit.Location location, int count, T data) {
        spawnParticle(particle, location.getX(), location.getY(), location.getZ(), count, 0, 0, 0, 0, data);
    }
    @Override public <T> void spawnParticle(org.bukkit.Particle particle, double x, double y, double z, int count, T data) {
        spawnParticle(particle, x, y, z, count, 0, 0, 0, 0, data);
    }
    @Override public <T> void spawnParticle(org.bukkit.Particle particle, org.bukkit.Location location, int count, double offsetX, double offsetY, double offsetZ, T data) {
        spawnParticle(particle, location.getX(), location.getY(), location.getZ(), count, offsetX, offsetY, offsetZ, 0, data);
    }
    @Override public <T> void spawnParticle(org.bukkit.Particle particle, double x, double y, double z, int count, double offsetX, double offsetY, double offsetZ, T data) {
        spawnParticle(particle, x, y, z, count, offsetX, offsetY, offsetZ, 0, data);
    }
    @Override public <T> void spawnParticle(org.bukkit.Particle particle, org.bukkit.Location location, int count, double offsetX, double offsetY, double offsetZ, double extra, T data) {
        spawnParticle(particle, location.getX(), location.getY(), location.getZ(), count, offsetX, offsetY, offsetZ, extra, data);
    }
    @Override public <T> void spawnParticle(org.bukkit.Particle particle, double x, double y, double z, int count, double offsetX, double offsetY, double offsetZ, double extra, T data) {
        try {
            Particle msParticle = Particle.fromKey(
                net.kyori.adventure.key.Key.key("minecraft", particle.name().toLowerCase())
            );
            if (msParticle == null) return;
            ParticlePacket packet = new ParticlePacket(
                msParticle, false, true, x, y, z,
                (float) offsetX, (float) offsetY, (float) offsetZ,
                (float) extra, count
            );
            player.sendPacket(packet);
        } catch (Throwable t) {
            logger.error("Failed to spawn particle: " + t.getMessage());
        }
    }

    @Override public void showTitle(net.kyori.adventure.title.Title title) { player.showTitle(title); }
    @Override public void stopAllSounds() {}
    @Override public void sendSignChange(org.bukkit.Location loc, java.util.List<net.kyori.adventure.text.Component> lines) {}
    @Override public void sendMessage(net.kyori.adventure.text.Component message) { player.sendMessage(message); }
    @Override public void sendActionBar(net.kyori.adventure.text.Component message) {
        player.sendActionBar(message);
    }
    @Override public void sendResourcePacks(net.kyori.adventure.resource.ResourcePackRequest request) {}
    @Override public void removeResourcePacks() {}
    @Override public String getLocale() {
        try {
            java.util.Locale locale = player.getSettings().locale();
            return locale != null ? locale.toString() : "en_US";
        } catch (Throwable t) {
            return "en_US";
        }
    }

    @Override public java.lang.Iterable<org.bukkit.boss.BossBar> activeBossBars() { return java.util.Collections.emptyList(); }

    @Override public org.bukkit.entity.Entity releaseLeftShoulderEntity() { return null; }
    @Override public org.bukkit.entity.Entity releaseRightShoulderEntity() { return null; }

    @Override
    public com.mojang.authlib.GameProfile getProfile() {
        com.mojang.authlib.GameProfile profile = new com.mojang.authlib.GameProfile(player.getUuid(), player.getUsername());
        net.minestom.server.entity.PlayerSkin skin = player.getSkin();
        if (skin != null && skin.textures() != null) {
            profile.putProperty("textures", new com.mojang.authlib.properties.Property("textures", skin.textures(), skin.signature()));
        }
        return profile;
    }

    @Override
    public net.minecraft.server.level.ServerPlayer getHandle() {
        return new net.minecraft.server.level.ServerPlayer(player);
    }
}
