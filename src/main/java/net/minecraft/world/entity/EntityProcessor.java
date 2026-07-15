package net.minecraft.world.entity;

@FunctionalInterface
public interface EntityProcessor {
    Entity process(Entity entity);
}
