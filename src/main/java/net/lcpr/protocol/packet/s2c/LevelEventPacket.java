package net.lcpr.protocol.packet.s2c;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.ByteBufferUtils;
import net.lcpr.protocol.packet.Packet;
import net.lcpr.protocol.utils.Vec;

import java.nio.ByteBuffer;

@Getter
@Setter
public class LevelEventPacket extends Packet {
    private int type;
    private int data;
    private Vec.i3 pos;
    private boolean globalEvent;

    @Override
    public void read(ByteBuffer byteBuffer) {
        type = byteBuffer.getInt();
        pos = Vec.i3.read(byteBuffer);
        data = byteBuffer.getInt();
        globalEvent = ByteBufferUtils.getBoolean(byteBuffer);
    }

    @Override
    public void write(ByteBuffer byteBuffer) {
        byteBuffer.putInt(type);
        Vec.i3.write(byteBuffer, pos);
        byteBuffer.putInt(data);
        ByteBufferUtils.putBoolean(byteBuffer, globalEvent);
    }

    @Override
    public int getEstimatedSize() {
        return 21;
    }
}
