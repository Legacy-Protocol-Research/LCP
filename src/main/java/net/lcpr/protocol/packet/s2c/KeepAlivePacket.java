package net.lcpr.protocol.packet.s2c;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;

import java.nio.ByteBuffer;

@Getter
@Setter
public class KeepAlivePacket extends Packet {
    private int id;

    @Override
    public void read(ByteBuffer byteBuffer) {
        id = byteBuffer.getInt();
    }

    @Override
    public void write(ByteBuffer byteBuffer) {
        byteBuffer.putInt(id);
    }

    @Override
    public int getEstimatedSize() {
        return 4;
    }
}
