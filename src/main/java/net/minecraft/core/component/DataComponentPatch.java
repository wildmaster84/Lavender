package net.minecraft.core.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;

public final class DataComponentPatch {
    public static final DataComponentPatch EMPTY = new DataComponentPatch();

    public static final Codec<DataComponentPatch> CODEC = new Codec<DataComponentPatch>() {
        @Override
        public DataResult<DataComponentPatch> parse(DynamicOps<?> ops, Object input) {
            return DataResult.success((DataComponentPatch) input);
        }

        @Override
        public DataResult<?> encodeStart(DynamicOps<?> ops, DataComponentPatch input) {
            return DataResult.success(new net.minecraft.nbt.CompoundTag());
        }
    };
}
