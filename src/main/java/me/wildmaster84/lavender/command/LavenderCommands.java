package me.wildmaster84.lavender.command;

import me.wildmaster84.adapter.Lavender;
import me.wildmaster84.lavender.plugin.LavenderPluginManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minestom.server.MinecraftServer;
import net.minestom.server.command.CommandManager;
import net.minestom.server.command.builder.Command;
import net.minestom.server.entity.Player;
import org.bukkit.Server;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.List;

public class LavenderCommands {

    private final Server server;
    private final Lavender lavender;

    public LavenderCommands(Server server, Lavender lavender) {
        this.server = server;
        this.lavender = lavender;
    }

    public void registerAll() {
        CommandManager cm = MinecraftServer.getCommandManager();
        cm.register(playersCommand());
        cm.register(resourcesCommand());
        cm.register(pluginsCommand());
        cm.register(stopCommand());
    }

    private Command playersCommand() {
        Command cmd = new Command("players");
        cmd.setDefaultExecutor((sender, context) -> {
            Collection<Player> players = MinecraftServer.getConnectionManager().getOnlinePlayers();
            if (players.isEmpty()) {
                sender.sendMessage("No players online.");
            } else {
                StringBuilder sb = new StringBuilder("Online players (" + players.size() + "):");
                for (Player p : players) {
                    sb.append("\n - ").append(p.getUsername());
                }
                sender.sendMessage(sb.toString());
            }
        });
        return cmd;
    }

    private Command resourcesCommand() {
        Command cmd = new Command("resources");
        cmd.setDefaultExecutor((sender, context) -> {
            Runtime rt = Runtime.getRuntime();
            long used = (rt.totalMemory() - rt.freeMemory()) / (1024 * 1024);
            long max = rt.maxMemory() / (1024 * 1024);
            int cores = rt.availableProcessors();
            sender.sendMessage(String.format("Memory: %dMB / %dMB | CPU cores: %d", used, max, cores));
        });
        return cmd;
    }

    private Command pluginsCommand() {
        Command cmd = new Command("plugins");
        cmd.setDefaultExecutor((sender, context) -> {
            PluginManager pm = server.getPluginManager();
            Plugin[] loadedPlugins = pm.getPlugins();
            List<String> failed = (pm instanceof LavenderPluginManager)
                    ? ((LavenderPluginManager) pm).getFailedPlugins()
                    : List.of();

            if (loadedPlugins.length == 0 && failed.isEmpty()) {
                sender.sendMessage("No plugins loaded.");
                return;
            }

            int total = loadedPlugins.length + failed.size();
            Component header = Component.text("Plugins (" + total + "): ", NamedTextColor.WHITE);

            Component separator = Component.text(", ", NamedTextColor.GRAY);
            Component body = Component.empty();
            boolean first = true;

            for (Plugin p : loadedPlugins) {
                if (!first) body = body.append(separator);
                first = false;

                Component hover = Component.text()
                        .append(Component.text(p.getName(), NamedTextColor.GREEN))
                        .append(Component.text("\nVersion: ", NamedTextColor.GRAY))
                        .append(Component.text(p.getDescription().getVersion(), NamedTextColor.WHITE))
                        .append(Component.text("\nAuthors: ", NamedTextColor.GRAY))
                        .append(Component.text(formatAuthors(p), NamedTextColor.WHITE))
                        .append(Component.text("\nStatus: ", NamedTextColor.GRAY))
                        .append(p.isEnabled()
                                ? Component.text("Enabled", NamedTextColor.GREEN)
                                : Component.text("Disabled", NamedTextColor.RED))
                        .build();

                body = body.append(Component.text(p.getName(), NamedTextColor.GREEN)
                        .hoverEvent(HoverEvent.hoverEvent(HoverEvent.Action.SHOW_TEXT, hover)));
            }

            for (String name : failed) {
                if (!first) body = body.append(separator);
                first = false;

                Component hover = Component.text()
                        .append(Component.text(name, NamedTextColor.RED))
                        .append(Component.text("\nStatus: ", NamedTextColor.GRAY))
                        .append(Component.text("Failed to load", NamedTextColor.RED))
                        .build();

                body = body.append(Component.text(name, NamedTextColor.RED)
                        .hoverEvent(HoverEvent.hoverEvent(HoverEvent.Action.SHOW_TEXT, hover)));
            }

            sender.sendMessage(header.append(body));
        });
        return cmd;
    }

    private String formatAuthors(Plugin p) {
        List<String> authors = p.getDescription().getAuthors();
        if (authors == null || authors.isEmpty()) return "Unknown";
        return String.join(", ", authors);
    }

    private Command stopCommand() {
        Command cmd = new Command("stop");
        cmd.setDefaultExecutor((sender, context) -> {
            sender.sendMessage("Stopping server...");
            lavender.stop();
        });
        return cmd;
    }
}
