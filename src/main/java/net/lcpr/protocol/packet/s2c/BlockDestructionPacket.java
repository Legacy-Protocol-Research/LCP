package net.lcpr.protocol.packet.s2c;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;
import net.lcpr.protocol.utils.Vec;

import java.nio.ByteBuffer;

@Getter
@Setter
public class BlockDestructionPacket extends Packet {
    private int id;
    private Vec.i3 pos;
    private int progress;

    @Override
    public void read(ByteBuffer byteBuffer) {
        id = byteBuffer.getInt();
        pos = Vec.i3.read(byteBuffer);
        progress = byteBuffer.get() & 0xFF;
    }

    @Override
    public void write(ByteBuffer byteBuffer) {
        byteBuffer.putInt(id);
        Vec.i3.write(byteBuffer, pos);
        byteBuffer.put((byte) progress);
    }

    @Override
    public int getEstimatedSize() {
        return 13;
    }
}
