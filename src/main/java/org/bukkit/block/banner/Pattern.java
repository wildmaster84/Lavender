package org.bukkit.block.banner;

import org.bukkit.DyeColor;

public class Pattern {
    private final DyeColor color;
    private final PatternType pattern;

    public Pattern(DyeColor color, PatternType pattern) {
        this.color = color;
        this.pattern = pattern;
    }

    public DyeColor getColor() { return color; }
    public PatternType getPattern() { return pattern; }

    public enum PatternType {
        BASE, SQUARE_BOTTOM_LEFT, SQUARE_BOTTOM_RIGHT, SQUARE_TOP_LEFT, SQUARE_TOP_RIGHT,
        STRIPE_BOTTOM, STRIPE_TOP, STRIPE_LEFT, STRIPE_RIGHT, STRIPE_MIDDLE,
        STRIPE_DOWNRIGHT, STRIPE_DOWNLEFT, STRIPE_VERTICAL, STRIPE_HORIZONTAL,
        STRIPE_CENTER, CROSS, STRAIGHT_CROSS, TRIANGLE_BOTTOM, TRIANGLE_TOP, TRIANGLES_BOTTOM,
        TRIANGLES_TOP, DIAGONAL_LEFT, DIAGONAL_RIGHT, DIAGONAL_LEFT_MIRROR, DIAGONAL_RIGHT_MIRROR,
        CIRCLE_MIDDLE, RHOMBUS_MIDDLE, HALF_VERTICAL, HALF_HORIZONTAL, HALF_VERTICAL_MIRROR,
        HALF_HORIZONTAL_MIRROR, BORDER, CURLY_BORDER, GRADIENT, GRADIENT_UP, BRICKSTONES,
        GLOBE, MOJANG, PIGLIN, FLOWER, CREEPER, SKULL, FLOW;
    }
}
