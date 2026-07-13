package org.bukkit.event.player;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import java.util.Set;

public class AsyncPlayerChatEvent extends PlayerEvent implements Cancellable {
    private static final org.bukkit.event.HandlerList HANDLER_LIST = new org.bukkit.event.HandlerList();
    private boolean cancelled = false;
    private String message;
    private String format = "<%1$s> %2$s";
    private final Set<Player> recipients;

    public AsyncPlayerChatEvent(boolean async, Player player, String message, Set<Player> recipients) {
        super(player);
        this.message = message;
        this.recipients = recipients;
    }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public String getFormat() { return format; }
    public void setFormat(String format) { this.format = format; }
    public Set<Player> getRecipients() { return recipients; }
    public boolean isAsync() { return true; }

    @Override
    public boolean isCancelled() { return cancelled; }
    @Override
    public void setCancelled(boolean cancel) { this.cancelled = cancel; }

    @Override
    public org.bukkit.event.HandlerList getHandlers() { return HANDLER_LIST; }
    public static org.bukkit.event.HandlerList getHandlerList() { return HANDLER_LIST; }
}
