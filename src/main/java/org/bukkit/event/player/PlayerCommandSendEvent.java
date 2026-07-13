package org.bukkit.event.player;

import java.util.Collection;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerCommandSendEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private final Player player;
    private final Collection<String> commands;

    public PlayerCommandSendEvent(Player player, Collection<String> commands) {
        this.player = player;
        this.commands = commands;
    }

    public Player getPlayer() { return player; }
    public Collection<String> getCommands() { return commands; }

    @Override
    public HandlerList getHandlers() { return handlers; }
    public static HandlerList getHandlerList() { return handlers; }
}
