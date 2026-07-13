package org.bukkit.map;

public interface MapCanvas {

    MapView getMap();
    int getBasePixelX();
    int getBasePixelZ();
    void setPixel(int x, int z, byte color);
    byte getPixel(int x, int z);
    byte getBasePixel(int x, int z);
    void setBasePixel(int x, int z, byte color);
    void drawImage(int x, int z, java.awt.Image image);
    void drawText(int x, int z, MapFont font, String text);
    void setCursor(int x, int z, byte direction, byte type);
    void setCursor(int x, int z, MapCursor.Type type, MapCursor.Direction direction);
    void addCursor(int x, int z, byte direction, byte type);
    void addCursor(int x, int z, MapCursor.Type type, MapCursor.Direction direction);
    java.util.Collection<MapCursor> getCursors();
    void clearCursors();
}
