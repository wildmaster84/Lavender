package net.minecraft.world.level.material;

public class FluidState {
    private final net.minestom.server.instance.block.Block block;

    public FluidState() {
        this(null);
    }

    public FluidState(net.minestom.server.instance.block.Block block) {
        this.block = block;
    }

    public boolean isEmpty() {
        return block == null || !block.isLiquid();
    }

    public boolean isSource() {
        if (block == null) return false;
        String level = block.getProperty("level");
        return level == null || level.equals("0");
    }

    public int getAmount() {
        if (block == null) return 0;
        String level = block.getProperty("level");
        if (level == null) return 0;
        try { return Integer.parseInt(level); } catch (NumberFormatException e) { return 0; }
    }
}
