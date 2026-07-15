package net.minecraft.server.level;

import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.player.ChatVisiblity;

public class ClientInformation {
    public ClientInformation(
            String language,
            int viewDistance,
            ChatVisiblity chatVisibility,
            boolean chatColors,
            int modelCustomisation,
            HumanoidArm mainHand,
            boolean filterText,
            boolean allowsListing,
            ParticleStatus particleStatus
    ) {
    }
}
