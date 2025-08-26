package net.lcpr.protocol.packet.s2c;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

@Getter
@Setter
public class EntityEventPacket extends Packet {
    private int entityId;
    private byte eventId;
    private int data;

    @Override
    public void read(DataInputStream inputStream) throws IOException {
        entityId = inputStream.readInt();
        eventId = inputStream.readByte();
        data = inputStream.readInt();
    }

    @Override
    public void write(DataOutputStream outputStream) throws IOException {
        outputStream.writeInt(entityId);
        outputStream.writeByte(eventId);
        outputStream.writeInt(data);
    }
}
