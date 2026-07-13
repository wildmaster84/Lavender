package org.bukkit.command;

import org.bukkit.plugin.Plugin;

public class PluginCommand extends Command {

    private final Plugin owningPlugin;
    private CommandExecutor executor;
    private TabCompleter completer;

    public PluginCommand(String name, Plugin owner) {
        super(name);
        this.owningPlugin = owner;
    }

    public void setExecutor(CommandExecutor executor) {
        this.executor = executor;
    }

    public CommandExecutor getExecutor() {
        return executor;
    }

    public void setTabCompleter(TabCompleter completer) {
        this.completer = completer;
    }

    public TabCompleter getTabCompleter() {
        return completer;
    }

    public Plugin getPlugin() {
        return owningPlugin;
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (executor != null) {
            return executor.onCommand(sender, this, commandLabel, args);
        }
        return false;
    }

    @Override
    public java.util.List<String> tabComplete(CommandSender sender, String alias, String[] args) {
        if (completer != null) {
            return completer.onTabComplete(sender, this, alias, args);
        }
        return super.tabComplete(sender, alias, args);
    }
}
