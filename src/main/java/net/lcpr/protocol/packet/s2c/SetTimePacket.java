package net.lcpr.protocol.packet.s2c;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;

import java.nio.ByteBuffer;

@Getter
@Setter
public class SetTimePacket extends Packet {
    private long gameTime;
    private long dayTime;

    @Override
    public void read(ByteBuffer byteBuffer) {
        gameTime = byteBuffer.getLong();
        dayTime = byteBuffer.getLong();
    }

    @Override
    public void write(ByteBuffer byteBuffer) {
        byteBuffer.putLong(gameTime);
        byteBuffer.putLong(dayTime);
    }

    @Override
    public int getEstimatedSize() {
        return 16;
    }

    @Override
    public boolean isAsync() {
        return true;
    }
}
