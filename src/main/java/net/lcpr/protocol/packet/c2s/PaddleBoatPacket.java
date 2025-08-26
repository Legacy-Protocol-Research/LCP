package net.lcpr.protocol.packet.c2s;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

@Getter
@Setter
public class PaddleBoatPacket extends Packet {
    private boolean left;
    private boolean right;

    @Override
    public void read(DataInputStream inputStream) throws IOException {
        left = inputStream.readBoolean();
        right = inputStream.readBoolean();
    }

    @Override
    public void write(DataOutputStream outputStream) throws IOException {
        outputStream.writeBoolean(left);
        outputStream.writeBoolean(right);
    }
}
