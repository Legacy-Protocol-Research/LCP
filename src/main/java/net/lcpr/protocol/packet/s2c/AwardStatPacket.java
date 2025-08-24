package net.lcpr.protocol.packet.s2c;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;

import java.nio.ByteBuffer;

@Getter
@Setter
public class AwardStatPacket extends Packet {
    private int stat;
    private String data;

    @Override
    public void read(ByteBuffer byteBuffer) {
        stat = byteBuffer.getInt();

        int size = byteBuffer.getInt();
        if (size < 1) return;

        // TODO find out how to writeBytes
    }

    @Override
    public void write(ByteBuffer byteBuffer) {
        byteBuffer.putInt(stat);
        byteBuffer.putInt(data.length());
    }

    @Override
    public int getEstimatedSize() {
        return 6;
    }
}
