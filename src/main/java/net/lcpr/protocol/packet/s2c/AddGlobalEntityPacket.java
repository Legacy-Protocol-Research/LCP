package net.lcpr.protocol.packet.s2c;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;
import net.lcpr.protocol.utils.Vec;

import java.nio.ByteBuffer;

@Getter
@Setter
public class AddGlobalEntityPacket extends Packet {
    private int id;
    private byte type;
    private Vec.i3 pos;

    @Override
    public void read(ByteBuffer byteBuffer) {
        id = byteBuffer.getInt();
        type = byteBuffer.get();
        pos = Vec.i3.read(byteBuffer);
    }

    @Override
    public void write(ByteBuffer byteBuffer) {
        byteBuffer.putInt(id);
        byteBuffer.put(type);
        Vec.i3.write(byteBuffer, pos);
    }

    @Override
    public int getEstimatedSize() {
        return 17;
    }
}
