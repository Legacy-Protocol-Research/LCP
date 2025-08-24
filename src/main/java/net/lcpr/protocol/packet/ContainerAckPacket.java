package net.lcpr.protocol.packet;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.ByteBufferUtils;
import net.lcpr.protocol.utils.Side;

import java.nio.ByteBuffer;

@Getter
@Setter
public class ContainerAckPacket extends Packet {
    private int containerId;
    private short uid;
    private boolean accepted;

    @Override
    public void read(ByteBuffer byteBuffer) {
        containerId = byteBuffer.get();
        uid = byteBuffer.getShort();
        accepted = ByteBufferUtils.getBoolean(byteBuffer);
    }

    @Override
    public void write(ByteBuffer byteBuffer) {
        byteBuffer.put((byte) containerId);
        byteBuffer.putShort(uid);
        ByteBufferUtils.putBoolean(byteBuffer, accepted);
    }

    @Override
    public int getEstimatedSize() {
        return this.getOrigin().equals(Side.CLIENTBOUND) ? 4 : super.getEstimatedSize();
    }
}
