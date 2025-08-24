package net.lcpr.protocol.packet.c2s;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;

import java.nio.ByteBuffer;

@Getter
@Setter
public class SetItemCarriedPacket extends Packet {
    private short itemId;

    @Override
    public void read(ByteBuffer byteBuffer) {
        itemId = byteBuffer.getShort();
    }

    @Override
    public void write(ByteBuffer byteBuffer) {
        byteBuffer.putShort(itemId);
    }
}
