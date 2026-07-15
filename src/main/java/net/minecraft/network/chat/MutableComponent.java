package net.minecraft.network.chat;

public class MutableComponent implements Component {
    private final net.kyori.adventure.text.Component adventure;

    public MutableComponent(net.kyori.adventure.text.Component adventure) {
        this.adventure = adventure;
    }

    public net.kyori.adventure.text.Component adventure() {
        return adventure;
    }

    @Override
    public String getString() {
        return net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer.plainText().serialize(adventure);
    }

    @Override
    public String getContents() {
        return getString();
    }
}
