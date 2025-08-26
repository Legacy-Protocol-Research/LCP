package net.lcpr.protocol.packet.s2c;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

@Getter
@Setter
public class KeepAlivePacket extends Packet {
    private int id;

    @Override
    public void read(DataInputStream inputStream) throws IOException {
        id = inputStream.readInt();
    }

    @Override
    public void write(DataOutputStream outputStream) throws IOException {
        outputStream.writeInt(id);
    }

    @Override
    public int getEstimatedSize() {
        return 4;
    }
}
