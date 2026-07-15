package net.minecraft.world.level.block.state.properties;

import java.util.List;
import java.util.Optional;

public class BooleanProperty extends Property<Boolean> {
    private final List<Boolean> values = List.of(false, true);

    public BooleanProperty(String name) {
        super(name);
    }

    public static BooleanProperty create(String name) {
        return new BooleanProperty(name);
    }

    @Override
    public List<Boolean> getPossibleValues() {
        return values;
    }

    public boolean hasValue(boolean value) {
        return true;
    }

    @Override
    public Optional<Boolean> getValue(String name) {
        return Optional.of(Boolean.parseBoolean(name));
    }

    @Override
    public String getName(Boolean value) {
        return value.toString();
    }

    @Override
    public Class<Boolean> getValueClass() {
        return Boolean.class;
    }
}
