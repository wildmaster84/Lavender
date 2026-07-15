package net.md_5.bungee.chat;

import com.google.gson.Gson;

public class VersionedComponentSerializer {
    private static final VersionedComponentSerializer INSTANCE = new VersionedComponentSerializer();
    private final Gson gson = new Gson();

    public static VersionedComponentSerializer forVersion(ChatVersion version) {
        return INSTANCE;
    }

    public Gson getGson() {
        return gson;
    }
}
