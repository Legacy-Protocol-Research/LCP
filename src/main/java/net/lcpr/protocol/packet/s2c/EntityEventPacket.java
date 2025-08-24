package net.lcpr.protocol.packet.s2c;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;

import java.nio.ByteBuffer;

@Getter
@Setter
public class EntityEventPacket extends Packet {
    private int entityId;
    private byte eventId;
    private int data;

    @Override
    public void read(ByteBuffer byteBuffer) {
        entityId = byteBuffer.getInt();
        eventId = byteBuffer.get();
        data = byteBuffer.getInt();
    }

    @Override
    public void write(ByteBuffer byteBuffer) {
        byteBuffer.putInt(entityId);
        byteBuffer.put(eventId);
        byteBuffer.putInt(data);
    }
}
