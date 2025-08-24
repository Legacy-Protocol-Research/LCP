package net.lcpr.protocol.packet.c2s;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;
import net.lcpr.protocol.utils.VariableTypes;

import java.nio.ByteBuffer;

@Getter
@Setter
public class PlayerCommandPacket extends Packet {
    private int id;
    private int action;
    private int data;

    @Override
    public void read(ByteBuffer byteBuffer) {
        id = VariableTypes.readInt(byteBuffer);
        action = byteBuffer.getInt();
        data = VariableTypes.readInt(byteBuffer);
    }

    @Override
    public void write(ByteBuffer byteBuffer) {
        VariableTypes.writeInt(byteBuffer, id);
        byteBuffer.putInt(action);
        VariableTypes.writeInt(byteBuffer, data);
    }

    @Override
    public int getEstimatedSize() {
        return 9;
    }
}
