package net.lcpr.protocol.packet;

import java.nio.ByteBuffer;

public class GetInfoPacket extends Packet {
    @Override
    public void read(ByteBuffer byteBuffer) {}

    @Override
    public void write(ByteBuffer byteBuffer) {}

    @Override
    public int getEstimatedSize() {
        return 0;
    }
}
