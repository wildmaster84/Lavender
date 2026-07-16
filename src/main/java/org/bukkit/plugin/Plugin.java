package org.bukkit.plugin;

public interface Plugin {
    String getName();
    boolean isEnabled();
    void onEnable();
    void onDisable();
    default void onLoad() {}
    org.bukkit.plugin.PluginDescriptionFile getDescription();
    org.bukkit.Server getServer();
    java.util.logging.Logger getLogger();
    java.io.File getDataFolder();
    java.io.InputStream getResource(String filename);
    void saveResource(String filename, boolean replace);
}
