package net.lcpr.protocol.packet;

import lombok.Getter;
import lombok.Setter;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

@Getter
@Setter
public class KickPlayerPacket extends Packet {
    private byte byte18;

    @Override
    public void read(DataInputStream inputStream) throws IOException {
        byte18 = inputStream.readByte();
    }

    @Override
    public void write(DataOutputStream outputStream) throws IOException {
        outputStream.writeByte(byte18);
    }

    @Override
    public int getEstimatedSize() {
        return 1;
    }
}
