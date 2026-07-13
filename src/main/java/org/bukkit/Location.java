package org.bukkit;

public class Location implements Cloneable {
    private World world;
    private double x, y, z;
    private float yaw, pitch;

    public Location(World world, double x, double y, double z) {
        this(world, x, y, z, 0, 0);
    }

    public Location(World world, double x, double y, double z, float yaw, float pitch) {
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
    }

    public Location() {}

    public World getWorld() { return world; }
    public void setWorld(World world) { this.world = world; }
    public double getX() { return x; }
    public void setX(double x) { this.x = x; }
    public double getY() { return y; }
    public void setY(double y) { this.y = y; }
    public double getZ() { return z; }
    public void setZ(double z) { this.z = z; }
    public float getYaw() { return yaw; }
    public void setYaw(float yaw) { this.yaw = yaw; }
    public float getPitch() { return pitch; }
    public void setPitch(float pitch) { this.pitch = pitch; }
    public int getBlockX() { return (int) Math.floor(x); }
    public int getBlockY() { return (int) Math.floor(y); }
    public int getBlockZ() { return (int) Math.floor(z); }
    public double length() { return Math.sqrt(x*x + y*y + z*z); }
    public double lengthSquared() { return x*x + y*y + z*z; }
    public double distance(Location o) { return Math.sqrt(distanceSquared(o)); }
    public double distanceSquared(Location o) {
        return Math.pow(x - o.x, 2) + Math.pow(y - o.y, 2) + Math.pow(z - o.z, 2);
    }
    public Location add(double x, double y, double z) { this.x += x; this.y += y; this.z += z; return this; }
    public Location add(Location vec) { this.x += vec.x; this.y += vec.y; this.z += vec.z; return this; }
    public Location subtract(double x, double y, double z) { this.x -= x; this.y -= y; this.z -= z; return this; }
    public Location subtract(Location vec) { this.x -= vec.x; this.y -= vec.y; this.z -= vec.z; return this; }
    public Location set(double x, double y, double z) { this.x = x; this.y = y; this.z = z; return this; }
    public Location multiply(double m) { x *= m; y *= m; z *= m; return this; }
    public Location zero() { x = y = z = 0; return this; }
    @Override public Location clone() { return new Location(world, x, y, z, yaw, pitch); }
    @Override public String toString() { return "Location{" + "world=" + world + ",x=" + x + ",y=" + y + ",z=" + z + ",yaw=" + yaw + ",pitch=" + pitch + "}"; }
    public org.bukkit.util.Vector toVector() { return new org.bukkit.util.Vector(x, y, z); }
    public org.bukkit.block.Block getBlock() { return world != null ? world.getBlockAt(getBlockX(), getBlockY(), getBlockZ()) : null; }
    public org.bukkit.Chunk getChunk() { return world != null ? world.getChunkAt(getBlockX() >> 4, getBlockZ() >> 4) : null; }
}
