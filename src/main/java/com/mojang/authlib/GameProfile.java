package com.mojang.authlib;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

public class GameProfile {
    private final UUID id;
    private final String name;
    private final java.util.Map<String, com.mojang.authlib.properties.Property> properties = new java.util.HashMap<>();

    public GameProfile(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() { return id; }
    public String getName() { return name; }
    public Collection<com.mojang.authlib.properties.Property> getProperties() { return Collections.unmodifiableCollection(properties.values()); }
    public void putProperty(String name, com.mojang.authlib.properties.Property value) { properties.put(name, value); }
}
