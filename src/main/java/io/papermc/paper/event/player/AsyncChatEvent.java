package io.papermc.paper.event.player;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Cancellable;

public class AsyncChatEvent extends org.bukkit.event.player.PlayerEvent implements Cancellable {
    private final net.kyori.adventure.text.Component message;
    private boolean cancelled = false;

    public AsyncChatEvent(Player player, net.kyori.adventure.text.Component message) {
        super(player);
        this.message = message;
    }

    public net.kyori.adventure.text.Component message() { return message; }
    public String getMessage() { return net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer.plainText().serialize(message); }

    @Override public boolean isCancelled() { return cancelled; }
    @Override public void setCancelled(boolean cancelled) { this.cancelled = cancelled; }

    @Override public HandlerList getHandlers() { return new HandlerList(); }
    public static HandlerList getHandlerList() { return new HandlerList(); }
}
