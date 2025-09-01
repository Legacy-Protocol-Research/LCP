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
public class SetCarriedItemPacket extends Packet {
    private byte itemId;

    @Override
    public void read(EndianInputStream inputStream) throws IOException {
        itemId = inputStream.readByte();
    }

    @Override
    public void write(EndianOutputStream outputStream) throws IOException {
        outputStream.writeByte(itemId);
    }

    @Override
    public PacketType getType() {
        return PacketType.ClientboundSetCarriedItemPacket;
    }

    @Override
    public int getEstimatedSize() {
        return 2;
    }
}
