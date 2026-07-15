package net.minecraft.nbt;

import java.util.ArrayList;
import java.util.Optional;

public class ListTag extends ArrayList<Tag> implements Tag {
    public ListTag() {
        super();
    }

    @Override
    public byte getId() {
        return 9;
    }

    @Override
    public Tag set(int index, Tag element) {
        return super.set(index, element);
    }

    public void addAndUnwrap(Tag tag) {
        add(tag);
    }

    public CompoundTag getCompoundOrEmpty(int index) {
        Tag tag = get(index);
        if (tag instanceof CompoundTag compoundTag) {
            return compoundTag;
        }
        return new CompoundTag();
    }

    public Optional<String> getString(int index) {
        Tag tag = get(index);
        if (tag instanceof StringTag stringTag) {
            return Optional.of(stringTag.value());
        }
        return Optional.empty();
    }

    public float getFloatOr(int index, float defaultValue) {
        Tag tag = get(index);
        if (tag instanceof FloatTag floatTag) {
            return floatTag.floatValue();
        }
        return defaultValue;
    }
}
