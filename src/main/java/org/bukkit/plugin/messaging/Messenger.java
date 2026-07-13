package org.bukkit.plugin.messaging;

public interface Messenger {
    void registerOutgoingPluginChannel(org.bukkit.plugin.Plugin plugin, String channel);
    void unregisterOutgoingPluginChannel(org.bukkit.plugin.Plugin plugin, String channel);
    void unregisterOutgoingPluginChannel(org.bukkit.plugin.Plugin plugin);
    PluginMessageListenerRegistration registerIncomingPluginChannel(org.bukkit.plugin.Plugin plugin, String channel, PluginMessageListener listener);
    void unregisterIncomingPluginChannel(org.bukkit.plugin.Plugin plugin, String channel, PluginMessageListener listener);
    void unregisterIncomingPluginChannel(org.bukkit.plugin.Plugin plugin, String channel);
    void unregisterIncomingPluginChannel(org.bukkit.plugin.Plugin plugin);
    boolean isRegistrationValid(String channel);
    boolean isChannelRegistered(String channel);
    java.util.Set<String> getIncomingChannels();
    java.util.Set<String> getOutgoingChannels();
    boolean isIncomingChannelRegistered(org.bukkit.plugin.Plugin plugin, String channel);
    boolean isOutgoingChannelRegistered(org.bukkit.plugin.Plugin plugin, String channel);
}
