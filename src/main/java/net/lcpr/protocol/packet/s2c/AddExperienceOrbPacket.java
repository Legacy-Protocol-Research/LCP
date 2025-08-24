package net.lcpr.protocol.packet.s2c;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;

import java.nio.ByteBuffer;

@Getter
@Setter
public class AddExperienceOrbPacket extends Packet {
    private int id;
    private int x;
    private int y;
    private int z;
    private int value;

    @Override
    public void read(ByteBuffer byteBuffer) {
        id = byteBuffer.getInt();
        x = byteBuffer.getInt();
        y = byteBuffer.getInt();
        z = byteBuffer.getInt();
        value = byteBuffer.getShort();
    }

    @Override
    public void write(ByteBuffer byteBuffer) {
        byteBuffer.putInt(id);
        byteBuffer.putInt(x);
        byteBuffer.putInt(y);
        byteBuffer.putInt(z);
        byteBuffer.putShort((short) value);
    }

    @Override
    public int getEstimatedSize() {
        return 18;
    }
}
