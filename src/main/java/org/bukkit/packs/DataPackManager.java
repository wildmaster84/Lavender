package org.bukkit.packs;

import java.util.Collection;

public interface DataPackManager {
    Collection<DataPack> getDataPacks();
    DataPack getDataPack(String name);
    boolean isEnabled(DataPack dataPack);
    boolean isEnabled(String name);
}
