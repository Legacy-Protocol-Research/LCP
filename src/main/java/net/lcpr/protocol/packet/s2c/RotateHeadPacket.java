package net.lcpr.protocol.packet.s2c;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;
import net.lcpr.protocol.utils.VariableTypes;

import java.nio.ByteBuffer;

@Getter
@Setter
public class RotateHeadPacket extends Packet {
    private int entityId;
    private byte yHeadRot;

    @Override
    public void read(ByteBuffer byteBuffer) {
        entityId = VariableTypes.readInt(byteBuffer);
        yHeadRot = byteBuffer.get();
    }

    @Override
    public void write(ByteBuffer byteBuffer) {
        VariableTypes.writeInt(byteBuffer, entityId);
        byteBuffer.putInt(yHeadRot);
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
