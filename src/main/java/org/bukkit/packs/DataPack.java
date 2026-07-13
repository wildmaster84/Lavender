package org.bukkit.packs;

public interface DataPack {
    String getName();
    String getTitle();
    String getDescription();
    int getPackFormat();
    Source getSource();
    boolean isEnabled();
    boolean isForced();

    enum Source {
        BUILTIN,
        FEATURE,
        WORLD,
        SERVER;
    }
}
