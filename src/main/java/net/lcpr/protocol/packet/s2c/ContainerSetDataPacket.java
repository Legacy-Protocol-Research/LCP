package net.lcpr.protocol.packet.s2c;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;
import net.lcpr.protocol.utils.EndianInputStream;
import net.lcpr.protocol.utils.EndianOutputStream;
import net.lcpr.protocol.utils.PacketType;

import java.io.IOException;

@Getter
@Setter
public class ContainerSetDataPacket extends Packet {
    private int containerId;
    private int id;
    private int value;

    @Override
    public void read(EndianInputStream inputStream) throws IOException {
        containerId = inputStream.readUnsignedByte();
        id = inputStream.readShort();
        value = inputStream.readShort();
    }

    @Override
    public void write(EndianOutputStream outputStream) throws IOException {
        outputStream.writeByte(containerId);
        outputStream.writeShort(id);
        outputStream.writeShort(value);
    }

    @Override
    public PacketType getType() {
        return PacketType.ClientboundContainerSetDataPacket;
    }
}
