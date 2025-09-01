package net.lcpr.protocol;

import net.lcpr.protocol.packet.Packet;
import net.lcpr.protocol.types.DisconnectReason;

public interface PacketListener {
    void handlePacket(Packet packet);

    void onUnhandledPacket(Packet packet);
    void onDisconnect(DisconnectReason reason);
    void canHandleAsyncPackets();
}
