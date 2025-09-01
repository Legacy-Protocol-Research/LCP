package net.lcpr.protocol.packet;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.utils.EndianInputStream;
import net.lcpr.protocol.utils.EndianOutputStream;
import net.lcpr.protocol.utils.PacketType;

import java.io.IOException;

@Getter
@Setter
public class TradeItemPacket extends Packet {
    private int dword18;
    private int dword1C;

    @Override
    public void read(EndianInputStream inputStream) throws IOException {
        dword18 = inputStream.readInt();
        dword1C = inputStream.readInt();
    }

    @Override
    public void write(EndianOutputStream outputStream) throws IOException {
        outputStream.writeInt(dword18);
        outputStream.writeInt(dword1C);
    }

    @Override
    public PacketType getType() {
        return PacketType.TradeItemPacket;
    }
}
