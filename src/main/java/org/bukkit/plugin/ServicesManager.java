package org.bukkit.plugin;

public interface ServicesManager {
    <T> void register(Class<T> service, T provider, Plugin plugin, ServicePriority priority);
    <T> void register(Class<T> service, T provider, Plugin plugin);
    void unregisterAll(Plugin plugin);
    void unregister(Class<?> service, Object provider);
    <T> T load(Class<T> service);
    <T> RegisteredServiceProvider<T> getRegistration(Class<T> service);
    <T> java.util.Collection<RegisteredServiceProvider<T>> getRegistrations(Class<T> service);
    java.util.Collection<RegisteredServiceProvider<?>> getRegistrations(Plugin plugin);
    java.util.Collection<Class<?>> getKnownServices();
    <T> boolean isProvidedFor(Class<T> service);
}
