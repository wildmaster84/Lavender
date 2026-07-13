package org.bukkit.plugin.java;

import io.papermc.paper.plugin.configuration.PluginMeta;
import org.bukkit.plugin.PluginDescriptionFile;
import java.util.List;

public class PluginMetaImpl implements PluginMeta {
    private final PluginDescriptionFile desc;

    public PluginMetaImpl(PluginDescriptionFile desc) { this.desc = desc; }

    @Override public String getName() { return desc.getName(); }
    @Override public String getVersion() { return desc.getVersion(); }
    @Override public String getMainClass() { return desc.getMain(); }
    @Override public List<String> getPluginDependencies() { return safeList(desc.getDepend()); }
    @Override public List<String> getPluginSoftDependencies() { return safeList(desc.getSoftDepend()); }
    @Override public List<String> getLoadBeforePlugins() { return safeList(desc.getLoadBefore()); }
    @Override public String getProvidedPlugins() { return ""; }
    @Override public List<String> getAuthors() { return safeList(desc.getAuthors()); }
    @Override public String getDescription() { return desc.getDescription() != null ? desc.getDescription() : ""; }
    @Override public String getAPIVersion() { return desc.getAPIVersion() != null ? desc.getAPIVersion() : ""; }
    @Override public String getWebsite() { return desc.getWebsite() != null ? desc.getWebsite() : ""; }
    @Override public List<String> getPermissions() { return java.util.Collections.emptyList(); }

    private static List<String> safeList(List<String> list) {
        return list != null ? list : java.util.Collections.emptyList();
    }
}
