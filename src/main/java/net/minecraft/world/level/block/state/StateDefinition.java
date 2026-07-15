package net.minecraft.world.level.block.state;

import net.minecraft.world.level.block.state.properties.Property;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class StateDefinition<O, S extends BlockState> {
    private final O owner;
    private final Collection<S> states;
    private final S defaultState;
    private final List<Property<?>> properties;
    private final Map<String, Property<?>> propertiesByName;

    public StateDefinition(O owner, S defaultState) {
        this.owner = owner;
        this.defaultState = defaultState;
        this.states = Collections.singletonList(defaultState);
        this.properties = Collections.emptyList();
        this.propertiesByName = Collections.emptyMap();
    }

    public StateDefinition(O owner, S defaultState, List<Property<?>> properties, List<S> states) {
        this.owner = owner;
        this.defaultState = defaultState;
        this.properties = properties;
        this.states = states;
        this.propertiesByName = new LinkedHashMap<>();
        for (Property<?> prop : properties) {
            propertiesByName.put(prop.getName(), prop);
        }
    }

    public StateDefinition() {
        this.owner = null;
        this.defaultState = null;
        this.states = Collections.emptyList();
        this.properties = Collections.emptyList();
        this.propertiesByName = Collections.emptyMap();
    }

    public Collection<S> getPossibleStates() {
        return states;
    }

    public S any() {
        return defaultState;
    }

    public S get(int id) {
        for (S state : states) {
            if (state.getId() == id) return state;
        }
        return defaultState;
    }

    public int getId(S state) {
        return state.getId();
    }

    public O getOwner() {
        return owner;
    }

    public Collection<Property<?>> getProperties() {
        return properties;
    }

    public Property<?> getProperty(String name) {
        return propertiesByName.get(name);
    }

    public boolean hasProperty(Property<?> property) {
        return propertiesByName.containsKey(property.getName());
    }

    public Map<String, Property<?>> propertiesByName() {
        return propertiesByName;
    }
}
