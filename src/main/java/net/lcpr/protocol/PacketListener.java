package net.lcpr.protocol;

import net.lcpr.protocol.packet.Packet;

public interface PacketListener {
    void handlePacket(Packet packet);
}
