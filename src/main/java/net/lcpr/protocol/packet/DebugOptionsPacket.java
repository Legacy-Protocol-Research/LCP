package net.lcpr.protocol.packet;

import lombok.Getter;
import lombok.Setter;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

@Getter
@Setter
public class DebugOptionsPacket extends Packet {
    private int dword18;

    @Override
    public void read(DataInputStream inputStream) throws IOException {
        dword18 = inputStream.readInt();
    }

    @Override
    public void write(DataOutputStream outputStream) throws IOException {
        outputStream.writeInt(dword18);
    }

    @Override
    public int getEstimatedSize() {
        return 4;
    }
}
