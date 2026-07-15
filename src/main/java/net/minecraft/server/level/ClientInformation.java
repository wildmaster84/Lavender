package net.minecraft.server.level;

import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.player.ChatVisiblity;

public class ClientInformation {
    private final String language;
    private final int viewDistance;
    private final ChatVisiblity chatVisibility;
    private final boolean chatColors;
    private final int modelCustomisation;
    private final HumanoidArm mainHand;
    private final boolean filterText;
    private final boolean allowsListing;
    private final ParticleStatus particleStatus;

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
        this.language = language;
        this.viewDistance = viewDistance;
        this.chatVisibility = chatVisibility;
        this.chatColors = chatColors;
        this.modelCustomisation = modelCustomisation;
        this.mainHand = mainHand;
        this.filterText = filterText;
        this.allowsListing = allowsListing;
        this.particleStatus = particleStatus;
    }

    public String language() { return language; }
    public int viewDistance() { return viewDistance; }
    public ChatVisiblity chatVisibility() { return chatVisibility; }
    public boolean chatColors() { return chatColors; }
    public HumanoidArm mainHand() { return mainHand; }
}
