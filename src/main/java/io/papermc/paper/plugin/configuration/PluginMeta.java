package io.papermc.paper.plugin.configuration;

import java.util.List;

public interface PluginMeta {
    String getName();
    String getVersion();
    String getMainClass();
    List<String> getPluginDependencies();
    List<String> getPluginSoftDependencies();
    List<String> getLoadBeforePlugins();
    String getProvidedPlugins();
    List<String> getAuthors();
    String getDescription();
    String getAPIVersion();
    String getWebsite();
    List<String> getPermissions();
}
