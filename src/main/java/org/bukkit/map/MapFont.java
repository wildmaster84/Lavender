package org.bukkit.map;

public abstract class MapFont {

    public static class Character {
        private final int height;
        private final int width;
        private final boolean[] data;

        public Character(int width, int height, boolean[] data) {
            this.width = width;
            this.height = height;
            this.data = data;
        }

        public int getWidth() { return width; }
        public int getHeight() { return height; }
        public boolean[] getData() { return data; }
    }

    public Character getCharacter(char c) { return null; }
    public boolean isValidChar(char c) { return false; }
    public int getWidth(String text) { return 0; }
    public int getHeight() { return 0; }
}
