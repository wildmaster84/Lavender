package org.bukkit.craftbukkit.inventory;

public class CraftItemStack extends org.bukkit.inventory.ItemStack {
    private net.minecraft.world.item.ItemStack nmsStack;

    public CraftItemStack() {
    }

    private CraftItemStack(net.minecraft.world.item.ItemStack nmsStack) {
        this.nmsStack = nmsStack;
    }

    public static CraftItemStack asCraftMirror(net.minecraft.world.item.ItemStack nmsStack) {
        return new CraftItemStack(nmsStack);
    }

    public static net.minecraft.world.item.ItemStack asNMSCopy(org.bukkit.inventory.ItemStack bukkitStack) {
        if (bukkitStack == null || bukkitStack.getType() == org.bukkit.Material.AIR) {
            return new net.minecraft.world.item.ItemStack();
        }
        String name = "minecraft:" + bukkitStack.getType().name().toLowerCase(java.util.Locale.ROOT);
        net.minestom.server.item.Material mat = net.minestom.server.item.Material.fromKey(name);
        if (mat == null) return new net.minecraft.world.item.ItemStack();
        return new net.minecraft.world.item.ItemStack(
                net.minestom.server.item.ItemStack.of(mat, bukkitStack.getAmount())
        );
    }

    @Override
    public org.bukkit.Material getType() {
        if (nmsStack == null) return org.bukkit.Material.AIR;
        net.minestom.server.item.Material mat = nmsStack.getMinestomStack().material();
        String name = mat.name();
        String stripped = name.startsWith("minecraft:") ? name.substring(10) : name;
        return org.bukkit.Material.matchMaterial(stripped);
    }

    @Override
    public int getAmount() {
        if (nmsStack == null) return 0;
        return nmsStack.getMinestomStack().amount();
    }

    public net.minecraft.world.item.ItemStack getHandle() {
        return nmsStack != null ? nmsStack : new net.minecraft.world.item.ItemStack();
    }
}
