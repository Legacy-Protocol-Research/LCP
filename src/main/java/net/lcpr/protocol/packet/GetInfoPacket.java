package net.lcpr.protocol.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class GetInfoPacket extends Packet {
    @Override
    public void read(DataInputStream inputStream) {}

    @Override
    public void write(DataOutputStream outputStream) {}

    @Override
    public int getEstimatedSize() {
        return 0;
    }
}
