package net.lcpr.protocol.packet.s2c;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;
import net.lcpr.protocol.utils.VariableTypes;

import java.nio.ByteBuffer;

@Getter
@Setter
public class CooldownPacket extends Packet {
    private int itemId;
    private int cooldown;

    @Override
    public void read(ByteBuffer byteBuffer) {
        itemId = VariableTypes.readInt(byteBuffer);
        cooldown = VariableTypes.readInt(byteBuffer);
    }

    @Override
    public void write(ByteBuffer byteBuffer) {
        VariableTypes.writeInt(byteBuffer, itemId);
        VariableTypes.writeInt(byteBuffer, cooldown);
    }
}
