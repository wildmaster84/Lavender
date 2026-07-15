package net.minecraft.world.damagesource;

public class DamageSource {
    private final String type;

    public DamageSource(String type) {
        this.type = type;
    }

    public String type() {
        return type;
    }
}
