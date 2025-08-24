package net.lcpr.protocol.packet;

import lombok.Getter;
import lombok.Setter;

import java.nio.ByteBuffer;

@Getter
@Setter
public class ContainerClosePacket extends Packet {
    private byte containerId;

    @Override
    public void read(ByteBuffer byteBuffer) {
        containerId = byteBuffer.get();
    }

    @Override
    public void write(ByteBuffer byteBuffer) {
        byteBuffer.put(containerId);
    }

    @Override
    public int getEstimatedSize() {
        return 1;
    }
}
