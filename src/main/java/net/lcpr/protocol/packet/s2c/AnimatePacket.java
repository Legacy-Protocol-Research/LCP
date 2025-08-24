package net.lcpr.protocol.packet.s2c;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;

import java.nio.ByteBuffer;

@Getter
@Setter
public class AnimatePacket extends Packet {
    private int id;
    private byte action;

    @Override
    public void read(ByteBuffer byteBuffer) {
        id = byteBuffer.getInt();
        action = byteBuffer.get();
    }

    @Override
    public void write(ByteBuffer byteBuffer) {
        byteBuffer.putInt(id);
        byteBuffer.put(action);
    }

    @Override
    public int getEstimatedSize() {
        return 5;
    }
}
