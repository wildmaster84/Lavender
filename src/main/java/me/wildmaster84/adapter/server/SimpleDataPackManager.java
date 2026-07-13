package me.wildmaster84.adapter.server;

import org.bukkit.packs.DataPack;
import org.bukkit.packs.DataPackManager;

import java.util.Collection;
import java.util.Collections;

public class SimpleDataPackManager implements DataPackManager {
    @Override
    public Collection<DataPack> getDataPacks() {
        return Collections.emptyList();
    }

    @Override
    public DataPack getDataPack(String name) {
        return null;
    }

    @Override
    public boolean isEnabled(DataPack dataPack) {
        return false;
    }

    @Override
    public boolean isEnabled(String name) {
        return false;
    }
}
