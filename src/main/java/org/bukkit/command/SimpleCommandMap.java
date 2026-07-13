package org.bukkit.command;

import java.util.*;

public class SimpleCommandMap implements CommandMap {
    public final Map<String, Command> knownCommands = new HashMap<>();

    @Override
    public boolean register(String fallbackPrefix, Command command) {
        String name = command.getName();
        knownCommands.put(name, command);
        if (fallbackPrefix != null && !fallbackPrefix.isEmpty()) {
            knownCommands.put(fallbackPrefix + ":" + name, command);
        }
        if (command.getAliases() != null) {
            for (String alias : command.getAliases()) {
                knownCommands.put(alias, command);
                if (fallbackPrefix != null && !fallbackPrefix.isEmpty()) {
                    knownCommands.put(fallbackPrefix + ":" + alias, command);
                }
            }
        }
        return true;
    }

    @Override
    public boolean dispatch(CommandSender sender, String cmdLine) {
        String[] parts = cmdLine.split(" ", 2);
        String name = parts[0];
        String[] args = parts.length > 1 ? parts[1].split(" ") : new String[0];
        Command cmd = knownCommands.get(name);
        if (cmd != null) {
            return cmd.execute(sender, name, args);
        }
        return false;
    }

    @Override
    public void clearCommands() {
        knownCommands.clear();
    }

    @Override
    public Command getCommand(String name) {
        return knownCommands.get(name);
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String cmdLine) {
        return Collections.emptyList();
    }
}
