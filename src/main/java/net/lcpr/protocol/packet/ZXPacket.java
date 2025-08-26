package net.lcpr.protocol.packet;

import lombok.Getter;
import lombok.Setter;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

@Getter
@Setter
public class ZXPacket extends Packet {
    private char field_28;
    private int x, z;

    @Override
    public void read(DataInputStream inputStream) throws IOException {
        field_28 = inputStream.readChar();
        x = inputStream.readInt();
        z = inputStream.readInt();
    }

    @Override
    public void write(DataOutputStream outputStream) throws IOException {
        outputStream.writeChar(field_28);
        outputStream.writeInt(x);
        outputStream.writeInt(z);
    }

    @Override
    public int getEstimatedSize() {
        return 10;
    }
}
