package org.bukkit.event.block;

import java.util.List;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.event.HandlerList;

public class BlockMultiPlaceEvent extends BlockPlaceEvent {
    private static final HandlerList HANDLER_LIST = new HandlerList();
    private final List<BlockState> states;

    public BlockMultiPlaceEvent(List<BlockState> states, Block clickedBlock, Block placedAgainst, ItemStack itemInHand, Player player, boolean canBuild) {
        super(clickedBlock, states.isEmpty() ? null : states.get(0), placedAgainst, itemInHand, player, canBuild);
        this.states = states;
    }

    public List<BlockState> getReplacedBlockStates() { return states; }
    @Override public HandlerList getHandlers() { return HANDLER_LIST; }
    public static HandlerList getHandlerList() { return HANDLER_LIST; }
}
