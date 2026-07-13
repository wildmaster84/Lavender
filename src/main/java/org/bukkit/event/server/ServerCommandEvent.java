package org.bukkit.event.server;

import org.bukkit.command.CommandSender;

public class ServerCommandEvent extends ServerEvent {
    public ServerCommandEvent(CommandSender sender, String command) {}
    public String getCommand() { return null; }
    public void setCommand(String command) {}
    public CommandSender getSender() { return null; }
    @Override public org.bukkit.event.HandlerList getHandlers() { return new org.bukkit.event.HandlerList(); }
    public static org.bukkit.event.HandlerList getHandlerList() { return new org.bukkit.event.HandlerList(); }
}