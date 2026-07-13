package me.wildmaster84.lavender.rcon;

import net.kyori.adventure.identity.Identity;
import net.kyori.adventure.pointer.Pointers;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import net.minestom.server.command.CommandSender;
import net.minestom.server.tag.TagHandler;

public class RCONSender implements CommandSender {
    private final StringBuilder output;
    private final TagHandler tagHandler = TagHandler.newHandler();
    private final Identity identity = Identity.nil();
    private final Pointers pointers = Pointers.builder()
            .withStatic(Identity.UUID, this.identity.uuid())
            .build();

    public RCONSender(StringBuilder output) {
        this.output = output;
    }

    @Override
    public void sendMessage(String message) {
        if (output.length() > 0) output.append("\n");
        output.append(message);
    }

    @Override
    public void sendMessage(Component message) {
        sendMessage(PlainTextComponentSerializer.plainText().serialize(message));
    }

    @Override
    public TagHandler tagHandler() { return tagHandler; }

    @Override
    public Identity identity() { return identity; }

    @Override
    public Pointers pointers() { return pointers; }
}
