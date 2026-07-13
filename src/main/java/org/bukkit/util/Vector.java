package org.bukkit.util;

public class Vector implements Cloneable {
    private double x, y, z;

    public Vector() { this(0, 0, 0); }
    public Vector(int x, int y, int z) { this.x = x; this.y = y; this.z = z; }
    public Vector(float x, float y, float z) { this.x = x; this.y = y; this.z = z; }
    public Vector(double x, double y, double z) { this.x = x; this.y = y; this.z = z; }

    public double getX() { return x; }
    public double getY() { return y; }
    public double getZ() { return z; }
    public void setX(double x) { this.x = x; }
    public void setY(double y) { this.y = y; }
    public void setZ(double z) { this.z = z; }
    public int getBlockX() { return (int) Math.floor(x); }
    public int getBlockY() { return (int) Math.floor(y); }
    public int getBlockZ() { return (int) Math.floor(z); }

    public Vector add(Vector vec) { x += vec.x; y += vec.y; z += vec.z; return this; }
    public Vector subtract(Vector vec) { x -= vec.x; y -= vec.y; z -= vec.z; return this; }
    public Vector multiply(double m) { x *= m; y *= m; z *= m; return this; }
    public Vector multiply(Vector vec) { x *= vec.x; y *= vec.y; z *= vec.z; return this; }
    public Vector divide(double d) { x /= d; y /= d; z /= d; return this; }
    public Vector divide(Vector vec) { x /= vec.x; y /= vec.y; z /= vec.z; return this; }
    public Vector copy(Vector vec) { x = vec.x; y = vec.y; z = vec.z; return this; }
    public Vector zero() { x = y = z = 0; return this; }
    public double length() { return Math.sqrt(lengthSquared()); }
    public double lengthSquared() { return x*x + y*y + z*z; }
    public double distance(Vector o) { return Math.sqrt(distanceSquared(o)); }
    public double distanceSquared(Vector o) { return Math.pow(x-o.x,2) + Math.pow(y-o.y,2) + Math.pow(z-o.z,2); }
    public float angle(Vector other) { return (float) Math.acos(dot(other) / (length() * other.length())); }
    public double dot(Vector other) { return x*other.x + y*other.y + z*other.z; }
    public Vector crossProduct(Vector o) { return new Vector(y*o.z - z*o.y, z*o.x - x*o.z, x*o.y - y*o.x); }
    public Vector normalize() { double len = length(); if (len > 0) { x /= len; y /= len; z /= len; } return this; }
    public Vector midpoint(Vector other) { return new Vector((x+other.x)/2, (y+other.y)/2, (z+other.z)/2); }
    public static Vector getMinimum(Vector v1, Vector v2) { return new Vector(Math.min(v1.x, v2.x), Math.min(v1.y, v2.y), Math.min(v1.z, v2.z)); }
    public static Vector getMaximum(Vector v1, Vector v2) { return new Vector(Math.max(v1.x, v2.x), Math.max(v1.y, v2.y), Math.max(v1.z, v2.z)); }
    public static Vector getRandom() { return new Vector(Math.random(), Math.random(), Math.random()); }
    @Override public Vector clone() { return new Vector(x, y, z); }
    @Override public String toString() { return "Vector{x=" + x + ",y=" + y + ",z=" + z + "}"; }
    @Override public boolean equals(Object obj) { if (!(obj instanceof Vector)) return false; Vector v = (Vector) obj; return x == v.x && y == v.y && z == v.z; }
    @Override public int hashCode() { return java.util.Objects.hash(x, y, z); }
}
