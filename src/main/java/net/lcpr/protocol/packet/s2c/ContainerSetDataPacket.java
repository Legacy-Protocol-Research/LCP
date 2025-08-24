package net.lcpr.protocol.packet.s2c;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;

import java.nio.ByteBuffer;

@Getter
@Setter
public class ContainerSetDataPacket extends Packet {
    private int containerId;
    private int id;
    private int value;

    @Override
    public void read(ByteBuffer byteBuffer) {
        containerId = byteBuffer.get();
        id = byteBuffer.getShort();
        value = byteBuffer.getShort();
    }

    @Override
    public void write(ByteBuffer byteBuffer) {
        byteBuffer.put((byte) containerId);
        byteBuffer.putShort((short) id);
        byteBuffer.putShort((short) value);
    }
}
