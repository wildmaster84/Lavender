package me.wildmaster84.lavender.plugin;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;

public class PluginClassLoader extends URLClassLoader {
    private final List<PluginClassLoader> dependencyLoaders;

    public PluginClassLoader(URL[] urls, ClassLoader parent, List<PluginClassLoader> dependencyLoaders) {
        super(urls, parent);
        this.dependencyLoaders = dependencyLoaders != null ? dependencyLoaders : List.of();
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        synchronized (getClassLoadingLock(name)) {
            Class<?> loadedClass = findLoadedClass(name);
            if (loadedClass != null) {
                if (resolve) resolveClass(loadedClass);
                return loadedClass;
            }
            try {
            	loadedClass = findClass(name);
                if (resolve) resolveClass(loadedClass);
                return loadedClass;
            } catch (ClassNotFoundException e) {}
            for (PluginClassLoader depLoader : dependencyLoaders) {
                try {
                    return depLoader.loadClass(name, resolve);
                } catch (ClassNotFoundException e) {}
            }
            return super.loadClass(name, resolve);
        }
    }
}
