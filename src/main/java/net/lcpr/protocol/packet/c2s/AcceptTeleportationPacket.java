package net.lcpr.protocol.packet.c2s;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;
import net.lcpr.protocol.utils.VariableTypes;

import java.nio.ByteBuffer;

@Getter
@Setter
public class AcceptTeleportationPacket extends Packet {
    private int id;

    @Override
    public void read(ByteBuffer byteBuffer) {
        id = VariableTypes.readInt(byteBuffer);
    }

    @Override
    public void write(ByteBuffer byteBuffer) {
        VariableTypes.writeInt(byteBuffer, id);
    }
}
