package me.wildmaster84.lavender.plugin;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.ServicesManager;

import java.util.*;

public class SimpleServicesManager implements ServicesManager {

    private final Map<Class<?>, List<RegisteredServiceProvider<?>>> registrations = new HashMap<>();

    @Override
    public <T> void register(Class<T> service, T provider, Plugin plugin, ServicePriority priority) {
        registrations.computeIfAbsent(service, k -> new ArrayList<>())
            .add(new RegisteredServiceProvider<>(service, provider, priority, plugin));
    }

    @Override
    public <T> void register(Class<T> service, T provider, Plugin plugin) {
        register(service, provider, plugin, ServicePriority.Normal);
    }

    @Override
    public void unregisterAll(Plugin plugin) {
        registrations.values().forEach(list -> list.removeIf(r -> r.getPlugin() == plugin));
    }

    @Override
    public void unregister(Class<?> service, Object provider) {
        List<RegisteredServiceProvider<?>> list = registrations.get(service);
        if (list != null) list.removeIf(r -> r.getProvider() == provider);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T load(Class<T> service) {
        RegisteredServiceProvider<T> reg = getRegistration(service);
        return reg != null ? reg.getProvider() : null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> RegisteredServiceProvider<T> getRegistration(Class<T> service) {
        List<RegisteredServiceProvider<?>> list = registrations.get(service);
        if (list == null || list.isEmpty()) return null;
        return (RegisteredServiceProvider<T>) list.get(0);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> java.util.Collection<RegisteredServiceProvider<T>> getRegistrations(Class<T> service) {
        List<RegisteredServiceProvider<?>> list = registrations.get(service);
        if (list == null) return Collections.emptyList();
        List<RegisteredServiceProvider<T>> result = new ArrayList<>();
        for (RegisteredServiceProvider<?> r : list) result.add((RegisteredServiceProvider<T>) r);
        return result;
    }

    @Override
    public java.util.Collection<RegisteredServiceProvider<?>> getRegistrations(Plugin plugin) {
        List<RegisteredServiceProvider<?>> result = new ArrayList<>();
        for (List<RegisteredServiceProvider<?>> list : registrations.values()) {
            for (RegisteredServiceProvider<?> r : list) {
                if (r.getPlugin() == plugin) result.add(r);
            }
        }
        return result;
    }

    @Override
    public Collection<Class<?>> getKnownServices() {
        return registrations.keySet();
    }

    @Override
    public <T> boolean isProvidedFor(Class<T> service) {
        List<RegisteredServiceProvider<?>> list = registrations.get(service);
        return list != null && !list.isEmpty();
    }
}
