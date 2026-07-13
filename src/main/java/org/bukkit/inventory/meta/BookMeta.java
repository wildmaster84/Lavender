package org.bukkit.inventory.meta;

import java.util.List;

public interface BookMeta extends ItemMeta {
    boolean hasTitle();
    String getTitle();
    boolean setTitle(String title);
    boolean hasAuthor();
    String getAuthor();
    void setAuthor(String author);
    boolean hasGeneration();
    Generation getGeneration();
    void setGeneration(Generation generation);
    boolean hasPages();
    List<String> getPages();
    void setPages(List<String> pages);
    void setPages(String... pages);
    void addPage(String... pages);
    int getPageCount();
    boolean hasItemMeta();
    BookMeta clone();

    enum Generation { ORIGINAL, COPY_OF_ORIGINAL, COPY_OF_COPY, TATTERED }
}
