package org.bukkit.craftbukkit.util;

import net.minecraft.network.chat.Component;

public class CraftChatMessage {
    public static Component fromJSON(String json) {
        return Component.fromJson(json);
    }
}
