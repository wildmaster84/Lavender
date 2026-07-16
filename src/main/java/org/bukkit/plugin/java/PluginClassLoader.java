package org.bukkit.plugin.java;

import org.bukkit.plugin.Plugin;

public class PluginClassLoader extends java.net.URLClassLoader {
    private Plugin plugin;
    private Plugin pluginInit;

    public PluginClassLoader(java.net.URL[] urls, ClassLoader parent) {
        super(urls, parent);
        this.plugin = null;
        this.pluginInit = null;
    }

    public PluginClassLoader(java.net.URL[] urls, ClassLoader parent, Plugin plugin) {
        super(urls, parent);
        this.plugin = plugin;
        this.pluginInit = plugin;
    }

    public Plugin getPlugin() {
        return (plugin == null) ? pluginInit : plugin;
    }

    Class<?> loadClass0(String name, boolean resolve, boolean checkParents, boolean global) throws ClassNotFoundException {
        return super.loadClass(name, resolve);
    }
}
