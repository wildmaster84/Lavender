package net.minecraft.core;

public enum Direction {
    NORTH(0, 0, -1),
    SOUTH(0, 0, 1),
    WEST(-1, 0, 0),
    EAST(1, 0, 0),
    UP(0, 1, 0),
    DOWN(0, -1, 0);

    private final int normalX;
    private final int normalY;
    private final int normalZ;

    Direction(int normalX, int normalY, int normalZ) {
        this.normalX = normalX;
        this.normalY = normalY;
        this.normalZ = normalZ;
    }

    public int getStepX() {
        return normalX;
    }

    public int getStepY() {
        return normalY;
    }

    public int getStepZ() {
        return normalZ;
    }

    public Direction getOpposite() {
        return switch (this) {
            case NORTH -> SOUTH;
            case SOUTH -> NORTH;
            case WEST -> EAST;
            case EAST -> WEST;
            case UP -> DOWN;
            case DOWN -> UP;
        };
    }

    public Axis getAxis() {
        return switch (this) {
            case NORTH, SOUTH -> Axis.Z;
            case EAST, WEST -> Axis.X;
            case UP, DOWN -> Axis.Y;
        };
    }

    public enum Axis {
        X,
        Y,
        Z;

        public static Axis fromName(String name) {
            try {
                return Axis.valueOf(name.toUpperCase());
            } catch (Exception e) {
                return X;
            }
        }

        public Axis opposite() {
            return this;
        }

        public boolean isVertical() {
            return this == Y;
        }

        public boolean isHorizontal() {
            return this != Y;
        }

        public String getName() {
            return name().toLowerCase();
        }
    }
}
