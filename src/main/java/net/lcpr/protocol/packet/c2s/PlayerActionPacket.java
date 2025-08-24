package net.lcpr.protocol.packet.c2s;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;
import net.lcpr.protocol.types.Direction;
import net.lcpr.protocol.utils.Vec;

import java.nio.ByteBuffer;

@Getter
@Setter
public class PlayerActionPacket extends Packet {
    private Vec.i3 pos;
    private Direction direction;
    private int action;
    private int useTime;

    @Override
    public void read(ByteBuffer byteBuffer) {
        action = byteBuffer.getInt();
        pos = Vec.i3.read(byteBuffer);
        direction = Direction.values()[byteBuffer.get()];
        useTime = byteBuffer.getInt();
    }

    @Override
    public void write(ByteBuffer byteBuffer) {
        byteBuffer.putInt(action);
        Vec.i3.write(byteBuffer, pos);
        byteBuffer.put((byte) direction.ordinal());
        byteBuffer.putInt(useTime);
    }

    @Override
    public int getEstimatedSize() {
        return 11;
    }
}
