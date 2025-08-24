package net.lcpr.protocol.packet;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.ByteBufferUtils;

import java.nio.ByteBuffer;

@Getter
@Setter
public class PlayerReadyPacket extends Packet {
    private long playerId;
    private boolean ready;

    @Override
    public void read(ByteBuffer byteBuffer) {
        playerId = ByteBufferUtils.getUInt(byteBuffer);
        ready = ByteBufferUtils.getBoolean(byteBuffer);
    }

    @Override
    public void write(ByteBuffer byteBuffer) {
        ByteBufferUtils.putUInt(byteBuffer, playerId);
        ByteBufferUtils.putBoolean(byteBuffer, ready);
    }

    @Override
    public int getEstimatedSize() {
        return 5;
    }
}
