package net.minecraft.world.level.storage;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.ProblemReporter;

public class TagValueOutput implements ValueOutput {
    public static TagValueOutput createWithContext(ProblemReporter reporter, HolderLookup.Provider provider) {
        return new TagValueOutput();
    }

    public CompoundTag buildResult() {
        return new CompoundTag();
    }
}
