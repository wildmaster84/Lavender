package net.minecraft.network.chat;

import com.mojang.serialization.Codec;

public class ComponentSerialization {
    public static final Codec<MutableComponent> CODEC = Codec.simple();
}
