package net.lcpr.protocol.packet.c2s;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;

import java.nio.ByteBuffer;

@Getter
@Setter
public class ClientCommandPacket extends Packet {
    private int commandId;

    @Override
    public void read(ByteBuffer byteBuffer) {
        commandId = byteBuffer.getInt();
    }

    @Override
    public void write(ByteBuffer byteBuffer) {
        byteBuffer.putInt(commandId);
    }

    @Override
    public int getEstimatedSize() {
        return 1;
    }
}
