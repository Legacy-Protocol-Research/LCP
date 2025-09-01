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
public class AcceptTeleportationPacket extends Packet {
    private int id;

    @Override
    public void read(EndianInputStream inputStream) throws IOException {
        id = inputStream.readVarInt();
    }

    @Override
    public void write(EndianOutputStream outputStream) throws IOException {
        outputStream.writeVarInt(id);
    }

    @Override
    public PacketType getType() {
        return PacketType.ServerboundAcceptTeleportationPacket;
    }
}
