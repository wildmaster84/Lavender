package me.wildmaster84.lavender.plugin;

import net.minestom.server.MinecraftServer;
import me.wildmaster84.adapter.player.LavenderPlayer;
import me.wildmaster84.adapter.server.LavenderServer;
import me.wildmaster84.adapter.player.LavenderConsoleSender;
import org.bukkit.Server;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.EventExecutor;
import org.bukkit.command.PluginCommand;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LavenderPluginManager extends org.bukkit.plugin.SimplePluginManager {
    public org.bukkit.command.CommandMap commandMap = new org.bukkit.command.SimpleCommandMap();
    private final Server server;
    private final File pluginsDir;
    private final File librariesDir;
    private final Map<String, Plugin> plugins = new LinkedHashMap<>();
    private final List<EventListenerEntry> registeredListeners = new CopyOnWriteArrayList<>();
    private final List<String> failedPlugins = new ArrayList<>();
    private static final Logger logger = LoggerFactory.getLogger("Lavender-Plugins");

    private static final String[] BUNDLED_LIBRARIES = {
        "org.apache.commons:commons-lang3:3.14.0",
        "net.kyori:adventure-text-minimessage:5.2.0",
        "com.googlecode.json-simple:json-simple:1.1.1",
        "org.xerial:sqlite-jdbc:3.46.1.3",
        "org.apache.logging.log4j:log4j-api:2.24.3",
        "org.apache.logging.log4j:log4j-to-slf4j:2.24.3",
    };

    public LavenderPluginManager(Server server, File pluginsDir) {
        this.server = server;
        this.pluginsDir = pluginsDir;
        this.librariesDir = new File(pluginsDir.getParentFile(), "libraries");
        if (!librariesDir.exists()) {
            librariesDir.mkdirs();
        }
        ensureBundledLibraries();
        org.bukkit.event.HandlerList.setUnregisterCallback(listener -> {
            registeredListeners.removeIf(e -> e.listener == listener);
        });
        org.bukkit.event.HandlerList.setUnregisterPluginCallback(plugin -> {
            registeredListeners.removeIf(e -> e.plugin == plugin);
        });
        org.bukkit.command.PluginCommand.setRegistrationCallback(pc -> {
            net.minestom.server.command.CommandManager cm = MinecraftServer.getCommandManager();
            String name = pc.getName();
            if (cm.getCommand(name) != null) return;
            registerMinestomCommand(cm, name, pc);
        });
    }

    private void ensureBundledLibraries() {
        for (String coordinates : BUNDLED_LIBRARIES) {
            String[] parts = coordinates.split(":");
            String fileName = parts[1] + "-" + parts[2] + ".jar";
            File cached = new File(librariesDir, fileName);
            if (cached.exists()) continue;
            downloadLibrary(coordinates);
        }
    }

    private java.net.URL[] getLibraryUrls() {
        if (!librariesDir.exists()) return new java.net.URL[0];
        File[] jars = librariesDir.listFiles((dir, name) -> name.endsWith(".jar"));
        if (jars == null || jars.length == 0) return new java.net.URL[0];
        java.net.URL[] urls = new java.net.URL[jars.length];
        for (int i = 0; i < jars.length; i++) {
            try {
                urls[i] = jars[i].toURI().toURL();
            } catch (java.net.MalformedURLException e) {
                logger.warn("Could not load library: " + jars[i].getName());
            }
        }
        return urls;
    }

    private File downloadLibrary(String coordinates) {
        String[] parts = coordinates.split(":");
        if (parts.length < 3) return null;
        String group = parts[0].replace(".", "/");
        String artifact = parts[1];
        String version = parts[2];
        String fileName = artifact + "-" + version + ".jar";
        File cached = new File(librariesDir, fileName);
        if (cached.exists()) return cached;

        String url = String.format("https://repo.maven.apache.org/maven2/%s/%s/%s/%s",
            group, artifact, version, fileName);
        try (java.io.InputStream in = new java.net.URL(url).openStream()) {
            java.nio.file.Files.copy(in, cached.toPath());
            logger.info("Downloaded library: " + coordinates);
            return cached;
        } catch (Exception e) {
            logger.warn("Failed to download library " + coordinates + ": " + e.getMessage());
            return null;
        }
    }

    private java.net.URL[] resolvePluginLibraries(PluginDescriptionFile desc) {
        List<java.net.URL> urls = new ArrayList<>();
        Object libraries = desc.getRawValue("libraries");
        if (libraries instanceof List) {
            for (Object lib : (List<?>) libraries) {
                if (lib instanceof String) {
                    File downloaded = downloadLibrary((String) lib);
                    if (downloaded != null) {
                        try { urls.add(downloaded.toURI().toURL()); }
                        catch (java.net.MalformedURLException e) {
                            logger.warn("Could not load downloaded library: " + lib);
                        }
                    }
                }
            }
        }
        return urls.toArray(new java.net.URL[0]);
    }

    @Override
    public Plugin getPlugin(String name) {
        return plugins.get(name.toLowerCase());
    }

    @Override
    public Plugin[] getPlugins() {
        return plugins.values().toArray(new Plugin[0]);
    }

    @Override
    public boolean isPluginEnabled(String name) {
        Plugin p = getPlugin(name);
        return p != null && p.isEnabled();
    }

    @Override
    public boolean isPluginEnabled(Plugin plugin) {
        return plugin != null && plugin.isEnabled();
    }

    @Override
    public void loadPlugins() {
        if (!pluginsDir.exists()) {
            pluginsDir.mkdirs();
            logger.info("Created plugins directory: " + pluginsDir.getPath());
            return;
        }

        File[] jarFiles = pluginsDir.listFiles((dir, name) -> name.endsWith(".jar"));
        if (jarFiles == null || jarFiles.length == 0) {
            logger.info("No plugins found in " + pluginsDir.getPath());
            return;
        }

        Map<String, File> pluginJars = new LinkedHashMap<>();
        Map<String, PluginDescriptionFile> pluginDescs = new LinkedHashMap<>();
        for (File jar : jarFiles) {
            try (JarFile jf = new JarFile(jar)) {
                JarEntry entry = jf.getJarEntry("plugin.yml");
                if (entry == null) {
                    logger.warn("No plugin.yml found in " + jar.getName());
                    logger.warn("Bukkit/SpigotMC plugins only.");
                    continue;
                }
                try (InputStream in = jf.getInputStream(entry)) {
                    org.yaml.snakeyaml.Yaml yaml = new org.yaml.snakeyaml.Yaml();
                    Map<String, Object> map = yaml.load(in);
                    PluginDescriptionFile desc = new PluginDescriptionFile(map);
                    if (desc.getName() == null || desc.getMain() == null) {
                        logger.warn("Invalid plugin.yml in " + jar.getName() + ": missing name or main");
                        continue;
                    }
                    String name = desc.getName().toLowerCase();
                    pluginJars.put(name, jar);
                    pluginDescs.put(name, desc);
                }
            } catch (Throwable e) {
                logger.error("Failed to parse plugin.yml from " + jar.getName() + ": " + e.getMessage());
            }
        }

        List<String> loadOrder = topologicalSort(pluginDescs);

        for (String name : loadOrder) {
            PluginDescriptionFile desc = pluginDescs.get(name);
            File jar = pluginJars.get(name);
            try {
                Plugin plugin = loadPlugin(jar, desc);
                if (plugin != null) {
                    plugins.put(name, plugin);
                }
            } catch (Throwable e) {
                failedPlugins.add(desc.getName());
                logger.error("Failed to load plugin from " + jar.getName() + ": " + e.getMessage());
                e.printStackTrace();
            }
        }

        for (String name : loadOrder) {
            Plugin plugin = plugins.get(name);
            if (plugin == null) continue;
            try {
                enablePlugin(plugin);
            } catch (Throwable e) {
                failedPlugins.add(plugin.getName());
                logger.error("Failed to enable plugin " + plugin.getName() + ": " + e.getMessage());
                e.printStackTrace();
            }
        }

        printPluginSummary();
    }

    private void printPluginSummary() {
        String green = "\u001b[32m";
        String red = "\u001b[31m";
        String reset = "\u001b[0m";

        StringBuilder sb = new StringBuilder();
        sb.append("Plugins (").append(plugins.size()).append(" loaded");
        if (!failedPlugins.isEmpty()) {
            sb.append(", ").append(failedPlugins.size()).append(" failed");
        }
        sb.append("): ");

        boolean first = true;
        for (Plugin p : plugins.values()) {
            if (!first) sb.append(", ");
            first = false;
            sb.append(green).append(p.getName()).append(reset);
        }
        for (String name : failedPlugins) {
            if (!first) sb.append(", ");
            first = false;
            sb.append(red).append(name).append(reset);
        }

        logger.info(sb.toString());
    }

    public List<String> getFailedPlugins() {
        return failedPlugins;
    }

    private List<String> topologicalSort(Map<String, PluginDescriptionFile> descs) {
        List<String> result = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        Set<String> visiting = new HashSet<>();
        Set<String> skipped = new HashSet<>();

        for (String name : descs.keySet()) {
            sortByDeps(name, descs, visited, visiting, skipped, result);
        }

        for (String name : skipped) {
            PluginDescriptionFile desc = descs.get(name);
            List<String> hardDeps = desc.getDepend();
            if (hardDeps != null) {
                logger.warn("Skipping plugin " + desc.getName() + ": missing dependency(s) " +
                    hardDeps.stream().filter(d -> !plugins.containsKey(d.toLowerCase()) && !descs.containsKey(d.toLowerCase()))
                    .collect(java.util.stream.Collectors.joining(", ")));
            }
        }

        return result;
    }

    private void sortByDeps(String name, Map<String, PluginDescriptionFile> descs,
                           Set<String> visited, Set<String> visiting, Set<String> skipped,
                           List<String> result) {
        if (visited.contains(name) || skipped.contains(name)) return;
        if (visiting.contains(name)) {
            logger.warn("Circular dependency detected involving " + name + ", loading anyway");
            return;
        }

        PluginDescriptionFile desc = descs.get(name);
        if (desc == null) return;

        visiting.add(name);

        List<String> hardDeps = desc.getDepend();
        if (hardDeps != null) {
            for (String dep : hardDeps) {
                String depLower = dep.toLowerCase();
                if (!descs.containsKey(depLower)) {
                    skipped.add(name);
                    visiting.remove(name);
                    return;
                }
                sortByDeps(depLower, descs, visited, visiting, skipped, result);
                if (skipped.contains(depLower)) {
                    skipped.add(name);
                    visiting.remove(name);
                    return;
                }
            }
        }

        List<String> softDeps = desc.getSoftDepend();
        if (softDeps != null) {
            for (String dep : softDeps) {
                String depLower = dep.toLowerCase();
                if (descs.containsKey(depLower) && !visited.contains(depLower) && !skipped.contains(depLower)) {
                    sortByDeps(depLower, descs, visited, visiting, skipped, result);
                }
            }
        }

        visiting.remove(name);
        visited.add(name);
        result.add(name);
    }

    private Plugin loadPlugin(File jarFile, PluginDescriptionFile desc) throws Exception {
        if (desc.getName() == null || desc.getMain() == null) {
            logger.warn("Invalid plugin.yml in " + jarFile.getName() + ": missing name or main");
            return null;
        }

        List<java.net.URL> classloaderUrls = new ArrayList<>();
        classloaderUrls.add(jarFile.toURI().toURL());
        Collections.addAll(classloaderUrls, getLibraryUrls());
        Collections.addAll(classloaderUrls, resolvePluginLibraries(desc));

        java.net.URLClassLoader classLoader = new java.net.URLClassLoader(
            classloaderUrls.toArray(new java.net.URL[0]),
            getClass().getClassLoader()
        );

        Class<?> mainClass = classLoader.loadClass(desc.getMain());
        if (!JavaPlugin.class.isAssignableFrom(mainClass)) {
            logger.warn("Main class " + desc.getMain() + " does not extend JavaPlugin");
            return null;
        }

        JavaPlugin plugin = (JavaPlugin) mainClass.getDeclaredConstructor().newInstance();
        File dataFolder = new File(pluginsDir, desc.getName());
        plugin.init(server, desc, dataFolder, classLoader, jarFile);

        try {
            plugin.onLoad();
        } catch (Throwable t) {
            logger.error("Error in onLoad() for plugin " + desc.getName() + ": " + t.getMessage(), t);
        }

        if (desc.getCommands() != null) {
            for (String cmdName : desc.getCommands().keySet()) {
                PluginCommand pc = plugin.getCommand(cmdName);
                if (pc != null) {
                    pc.setExecutor(plugin);
                    if (this.commandMap != null) {
                        this.commandMap.register(desc.getName().toLowerCase(), pc);
                    }
                }
            }
        }

        return plugin;
    }

    @Override
    public void enablePlugin(Plugin plugin) {
        if (plugin.isEnabled()) return;
        ((JavaPlugin) plugin).setEnabled(true);
        plugin.onEnable();
        registerPluginCommands();
    }

    private void registerPluginCommands() {
        net.minestom.server.command.CommandManager cm = MinecraftServer.getCommandManager();
        org.bukkit.command.SimpleCommandMap simpleCommandMap = (org.bukkit.command.SimpleCommandMap) this.commandMap;
        Set<String> seen = new HashSet<>();
        for (Map.Entry<String, org.bukkit.command.Command> entry : simpleCommandMap.knownCommands.entrySet()) {
            if (entry.getKey().contains(":")) continue;
            org.bukkit.command.Command cmd = entry.getValue();
            String name = cmd.getName();
            if (seen.contains(name)) continue;
            seen.add(name);
            if (cm.getCommand(name) != null) continue;
            registerMinestomCommand(cm, name, cmd);
        }
    }

    private void registerMinestomCommand(net.minestom.server.command.CommandManager cm, String name, org.bukkit.command.Command cmd) {
        net.minestom.server.command.builder.Command msCmd = new net.minestom.server.command.builder.Command(name);

        java.util.function.BiConsumer<net.minestom.server.command.CommandSender, String[]> executor = (sender, args) -> {
            org.bukkit.command.CommandSender bukkitSender = wrapSender(sender);
            try {
                cmd.execute(bukkitSender, name, args != null ? args : new String[0]);
            } catch (Throwable e) {
                logger.error("Error executing command '" + name + "': " + e.getMessage(), e);
            }
        };

        msCmd.setDefaultExecutor((sender, context) -> {
            String input = context.getInput();
            if (input.trim().length() > name.length()) return;
            executor.accept(sender, new String[0]);
        });

        net.minestom.server.command.builder.arguments.ArgumentStringArray argsArg = new net.minestom.server.command.builder.arguments.ArgumentStringArray("args");
        argsArg.setSuggestionCallback((sender, context, suggestion) -> {
            org.bukkit.command.CommandSender bukkitSender = wrapSender(sender);
            String input = suggestion.getInput();
            String[] parts = input.split(" ", -1);
            String[] cmdArgs = parts.length > 1 ? java.util.Arrays.copyOfRange(parts, 1, parts.length) : new String[0];
            if (cmdArgs.length > 0 && cmdArgs[cmdArgs.length - 1].isEmpty()) {
                cmdArgs = java.util.Arrays.copyOf(cmdArgs, cmdArgs.length - 1);
            }
            try {
                java.util.List<String> completions = cmd.tabComplete(bukkitSender, name, cmdArgs);
                if (completions != null) {
                    for (String c : completions) {
                        suggestion.addEntry(new net.minestom.server.command.builder.suggestion.SuggestionEntry(c));
                    }
                }
            } catch (Throwable e) {
                logger.warn("Tab completion error for command '" + name + "': " + e.getMessage());
            }
        });
        msCmd.addSyntax((sender, context) -> {
            String[] args = context.get(argsArg);
            executor.accept(sender, args);
        }, argsArg);

        cm.register(msCmd);
    }

    private org.bukkit.command.CommandSender wrapSender(Object sender) {
        if (sender instanceof net.minestom.server.command.CommandSender cs) {
            if (cs instanceof net.minestom.server.entity.Player mp) {
                return LavenderPlayer.wrap(mp, (LavenderServer) server);
            }
        }
        return new LavenderConsoleSender((org.bukkit.Server) server);
    }

    @Override
    public void disablePlugin(Plugin plugin) {
        if (!plugin.isEnabled()) return;
        ((JavaPlugin) plugin).setEnabled(false);
        plugin.onDisable();
    }

    @Override
    public void registerEvents(Listener listener, Plugin plugin) {
        for (Method method : listener.getClass().getMethods()) {
            if (method.isAnnotationPresent(EventHandler.class)) {
                EventHandler annotation = method.getAnnotation(EventHandler.class);
                Class<?>[] params = method.getParameterTypes();
                if (params.length == 1 && Event.class.isAssignableFrom(params[0])) {
                    @SuppressWarnings("unchecked")
                    Class<? extends Event> eventType = (Class<? extends Event>) params[0];
                    registeredListeners.add(new EventListenerEntry(
                        listener, method, eventType, annotation.priority(), plugin
                    ));
                }
            }
        }
        registeredListeners.sort(Comparator.comparingInt(e -> e.priority.getSlot()));
    }

    @Override
    public void registerEvent(Class<? extends Event> event, Listener listener, EventPriority priority, EventExecutor executor, Plugin plugin) {
        registeredListeners.add(new EventListenerEntry(listener, executor, event, priority, plugin));
        registeredListeners.sort(Comparator.comparingInt(e -> e.priority.getSlot()));
    }

    @Override
    public void registerEvent(Class<? extends Event> event, Listener listener, EventPriority priority, EventExecutor executor, Plugin plugin, boolean ignoreCancelled) {
        registeredListeners.add(new EventListenerEntry(listener, executor, event, priority, plugin));
        registeredListeners.sort(Comparator.comparingInt(e -> e.priority.getSlot()));
    }

    @Override
    public void callEvent(Event event) {
        for (EventListenerEntry entry : registeredListeners) {
            if (entry.eventType.isInstance(event)) {
                try {
                    if (entry.executor != null) {
                        entry.executor.execute(entry.listener, event);
                    } else if (entry.method != null) {
                        entry.method.invoke(entry.listener, event);
                    }
                } catch (Throwable e) {
                    logger.warn("Could not pass event " + event.getEventName() +
                        " to plugin " + entry.plugin.getName() + ": " + e.getMessage(), e);
                }
            }
        }
    }

    @Override
    public List<Listener> getRegisteredListeners() {
        List<Listener> result = new ArrayList<>();
        for (EventListenerEntry entry : registeredListeners) {
            result.add(entry.listener);
        }
        return result;
    }

    public void disableAll() {
        for (Plugin plugin : new ArrayList<>(plugins.values())) {
            try {
                disablePlugin(plugin);
            } catch (Exception e) {
                logger.error("Error disabling plugin " + plugin.getName() + ": " + e.getMessage());
            }
        }
    }

    private static class EventListenerEntry {
        final Listener listener;
        final Method method;
        final EventExecutor executor;
        final Class<? extends Event> eventType;
        final EventPriority priority;
        final Plugin plugin;

        EventListenerEntry(Listener listener, Method method, Class<? extends Event> eventType,
                          EventPriority priority, Plugin plugin) {
            this.listener = listener;
            this.method = method;
            this.executor = null;
            this.eventType = eventType;
            this.priority = priority;
            this.plugin = plugin;
        }

        EventListenerEntry(Listener listener, EventExecutor executor, Class<? extends Event> eventType,
                          EventPriority priority, Plugin plugin) {
            this.listener = listener;
            this.method = null;
            this.executor = executor;
            this.eventType = eventType;
            this.priority = priority;
            this.plugin = plugin;
        }
    }
}
