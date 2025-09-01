package net.lcpr.protocol.packet.s2c;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;
import net.lcpr.protocol.utils.EndianInputStream;
import net.lcpr.protocol.utils.EndianOutputStream;

import java.io.IOException;

@Getter
@Setter
public class RotateHeadPacket extends Packet {
    private int entityId;
    private byte yHeadRot;

    @Override
    public void read(EndianInputStream inputStream) throws IOException {
        entityId = inputStream.readVarInt();
        yHeadRot = inputStream.readByte();
    }

    @Override
    public void write(EndianOutputStream outputStream) throws IOException {
        outputStream.writeVarInt(entityId);
        outputStream.writeByte(yHeadRot);
    }

    @Override
    public int getEstimatedSize() {
        return 5;
    }

    @Override
    public boolean isAsync() {
        return true;
    }
}
