package net.lcpr.protocol.packet.s2c;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;

import java.nio.ByteBuffer;

@Getter
@Setter
public class GameEventPacket extends Packet {
    private int event;
    private float param;
    private int playerIndex;

    @Override
    public void read(ByteBuffer byteBuffer) {
        event = byteBuffer.get();
        param = byteBuffer.getFloat();
        playerIndex = byteBuffer.getInt();
    }

    @Override
    public void write(ByteBuffer byteBuffer) {
        byteBuffer.put((byte) event);
        byteBuffer.putFloat(param);
        byteBuffer.putInt(playerIndex);
    }

    @Override
    public int getEstimatedSize() {
        return 3;
    }
}
