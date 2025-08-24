package net.lcpr.protocol.packet;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.types.DisconnectReason;

import java.nio.ByteBuffer;

/**
 * DisconnectPacket is a S2C packet which once the client recieves, it will disconnect from the server,
 * showing the relevant disconnect reason provided in the packet.
 */
@Getter
@Setter
public class DisconnectPacket extends Packet {
    private DisconnectReason disconnectReason;

    @Override
    public void read(ByteBuffer byteBuffer) {
        disconnectReason = DisconnectReason.fromId(byteBuffer.getInt());
    }

    @Override
    public void write(ByteBuffer byteBuffer) {
        byteBuffer.putInt(disconnectReason.getId());
    }

    @Override
    public int getEstimatedSize() {
        return 4;
    }
}
