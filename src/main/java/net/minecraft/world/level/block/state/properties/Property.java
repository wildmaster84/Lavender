package net.minecraft.world.level.block.state.properties;

import java.util.List;
import java.util.Optional;

public abstract class Property<T> {
    private final String name;

    protected Property(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract List<T> getPossibleValues();

    public boolean hasValue(T value) {
        return getPossibleValues().contains(value);
    }

    public Optional<T> getValue(String name) {
        return Optional.empty();
    }

    public String getName(T value) {
        return value.toString();
    }

    public Class<T> getValueClass() {
        return null;
    }

    public int generateId(T value) {
        return 0;
    }

    public int getOrdinal(T value) {
        return 0;
    }
}
