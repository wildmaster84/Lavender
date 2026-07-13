package org.bukkit.configuration.file;

import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.yaml.snakeyaml.Yaml;
import java.io.File;

public class YamlConfiguration extends FileConfiguration {

    private final Yaml yaml = new Yaml();
    private java.util.Map<String, Object> data = new java.util.LinkedHashMap<>();
    private java.util.Map<String, Object> defaults = new java.util.LinkedHashMap<>();
    protected org.yaml.snakeyaml.DumperOptions yamlOptions = new org.yaml.snakeyaml.DumperOptions();
    protected YamlConfigurationOptions options;

    public static YamlConfiguration loadConfiguration(File file) {
        YamlConfiguration config = new YamlConfiguration();
        if (file != null && file.exists()) {
            try {
                config.load(file);
            } catch (Exception e) {
            }
        }
        return config;
    }

    public static YamlConfiguration loadConfiguration(java.io.Reader reader) {
        YamlConfiguration config = new YamlConfiguration();
        if (reader != null) {
            try {
                java.io.BufferedReader br = new java.io.BufferedReader(reader);
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                config.loadFromString(sb.toString());
            } catch (Exception e) {
            }
        }
        return config;
    }

    @Override
    public String saveToString() {
        java.util.Map<String, Object> toSave = data;
        if (options().copyDefaults() && !defaults.isEmpty()) {
            toSave = new java.util.LinkedHashMap<>(data);
            mergeDefaults(toSave, "", defaults);
        }
        return yaml.dump(toSave);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void loadFromString(String contents) {
        if (contents == null || contents.isEmpty()) {
            data = new java.util.LinkedHashMap<>();
            return;
        }
        Object loaded = yaml.load(contents);
        if (loaded instanceof java.util.Map) {
            data = (java.util.Map<String, Object>) loaded;
        } else {
            data = new java.util.LinkedHashMap<>();
        }
    }

    @Override
    public String getName() { return "yaml"; }

    @Override
    public YamlConfigurationOptions options() { return new YamlConfigurationOptions(this); }

    @Override
    public Object get(String path) {
        if (path == null) return null;
        Object val = getNested(path);
        if (val == null) {
            val = getNestedFrom(path, defaults);
        }
        return val;
    }

    @Override
    public Object get(String path, Object def) {
        Object val = get(path);
        return val != null ? val : def;
    }

    @Override
    public String getString(String path) {
        Object val = get(path);
        return val != null ? val.toString() : null;
    }

    @Override
    public String getString(String path, String def) {
        Object val = get(path);
        return val != null ? val.toString() : def;
    }

    @Override
    public int getInt(String path) {
        Object val = get(path);
        if (val instanceof Number) return ((Number) val).intValue();
        return 0;
    }

    @Override
    public int getInt(String path, int def) {
        Object val = get(path);
        if (val instanceof Number) return ((Number) val).intValue();
        return def;
    }

    @Override
    public boolean getBoolean(String path) {
        Object val = get(path);
        return val instanceof Boolean ? (Boolean) val : false;
    }

    @Override
    public boolean getBoolean(String path, boolean def) {
        Object val = get(path);
        return val instanceof Boolean ? (Boolean) val : def;
    }

    @Override
    public double getDouble(String path) {
        Object val = get(path);
        if (val instanceof Number) return ((Number) val).doubleValue();
        return 0;
    }

    @Override
    public double getDouble(String path, double def) {
        Object val = get(path);
        if (val instanceof Number) return ((Number) val).doubleValue();
        return def;
    }

    @Override
    public long getLong(String path) {
        Object val = get(path);
        if (val instanceof Number) return ((Number) val).longValue();
        return 0;
    }

    @Override
    public long getLong(String path, long def) {
        Object val = get(path);
        if (val instanceof Number) return ((Number) val).longValue();
        return def;
    }

    @Override
    public java.util.List<?> getList(String path) {
        Object val = get(path);
        if (val instanceof java.util.List) return (java.util.List<?>) val;
        return null;
    }

    @Override
    public java.util.List<String> getStringList(String path) {
        Object val = get(path);
        if (val instanceof java.util.List) {
            java.util.List<String> result = new java.util.ArrayList<>();
            for (Object o : (java.util.List<?>) val) {
                result.add(o != null ? o.toString() : null);
            }
            return result;
        }
        return new java.util.ArrayList<>();
    }

    @Override
    public boolean contains(String path) {
        return get(path) != null;
    }

    @Override
    public boolean contains(String path, boolean ignoreDefault) {
        return contains(path);
    }

    @Override
    public boolean isSet(String path) {
        return contains(path);
    }

    @Override
    public void set(String path, Object value) {
        if (path == null) return;
        setNested(path, value);
    }

    @Override
    public ConfigurationSection createSection(String path) {
        YamlConfiguration section = new YamlConfiguration();
        set(path, section.data);
        return section;
    }

    @Override
    public ConfigurationSection getConfigurationSection(String path) {
        Object val = get(path);
        if (val instanceof java.util.Map) {
            YamlConfiguration section = new YamlConfiguration();
            section.data = (java.util.Map<String, Object>) val;
            return section;
        }
        return null;
    }

    @Override
    public java.util.List<?> getList(String path, java.util.List<?> def) {
        java.util.List<?> val = getList(path);
        return val != null ? val : def;
    }

    @Override
    public java.util.List<Integer> getIntegerList(String path) {
        Object val = get(path);
        if (val instanceof java.util.List) {
            java.util.List<Integer> result = new java.util.ArrayList<>();
            for (Object o : (java.util.List<?>) val) {
                if (o instanceof Number) result.add(((Number) o).intValue());
            }
            return result;
        }
        return new java.util.ArrayList<>();
    }

    @Override
    public java.util.List<Boolean> getBooleanList(String path) {
        Object val = get(path);
        if (val instanceof java.util.List) {
            java.util.List<Boolean> result = new java.util.ArrayList<>();
            for (Object o : (java.util.List<?>) val) {
                if (o instanceof Boolean) result.add((Boolean) o);
            }
            return result;
        }
        return new java.util.ArrayList<>();
    }

    @Override
    public java.util.List<Double> getDoubleList(String path) {
        Object val = get(path);
        if (val instanceof java.util.List) {
            java.util.List<Double> result = new java.util.ArrayList<>();
            for (Object o : (java.util.List<?>) val) {
                if (o instanceof Number) result.add(((Number) o).doubleValue());
            }
            return result;
        }
        return new java.util.ArrayList<>();
    }

    @Override
    public java.util.List<Float> getFloatList(String path) {
        Object val = get(path);
        if (val instanceof java.util.List) {
            java.util.List<Float> result = new java.util.ArrayList<>();
            for (Object o : (java.util.List<?>) val) {
                if (o instanceof Number) result.add(((Number) o).floatValue());
            }
            return result;
        }
        return new java.util.ArrayList<>();
    }

    @Override
    public java.util.List<Long> getLongList(String path) {
        Object val = get(path);
        if (val instanceof java.util.List) {
            java.util.List<Long> result = new java.util.ArrayList<>();
            for (Object o : (java.util.List<?>) val) {
                if (o instanceof Number) result.add(((Number) o).longValue());
            }
            return result;
        }
        return new java.util.ArrayList<>();
    }

    @Override
    public java.util.List<Byte> getByteList(String path) {
        Object val = get(path);
        if (val instanceof java.util.List) {
            java.util.List<Byte> result = new java.util.ArrayList<>();
            for (Object o : (java.util.List<?>) val) {
                if (o instanceof Number) result.add(((Number) o).byteValue());
            }
            return result;
        }
        return new java.util.ArrayList<>();
    }

    @Override
    public java.util.List<Character> getCharacterList(String path) {
        Object val = get(path);
        if (val instanceof java.util.List) {
            java.util.List<Character> result = new java.util.ArrayList<>();
            for (Object o : (java.util.List<?>) val) {
                if (o instanceof Character) result.add((Character) o);
            }
            return result;
        }
        return new java.util.ArrayList<>();
    }

    @Override
    public java.util.List<java.util.Map<?, ?>> getMapList(String path) {
        Object val = get(path);
        if (val instanceof java.util.List) {
            java.util.List<java.util.Map<?, ?>> result = new java.util.ArrayList<>();
            for (Object o : (java.util.List<?>) val) {
                if (o instanceof java.util.Map) result.add((java.util.Map<?, ?>) o);
            }
            return result;
        }
        return new java.util.ArrayList<>();
    }

    @Override
    public boolean isString(String path) { return get(path) instanceof String; }
    @Override
    public boolean isInt(String path) { return get(path) instanceof Integer; }
    @Override
    public boolean isBoolean(String path) { return get(path) instanceof Boolean; }
    @Override
    public boolean isDouble(String path) { return get(path) instanceof Double; }
    @Override
    public boolean isLong(String path) { return get(path) instanceof Long; }
    @Override
    public boolean isList(String path) { return get(path) instanceof java.util.List; }
    @Override
    public boolean isConfigurationSection(String path) { return get(path) instanceof java.util.Map; }

    @Override
    public Configuration getDefaultSection() { return null; }

    @Override
    public java.util.Set<String> getKeys(boolean deep) {
        java.util.Set<String> keys = new java.util.LinkedHashSet<>();
        for (Object key : data.keySet()) {
            keys.add(String.valueOf(key));
        }
        return keys;
    }

    @Override
    public java.util.Map<String, Object> getValues(boolean deep) {
        java.util.Map<String, Object> result = new java.util.LinkedHashMap<>();
        for (java.util.Map.Entry<String, Object> entry : data.entrySet()) {
            result.put(String.valueOf(entry.getKey()), entry.getValue());
        }
        return result;
    }

    @Override
    public Configuration getRoot() { return this; }

    @Override
    public ConfigurationSection getParent() { return null; }

    @Override
    public void setComments(String path, java.util.List<String> comments) {}
    @Override
    public java.util.List<String> getComments(String path) { return new java.util.ArrayList<>(); }
    @Override
    public void setComment(String path, String comment) {}
    @Override
    public String getComment(String path) { return null; }

    @Override
    public void addDefault(String path, Object value) {
        if (path == null) return;
        if (!contains(path)) {
            setNested(path, value, defaults);
        }
    }

    @Override
    public void addDefaults(java.util.Map<String, Object> map) {
        if (map != null) {
            for (java.util.Map.Entry<String, Object> entry : map.entrySet()) {
                addDefault(entry.getKey(), entry.getValue());
            }
        }
    }

    @Override
    public void addDefaults(Configuration config) {
        if (config != null) {
            for (String key : config.getKeys(false)) {
                addDefault(key, config.get(key));
            }
        }
    }

    @Override
    public Configuration getDefaults() {
        YamlConfiguration def = new YamlConfiguration();
        def.data = this.defaults;
        return def;
    }

    @Override
    public void setDefaults(Configuration config) {
        if (config == null) {
            this.defaults = new java.util.LinkedHashMap<>();
        } else {
            this.defaults = new java.util.LinkedHashMap<>();
            for (String key : config.getKeys(false)) {
                setNested(key, config.get(key), this.defaults);
            }
        }
    }

    @SuppressWarnings("unchecked")
    private Object getNested(String path) {
        return getNestedFrom(path, data);
    }

    @SuppressWarnings("unchecked")
    private Object getNestedFrom(String path, java.util.Map<String, Object> source) {
        String[] parts = path.split("\\.");
        Object current = source;
        for (String part : parts) {
            if (current instanceof java.util.Map) {
                java.util.Map<Object, Object> map = (java.util.Map<Object, Object>) current;
                current = map.get(part);
                if (current == null) {
                    try {
                        current = map.get(Integer.parseInt(part));
                    } catch (NumberFormatException e) {
                    }
                }
            } else {
                return null;
            }
        }
        return current;
    }

    @SuppressWarnings("unchecked")
    private void setNested(String path, Object value) {
        setNested(path, value, data);
    }

    @SuppressWarnings("unchecked")
    private void setNested(String path, Object value, java.util.Map<String, Object> target) {
        String[] parts = path.split("\\.");
        java.util.Map<String, Object> current = target;
        for (int i = 0; i < parts.length - 1; i++) {
            Object next = current.get(parts[i]);
            if (!(next instanceof java.util.Map)) {
                next = new java.util.LinkedHashMap<>();
                current.put(parts[i], next);
            }
            current = (java.util.Map<String, Object>) next;
        }
        if (value == null) {
            current.remove(parts[parts.length - 1]);
        } else {
            current.put(parts[parts.length - 1], value);
        }
    }

    @SuppressWarnings("unchecked")
    private void mergeDefaults(java.util.Map<String, Object> target, String prefix, java.util.Map<String, Object> source) {
        for (java.util.Map.Entry<String, Object> entry : source.entrySet()) {
            String key = prefix.isEmpty() ? entry.getKey() : prefix + "." + entry.getKey();
            if (entry.getValue() instanceof java.util.Map) {
                java.util.Map<String, Object> nested = (java.util.Map<String, Object>) entry.getValue();
                mergeDefaults(target, key, nested);
            } else {
                if (getNestedFrom(key, target) == null) {
                    setNested(key, entry.getValue(), target);
                }
            }
        }
    }
}
