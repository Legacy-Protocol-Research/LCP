package net.lcpr.protocol.packet;

import lombok.Getter;
import lombok.Setter;

import java.nio.ByteBuffer;

@Getter
@Setter
public class UpdateProgressPacket extends Packet {
    private int progress;

    @Override
    public void read(ByteBuffer byteBuffer) {
        progress = byteBuffer.getInt();
    }

    @Override
    public void write(ByteBuffer byteBuffer) {
        byteBuffer.putInt(progress);
    }

    @Override
    public int getEstimatedSize() {
        return 1;
    }
}
