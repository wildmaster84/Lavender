package org.bukkit.plugin.messaging;

public class PluginMessageListenerRegistration {
    private final org.bukkit.plugin.Plugin plugin;
    private final String channel;
    private final PluginMessageListener listener;

    public PluginMessageListenerRegistration(org.bukkit.plugin.Plugin plugin, String channel, PluginMessageListener listener) {
        this.plugin = plugin;
        this.channel = channel;
        this.listener = listener;
    }

    public org.bukkit.plugin.Plugin getPlugin() { return plugin; }
    public String getChannel() { return channel; }
    public PluginMessageListener getListener() { return listener; }
}
