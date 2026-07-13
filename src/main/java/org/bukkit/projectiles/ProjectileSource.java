package org.bukkit.projectiles;

public interface ProjectileSource {
    <T extends org.bukkit.entity.Projectile> T launchProjectile(java.lang.Class<? extends org.bukkit.entity.Projectile> projectile);
    <T extends org.bukkit.entity.Projectile> T launchProjectile(java.lang.Class<? extends org.bukkit.entity.Projectile> projectile, org.bukkit.util.Vector velocity);
}
