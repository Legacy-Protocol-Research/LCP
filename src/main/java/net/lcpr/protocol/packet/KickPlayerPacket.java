package net.lcpr.protocol.packet;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.utils.EndianInputStream;
import net.lcpr.protocol.utils.EndianOutputStream;
import net.lcpr.protocol.utils.PacketType;

import java.io.IOException;

@Getter
@Setter
public class KickPlayerPacket extends Packet {
    private byte byte18;

    @Override
    public void read(EndianInputStream inputStream) throws IOException {
        byte18 = inputStream.readByte();
    }

    @Override
    public void write(EndianOutputStream outputStream) throws IOException {
        outputStream.writeByte(byte18);
    }

    @Override
    public PacketType getType() {
        return PacketType.KickPlayerPacket;
    }

    @Override
    public int getEstimatedSize() {
        return 1;
    }
}
