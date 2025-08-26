package net.lcpr.protocol.packet;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.utils.EndianInputStream;
import net.lcpr.protocol.utils.EndianOutputStream;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * A bi-directional packet which broadcasts when a container is closed
 *
 * @c2s Tells the server that the client closed the container
 * @s2c Unknown
 */
@Getter
@Setter
public class ContainerClosePacket extends Packet {
    private byte containerId;

    @Override
    public void read(EndianInputStream inputStream) throws IOException {
        containerId = inputStream.readByte();
    }

    @Override
    public void write(EndianOutputStream outputStream) throws IOException {
        outputStream.writeByte(containerId);
    }

    @Override
    public int getEstimatedSize() {
        return 1;
    }
}
