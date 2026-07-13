package me.wildmaster84.lavender.plugin;

import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.slf4j.Logger;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class SparkCompat {

    public static void install(Plugin plugin, Logger logger) {
        if (!plugin.getName().equalsIgnoreCase("spark")) return;

        try {
            ClassLoader pluginCL = plugin.getClass().getClassLoader();
            installLoggerFilter(pluginCL, logger);
            installThreadBeanPatch(plugin, pluginCL, logger);
        } catch (Exception e) {
            logger.warn("Could not install Spark compat patches: " + e.getMessage(), e);
        }
    }

    private static void installLoggerFilter(ClassLoader pluginCL, Logger logger) throws Exception {
        Class<?> sparkStaticLoggerClass = Class.forName("me.lucko.spark.common.util.log.SparkStaticLogger", true, pluginCL);
        Field loggerField = sparkStaticLoggerClass.getDeclaredField("logger");
        loggerField.setAccessible(true);
        Object originalLogger = loggerField.get(null);
        Class<?> loggerInterface = Class.forName("me.lucko.spark.common.util.log.Logger", true, pluginCL);

        Object filteringLogger = Proxy.newProxyInstance(
            pluginCL,
            new Class[]{loggerInterface},
            (proxy, method, args) -> {
                if (method.getName().equals("log") && method.getParameterCount() == 3) {
                    String msg = (String) args[1];
                    Throwable t = (Throwable) args[2];
                    if (t instanceof NullPointerException
                            && msg != null && msg.contains("spark-java-sampler")) {
                        return null;
                    }
                }
                return method.invoke(originalLogger, args);
            }
        );
        loggerField.set(null, filteringLogger);
        logger.info("Installed NPE filter on Spark's logger");
    }

    private static void installThreadBeanPatch(Plugin plugin, ClassLoader pluginCL, Logger logger) throws Exception {
        Class<?> javaSamplerClass = Class.forName("me.lucko.spark.common.sampler.java.JavaSampler", true, pluginCL);
        Field threadBeanField = javaSamplerClass.getDeclaredField("threadBean");
        threadBeanField.setAccessible(true);

        BukkitScheduler scheduler = org.bukkit.Bukkit.getServer().getScheduler();
        scheduler.runTaskTimer(plugin, () -> {
            try {
                // BukkitSparkPlugin.platform → SparkPlatform.samplerContainer → activeSampler
                Field platformField = plugin.getClass().getDeclaredField("platform");
                platformField.setAccessible(true);
                Object platform = platformField.get(plugin);
                if (platform == null) return;

                Field containerField = platform.getClass().getDeclaredField("samplerContainer");
                containerField.setAccessible(true);
                Object container = containerField.get(platform);
                if (container == null) return;

                Field refField = container.getClass().getDeclaredField("activeSampler");
                refField.setAccessible(true);
                @SuppressWarnings("unchecked")
                AtomicReference<?> ref = (AtomicReference<?>) refField.get(container);
                Object sampler = ref.get();
                if (sampler == null) return;

                if (javaSamplerClass.isInstance(sampler)) {
                    ThreadMXBean currentBean = (ThreadMXBean) threadBeanField.get(sampler);
                    if (currentBean.getClass().getName().contains("$Proxy")) return;

                    ThreadMXBean realBean = ManagementFactory.getThreadMXBean();
                    ThreadMXBean filteredBean = (ThreadMXBean) Proxy.newProxyInstance(
                        ThreadMXBean.class.getClassLoader(),
                        new Class[]{ThreadMXBean.class},
                        (proxy, method, mArgs) -> {
                            if (method.getReturnType() == ThreadInfo[].class) {
                                ThreadInfo[] result = (ThreadInfo[]) method.invoke(realBean, mArgs);
                                if (result == null) return null;
                                ArrayList<ThreadInfo> filtered = new ArrayList<>();
                                for (ThreadInfo ti : result) {
                                    if (ti != null) filtered.add(ti);
                                }
                                return filtered.toArray(new ThreadInfo[0]);
                            }
                            return method.invoke(realBean, mArgs);
                        }
                    );
                    threadBeanField.set(sampler, filteredBean);
                    logger.info("Patched JavaSampler ThreadMXBean to filter null ThreadInfo entries");
                }
            } catch (Exception e) {}
        }, 20L, 20L);
    }
}
