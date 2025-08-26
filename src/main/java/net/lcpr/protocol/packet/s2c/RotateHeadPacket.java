package net.lcpr.protocol.packet.s2c;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;
import net.lcpr.protocol.utils.VariableTypes;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

@Getter
@Setter
public class RotateHeadPacket extends Packet {
    private int entityId;
    private byte yHeadRot;

    @Override
    public void read(DataInputStream inputStream) throws IOException {
        entityId = VariableTypes.readInt(inputStream);
        yHeadRot = inputStream.readByte();
    }

    @Override
    public void write(DataOutputStream outputStream) throws IOException {
        VariableTypes.writeInt(outputStream, entityId);
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
