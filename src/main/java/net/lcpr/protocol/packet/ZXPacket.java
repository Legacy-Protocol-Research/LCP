package net.lcpr.protocol.packet;

import lombok.Getter;
import lombok.Setter;

import java.nio.ByteBuffer;

@Getter
@Setter
public class ZXPacket extends Packet {
    private char field_28;
    private int x, z;

    @Override
    public void read(ByteBuffer byteBuffer) {
        field_28 = byteBuffer.getChar();
        x = byteBuffer.getInt();
        z = byteBuffer.getInt();
    }

    @Override
    public void write(ByteBuffer byteBuffer) {
        byteBuffer.putChar(field_28);
        byteBuffer.putInt(x);
        byteBuffer.putInt(z);
    }

    @Override
    public int getEstimatedSize() {
        return 10;
    }
}
