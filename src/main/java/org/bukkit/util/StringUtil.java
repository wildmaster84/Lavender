package org.bukkit.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StringUtil {

    public static boolean startsWithIgnoreCase(String string, String prefix) {
        if (string == null || prefix == null) return false;
        return string.toLowerCase().startsWith(prefix.toLowerCase());
    }

    public static List<String> partialCopyOf(String token, Collection<String> originals) {
        return partialCopyOf(token, originals, 100);
    }

    public static List<String> partialCopyOf(String token, Collection<String> originals, int maxSize) {
        List<String> result = new ArrayList<>();
        if (token == null) {
            for (String s : originals) {
                if (result.size() >= maxSize) break;
                result.add(s);
            }
            return result;
        }
        for (String s : originals) {
            if (result.size() >= maxSize) break;
            if (startsWithIgnoreCase(s, token)) {
                result.add(s);
            }
        }
        return result;
    }
}
