package net.lcpr.protocol.packet;

import lombok.Getter;
import lombok.Setter;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

@Getter
@Setter
public class UpdateProgressPacket extends Packet {
    private int progress;

    @Override
    public void read(DataInputStream inputStream) throws IOException {
        progress = inputStream.readInt();
    }

    @Override
    public void write(DataOutputStream outputStream) throws IOException {
        outputStream.writeInt(progress);
    }

    @Override
    public int getEstimatedSize() {
        return 1;
    }
}
