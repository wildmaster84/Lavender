package me.wildmaster84.adapter.world;

import org.bukkit.Material;

public class SimpleBlockData extends org.bukkit.craftbukkit.block.data.CraftBlockData {

    public SimpleBlockData(String data) {
        super(parseMaterial(data), parseExtra(data));
    }

    public SimpleBlockData(Material material) {
        super(material, "");
    }

    public SimpleBlockData(Material material, String extra) {
        super(material, extra);
    }

    private static Material parseMaterial(String data) {
        String namePart = data;
        int bracketIdx = data.indexOf('[');
        if (bracketIdx >= 0) {
            namePart = data.substring(0, bracketIdx);
        }
        if (namePart.startsWith("minecraft:")) {
            namePart = namePart.substring(10);
        }
        Material parsed = Material.matchMaterial(namePart);
        return parsed != null ? parsed : Material.AIR;
    }

    private static String parseExtra(String data) {
        int bracketIdx = data.indexOf('[');
        if (bracketIdx < 0) return "";
        int closeIdx = data.indexOf(']', bracketIdx);
        if (closeIdx < 0) return "";
        return data.substring(bracketIdx + 1, closeIdx);
    }
}
