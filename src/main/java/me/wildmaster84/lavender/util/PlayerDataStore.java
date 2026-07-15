package me.wildmaster84.lavender.util;

import org.bukkit.configuration.file.YamlConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.wildmaster84.adapter.Lavender;

import java.io.File;
import java.util.UUID;

public class PlayerDataStore {
    private static final Logger log = LoggerFactory.getLogger(Lavender.BRAND_NAME);
    private final File file;
    private YamlConfiguration config;

    public PlayerDataStore(File dataDir) {
        if (!dataDir.exists()) dataDir.mkdirs();
        this.file = new File(dataDir, "players.yml");
        load();
    }

    private void load() {
        if (file.exists()) {
            config = YamlConfiguration.loadConfiguration(file);
        } else {
            config = new YamlConfiguration();
        }
    }

    private void save() {
        try {
            config.save(file);
        } catch (Exception e) {
            log.warn("Failed to save players.yml: {}", e.getMessage());
        }
    }

    public void savePlayer(UUID uuid, String worldName, double x, double y, double z, float yaw, float pitch) {
        String key = uuid.toString();
        config.set(key + ".world", worldName);
        config.set(key + ".x", x);
        config.set(key + ".y", y);
        config.set(key + ".z", z);
        config.set(key + ".yaw", (double) yaw);
        config.set(key + ".pitch", (double) pitch);
        save();
    }

    public String getWorld(UUID uuid) {
        return config.getString(uuid.toString() + ".world");
    }

    public double[] getPosition(UUID uuid) {
        String key = uuid.toString();
        if (!config.contains(key + ".x")) return null;
        return new double[] {
            config.getDouble(key + ".x"),
            config.getDouble(key + ".y"),
            config.getDouble(key + ".z"),
            config.getDouble(key + ".yaw"),
            config.getDouble(key + ".pitch")
        };
    }

    public boolean hasData(UUID uuid) {
        return config.contains(uuid.toString() + ".world");
    }

    public void clearPlayer(UUID uuid) {
        config.set(uuid.toString(), null);
        save();
    }
}
