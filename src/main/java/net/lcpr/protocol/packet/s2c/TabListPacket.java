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
public class TabListPacket extends Packet {
    private String str18;
    private String str38;

    @Override
    public void read(EndianInputStream inputStream) throws IOException {
        str18 = inputStream.readUTF(0x7FFF);
        str38 = inputStream.readUTF(0x7FFF);
    }

    @Override
    public void write(EndianOutputStream outputStream) throws IOException {
        outputStream.writeUTF(str18);
        outputStream.writeUTF(str38);
    }

    @Override
    public PacketType getType() {
        return PacketType.ClientboundTabListPacket;
    }
}
