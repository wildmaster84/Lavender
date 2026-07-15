package org.bukkit.block;

import org.bukkit.block.BlockState;

public interface Sign extends BlockState {
    String[] getLines();
    String getLine(int index) throws IndexOutOfBoundsException;
    void setLine(int index, String line) throws IndexOutOfBoundsException;
}
