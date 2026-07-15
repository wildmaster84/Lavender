package net.minecraft.nbt;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class CompoundTag implements Tag {
    private final Map<String, Tag> tags = new LinkedHashMap<>();

    public CompoundTag() {
    }

    @Override
    public byte getId() {
        return 10;
    }

    public Set<String> keySet() {
        return tags.keySet();
    }

    public Tag get(String key) {
        return tags.get(key);
    }

    public Tag put(String key, Tag value) {
        return tags.put(key, value);
    }

    public Tag remove(String key) {
        return tags.remove(key);
    }

    public boolean isEmpty() {
        return tags.isEmpty();
    }

    public boolean contains(String key) {
        return tags.containsKey(key);
    }

    public void putString(String key, String value) {
        tags.put(key, StringTag.valueOf(value));
    }

    public Optional<String> getString(String key) {
        Tag tag = tags.get(key);
        if (tag instanceof StringTag stringTag) {
            return Optional.of(stringTag.value());
        }
        return Optional.empty();
    }

    public void putInt(String key, int value) {
        tags.put(key, IntTag.valueOf(value));
    }

    public Optional<Integer> getInt(String key) {
        Tag tag = tags.get(key);
        if (tag instanceof IntTag intTag) {
            return Optional.of(intTag.intValue());
        }
        return Optional.empty();
    }

    public int getIntOr(String key, int defaultValue) {
        Tag tag = tags.get(key);
        if (tag instanceof IntTag intTag) {
            return intTag.intValue();
        }
        return defaultValue;
    }

    public void putByte(String key, byte value) {
        tags.put(key, ByteTag.valueOf(value));
    }

    public Optional<Byte> getByte(String key) {
        Tag tag = tags.get(key);
        if (tag instanceof ByteTag byteTag) {
            return Optional.of(byteTag.byteValue());
        }
        return Optional.empty();
    }

    public byte getByteOr(String key, byte defaultValue) {
        Tag tag = tags.get(key);
        if (tag instanceof ByteTag byteTag) {
            return byteTag.byteValue();
        }
        return defaultValue;
    }

    public void putShort(String key, short value) {
        tags.put(key, ShortTag.valueOf(value));
    }

    public Optional<Short> getShort(String key) {
        Tag tag = tags.get(key);
        if (tag instanceof ShortTag shortTag) {
            return Optional.of(shortTag.shortValue());
        }
        return Optional.empty();
    }

    public short getShortOr(String key, short defaultValue) {
        Tag tag = tags.get(key);
        if (tag instanceof ShortTag shortTag) {
            return shortTag.shortValue();
        }
        return defaultValue;
    }

    public void putFloat(String key, float value) {
        tags.put(key, FloatTag.valueOf(value));
    }

    public Optional<Float> getFloat(String key) {
        Tag tag = tags.get(key);
        if (tag instanceof FloatTag floatTag) {
            return Optional.of(floatTag.floatValue());
        }
        return Optional.empty();
    }

    public Optional<Boolean> getBoolean(String key) {
        Tag tag = tags.get(key);
        if (tag instanceof ByteTag byteTag) {
            return Optional.of(byteTag.byteValue() != 0);
        }
        return Optional.empty();
    }

    public void putIntArray(String key, int[] value) {
        tags.put(key, new IntArrayTag(value));
    }

    public Optional<byte[]> getByteArray(String key) {
        Tag tag = tags.get(key);
        if (tag instanceof ByteArrayTag byteArrayTag) {
            return Optional.of(byteArrayTag.getAsByteArray());
        }
        return Optional.empty();
    }

    public Optional<CompoundTag> getCompound(String key) {
        Tag tag = tags.get(key);
        if (tag instanceof CompoundTag compoundTag) {
            return Optional.of(compoundTag);
        }
        return Optional.empty();
    }

    public CompoundTag getCompoundOrEmpty(String key) {
        Tag tag = tags.get(key);
        if (tag instanceof CompoundTag compoundTag) {
            return compoundTag;
        }
        return new CompoundTag();
    }

    public Optional<ListTag> getList(String key) {
        Tag tag = tags.get(key);
        if (tag instanceof ListTag listTag) {
            return Optional.of(listTag);
        }
        return Optional.empty();
    }

    public ListTag getListOrEmpty(String key) {
        Tag tag = tags.get(key);
        if (tag instanceof ListTag listTag) {
            return listTag;
        }
        return new ListTag();
    }

}
