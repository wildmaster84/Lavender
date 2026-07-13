package org.bukkit.event.block;

public enum Action {
    LEFT_CLICK_AIR,
    LEFT_CLICK_BLOCK,
    RIGHT_CLICK_AIR,
    RIGHT_CLICK_BLOCK,
    PHYSICAL;

    public boolean isRightClick() {
        return this == RIGHT_CLICK_AIR || this == RIGHT_CLICK_BLOCK;
    }

    public boolean isLeftClick() {
        return this == LEFT_CLICK_AIR || this == LEFT_CLICK_BLOCK;
    }
}
