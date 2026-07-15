package net.minecraft.core;

public class SectionPos {
    public static int blockToSectionCoord(int coord) {
        return coord >> 4;
    }
}
