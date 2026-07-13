package org.bukkit.event.server;

import org.bukkit.command.CommandSender;

public class ServerCommandEvent extends ServerEvent {
    private static final org.bukkit.event.HandlerList HANDLER_LIST = new org.bukkit.event.HandlerList();
    public ServerCommandEvent(CommandSender sender, String command) {}
    public String getCommand() { return null; }
    public void setCommand(String command) {}
    public CommandSender getSender() { return null; }
    @Override public org.bukkit.event.HandlerList getHandlers() { return HANDLER_LIST; }
    public static org.bukkit.event.HandlerList getHandlerList() { return HANDLER_LIST; }
}