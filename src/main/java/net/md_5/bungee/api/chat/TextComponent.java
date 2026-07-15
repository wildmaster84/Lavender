package net.md_5.bungee.api.chat;

public class TextComponent extends BaseComponent {
    private String text;

    public TextComponent() { this.text = ""; }
    public TextComponent(String text) { this.text = text; }
    public TextComponent(TextComponent original) { this.text = original.text; }
    public TextComponent(BaseComponent... components) {
        this.text = "";
        if (components != null) {
            StringBuilder sb = new StringBuilder();
            for (BaseComponent c : components) sb.append(c.toPlainText());
            this.text = sb.toString();
        }
    }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    @Override
    public String toPlainText() { return text; }
    @Override
    public String toLegacyText() {
        StringBuilder sb = new StringBuilder();
        if (getColor() != null) sb.append(getColor());
        sb.append(text);
        return sb.toString();
    }

    public static BaseComponent[] fromLegacyText(String message) {
        return new BaseComponent[] { new TextComponent(message) };
    }
    public static BaseComponent[] fromLegacyText(String message, boolean color) {
        return new BaseComponent[] { new TextComponent(message) };
    }
}
