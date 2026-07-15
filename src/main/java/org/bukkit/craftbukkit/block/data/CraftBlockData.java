package org.bukkit.craftbukkit.block.data;

import net.minecraft.world.level.block.state.BlockState;
import org.bukkit.Material;
import org.bukkit.block.data.BlockData;

public class CraftBlockData implements BlockData {
    private final Material material;
    private final String representation;

    protected CraftBlockData(Material material, String extra) {
        this.material = material;
        String base = "minecraft:" + material.name().toLowerCase(java.util.Locale.ROOT);
        this.representation = extra != null && !extra.isEmpty() ? base + "[" + extra + "]" : base;
    }

    public BlockState getState() {
        String key = "minecraft:" + material.name().toLowerCase(java.util.Locale.ROOT);
        net.minestom.server.instance.block.Block msBlock = net.minestom.server.instance.block.Block.fromKey(key);
        if (msBlock == null) msBlock = net.minestom.server.instance.block.Block.AIR;
        return new BlockState(net.minecraft.world.level.block.Block.of(msBlock.defaultState()), msBlock);
    }

    public static CraftBlockData createData(net.minecraft.world.level.block.state.BlockState state) {
        net.minestom.server.instance.block.Block msBlock = state.getMinestomBlock();
        String name = msBlock.name();
        String stripped = name.startsWith("minecraft:") ? name.substring(10) : name;
        Material material = Material.matchMaterial(stripped);
        if (material == null) material = Material.AIR;
        StringBuilder extra = new StringBuilder();
        for (java.util.Map.Entry<String, String> entry : msBlock.properties().entrySet()) {
            if (extra.length() > 0) extra.append(",");
            extra.append(entry.getKey()).append("=").append(entry.getValue());
        }
        return new CraftBlockData(material, extra.toString()) {
            @Override
            public BlockState getState() {
                return state;
            }
        };
    }

    @Override
    public String getAsString() { return representation; }

    @Override
    public String getAsString(boolean hideUnused) { return representation; }

    @Override
    public Material getMaterial() { return material; }

    @Override
    public BlockData clone() { return new CraftBlockData(material, extractExtra()); }

    private String extractExtra() {
        int bracketIdx = representation.indexOf('[');
        if (bracketIdx < 0) return "";
        int closeIdx = representation.indexOf(']', bracketIdx);
        if (closeIdx < 0) return "";
        return representation.substring(bracketIdx + 1, closeIdx);
    }

    @Override
    public boolean matches(BlockData data) {
        if (data == null) return false;
        return material == data.getMaterial();
    }

    @Override
    public org.bukkit.block.data.SoundGroup getSoundGroup() { return null; }

    @Override
    public float getDestroySpeed() { return 1.0f; }

    @Override
    public int getLightEmission() { return 0; }

    @Override
    public boolean isOccluding() { return false; }

    @Override
    public boolean isFaceSturdy(org.bukkit.block.BlockFace face, boolean isCorrectFace) { return false; }

    @Override
    public boolean isPreferredTool(org.bukkit.inventory.ItemStack item) { return false; }

    @Override
    public boolean isCollidable() { return false; }

    @Override
    public org.bukkit.block.BlockState createBlockState() { return null; }

    @Override
    public int getLight() { return 0; }

    @Override
    public int getOpacity() { return 0; }

    @Override
    public org.bukkit.Axis getAxis() { return null; }

    @Override
    public boolean isFlammable() { return false; }

    @Override
    public boolean isRandomlyTicked() { return false; }
}
