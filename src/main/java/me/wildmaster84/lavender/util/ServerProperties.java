package me.wildmaster84.lavender.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;

public class ServerProperties {

    private static final Logger log = LoggerFactory.getLogger("Lavender");

    private final Path path;
    private final Map<String, String> properties = new LinkedHashMap<>();

    private static final Map<String, String> DEFAULTS = new LinkedHashMap<>();
    static {
        DEFAULTS.put("server-port", "25565");
        DEFAULTS.put("server-ip", "0.0.0.0");
        DEFAULTS.put("motd", "Lavender Server");
        DEFAULTS.put("rcon-enabled", "true");
        DEFAULTS.put("rcon-port", "25575");
        DEFAULTS.put("rcon-password", "lavender");
        DEFAULTS.put("max-players", "20");
        DEFAULTS.put("view-distance", "10");
        DEFAULTS.put("simulation-distance", "8");
        DEFAULTS.put("online-mode", "false");
        DEFAULTS.put("pvp", "true");
        DEFAULTS.put("allow-flight", "false");
        DEFAULTS.put("level-seed", "-52667595");
        DEFAULTS.put("gamemode", "creative");
        DEFAULTS.put("difficulty", "peaceful");
        DEFAULTS.put("spawn-protection", "0");
        DEFAULTS.put("enable-command-block", "false");
        DEFAULTS.put("resource-pack", "");
        DEFAULTS.put("require-resource-pack", "false");
    }

    public ServerProperties(File file) {
        this.path = file.toPath();
        load();
    }

    private void load() {
        properties.putAll(DEFAULTS);

        if (Files.exists(path)) {
            try (BufferedReader reader = Files.newBufferedReader(path)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    line = line.trim();
                    if (line.isEmpty() || line.startsWith("#")) continue;
                    int eq = line.indexOf('=');
                    if (eq < 0) continue;
                    String key = line.substring(0, eq).trim();
                    String value = line.substring(eq + 1).trim();
                    properties.put(key, value);
                }
            } catch (IOException e) {
                log.warn("Failed to read server.properties: " + e.getMessage());
            }
        } else {
            log.info("server.properties not found, generating with defaults");
            save();
        }
    }

    private void save() {
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write("# Lavender server properties");
            writer.newLine();
            for (Map.Entry<String, String> entry : properties.entrySet()) {
                writer.write(entry.getKey() + "=" + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            log.warn("Failed to write server.properties: " + e.getMessage());
        }
    }

    public String get(String key) {
        return properties.getOrDefault(key, DEFAULTS.get(key));
    }

    public String get(String key, String def) {
        return properties.getOrDefault(key, def);
    }

    public int getInt(String key, int def) {
        try {
            return Integer.parseInt(properties.getOrDefault(key, String.valueOf(def)));
        } catch (NumberFormatException e) {
            return def;
        }
    }

    public boolean getBoolean(String key, boolean def) {
        String val = properties.get(key);
        if (val == null) return def;
        return Boolean.parseBoolean(val);
    }

    public long getLong(String key, long def) {
        try {
            return Long.parseLong(properties.getOrDefault(key, String.valueOf(def)));
        } catch (NumberFormatException e) {
            log.error(e.getMessage());
            return def;
        }
    }

    public int getServerPort() { return getInt("server-port", 25565); }
    public String getServerIp() { return get("server-ip", "0.0.0.0"); }
    public String getMotd() { return get("motd", "Lavender Server"); }
    public boolean isRconEnabled() { return getBoolean("rcon-enabled", true); }
    public int getRconPort() { return getInt("rcon-port", 25575); }
    public String getRconPassword() { return get("rcon-password", "lavender"); }
    public int getMaxPlayers() { return getInt("max-players", 20); }
    public int getViewDistance() { return getInt("view-distance", 10); }
    public int getSimulationDistance() { return getInt("simulation-distance", 8); }
    public boolean isOnlineMode() { return getBoolean("online-mode", false); }
    public boolean isPvp() { return getBoolean("pvp", true); }
    public boolean isAllowFlight() { return getBoolean("allow-flight", false); }
    public long getLevelSeed() { return getLong("level-seed", 0); }
    public String getGamemode() { return get("gamemode", "creative"); }
    public String getDifficulty() { return get("difficulty", "peaceful"); }
    public int getSpawnProtection() { return getInt("spawn-protection", 0); }
    public String getResourcePack() { return get("resource-pack", ""); }
    public boolean isRequireResourcePack() { return getBoolean("require-resource-pack", false); }
}
