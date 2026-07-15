package net.minecraft.world.level.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class StructureBlockEntity extends BlockEntity {
    public void setSaveName(String name) {
    }

    public void setAuthor(String author) {
    }

    public void setMode(net.minecraft.world.level.block.entity.StructureBlockEntity.Mode mode) {
    }

    public void setBoundingBox(BlockPos pos1, BlockPos pos2) {
    }

    public boolean saveStructure() {
        return true;
    }

    public boolean loadStructure() {
        return true;
    }

    public enum Mode {
        SAVE,
        LOAD,
        CORNER,
        DATA
    }
}
