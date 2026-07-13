package org.bukkit.plugin.java;

import org.bukkit.plugin.Plugin;

public class PluginClassLoader extends java.net.URLClassLoader {
    private final Plugin plugin;

    public PluginClassLoader(java.net.URL[] urls, ClassLoader parent) {
        super(urls, parent);
        this.plugin = null;
    }

    public PluginClassLoader(java.net.URL[] urls, ClassLoader parent, Plugin plugin) {
        super(urls, parent);
        this.plugin = plugin;
    }

    public Plugin getPlugin() {
        return plugin;
    }
}
