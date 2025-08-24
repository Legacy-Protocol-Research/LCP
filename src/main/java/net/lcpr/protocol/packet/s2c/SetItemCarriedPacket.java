package net.lcpr.protocol.packet.s2c;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;

import java.nio.ByteBuffer;

@Getter
@Setter
public class SetItemCarriedPacket extends Packet {
    private byte itemId;

    @Override
    public void read(ByteBuffer byteBuffer) {
        itemId = byteBuffer.get();
    }

    @Override
    public void write(ByteBuffer byteBuffer) {
        byteBuffer.put(itemId);
    }

    @Override
    public int getEstimatedSize() {
        return 2;
    }
}
