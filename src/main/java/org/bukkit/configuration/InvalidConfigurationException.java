package org.bukkit.configuration;

public class InvalidConfigurationException extends Exception {
    public InvalidConfigurationException() { super(); }
    public InvalidConfigurationException(String msg) { super(msg); }
    public InvalidConfigurationException(String msg, Throwable cause) { super(msg, cause); }
    public InvalidConfigurationException(Throwable cause) { super(cause); }
}
