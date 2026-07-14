package me.wildmaster84.lavender.rcon;

import java.io.*;
import java.net.*;
import net.minestom.server.MinecraftServer;
import net.minestom.server.command.CommandManager;

public class RCONServer {
    private final int port;
    private final String password;
    private ServerSocket serverSocket;
    private volatile boolean running;

    public RCONServer(int port, String password) {
        this.port = port;
        this.password = password;
    }

    public void start() {
        running = true;
        try {
            serverSocket = new ServerSocket(port);
            Thread t = Thread.ofVirtual().name("RCON-Accept").start(this::acceptLoop);
            MinecraftServer.LOGGER.info("RCON listening on port " + port);
        } catch (IOException e) {
            MinecraftServer.LOGGER.error("RCON failed to start: " + e.getMessage());
        }
    }

    public void stop() {
        running = false;
        try { if (serverSocket != null) serverSocket.close(); } catch (IOException ignored) {}
    }

    private void acceptLoop() {
        while (running) {
            try {
                Socket client = serverSocket.accept();
                Thread.startVirtualThread(() -> handleClient(client));
            } catch (IOException e) {
                if (running) MinecraftServer.LOGGER.error("RCON accept error: " + e.getMessage());
            }
        }
    }

    private void handleClient(Socket client) {
        try (Socket s = client;
             DataInputStream in = new DataInputStream(new BufferedInputStream(s.getInputStream()));
             DataOutputStream out = new DataOutputStream(new BufferedOutputStream(s.getOutputStream()))) {

            boolean authed = false;

            while (running && !s.isClosed()) {
                int length = readLEInt(in);
                int reqId = readLEInt(in);
                int type = readLEInt(in);
                String payload = readString(in);
                in.readByte();

                if (type == 3) {
                    if (payload.equals(password)) {
                        authed = true;
                        sendPacket(out, reqId, 2, "");
                    } else {
                        sendPacket(out, -1, 2, "");
                    }
                } else if (type == 2 && authed) {
                    String result = executeCommand(payload);
                    sendPacket(out, reqId, 0, result);
                }
            }
        } catch (IOException ignored) {}
    }

    private String executeCommand(String command) {
        CommandManager cm = MinecraftServer.getCommandManager();
        StringBuilder output = new StringBuilder();
        RCONSender sender = new RCONSender(output);
        cm.execute(sender, command);
        return output.toString();
    }

    private static int readLEInt(DataInputStream in) throws IOException {
        int a = in.read(), b = in.read(), c = in.read(), d = in.read();
        if ((a | b | c | d) < 0) throw new EOFException();
        return a | (b << 8) | (c << 16) | (d << 24);
    }

    private static String readString(DataInputStream in) throws IOException {
        StringBuilder sb = new StringBuilder();
        int b;
        while ((b = in.read()) != 0) sb.append((char) b);
        return sb.toString();
    }

    private static void sendPacket(DataOutputStream out, int reqId, int type, String payload) throws IOException {
        int length = 4 + 4 + payload.length() + 1 + 1;
        writeLEInt(out, length);
        writeLEInt(out, reqId);
        writeLEInt(out, type);
        out.writeBytes(payload);
        out.writeByte(0);
        out.writeByte(0);
        out.flush();
    }

    private static void writeLEInt(DataOutputStream out, int v) throws IOException {
        out.write(v & 0xFF);
        out.write((v >> 8) & 0xFF);
        out.write((v >> 16) & 0xFF);
        out.write((v >> 24) & 0xFF);
    }
}
