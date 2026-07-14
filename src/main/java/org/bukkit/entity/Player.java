package org.bukkit.entity;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import java.util.UUID;

public interface Player extends HumanEntity, org.bukkit.OfflinePlayer, org.bukkit.command.CommandSender, org.bukkit.permissions.Permissible, org.bukkit.configuration.serialization.ConfigurationSerializable {

    UUID getUniqueId();
    String getName();
    String getDisplayName();
    void setDisplayName(String name);
    String getPlayerListName();
    void setPlayerListName(String name);
    void sendMessage(String message);
    void sendMessage(String[] messages);
    void sendMessage(UUID sender, String message);
    void sendMessage(UUID sender, String[] messages);
    void sendRawMessage(String message);
    void sendRawMessage(UUID sender, String message);

    World getWorld();
    Location getLocation();
    boolean teleport(Location location);
    boolean teleport(Entity destination);
    java.util.concurrent.CompletableFuture<Boolean> teleportAsync(Location location);
    java.util.concurrent.CompletableFuture<Boolean> teleportAsync(org.bukkit.World world, Location location);

    GameMode getGameMode();
    void setGameMode(GameMode mode);

    boolean isOnline();
    boolean isBanned();
    void setBanned(boolean banned);
    boolean isWhitelisted();
    void setWhitelisted(boolean value);

    PlayerInventory getInventory();
    ItemStack getItemInHand();
    void setItemInHand(ItemStack item);
    ItemStack getItemOnCursor();
    void setItemOnCursor(ItemStack item);
    boolean hasInventorySpace();

    double getHealth();
    void setHealth(double health);
    double getMaxHealth();
    void setMaxHealth(double maxHealth);
    double getHealthScale();
    void setHealthScale(double scale);

    int getFoodLevel();
    void setFoodLevel(int value);
    float getExhaustion();
    void setExhaustion(float value);
    float getSaturation();
    void setSaturation(float value);

    int getLevel();
    void setLevel(int level);
    int getTotalExperience();
    void setTotalExperience(int xp);
    float getExp();
    void setExp(float exp);
    void giveExp(int xp);

    boolean isSneaking();
    void setSneaking(boolean sneak);
    boolean isSprinting();
    void setSprinting(boolean sprint);
    boolean isFlying();
    void setFlying(boolean fly);
    boolean isGliding();
    void setGliding(boolean glide);
    boolean isSwimming();
    void setSwimming(boolean swim);

    void kickPlayer(String message);
    void chat(String message);
    boolean performCommand(String command);

    java.util.Set<org.bukkit.NamespacedKey> getDiscoveredRecipes();
    boolean hasDiscoveredRecipe(org.bukkit.NamespacedKey recipe);
    void discoverRecipe(org.bukkit.NamespacedKey recipe);
    void undiscoverRecipe(org.bukkit.NamespacedKey recipe);
    int discoverRecipes(java.util.Collection<org.bukkit.NamespacedKey> recipes);
    int undiscoverRecipes(java.util.Collection<org.bukkit.NamespacedKey> recipes);

    int getBeeStingersInBody();
    void setBeeStingersInBody(int count);
    void setVisualFire(boolean fire);
    boolean isVisualFire();
    void resetCooldown();
    boolean isConnected();

    void updateInventory();
    void closeInventory();
    org.bukkit.inventory.InventoryView openInventory(org.bukkit.inventory.Inventory inventory);
    org.bukkit.inventory.InventoryView getOpenInventory();

    void playSound(Location location, org.bukkit.Sound sound, float volume, float pitch);
    void playSound(Location location, String sound, float volume, float pitch);
    void stopSound(org.bukkit.Sound sound);
    void stopSound(String sound);

    void sendTitle(String title, String subtitle, int fadeIn, int stay, int fadeOut);
    void sendTitle(String title, String subtitle);
    void resetTitle();

    void setPlayerListHeaderFooter(String header, String footer);

    void setResourcePack(String url);
    void setResourcePack(String url, byte[] hash);

    java.net.InetSocketAddress getAddress();
    java.net.InetSocketAddress getVirtualHost();

    boolean isSleeping();
    int getSleepTicks();

    org.bukkit.Location getBedSpawnLocation();
    void setBedSpawnLocation(org.bukkit.Location location);
    void setBedSpawnLocation(org.bukkit.Location location, boolean force);

    org.bukkit.WeatherType getPlayerWeather();
    void setPlayerWeather(org.bukkit.WeatherType type);
    void resetPlayerWeather();

    long getPlayerTime();
    long getPlayerTimeOffset();
    boolean isPlayerTimeRelative();
    void setPlayerTime(long time, boolean relative);
    void resetPlayerTime();

    void giveExpLevels(int amount);
    int getExpToLevel();

    void damage(double amount);
    void damage(double amount, org.bukkit.entity.Entity source);

    double getEyeHeight();
    double getEyeHeight(boolean ignoreSneaking);
    org.bukkit.Location getEyeLocation();

    org.bukkit.block.Block getTargetBlock(java.util.Set<org.bukkit.Material> transparent, int maxDistance);
    java.util.List<org.bukkit.block.Block> getLastTwoTargetBlocks(java.util.Set<org.bukkit.Material> transparent, int maxDistance);
    org.bukkit.block.Block getTargetBlockExact(int maxDistance);
    org.bukkit.block.Block getTargetBlockExact(int maxDistance, java.util.Set<org.bukkit.Material> transparent);
    void setAllowFlight(boolean flight);
    boolean getAllowFlight();

    void sendBlockChange(org.bukkit.Location loc, org.bukkit.Material material, byte data);
    void sendBlockChange(org.bukkit.Location loc, org.bukkit.block.data.BlockData blockData);
    void sendSignChange(org.bukkit.Location loc, String[] lines);

    void setCooldown(org.bukkit.Material material, int ticks);
    int getCooldown(org.bukkit.Material material);
    boolean hasCooldown(org.bukkit.Material material);

    long getFirstPlayed();
    long getLastPlayed();
    boolean hasPlayedBefore();

    java.util.Map<String, Object> serialize();

    java.util.Collection<org.bukkit.potion.PotionEffect> getActivePotionEffects();
    boolean addPotionEffect(org.bukkit.potion.PotionEffect effect);
    boolean removePotionEffect(org.bukkit.potion.PotionEffectType effect);
    boolean hasPotionEffect(org.bukkit.potion.PotionEffectType type);

    boolean eject();

    Spigot spigot();

    void setFreezeTicks(int ticks);
    int getFreezeTicks();
    boolean isFrozen();

    void setWalkSpeed(float speed);
    float getWalkSpeed();
    void setFlySpeed(float speed);
    float getFlySpeed();

    void setMaximumAir(int ticks);
    int getMaximumAir();
    void setRemainingAir(int ticks);
    int getRemainingAir();

    void setCollidable(boolean collidable);
    boolean isCollidable();

    void setAI(boolean ai);
    boolean hasAI();

    void setCanPickupItems(boolean pickup);
    boolean getCanPickupItems();

    void setNoPhysics(boolean noPhysics);

    void setSimulationDistance(int distance);
    void setViewDistance(int distance);
    int getClientViewDistance();

    void setScoreboard(org.bukkit.scoreboard.Scoreboard scoreboard);
    org.bukkit.scoreboard.Scoreboard getScoreboard();

    void setWorldBorder(org.bukkit.WorldBorder border);

    void setNoDamageTicks(int ticks);
    int getNoDamageTicks();
    void setMaximumNoDamageTicks(int ticks);
    int getMaximumNoDamageTicks();

    void setArrowsInBody(int arrows);
    int getArrowsInBody();

    void setRespawnLocation(org.bukkit.Location location);
    org.bukkit.Location getRespawnLocation();

    org.bukkit.attribute.AttributeInstance getAttribute(org.bukkit.attribute.Attribute attribute);

    void hidePlayer(org.bukkit.plugin.Plugin plugin, org.bukkit.entity.Player player);
    void showPlayer(org.bukkit.plugin.Plugin plugin, org.bukkit.entity.Player player);
    void showEntity(org.bukkit.plugin.Plugin plugin, org.bukkit.entity.Entity entity);
    void hideEntity(org.bukkit.plugin.Plugin plugin, org.bukkit.entity.Entity entity);
    boolean canSee(org.bukkit.entity.Player player);

    void spawnParticle(org.bukkit.Particle particle, org.bukkit.Location location, int count);
    void spawnParticle(org.bukkit.Particle particle, double x, double y, double z, int count);
    void spawnParticle(org.bukkit.Particle particle, org.bukkit.Location location, int count, double offsetX, double offsetY, double offsetZ);
    void spawnParticle(org.bukkit.Particle particle, double x, double y, double z, int count, double offsetX, double offsetY, double offsetZ);
    void spawnParticle(org.bukkit.Particle particle, org.bukkit.Location location, int count, double offsetX, double offsetY, double offsetZ, double extra);
    void spawnParticle(org.bukkit.Particle particle, double x, double y, double z, int count, double offsetX, double offsetY, double offsetZ, double extra);
    <T> void spawnParticle(org.bukkit.Particle particle, org.bukkit.Location location, int count, T data);
    <T> void spawnParticle(org.bukkit.Particle particle, double x, double y, double z, int count, T data);
    <T> void spawnParticle(org.bukkit.Particle particle, org.bukkit.Location location, int count, double offsetX, double offsetY, double offsetZ, T data);
    <T> void spawnParticle(org.bukkit.Particle particle, double x, double y, double z, int count, double offsetX, double offsetY, double offsetZ, T data);
    <T> void spawnParticle(org.bukkit.Particle particle, org.bukkit.Location location, int count, double offsetX, double offsetY, double offsetZ, double extra, T data);
    <T> void spawnParticle(org.bukkit.Particle particle, double x, double y, double z, int count, double offsetX, double offsetY, double offsetZ, double extra, T data);

    void showTitle(net.kyori.adventure.title.Title title);
    void stopAllSounds();
    void sendSignChange(org.bukkit.Location loc, java.util.List<net.kyori.adventure.text.Component> lines);
    void sendMessage(net.kyori.adventure.text.Component message);
    void sendActionBar(net.kyori.adventure.text.Component message);
    void sendResourcePacks(net.kyori.adventure.resource.ResourcePackRequest request);
    void removeResourcePacks();
    String getLocale();

    java.lang.Iterable<org.bukkit.boss.BossBar> activeBossBars();

    org.bukkit.entity.Entity releaseLeftShoulderEntity();
    org.bukkit.entity.Entity releaseRightShoulderEntity();

    class Spigot {
        private Player player;

        public Spigot() {}
        public Spigot(Player player) { this.player = player; }

        private Player player() {
            if (player == null) throw new IllegalStateException("Spigot not bound to a player");
            return player;
        }

        public void sendMessage(net.md_5.bungee.api.chat.BaseComponent... components) {
            send(net.md_5.bungee.api.ChatMessageType.CHAT, components);
        }

        public void sendMessage(net.md_5.bungee.api.ChatColor flag, net.md_5.bungee.api.chat.BaseComponent... components) {
            net.md_5.bungee.api.chat.TextComponent prefix = new net.md_5.bungee.api.chat.TextComponent();
            prefix.setColor(flag);
            net.md_5.bungee.api.chat.BaseComponent[] prefixed = new net.md_5.bungee.api.chat.BaseComponent[components.length + 1];
            prefixed[0] = prefix;
            System.arraycopy(components, 0, prefixed, 1, components.length);
            send(net.md_5.bungee.api.ChatMessageType.CHAT, prefixed);
        }

        public void sendMessage(net.md_5.bungee.api.chat.BaseComponent component) {
            send(net.md_5.bungee.api.ChatMessageType.CHAT, new net.md_5.bungee.api.chat.BaseComponent[]{component});
        }

        public void sendMessage(net.md_5.bungee.api.ChatColor flag, net.md_5.bungee.api.chat.BaseComponent component) {
            sendMessage(flag, new net.md_5.bungee.api.chat.BaseComponent[]{component});
        }

        public void sendMessage(net.md_5.bungee.api.ChatMessageType type, net.md_5.bungee.api.chat.BaseComponent... components) {
            send(type, components);
        }

        private void send(net.md_5.bungee.api.ChatMessageType type, net.md_5.bungee.api.chat.BaseComponent[] components) {
            Player p = player();
            StringBuilder sb = new StringBuilder();
            for (net.md_5.bungee.api.chat.BaseComponent c : components) {
                if (c instanceof net.md_5.bungee.api.chat.TextComponent tc) {
                    sb.append(tc.getText());
                }
            }
            String text = sb.toString();
            if (type == net.md_5.bungee.api.ChatMessageType.ACTION_BAR) {
                p.sendActionBar(net.kyori.adventure.text.Component.text(text));
            } else {
                p.sendMessage(text);
            }
        }

        public void respawn() {}
    }
}
