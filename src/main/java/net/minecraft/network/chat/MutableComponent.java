package net.minecraft.network.chat;

public class MutableComponent implements Component {
    private final net.kyori.adventure.text.Component adventure;

    public MutableComponent(String text) {
        this.adventure = net.kyori.adventure.text.Component.text(text);
    }

    public MutableComponent(net.kyori.adventure.text.Component component) {
        this.adventure = component == null ? net.kyori.adventure.text.Component.empty() : component;
    }

    @Override
    public String getString() {
        return net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer.plainText().serialize(adventure);
    }

    @Override
    public String getContents() { return getString(); }

    @Override
    public net.kyori.adventure.text.Component getAdventureComponent() { return adventure; }
}
