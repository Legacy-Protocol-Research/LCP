package net.lcpr.protocol.packet.c2s;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;
import net.lcpr.protocol.utils.EndianInputStream;
import net.lcpr.protocol.utils.EndianOutputStream;
import net.lcpr.protocol.utils.PacketType;

import java.io.IOException;

@Getter
@Setter
public class ClientCommandPacket extends Packet {
    private int commandId;

    @Override
    public void read(EndianInputStream inputStream) throws IOException {
        commandId = inputStream.readInt();
    }

    @Override
    public void write(EndianOutputStream outputStream) throws IOException {
        outputStream.writeInt(commandId);
    }

    @Override
    public PacketType getType() {
        return PacketType.ServerboundClientCommandPacket;
    }

    @Override
    public int getEstimatedSize() {
        return 1;
    }
}
