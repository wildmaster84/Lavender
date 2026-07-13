package org.bukkit.configuration;

public abstract class MemoryConfiguration extends MemorySection implements Configuration {

    @Override public void addDefault(String path, Object value) {}
    @Override public void addDefaults(java.util.Map<String, Object> defaults) {}
    @Override public void addDefaults(Configuration defaults) {}
    @Override public Configuration getDefaults() { return null; }
    @Override public void setDefaults(Configuration defaults) {}
    @Override public Configuration getRoot() { return this; }
    @Override public Configuration getDefaultSection() { return null; }
    @Override public ConfigurationOptions options() { return new ConfigurationOptions(this); }
}
