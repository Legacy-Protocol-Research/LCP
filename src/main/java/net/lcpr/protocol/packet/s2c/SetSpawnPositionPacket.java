package net.lcpr.protocol.packet.s2c;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;
import net.lcpr.protocol.utils.Vec;

import java.nio.ByteBuffer;

@Getter
@Setter
public class SetSpawnPositionPacket extends Packet {
    private Vec.i3 pos;

    @Override
    public void read(ByteBuffer byteBuffer) {
        pos = Vec.i3.read(byteBuffer);
    }

    @Override
    public void write(ByteBuffer byteBuffer) {
        Vec.i3.write(byteBuffer, pos);
    }

    @Override
    public int getEstimatedSize() {
        return 12;
    }
}
