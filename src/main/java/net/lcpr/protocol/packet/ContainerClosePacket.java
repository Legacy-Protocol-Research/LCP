package net.lcpr.protocol.packet;

import lombok.Getter;
import lombok.Setter;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

@Getter
@Setter
public class ContainerClosePacket extends Packet {
    private byte containerId;

    @Override
    public void read(DataInputStream inputStream) throws IOException {
        containerId = inputStream.readByte();
    }

    @Override
    public void write(DataOutputStream outputStream) throws IOException {
        outputStream.writeByte(containerId);
    }

    @Override
    public int getEstimatedSize() {
        return 1;
    }
}
