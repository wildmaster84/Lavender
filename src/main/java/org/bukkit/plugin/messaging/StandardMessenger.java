package org.bukkit.plugin.messaging;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class StandardMessenger implements Messenger {
    private final Set<String> incomingChannels = ConcurrentHashMap.newKeySet();
    private final Set<String> outgoingChannels = ConcurrentHashMap.newKeySet();

    @Override
    public void registerOutgoingPluginChannel(org.bukkit.plugin.Plugin plugin, String channel) {
        outgoingChannels.add(channel);
    }

    @Override
    public void unregisterOutgoingPluginChannel(org.bukkit.plugin.Plugin plugin, String channel) {
        outgoingChannels.remove(channel);
    }

    @Override
    public void unregisterOutgoingPluginChannel(org.bukkit.plugin.Plugin plugin) {
        outgoingChannels.clear();
    }

    @Override
    public PluginMessageListenerRegistration registerIncomingPluginChannel(org.bukkit.plugin.Plugin plugin, String channel, PluginMessageListener listener) {
        incomingChannels.add(channel);
        return new PluginMessageListenerRegistration(plugin, channel, listener);
    }

    @Override
    public void unregisterIncomingPluginChannel(org.bukkit.plugin.Plugin plugin, String channel, PluginMessageListener listener) {
        incomingChannels.remove(channel);
    }

    @Override
    public void unregisterIncomingPluginChannel(org.bukkit.plugin.Plugin plugin, String channel) {
        incomingChannels.remove(channel);
    }

    @Override
    public void unregisterIncomingPluginChannel(org.bukkit.plugin.Plugin plugin) {
        incomingChannels.clear();
    }

    @Override
    public boolean isRegistrationValid(String channel) { return incomingChannels.contains(channel); }
    @Override
    public boolean isChannelRegistered(String channel) { return incomingChannels.contains(channel) || outgoingChannels.contains(channel); }
    @Override
    public Set<String> getIncomingChannels() { return incomingChannels; }
    @Override
    public Set<String> getOutgoingChannels() { return outgoingChannels; }
    @Override
    public boolean isIncomingChannelRegistered(org.bukkit.plugin.Plugin plugin, String channel) { return incomingChannels.contains(channel); }
    @Override
    public boolean isOutgoingChannelRegistered(org.bukkit.plugin.Plugin plugin, String channel) { return outgoingChannels.contains(channel); }
}
