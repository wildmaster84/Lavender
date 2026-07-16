package org.bukkit.material;

import org.bukkit.Material;

public class MaterialData {
    private final Material material;
    private byte data;

    public MaterialData(Material material) {
        this.material = material;
    }

    public MaterialData(Material material, byte data) {
        this.material = material;
        this.data = data;
    }

    public Material getItemType() { return material; }
    public byte getData() { return data; }
    public void setData(byte data) { this.data = data; }
    public MaterialData toItemStack() { return this; }
    public MaterialData clone() { return new MaterialData(material, data); }
    public String toString() { return material + ":" + data; }
}
