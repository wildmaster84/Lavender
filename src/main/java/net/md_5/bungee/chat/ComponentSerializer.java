package net.md_5.bungee.chat;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import com.google.gson.Gson;

public class ComponentSerializer {
    public static Gson gson = new Gson();

    public static BaseComponent[] parse(String json) {
        if (json == null || json.isEmpty()) return new BaseComponent[0];
        net.kyori.adventure.text.Component adventure = net.kyori.adventure.text.serializer.gson.GsonComponentSerializer.gson().deserialize(json);
        String legacy = net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer.legacySection().serialize(adventure);
        return new BaseComponent[] { new TextComponent(legacy) };
    }

    public static String toString(BaseComponent... components) {
        StringBuilder sb = new StringBuilder();
        for (BaseComponent c : components) sb.append(c.toLegacyText());
        return sb.toString();
    }
}
