package net.lcpr.protocol.packet;

import lombok.Getter;
import lombok.Setter;

import java.nio.ByteBuffer;

@Getter
@Setter
public class DebugOptionsPacket extends Packet {
    private int word18;

    @Override
    public void read(ByteBuffer byteBuffer) {
        word18 = byteBuffer.getInt();
    }

    @Override
    public void write(ByteBuffer byteBuffer) {
        byteBuffer.putInt(word18);
    }

    @Override
    public int getEstimatedSize() {
        return 4;
    }
}
