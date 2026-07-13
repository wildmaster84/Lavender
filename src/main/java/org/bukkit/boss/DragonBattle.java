package org.bukkit.boss;

import org.bukkit.Location;
import org.bukkit.entity.EnderDragon;

public interface DragonBattle {
    EnderDragon getEnderDragon();
    org.bukkit.boss.BossBar getBossBar();
    Location getEndPortalLocation();
    void setEndPortalLocation(Location location);
    boolean hasDraggedKilledPreviously();
    void setPreviouslyKilled(boolean previouslyKilled);
    int getRespawnPhase();
    boolean setRespawnPhase(org.bukkit.boss.DragonBattle.RespawnPhase phase);
    void resetCrystals();
    boolean respawnCrystals(java.util.List<org.bukkit.Location> list);

    enum RespawnPhase {
        START,
        PREPARE_TO_SUMMON_PILLARS,
        SUMMON_PILLARS,
        SUMMONING_DRAGON,
        END;
    }
}
