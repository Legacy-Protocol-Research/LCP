package net.lcpr.protocol.connection;

import net.lcpr.protocol.PacketListener;
import net.lcpr.protocol.packet.Packet;
import net.lcpr.protocol.types.DisconnectReason;

import java.io.IOException;

public interface Connection {
    boolean getAndSetRunning(boolean running);

    void send(Packet packet);

    void queue(Packet packet);

    void setListener(PacketListener packetListener);

    boolean writeTick() throws IOException;

    void flush();

    boolean readTick();

    void tick() throws IOException, InterruptedException;

    void close(DisconnectReason reason) throws IOException, InterruptedException;

    void sendAndQuit() throws IOException, InterruptedException;
}
