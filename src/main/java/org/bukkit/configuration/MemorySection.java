package org.bukkit.configuration;

public class MemorySection implements ConfigurationSection {

    @Override public Object get(String path) { return null; }
    @Override public Object get(String path, Object def) { return def; }
    @Override public String getString(String path) { return null; }
    @Override public String getString(String path, String def) { return def; }
    @Override public int getInt(String path) { return 0; }
    @Override public int getInt(String path, int def) { return def; }
    @Override public boolean getBoolean(String path) { return false; }
    @Override public boolean getBoolean(String path, boolean def) { return def; }
    @Override public double getDouble(String path) { return 0; }
    @Override public double getDouble(String path, double def) { return def; }
    @Override public long getLong(String path) { return 0; }
    @Override public long getLong(String path, long def) { return def; }

    @Override public java.util.List<?> getList(String path) { return null; }
    @Override public java.util.List<?> getList(String path, java.util.List<?> def) { return def; }
    @Override public java.util.List<String> getStringList(String path) { return new java.util.ArrayList<>(); }
    @Override public java.util.List<Integer> getIntegerList(String path) { return new java.util.ArrayList<>(); }
    @Override public java.util.List<Boolean> getBooleanList(String path) { return new java.util.ArrayList<>(); }
    @Override public java.util.List<Double> getDoubleList(String path) { return new java.util.ArrayList<>(); }
    @Override public java.util.List<Float> getFloatList(String path) { return new java.util.ArrayList<>(); }
    @Override public java.util.List<Long> getLongList(String path) { return new java.util.ArrayList<>(); }
    @Override public java.util.List<Byte> getByteList(String path) { return new java.util.ArrayList<>(); }
    @Override public java.util.List<Character> getCharacterList(String path) { return new java.util.ArrayList<>(); }
    @Override public java.util.List<java.util.Map<?, ?>> getMapList(String path) { return new java.util.ArrayList<>(); }

    @Override public boolean contains(String path) { return false; }
    @Override public boolean contains(String path, boolean ignoreDefault) { return false; }
    @Override public boolean isSet(String path) { return false; }

    @Override public void set(String path, Object value) {}
    @Override public ConfigurationSection createSection(String path) { return null; }
    @Override public ConfigurationSection getConfigurationSection(String path) { return null; }

    @Override public java.util.Set<String> getKeys(boolean deep) { return new java.util.HashSet<>(); }
    @Override public java.util.Map<String, Object> getValues(boolean deep) { return new java.util.HashMap<>(); }
    @Override public String getName() { return "memory"; }

    @Override public boolean isString(String path) { return false; }
    @Override public boolean isInt(String path) { return false; }
    @Override public boolean isBoolean(String path) { return false; }
    @Override public boolean isDouble(String path) { return false; }
    @Override public boolean isLong(String path) { return false; }
    @Override public boolean isList(String path) { return false; }
    @Override public boolean isConfigurationSection(String path) { return false; }

    @Override public ConfigurationSection getParent() { return null; }
    @Override public Configuration getRoot() { return null; }

    @Override public void setComments(String path, java.util.List<String> comments) {}
    @Override public java.util.List<String> getComments(String path) { return new java.util.ArrayList<>(); }
    @Override public void setComment(String path, String comment) {}
    @Override public String getComment(String path) { return null; }
}
