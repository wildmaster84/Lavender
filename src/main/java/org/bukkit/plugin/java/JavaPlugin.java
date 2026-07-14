package org.bukkit.plugin.java;

import org.bukkit.Server;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.Command;
import org.bukkit.command.TabCompleter;
import org.bukkit.command.PluginCommand;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public abstract class JavaPlugin implements Plugin, CommandExecutor, TabCompleter {
    private Server server;
    private PluginDescriptionFile description;
    private File dataFolder;
    private Logger logger;
    private boolean enabled = false;
    private FileConfiguration config = null;
    private final Map<String, PluginCommand> registeredCommands = new java.util.HashMap<>();
    private ClassLoader classLoader;

    protected JavaPlugin() {}

    public void init(Server server, PluginDescriptionFile description, File dataFolder, ClassLoader classLoader) {
        this.server = server;
        this.description = description;
        this.dataFolder = dataFolder;
        this.classLoader = classLoader;
        this.logger = Logger.getLogger(description.getName());
        if (!dataFolder.exists()) {
            dataFolder.mkdirs();
        }
    }

    public void init(Server server, PluginDescriptionFile description, File dataFolder) {
        init(server, description, dataFolder, getClass().getClassLoader());
    }

    protected ClassLoader getClassLoader() { return classLoader; }

    @Override public String getName() { return description.getName(); }
    @Override public boolean isEnabled() { return enabled; }
    public void setEnabled(boolean enabled) { this.enabled = enabled; }
    @Override public abstract void onEnable();
    @Override public void onDisable() {}
    public void onLoad() {}
    @Override public PluginDescriptionFile getDescription() { return description; }

    @Override public Server getServer() { return server; }
    @Override public Logger getLogger() { return logger; }
    @Override public File getDataFolder() { return dataFolder; }

    public PluginCommand getCommand(String name) {
        if (description != null && description.getCommands() != null) {
            Map<String, Object> commands = description.getCommands();
            if (commands.containsKey(name)) {
                return registeredCommands.computeIfAbsent(name, n -> createPluginCommand(n, commands));
            }
            for (Map.Entry<String, Object> entry : commands.entrySet()) {
                if (entry.getValue() instanceof Map) {
                    @SuppressWarnings("unchecked")
                    Map<String, Object> cmdData = (Map<String, Object>) entry.getValue();
                    Object aliases = cmdData.get("aliases");
                    if (aliases instanceof List) {
                        @SuppressWarnings("unchecked")
                        List<String> aliasList = (List<String>) aliases;
                        if (aliasList.contains(name)) {
                            return getCommand(entry.getKey());
                        }
                    }
                }
            }
        }
        return registeredCommands.computeIfAbsent(name, n -> new PluginCommand(n, this));
    }

    @SuppressWarnings("unchecked")
    private PluginCommand createPluginCommand(String name, Map<String, Object> commands) {
        PluginCommand cmd = new PluginCommand(name, this);
        Object cmdData = commands.get(name);
        if (cmdData instanceof Map) {
            Map<String, Object> data = (Map<String, Object>) cmdData;
            Object desc = data.get("description");
            if (desc != null) cmd.setDescription(desc.toString());
            Object usage = data.get("usage");
            if (usage != null) cmd.setUsage(usage.toString());
            Object aliases = data.get("aliases");
            if (aliases instanceof List) cmd.setAliases((List<String>) aliases);
            Object perm = data.get("permission");
            if (perm != null) cmd.setPermission(perm.toString());
        }
        return cmd;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return null;
    }

    @Override
    public InputStream getResource(String filename) {
        if (filename == null) return null;
        String normalized = filename.replace(File.separatorChar, '/').replace('\\', '/');
        return getClass().getClassLoader().getResourceAsStream(normalized);
    }

    public FileConfiguration getConfig() {
        if (config == null) {
            reloadConfig();
        }
        return config;
    }

    public void reloadConfig() {
        File configFile = new File(dataFolder, "config.yml");
        config = YamlConfiguration.loadConfiguration(configFile);
    }

    public void saveConfig() {
        if (config != null) {
            try {
                config.save(new File(dataFolder, "config.yml"));
            } catch (IOException e) {
                logger.warning("Could not save config: " + e.getMessage());
            }
        }
    }

    public void saveDefaultConfig() {
        File configFile = new File(dataFolder, "config.yml");
        if (!configFile.exists()) {
            try (InputStream in = getResource("config.yml")) {
                if (in != null) {
                    java.nio.file.Files.copy(in, configFile.toPath());
                }
            } catch (IOException e) {
                logger.warning("Could not save default config: " + e.getMessage());
            }
        }
    }

    public void saveResource(String resourcePath, boolean replace) {
        if (resourcePath == null || resourcePath.isEmpty()) return;
        File outFile = new File(dataFolder, resourcePath);
        if (outFile.exists() && !replace) return;
        outFile.getParentFile().mkdirs();
        try (InputStream in = getResource(resourcePath)) {
            if (in != null) {
                if (replace && outFile.exists()) {
                    outFile.delete();
                }
                java.nio.file.Files.copy(in, outFile.toPath());
            }
        } catch (IOException e) {
            logger.warning("Could not save resource: " + e.getMessage());
        }
    }

    public FileConfiguration getConfig(String name) {
        File file = new File(dataFolder, name);
        return YamlConfiguration.loadConfiguration(file);
    }

    public void saveConfig(String name) {
        FileConfiguration cfg = getConfig(name);
        try {
            cfg.save(new File(dataFolder, name));
        } catch (IOException e) {
            logger.warning("Could not save config: " + e.getMessage());
        }
    }
}
