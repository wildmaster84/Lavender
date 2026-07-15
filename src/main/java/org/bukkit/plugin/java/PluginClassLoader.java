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

    Class<?> loadClass0(String name, boolean resolve, boolean checkParents, boolean global) throws ClassNotFoundException {
        return super.loadClass(name, resolve);
    }
}
