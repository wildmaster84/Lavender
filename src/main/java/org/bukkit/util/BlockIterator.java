package org.bukkit.util;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class BlockIterator implements Iterator<Block> {

    private final World world;
    private final int maxDistance;
    private int currentDistance;

    private int blockX, blockY, blockZ;
    private int stepX, stepY, stepZ;
    private double tMaxX, tMaxY, tMaxZ;
    private final double tDeltaX, tDeltaY, tDeltaZ;

    public BlockIterator(World world, Vector start, Vector direction, double yOffset, int maxDistance) {
        this.world = world;
        this.maxDistance = maxDistance;

        double startX = start.getX();
        double startY = start.getY() + yOffset;
        double startZ = start.getZ();

        Vector dir = direction.clone().normalize();

        blockX = (int) Math.floor(startX);
        blockY = (int) Math.floor(startY);
        blockZ = (int) Math.floor(startZ);

        stepX = (int) Math.signum(dir.getX());
        stepY = (int) Math.signum(dir.getY());
        stepZ = (int) Math.signum(dir.getZ());

        double absX = Math.abs(dir.getX());
        double absY = Math.abs(dir.getY());
        double absZ = Math.abs(dir.getZ());

        tDeltaX = absX > 0 ? 1.0 / absX : Double.MAX_VALUE;
        tDeltaY = absY > 0 ? 1.0 / absY : Double.MAX_VALUE;
        tDeltaZ = absZ > 0 ? 1.0 / absZ : Double.MAX_VALUE;

        if (stepX > 0) {
            tMaxX = (blockX + 1 - startX) * tDeltaX;
        } else if (stepX < 0) {
            tMaxX = (startX - blockX) * tDeltaX;
        } else {
            tMaxX = Double.MAX_VALUE;
        }

        if (stepY > 0) {
            tMaxY = (blockY + 1 - startY) * tDeltaY;
        } else if (stepY < 0) {
            tMaxY = (startY - blockY) * tDeltaY;
        } else {
            tMaxY = Double.MAX_VALUE;
        }

        if (stepZ > 0) {
            tMaxZ = (blockZ + 1 - startZ) * tDeltaZ;
        } else if (stepZ < 0) {
            tMaxZ = (startZ - blockZ) * tDeltaZ;
        } else {
            tMaxZ = Double.MAX_VALUE;
        }

        currentDistance = 0;
    }

    public BlockIterator(Location loc, double yOffset, int maxDistance) {
        this(loc.getWorld(), loc.toVector(), getDirection(loc), yOffset, maxDistance);
    }

    public BlockIterator(Location loc, double yOffset) {
        this(loc, yOffset, 0);
    }

    public BlockIterator(Location loc) {
        this(loc, 0, 0);
    }

    public BlockIterator(LivingEntity entity, int maxDistance) {
        this(entity.getEyeLocation(), 0, maxDistance);
    }

    public BlockIterator(LivingEntity entity) {
        this(entity, 0);
    }

    private static Vector getDirection(Location loc) {
        double yaw = Math.toRadians(-loc.getYaw() - 90);
        double pitch = Math.toRadians(-loc.getPitch());
        double xz = Math.cos(pitch);
        return new Vector(
            -xz * Math.sin(yaw),
            -Math.sin(pitch),
            xz * Math.cos(yaw)
        );
    }

    @Override
    public boolean hasNext() {
        if (maxDistance > 0 && currentDistance >= maxDistance) return false;
        return tMaxX != Double.MAX_VALUE || tMaxY != Double.MAX_VALUE || tMaxZ != Double.MAX_VALUE;
    }

    @Override
    public Block next() {
        if (!hasNext()) throw new NoSuchElementException();

        Block block = world.getBlockAt(blockX, blockY, blockZ);

        if (tMaxX < tMaxY) {
            if (tMaxX < tMaxZ) {
                blockX += stepX;
                tMaxX += tDeltaX;
            } else {
                blockZ += stepZ;
                tMaxZ += tDeltaZ;
            }
        } else {
            if (tMaxY < tMaxZ) {
                blockY += stepY;
                tMaxY += tDeltaY;
            } else {
                blockZ += stepZ;
                tMaxZ += tDeltaZ;
            }
        }

        currentDistance++;
        return block;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
