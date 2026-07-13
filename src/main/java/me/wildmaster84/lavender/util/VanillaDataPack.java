package me.wildmaster84.lavender.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.minestom.server.MinecraftServer;

import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.*;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Stream;

public class VanillaDataPack {

    private static final Logger log = LoggerFactory.getLogger("Lavender");

    private static final String MC_VERSION = getMinecraftVersion();
    private static final String VERSION_MANIFEST_URL = "https://launchermeta.mojang.com/mc/game/version_manifest.json";

    private static String getMinecraftVersion() {
        return MinecraftServer.VERSION_NAME;
    }

    private final Path runtimeDir;

    public VanillaDataPack(Path runtimeDir) {
        this.runtimeDir = runtimeDir;
    }

    public void ensureExtracted() {
        Path dataDir = runtimeDir.resolve("data");
        if (Files.isDirectory(dataDir) && containsFiles(dataDir)) {
            log.info("Vanilla data pack already present");
            return;
        }

        log.info("Extracting vanilla data pack (MC " + MC_VERSION + ")...");
        Path serverJar = null;
        try {
            serverJar = downloadVanillaServerJar();
            extractDataPack(serverJar, dataDir);
            log.info("Vanilla data pack extracted to {}", runtimeDir.relativize(dataDir));
        } catch (Exception e) {
            log.error("Failed to extract vanilla data pack: " + e.getMessage(), e);
        } finally {
            if (serverJar != null) {
                try { Files.deleteIfExists(serverJar); } catch (IOException ignored) {}
            }
        }
    }

    private Path downloadVanillaServerJar() throws Exception {
        HttpClient client = HttpClient.newHttpClient();

        HttpResponse<String> manifestResp = client.send(
            HttpRequest.newBuilder(URI.create(VERSION_MANIFEST_URL)).build(),
            HttpResponse.BodyHandlers.ofString());

        if (manifestResp.statusCode() != 200)
            throw new IOException("Failed to fetch version manifest: HTTP " + manifestResp.statusCode());

        String manifestJson = manifestResp.body();
        String versionUrl = extractVersionUrl(manifestJson, MC_VERSION);
        if (versionUrl == null)
            throw new IOException("MC version " + MC_VERSION + " not found in manifest");

        HttpResponse<String> versionResp = client.send(
            HttpRequest.newBuilder(URI.create(versionUrl)).build(),
            HttpResponse.BodyHandlers.ofString());

        if (versionResp.statusCode() != 200)
            throw new IOException("Failed to fetch version detail: HTTP " + versionResp.statusCode());

        String versionJson = versionResp.body();
        String serverUrl = extractServerUrl(versionJson);
        if (serverUrl == null)
            throw new IOException("Server download URL not found in version detail");

        Path jarPath = runtimeDir.resolve(".vanilla-server-" + MC_VERSION + ".jar");
        log.info("Downloading vanilla server jar from Mojang...");
        HttpResponse<Path> downloadResp = client.send(
            HttpRequest.newBuilder(URI.create(serverUrl)).build(),
            HttpResponse.BodyHandlers.ofFile(jarPath));

        if (downloadResp.statusCode() != 200)
            throw new IOException("Failed to download server jar: HTTP " + downloadResp.statusCode());

        return jarPath;
    }

    private void extractDataPack(Path serverJarPath, Path dataDir) throws Exception {
        try (JarFile bundlerJar = new JarFile(serverJarPath.toFile())) {
            String innerEntry = "META-INF/versions/" + MC_VERSION + "/server-" + MC_VERSION + ".jar";

            JarEntry innerJarEntry = bundlerJar.getJarEntry(innerEntry);
            if (innerJarEntry == null) {
                Enumeration<JarEntry> entries = bundlerJar.entries();
                while (entries.hasMoreElements()) {
                    JarEntry e = entries.nextElement();
                    if (e.getName().startsWith("META-INF/versions/") && e.getName().endsWith(".jar")) {
                        innerJarEntry = e;
                        break;
                    }
                }
            }

            if (innerJarEntry == null)
                throw new IOException("Inner server jar not found in bundler");

            Path innerJarPath = runtimeDir.resolve(".vanilla-inner-" + MC_VERSION + ".jar");
            try (InputStream is = bundlerJar.getInputStream(innerJarEntry)) {
                Files.copy(is, innerJarPath, StandardCopyOption.REPLACE_EXISTING);
            }

            try (JarFile innerJar = new JarFile(innerJarPath.toFile())) {
                Enumeration<JarEntry> entries = innerJar.entries();
                int count = 0;
                while (entries.hasMoreElements()) {
                    JarEntry entry = entries.nextElement();
                    if (!entry.getName().startsWith("data/")) continue;
                    if (entry.isDirectory()) {
                        Files.createDirectories(dataDir.resolve(entry.getName().substring(5)));
                        continue;
                    }
                    Path dest = dataDir.resolve(entry.getName().substring(5));
                    Files.createDirectories(dest.getParent());
                    try (InputStream is = innerJar.getInputStream(entry)) {
                        Files.copy(is, dest, StandardCopyOption.REPLACE_EXISTING);
                    }
                    count++;
                }
                log.info("Extracted {} data files", count);
            } finally {
                try { Files.deleteIfExists(innerJarPath); } catch (IOException ignored) {}
            }
        }
    }

    private static boolean containsFiles(Path dir) {
        try (Stream<Path> stream = Files.walk(dir)) {
            return stream.anyMatch(Files::isRegularFile);
        } catch (IOException e) {
            return false;
        }
    }

    private static String extractVersionUrl(String json, String version) {
        String needle = "\"id\"";
        int searchFrom = 0;
        while (true) {
            int idx = json.indexOf(needle, searchFrom);
            if (idx < 0) return null;
            int colonIdx = json.indexOf(":", idx);
            if (colonIdx < 0) return null;
            int valStart = json.indexOf("\"", colonIdx);
            if (valStart < 0) return null;
            int valEnd = json.indexOf("\"", valStart + 1);
            if (valEnd < 0) return null;
            String idValue = json.substring(valStart + 1, valEnd);
            if (idValue.equals(version)) {
                int urlStart = json.indexOf("\"url\"", valEnd);
                if (urlStart < 0) return null;
                int urlColon = json.indexOf(":", urlStart);
                if (urlColon < 0) return null;
                int urlValStart = json.indexOf("\"", urlColon);
                if (urlValStart < 0) return null;
                int urlValEnd = json.indexOf("\"", urlValStart + 1);
                if (urlValEnd < 0) return null;
                return json.substring(urlValStart + 1, urlValEnd);
            }
            searchFrom = valEnd + 1;
        }
    }

    private static String extractServerUrl(String json) {
        String needle = "\"server\"";
        int idx = json.indexOf(needle);
        if (idx < 0) return null;
        int urlStart = json.indexOf("\"url\"", idx);
        if (urlStart < 0) return null;
        int urlColon = json.indexOf(":", urlStart);
        if (urlColon < 0) return null;
        int urlValStart = json.indexOf("\"", urlColon);
        if (urlValStart < 0) return null;
        int urlValEnd = json.indexOf("\"", urlValStart + 1);
        if (urlValEnd < 0) return null;
        return json.substring(urlValStart + 1, urlValEnd);
    }
}
