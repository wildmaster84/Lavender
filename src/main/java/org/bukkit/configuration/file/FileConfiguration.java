package org.bukkit.configuration.file;

import org.bukkit.configuration.Configuration;
import java.io.File;

public abstract class FileConfiguration extends org.bukkit.configuration.MemoryConfiguration implements Configuration {

    public abstract String saveToString();
    public abstract void loadFromString(String contents);

    public void save(File file) throws java.io.IOException {
        String data = saveToString();
        java.nio.file.Files.writeString(file.toPath(), data);
    }

    public void load(File file) throws java.io.IOException, org.bukkit.configuration.InvalidConfigurationException {
        String data = java.nio.file.Files.readString(file.toPath());
        loadFromString(data);
    }

    public void load(java.io.Reader reader) throws java.io.IOException, org.bukkit.configuration.InvalidConfigurationException {
        StringBuilder builder = new StringBuilder();
        int ch;
        while ((ch = reader.read()) != -1) {
            builder.append((char) ch);
        }
        loadFromString(builder.toString());
    }

    @Override
    public FileConfigurationOptions options() { return new FileConfigurationOptions(this); }
}
