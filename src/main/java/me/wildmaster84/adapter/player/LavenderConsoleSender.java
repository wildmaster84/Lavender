package me.wildmaster84.adapter.player;

import org.bukkit.Server;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;

import java.util.Set;

public class LavenderConsoleSender implements ConsoleCommandSender {

    private final Server server;

    public LavenderConsoleSender(Server server) {
        this.server = server;
    }

    @Override public void sendMessage(String message) {
        server.getLogger().info(convertColorCodes(message));
    }
    @Override public void sendMessage(String[] messages) { for (String m : messages) sendMessage(m); }
    @Override public void sendMessage(java.util.UUID sender, String message) { sendMessage(message); }
    @Override public void sendMessage(java.util.UUID sender, String[] messages) { for (String m : messages) sendMessage(m); }
    @Override public String getName() { return "Console"; }
    @Override public boolean isOp() { return true; }
    @Override public void setOp(boolean value) {}
    @Override public Set<PermissionAttachment> getEffectivePermissions() { return java.util.Collections.emptySet(); }
    @Override public PermissionAttachment addAttachment(org.bukkit.plugin.Plugin plugin, String name, boolean value) { return null; }
    @Override public PermissionAttachment addAttachment(org.bukkit.plugin.Plugin plugin) { return null; }
    @Override public void recalculatePermissions() {}
    @Override public boolean hasPermission(String name) { return true; }
    @Override public boolean hasPermission(Permission perm) { return true; }
    @Override public Server getServer() { return server; }
    @Override public ConsoleCommandSender getConsoleSender() { return this; }
    @Override public boolean isConversing() { return false; }

    public static String convertColorCodes(String message) {
        if (message == null) return "";
        StringBuilder sb = new StringBuilder(message.length());
        for (int i = 0; i < message.length(); i++) {
            char c = message.charAt(i);
            if (c == '\u00A7' && i + 1 < message.length()) {
                char code = Character.toLowerCase(message.charAt(i + 1));
                String ansi = minecraftToAnsi(code);
                if (ansi != null) {
                    sb.append(ansi);
                    i++;
                } else {
                    sb.append(c);
                }
            } else {
                sb.append(c);
            }
        }
        if (sb.length() > 0 && sb.toString().contains("\u001b[")) {
            sb.append("\u001b[0m");
        }
        return sb.toString();
    }

    private static String minecraftToAnsi(char code) {
        switch (code) {
            case '0': return "\u001b[30m"; // black
            case '1': return "\u001b[34m"; // dark blue
            case '2': return "\u001b[32m"; // dark green
            case '3': return "\u001b[36m"; // dark aqua
            case '4': return "\u001b[31m"; // dark red
            case '5': return "\u001b[35m"; // dark purple
            case '6': return "\u001b[33m"; // gold
            case '7': return "\u001b[37m"; // gray
            case '8': return "\u001b[90m"; // dark gray
            case '9': return "\u001b[94m"; // blue
            case 'a': return "\u001b[92m"; // green
            case 'b': return "\u001b[96m"; // aqua
            case 'c': return "\u001b[91m"; // red
            case 'd': return "\u001b[95m"; // light purple
            case 'e': return "\u001b[93m"; // yellow
            case 'f': return "\u001b[97m"; // white
            case 'l': return "\u001b[1m";  // bold
            case 'o': return "\u001b[3m";  // italic
            case 'n': return "\u001b[4m";  // underline
            case 'm': return "\u001b[9m";  // strikethrough
            case 'k': return "";           // obfuscated — skip
            case 'r': return "\u001b[0m";  // reset
            default: return null;
        }
    }
}
