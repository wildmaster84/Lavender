package me.wildmaster84.lavender.plugin;

import java.io.File;
import java.net.URL;
import java.util.List;

import org.bukkit.Server;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class PluginClassLoader extends org.bukkit.plugin.java.PluginClassLoader {
    private final List<PluginClassLoader> dependencyLoaders;
    private final Server server;
    private final PluginDescriptionFile description;
    private final File dataFolder;
    private final File jarFile;
    private JavaPlugin plugin;

    public PluginClassLoader(URL[] urls, ClassLoader parent, List<PluginClassLoader> dependencyLoaders,
                              Server server, PluginDescriptionFile description, File dataFolder, File jarFile) {
        super(urls, parent);
        this.dependencyLoaders = dependencyLoaders != null ? dependencyLoaders : List.of();
        this.server = server;
        this.description = description;
        this.dataFolder = dataFolder;
        this.jarFile = jarFile;
    }

    public void initialize(JavaPlugin javaPlugin) {
        if (this.plugin != null) {
            throw new IllegalArgumentException("Plugin already initialized for this classloader");
        }
        this.plugin = javaPlugin;
        javaPlugin.init(server, description, dataFolder, this, jarFile);
    }

    @Override
    public org.bukkit.plugin.Plugin getPlugin() { return plugin; }

    public PluginDescriptionFile getDescription() { return description; }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        synchronized (getClassLoadingLock(name)) {
            Class<?> loadedClass = findLoadedClass(name);
            if (loadedClass != null) {
                if (resolve) resolveClass(loadedClass);
                return loadedClass;
            }
            try {
                loadedClass = findClass(name);
                if (resolve) resolveClass(loadedClass);
                return loadedClass;
            } catch (ClassNotFoundException e) {}
            for (PluginClassLoader depLoader : dependencyLoaders) {
                try {
                    return depLoader.loadClass(name, resolve);
                } catch (ClassNotFoundException e) {}
            }
            return super.loadClass(name, resolve);
        }
    }
}
