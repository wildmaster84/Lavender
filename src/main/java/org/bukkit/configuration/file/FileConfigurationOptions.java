package org.bukkit.configuration.file;

import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationOptions;

public class FileConfigurationOptions extends ConfigurationOptions {
    private boolean headerLoaded = true;
    private String header = null;

    protected FileConfigurationOptions(FileConfiguration configuration) {
        super(configuration);
    }

    @Override
    public FileConfiguration configuration() { return (FileConfiguration) super.configuration(); }

    public String header() { return header; }
    public FileConfigurationOptions header(String value) { this.header = value; return this; }
    public boolean copyHeader() { return headerLoaded; }
    public FileConfigurationOptions copyHeader(boolean value) { this.headerLoaded = value; return this; }

    @Override
    public FileConfigurationOptions pathSeparator(char value) { super.pathSeparator(value); return this; }
    @Override
    public FileConfigurationOptions copyDefaults(boolean value) { super.copyDefaults(value); return this; }
}
