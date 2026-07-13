package org.bukkit.command;

public interface BlockCommandSender extends CommandSender {
    org.bukkit.block.Block getBlock();
}
