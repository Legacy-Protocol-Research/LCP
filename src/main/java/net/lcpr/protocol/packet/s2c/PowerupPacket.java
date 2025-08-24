package net.lcpr.protocol.packet.s2c;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;

import java.nio.ByteBuffer;

@Getter
@Setter
public class PowerupPacket extends Packet {
    private int powerupId;
    private int powerupTime;

    @Override
    public void read(ByteBuffer byteBuffer) {
        powerupId = byteBuffer.getInt();
        powerupTime = byteBuffer.getInt();
    }

    @Override
    public void write(ByteBuffer byteBuffer) {
        byteBuffer.putInt(powerupId);
        byteBuffer.putInt(powerupTime);
    }

    @Override
    public int getEstimatedSize() {
        return 2;
    }
}
