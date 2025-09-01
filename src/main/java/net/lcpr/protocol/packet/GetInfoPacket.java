package net.lcpr.protocol.packet;

import net.lcpr.protocol.utils.EndianInputStream;
import net.lcpr.protocol.utils.EndianOutputStream;
import net.lcpr.protocol.utils.PacketType;

public class GetInfoPacket extends Packet {
    @Override
    public void read(EndianInputStream inputStream) {}

    @Override
    public void write(EndianOutputStream outputStream) {}

    @Override
    public PacketType getType() {
        return PacketType.GetInfoPacket;
    }

    @Override
    public int getEstimatedSize() {
        return 0;
    }
}
