package org.bukkit.block.data.type;

import org.bukkit.block.data.Directional;

public interface Bed extends Directional {

    enum Part {
        HEAD,
        FOOT
    }

    Part getPart();
    void setPart(Part part);
}
