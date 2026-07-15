package net.minecraft.world.level.storage;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.ProblemReporter;

public class TagValueInput implements ValueInput {
    public static ValueInput create(ProblemReporter reporter, HolderLookup.Provider provider, CompoundTag tag) {
        return new TagValueInput();
    }
}
