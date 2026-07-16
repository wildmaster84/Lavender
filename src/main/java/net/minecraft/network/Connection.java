package net.minecraft.network;

import io.netty.channel.Channel;

public class Connection {
    public Channel channel;

    public Connection(Channel channel) {
        this.channel = channel;
    }

    public Connection() {
    }
}
