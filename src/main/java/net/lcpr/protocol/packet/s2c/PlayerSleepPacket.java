package net.lcpr.protocol.packet.s2c;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;
import net.lcpr.protocol.utils.VariableTypes;
import net.lcpr.protocol.utils.Vec;

import java.nio.ByteBuffer;

@Getter
@Setter
public class PlayerSleepPacket extends Packet {
    private int playerId;
    private Vec.i3 pos;

    @Override
    public void read(ByteBuffer byteBuffer) {
        playerId = VariableTypes.readInt(byteBuffer);
        pos = Vec.i3.read(byteBuffer);
    }

    @Override
    public void write(ByteBuffer byteBuffer) {
        VariableTypes.writeInt(byteBuffer, playerId);
        Vec.i3.write(byteBuffer, pos);
    }
}
