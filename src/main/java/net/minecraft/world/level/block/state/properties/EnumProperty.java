package net.minecraft.world.level.block.state.properties;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class EnumProperty<T extends Comparable<T>> extends Property<T> {
    private final Set<String> valueNames;
    private final Map<String, T> valuesByName;

    public static <T extends Enum<T>> EnumProperty<T> create(String name, Class<T> clazz) {
        return new EnumProperty<>(name, Collections.emptySet());
    }

    public EnumProperty(String name, Set<String> valueNames) {
        super(name);
        this.valueNames = valueNames;
        this.valuesByName = new LinkedHashMap<>();
    }

    @Override
    public List<T> getPossibleValues() {
        return List.of();
    }

    @Override
    public Optional<T> getValue(String name) {
        @SuppressWarnings("unchecked")
        T value = (T) name;
        return Optional.of(value);
    }

    @Override
    public Class<T> getValueClass() {
        return null;
    }
}
