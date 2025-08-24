package net.lcpr.protocol.packet;

import lombok.Getter;
import lombok.Setter;

import java.nio.ByteBuffer;

@Getter
@Setter
public class KickPlayerPacket extends Packet {
    private byte byte18;

    @Override
    public void read(ByteBuffer byteBuffer) {
        byte18 = byteBuffer.get();
    }

    @Override
    public void write(ByteBuffer byteBuffer) {
        byteBuffer.put(byte18);
    }

    @Override
    public int getEstimatedSize() {
        return 1;
    }
}
