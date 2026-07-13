package org.bukkit.configuration;

public interface Configuration extends ConfigurationSection {
    void addDefault(String path, Object value);
    void addDefaults(java.util.Map<String, Object> defaults);
    void addDefaults(Configuration defaults);
    Configuration getDefaults();
    void setDefaults(Configuration defaults);
    Configuration getRoot();
    ConfigurationSection getParent();
    Configuration getDefaultSection();
    ConfigurationOptions options();
}
