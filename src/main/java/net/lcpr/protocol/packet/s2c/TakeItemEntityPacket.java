package net.lcpr.protocol.packet.s2c;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;
import net.lcpr.protocol.utils.VariableTypes;

import java.nio.ByteBuffer;

@Getter
@Setter
public class TakeItemEntityPacket extends Packet {
    private int playerId, itemId, amount;

    @Override
    public void read(ByteBuffer byteBuffer) {
        playerId = VariableTypes.readInt(byteBuffer);
        itemId = VariableTypes.readInt(byteBuffer);
        amount = VariableTypes.readInt(byteBuffer);
    }

    @Override
    public void write(ByteBuffer byteBuffer) {
        VariableTypes.writeInt(byteBuffer, playerId);
        VariableTypes.writeInt(byteBuffer, itemId);
        VariableTypes.writeInt(byteBuffer, amount);
    }
}
