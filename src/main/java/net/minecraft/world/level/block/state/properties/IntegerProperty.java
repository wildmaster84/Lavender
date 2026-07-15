package net.minecraft.world.level.block.state.properties;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class IntegerProperty extends Property<Integer> {
    private final int min;
    private final int max;
    private final List<Integer> values;

    public IntegerProperty(String name, int min, int max) {
        super(name);
        this.min = min;
        this.max = max;
        this.values = IntStream.rangeClosed(min, max).boxed().collect(Collectors.toList());
    }

    public static IntegerProperty create(String name, int min, int max) {
        return new IntegerProperty(name, min, max);
    }

    @Override
    public List<Integer> getPossibleValues() {
        return values;
    }

    public boolean hasValue(int value) {
        return value >= min && value <= max;
    }

    @Override
    public Optional<Integer> getValue(String name) {
        try {
            return Optional.of(Integer.parseInt(name));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    @Override
    public String getName(Integer value) {
        return value.toString();
    }

    @Override
    public Class<Integer> getValueClass() {
        return Integer.class;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }
}
