package net.minecraft.world.level.redstone;

import net.minecraft.core.Direction;

public class Orientation {
    public static final Orientation UP = new Orientation();
    public static final Orientation DOWN = new Orientation();

    public static Orientation forAxis(Direction.Axis axis) {
        return new Orientation();
    }

    public static Orientation forDirection(Direction direction) {
        return new Orientation();
    }

    public static Orientation random() {
        return new Orientation();
    }

    public Direction nearest(Direction direction) {
        return Direction.NORTH;
    }

    public Direction nearest(Direction first, Direction second, Direction third) {
        return Direction.NORTH;
    }

    public Direction getFront() {
        return Direction.NORTH;
    }

    public Direction getBack() {
        return Direction.SOUTH;
    }

    public Direction getLeft() {
        return Direction.WEST;
    }

    public Direction getRight() {
        return Direction.EAST;
    }

    public Orientation withFront(Direction direction) {
        return this;
    }

    public Orientation opposite() {
        return this;
    }

    public Orientation rotateAround(Direction.Axis axis) {
        return this;
    }
}
