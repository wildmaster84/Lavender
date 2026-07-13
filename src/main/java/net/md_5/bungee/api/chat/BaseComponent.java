package net.md_5.bungee.api.chat;

public abstract class BaseComponent {
    private BaseComponent[] extra;
    private net.md_5.bungee.api.ChatColor color;
    private net.md_5.bungee.api.ChatColor[] formats;
    private String insertion;
    private ClickEvent clickEvent;
    private HoverEvent hoverEvent;

    public BaseComponent[] getExtra() { return extra; }
    public void setExtra(BaseComponent[] extra) { this.extra = extra; }
    public net.md_5.bungee.api.ChatColor getColor() { return color; }
    public void setColor(net.md_5.bungee.api.ChatColor color) { this.color = color; }
    public net.md_5.bungee.api.ChatColor[] getFormats() { return formats; }
    public void setFormats(net.md_5.bungee.api.ChatColor[] formats) { this.formats = formats; }
    public String getInsertion() { return insertion; }
    public void setInsertion(String insertion) { this.insertion = insertion; }
    public ClickEvent getClickEvent() { return clickEvent; }
    public void setClickEvent(ClickEvent clickEvent) { this.clickEvent = clickEvent; }
    public HoverEvent getHoverEvent() { return hoverEvent; }
    public void setHoverEvent(HoverEvent hoverEvent) { this.hoverEvent = hoverEvent; }

    public boolean hasFormatting() { return color != null || formats != null || insertion != null || clickEvent != null || hoverEvent != null; }
    public abstract String toPlainText();
    public abstract String toLegacyText();

    public static String toPlainText(BaseComponent... components) {
        StringBuilder sb = new StringBuilder();
        for (BaseComponent c : components) sb.append(c.toPlainText());
        return sb.toString();
    }
    public static String toLegacyText(BaseComponent... components) {
        StringBuilder sb = new StringBuilder();
        for (BaseComponent c : components) sb.append(c.toLegacyText());
        return sb.toString();
    }
    public static BaseComponent[] duplicateWithoutFormatting(BaseComponent... components) {
        return components;
    }
}
