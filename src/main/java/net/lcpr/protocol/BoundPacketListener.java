package net.lcpr.protocol;

import net.lcpr.protocol.packet.Packet;
import net.lcpr.protocol.utils.Side;

public interface BoundPacketListener extends PacketListener {
    default void handlePacket(Packet packet) {
        if (packet.getSide() == Side.CLIENTBOUND) {
            handleClientboundPacket(packet);
        } else {
            handleServerboundPacket(packet);
        }
    }
    default void onUnhandledPacket(Packet packet) {
        if (packet.getSide() == Side.CLIENTBOUND) {
            onUnhandledClientboundPacket(packet);
        } else {
            onUnhandledServerboundPacket(packet);
        }
    }

    void handleClientboundPacket(Packet packet);
    void handleServerboundPacket(Packet packet);

    void onUnhandledClientboundPacket(Packet packet);
    void onUnhandledServerboundPacket(Packet packet);
}
