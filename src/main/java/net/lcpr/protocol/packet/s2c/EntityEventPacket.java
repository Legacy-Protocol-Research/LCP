package net.lcpr.protocol.packet.s2c;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;
import net.lcpr.protocol.utils.EndianInputStream;
import net.lcpr.protocol.utils.EndianOutputStream;

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
    public void read(EndianInputStream inputStream) throws IOException {
        entityId = inputStream.readInt();
        eventId = inputStream.readByte();
        data = inputStream.readInt();
    }

    @Override
    public void write(EndianOutputStream outputStream) throws IOException {
        outputStream.writeInt(entityId);
        outputStream.writeByte(eventId);
        outputStream.writeInt(data);
    }
}
