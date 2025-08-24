package net.lcpr.protocol.packet.c2s;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;

import java.nio.ByteBuffer;

@Getter
@Setter
public class ResourcePackPacket extends Packet {
    private int packId;

    @Override
    public void read(ByteBuffer byteBuffer) {
        packId = byteBuffer.getInt();
    }

    @Override
    public void write(ByteBuffer byteBuffer) {
        byteBuffer.putInt(packId);
    }
}
