package me.wildmaster84.adapter.entity;

import me.wildmaster84.adapter.server.LavenderServer;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.Entity;
import net.minestom.server.coordinate.Vec;

public class LavenderEntity implements org.bukkit.entity.Entity {

    private final Entity entity;
    private final LavenderServer server;

    public LavenderEntity(Entity entity, LavenderServer server) {
        this.entity = entity;
        this.server = server;
    }

    @Override
    public java.util.UUID getUniqueId() {
        return entity.getUuid();
    }

    @Override
    public String getName() {
        return entity.getEntityType().name();
    }

    @Override
    public org.bukkit.Location getLocation() {
        Pos pos = entity.getPosition();
        return new org.bukkit.Location(server.getWorld("world"), pos.x(), pos.y(), pos.z(), pos.yaw(), pos.pitch());
    }

    @Override
    public org.bukkit.World getWorld() {
        net.minestom.server.instance.Instance instance = entity.getInstance();
        if (instance != null) return server.getWorld(instance);
        return server.getWorld("world");
    }

    @Override
    public double getX() { return entity.getPosition().x(); }
    @Override
    public double getY() { return entity.getPosition().y(); }
    @Override
    public double getZ() { return entity.getPosition().z(); }

    @Override
    public org.bukkit.util.Vector getVelocity() {
        Vec v = entity.getVelocity();
        return new org.bukkit.util.Vector(v.x(), v.y(), v.z());
    }

    @Override
    public void setVelocity(org.bukkit.util.Vector velocity) {
        entity.setVelocity(new Vec(velocity.getX(), velocity.getY(), velocity.getZ()));
    }

    @Override
    public boolean isOnGround() {
        return entity.isOnGround();
    }

    @Override
    public boolean isDead() {
        return entity.isRemoved();
    }

    @Override
    public boolean isValid() {
        return !entity.isRemoved();
    }

    @Override
    public void remove() {
        entity.remove();
    }

    @Override
    public int getEntityId() {
        return entity.getEntityId();
    }

    @Override
    public int getFireTicks() { return 0; }
    @Override
    public void setFireTicks(int ticks) {}
    @Override
    public int getMaxFireTicks() { return 20; }
    @Override
    public float getFallDistance() { return 0; }
    @Override
    public void setFallDistance(float distance) {}
    @Override
    public boolean isInsideVehicle() { return entity.getVehicle() != null; }
    @Override
    public boolean leaveVehicle() {
        if (entity.getVehicle() != null) {
            entity.getVehicle().removePassenger(entity);
            return true;
        }
        return false;
    }
    @Override
    public org.bukkit.entity.Entity getVehicle() { return null; }

    @Override
    public void setCustomName(String name) {
        entity.setCustomName(net.kyori.adventure.text.Component.text(name));
    }

    @Override
    public String getCustomName() {
        net.kyori.adventure.text.Component name = entity.getCustomName();
        return name != null ? net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer.plainText().serialize(name) : null;
    }

    @Override
    public void setCustomNameVisible(boolean flag) { entity.setCustomNameVisible(flag); }
    @Override
    public boolean isCustomNameVisible() { return entity.isCustomNameVisible(); }
    @Override
    public void setGlowing(boolean flag) { entity.setGlowing(flag); }
    @Override
    public boolean isGlowing() { return entity.isGlowing(); }
    @Override
    public void setInvulnerable(boolean flag) {}
    @Override
    public boolean isInvulnerable() { return false; }
    @Override
    public void setSilent(boolean flag) {}
    @Override
    public boolean isSilent() { return false; }
    @Override
    public boolean hasGravity() { return true; }
    @Override
    public void setGravity(boolean gravity) {}
    @Override
    public int getPortalCooldown() { return 0; }
    @Override
    public void setPortalCooldown(int cooldown) {}
    @Override
    public int getTicksLived() { return 0; }
    @Override
    public void setTicksLived(int value) {}
    @Override
    public void sendMessage(String message) {}
    @Override
    public void sendMessage(String[] messages) {}
    @Override
    public boolean isOp() { return false; }
    @Override
    public void setOp(boolean value) {}

    @Override
    public void setMetadata(String metadataKey, org.bukkit.metadata.MetadataValue metadataValue) {}
    @Override
    public java.util.List<org.bukkit.metadata.MetadataValue> getMetadata(String metadataKey) { return java.util.Collections.emptyList(); }
    @Override
    public boolean hasMetadata(String metadataKey) { return false; }
    @Override
    public void removeMetadata(String metadataKey, org.bukkit.plugin.Plugin plugin) {}

    @Override
    public org.bukkit.entity.EntityType getType() {
        return org.bukkit.entity.EntityType.valueOf(entity.getEntityType().name());
    }

    @Override
    public boolean teleport(org.bukkit.Location location) { return false; }
    @Override
    public boolean teleport(org.bukkit.entity.Entity destination) { return false; }

    @Override
    public boolean eject() {
        if (!entity.getPassengers().isEmpty()) {
            entity.getPassengers().forEach(entity::removePassenger);
            return true;
        }
        return false;
    }
}
