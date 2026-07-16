package org.bukkit.plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.bukkit.permissions.Permission;

public class PluginDescriptionFile {
    private String name;
    private String main;
    private String version;
    private String apiVersion;
    private List<String> authors;
    private String description;
    private List<String> depend;
    private List<String> softDepend;
    private List<String> loadBefore;
    private String website;
    private Map<String, Object> commands;
    private Map<String, Object> rawMap;
    private List<Permission> permissions;

    public PluginDescriptionFile() {}

    @SuppressWarnings("unchecked")
    public PluginDescriptionFile(Map<String, Object> map) {
        this.rawMap = map;
        this.name = getAsString(map.get("name"));
        this.main = getAsString(map.get("main"));
        this.version = getAsString(map.get("version"));
        this.apiVersion = getAsString(map.get("api-version"));
        this.description = getAsString(map.get("description"));

        Object authorsObj = map.get("authors");
        if (authorsObj instanceof List) {
            this.authors = (List<String>) authorsObj;
        } else if (map.get("author") != null) {
            this.authors = List.of(getAsString(map.get("author")));
        }

        this.depend = (List<String>) map.get("depend");
        this.softDepend = (List<String>) map.get("softdepend");
        this.loadBefore = (List<String>) map.get("loadbefore");
        this.website = getAsString(map.get("website"));

        Object commandsObj = map.get("commands");
        if (commandsObj instanceof Map) {
            this.commands = (Map<String, Object>) commandsObj;
        }

        this.permissions = parsePermissions(map.get("permissions"));
    }

    @SuppressWarnings("unchecked")
    private List<Permission> parsePermissions(Object permsObj) {
        List<Permission> result = new ArrayList<>();
        if (permsObj instanceof List) {
            for (Object perm : (List<Object>) permsObj) {
                if (perm instanceof Map) {
                    Map<String, Object> permMap = (Map<String, Object>) perm;
                    String permName = getAsString(permMap.get("name"));
                    String permDesc = getAsString(permMap.get("description"));
                    if (permName == null) continue;
                    Permission p = new Permission(permName, permDesc != null ? permDesc : "");
                    result.add(p);
                } else if (perm instanceof String) {
                    result.add(new Permission((String) perm, ""));
                }
            }
        } else if (permsObj instanceof Map) {
            for (Map.Entry<String, Object> entry : ((Map<String, Object>) permsObj).entrySet()) {
                String permName = entry.getKey();
                String permDesc = "";
                if (entry.getValue() instanceof Map) {
                    permDesc = getAsString(((Map<String, Object>) entry.getValue()).get("description"));
                    if (permDesc == null) permDesc = "";
                }
                result.add(new Permission(permName, permDesc));
            }
        }
        return result;
    }

    public Object getRawValue(String key) {
        return rawMap != null ? rawMap.get(key) : null;
    }

    private static String getAsString(Object obj) {
        if (obj == null) return null;
        return obj.toString();
    }

    public String getName() { return name; }
    public String getMain() { return main; }
    public String getVersion() { return version; }
    public String getFullName() { return name + " v" + version; }
    public String getAPIVersion() { return apiVersion; }
    public List<String> getAuthors() { return authors; }
    public String getDescription() { return description; }
    public List<String> getDepend() { return depend; }
    public List<String> getSoftDepend() { return softDepend; }
    public List<String> getLoadBefore() { return loadBefore; }
    public String getWebsite() { return website; }
    public Map<String, Object> getCommands() { return commands; }
    public List<Permission> getPermissions() { return permissions != null ? permissions : new ArrayList<>(); }
}
