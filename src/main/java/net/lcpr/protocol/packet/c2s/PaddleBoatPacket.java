package net.lcpr.protocol.packet.c2s;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.ByteBufferUtils;
import net.lcpr.protocol.packet.Packet;

import java.nio.ByteBuffer;

@Getter
@Setter
public class PaddleBoatPacket extends Packet {
    private boolean left;
    private boolean right;

    @Override
    public void read(ByteBuffer byteBuffer) {
        left = ByteBufferUtils.getBoolean(byteBuffer);
        right = ByteBufferUtils.getBoolean(byteBuffer);
    }

    @Override
    public void write(ByteBuffer byteBuffer) {
        ByteBufferUtils.putBoolean(byteBuffer, left);
        ByteBufferUtils.putBoolean(byteBuffer, right);
    }
}
