package net.minecraft.world.level.block.state.properties;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import net.minecraft.util.StringRepresentable;

public class EnumProperty<T extends Comparable<T>> extends Property<T> {
    private final List<T> values;
    private final Map<String, T> valuesByName;

    public static <T extends Enum<T>> EnumProperty<T> create(String name, Class<T> clazz) {
        return new EnumProperty<>(name, Set.of());
    }

    @SuppressWarnings("unchecked")
    public EnumProperty(String name, Set<String> valueNames) {
        super(name);
        this.values = new ArrayList<>();
        this.valuesByName = new LinkedHashMap<>();
        for (String v : valueNames) {
            T val = (T) new StringRepresentableValue(v);
            values.add(val);
            valuesByName.put(v, val);
        }
    }

    @Override
    public List<T> getPossibleValues() {
        return values;
    }

    @Override
    public Optional<T> getValue(String name) {
        return Optional.ofNullable(valuesByName.get(name));
    }

    @Override
    public String getName(T value) {
        if (value instanceof StringRepresentable sr) {
            return sr.getSerializedName();
        }
        return value.toString();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Class<T> getValueClass() {
        if (values.isEmpty()) return null;
        return (Class<T>) values.get(0).getClass();
    }

    public static class StringRepresentableValue implements StringRepresentable, Comparable<StringRepresentableValue> {
        private final String name;

        public StringRepresentableValue(String name) {
            this.name = name;
        }

        @Override
        public String getSerializedName() {
            return name;
        }

        @Override
        public int compareTo(StringRepresentableValue other) {
            return name.compareTo(other.name);
        }

        @Override
        public String toString() {
            return name;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof StringRepresentableValue)) return false;
            return name.equals(((StringRepresentableValue) obj).name);
        }

        @Override
        public int hashCode() {
            return name.hashCode();
        }
    }
}
