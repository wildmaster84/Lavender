package net.minecraft.network.chat;

public interface Component {
    String getString();
    String getContents();
    net.kyori.adventure.text.Component getAdventureComponent();

    static Component fromJson(String json) {
        net.kyori.adventure.text.Component adventure;
        try {
            adventure = net.kyori.adventure.text.serializer.gson.GsonComponentSerializer.gson().deserialize(json);
        } catch (Exception e) {
            adventure = net.kyori.adventure.text.Component.text(json);
        }
        return new SimpleComponent(adventure);
    }

    static MutableComponent literal(String text) {
        return new MutableComponent(text);
    }

    class SimpleComponent implements Component {
        private final net.kyori.adventure.text.Component adventure;

        public SimpleComponent(net.kyori.adventure.text.Component adventure) {
            this.adventure = adventure;
        }

        public SimpleComponent(String text) {
            this.adventure = net.kyori.adventure.text.Component.text(text);
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
}
