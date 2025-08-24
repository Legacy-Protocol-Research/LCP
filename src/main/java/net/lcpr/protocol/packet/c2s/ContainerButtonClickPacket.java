package net.lcpr.protocol.packet.c2s;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;

import java.nio.ByteBuffer;

@Getter
@Setter
public class ContainerButtonClickPacket extends Packet {
    private int containerId;
    private int buttonId;

    @Override
    public void read(ByteBuffer byteBuffer) {
        containerId = byteBuffer.get();
        buttonId = byteBuffer.get();
    }

    @Override
    public void write(ByteBuffer byteBuffer) {
        byteBuffer.put((byte) containerId);
        byteBuffer.put((byte) buttonId);
    }

    @Override
    public int getEstimatedSize() {
        return 2;
    }
}
