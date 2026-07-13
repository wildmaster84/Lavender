package org.bukkit.scoreboard;

public interface Criteria {
    String DUMMY = "dummy";
    String getName();
    boolean isReadOnly();
    org.bukkit.scoreboard.RenderType getDefaultRenderType();
}
