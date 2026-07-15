package org.bukkit.block.data.type;

import org.bukkit.block.data.Directional;

public interface Chest extends Directional {

    enum Type {
        SINGLE,
        LEFT,
        RIGHT
    }

    Type getType();
    void setType(Type type);
}
