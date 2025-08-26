package net.lcpr.protocol.packet.c2s;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

@Getter
@Setter
public class ResourcePackPacket extends Packet {
    private int packId;

    @Override
    public void read(DataInputStream inputStream) throws IOException {
        packId = inputStream.readInt();
    }

    @Override
    public void write(DataOutputStream outputStream) throws IOException {
        outputStream.writeInt(packId);
    }
}
