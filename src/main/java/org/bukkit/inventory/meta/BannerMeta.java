package org.bukkit.inventory.meta;

import java.util.List;
import org.bukkit.DyeColor;

public interface BannerMeta extends ItemMeta {
    DyeColor getBaseColor();
    void setBaseColor(DyeColor color);
    List<org.bukkit.block.banner.Pattern> getPatterns();
    void setPatterns(List<org.bukkit.block.banner.Pattern> patterns);
    void addPattern(org.bukkit.block.banner.Pattern pattern);
    org.bukkit.block.banner.Pattern getPattern(int i);
    int numberOfPatterns();
    void removePattern(int i);
}
