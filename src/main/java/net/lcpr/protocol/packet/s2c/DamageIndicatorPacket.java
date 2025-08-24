package net.lcpr.protocol.packet.s2c;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.ByteBufferUtils;
import net.lcpr.protocol.packet.Packet;

import java.nio.ByteBuffer;

@Getter
@Setter
public class DamageIndicatorPacket extends Packet {
    private float x;
    private float z;
    private boolean allDirections;

    @Override
    public void read(ByteBuffer byteBuffer) {
        x = byteBuffer.getFloat();
        z = byteBuffer.getFloat();
        allDirections = ByteBufferUtils.getBoolean(byteBuffer);
    }

    @Override
    public void write(ByteBuffer byteBuffer) {
        byteBuffer.putFloat(x);
        byteBuffer.putFloat(z);
        ByteBufferUtils.putBoolean(byteBuffer, allDirections);
    }

    @Override
    public int getEstimatedSize() {
        return 9;
    }
}
