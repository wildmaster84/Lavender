package org.bukkit.block;

public enum BlockFace {
    NORTH, EAST, SOUTH, WEST, UP, DOWN, SELF,
    NORTH_EAST, NORTH_WEST, SOUTH_EAST, SOUTH_WEST,
    NORTH_NORTH_EAST, NORTH_NORTH_WEST, SOUTH_SOUTH_EAST, SOUTH_SOUTH_WEST,
    EAST_NORTH_EAST, EAST_SOUTH_EAST, WEST_NORTH_WEST, WEST_SOUTH_WEST;

    public BlockFace getOppositeFace() {
        switch (this) {
            case NORTH: return SOUTH;
            case SOUTH: return NORTH;
            case EAST: return WEST;
            case WEST: return EAST;
            case UP: return DOWN;
            case DOWN: return UP;
            case NORTH_EAST: return SOUTH_WEST;
            case NORTH_WEST: return SOUTH_EAST;
            case SOUTH_EAST: return NORTH_WEST;
            case SOUTH_WEST: return NORTH_EAST;
            default: return SELF;
        }
    }

    public org.bukkit.util.Vector getDirection() {
        switch (this) {
            case NORTH: return new org.bukkit.util.Vector(0, 0, -1);
            case SOUTH: return new org.bukkit.util.Vector(0, 0, 1);
            case EAST: return new org.bukkit.util.Vector(1, 0, 0);
            case WEST: return new org.bukkit.util.Vector(-1, 0, 0);
            case UP: return new org.bukkit.util.Vector(0, 1, 0);
            case DOWN: return new org.bukkit.util.Vector(0, -1, 0);
            default: return new org.bukkit.util.Vector(0, 0, 0);
        }
    }

    public int getModX() {
        return switch (this) {
            case EAST, EAST_NORTH_EAST, EAST_SOUTH_EAST -> 1;
            case WEST, WEST_NORTH_WEST, WEST_SOUTH_WEST -> -1;
            case NORTH_EAST, NORTH_NORTH_EAST -> 1;
            case NORTH_WEST, NORTH_NORTH_WEST -> -1;
            case SOUTH_EAST, SOUTH_SOUTH_EAST -> 1;
            case SOUTH_WEST, SOUTH_SOUTH_WEST -> -1;
            default -> 0;
        };
    }

    public int getModY() {
        return switch (this) {
            case UP -> 1;
            case DOWN -> -1;
            default -> 0;
        };
    }

    public int getModZ() {
        return switch (this) {
            case NORTH, NORTH_EAST, NORTH_WEST, NORTH_NORTH_EAST, NORTH_NORTH_WEST -> -1;
            case SOUTH, SOUTH_EAST, SOUTH_WEST, SOUTH_SOUTH_EAST, SOUTH_SOUTH_WEST -> 1;
            default -> 0;
        };
    }
}
