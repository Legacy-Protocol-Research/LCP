package net.lcpr.protocol.packet;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.types.DisconnectReason;
import net.lcpr.protocol.utils.EndianInputStream;
import net.lcpr.protocol.utils.EndianOutputStream;

import java.io.IOException;

/**
 * Unknown whether the packet is bi-directional or not
 */
@Getter
@Setter
public class DisconnectPacket extends Packet {
    private DisconnectReason disconnectReason;

    @Override
    public void read(EndianInputStream inputStream) throws IOException {
        disconnectReason = DisconnectReason.fromId(inputStream.readInt());
    }

    @Override
    public void write(EndianOutputStream outputStream) throws IOException {
        outputStream.writeInt(disconnectReason.getId());
    }

    @Override
    public int getEstimatedSize() {
        return 4;
    }
}
