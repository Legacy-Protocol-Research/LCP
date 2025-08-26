package net.lcpr.protocol.packet.c2s;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

@Getter
@Setter
public class ClientCommandPacket extends Packet {
    private int commandId;

    @Override
    public void read(DataInputStream inputStream) throws IOException {
        commandId = inputStream.readInt();
    }

    @Override
    public void write(DataOutputStream outputStream) throws IOException {
        outputStream.writeInt(commandId);
    }

    @Override
    public int getEstimatedSize() {
        return 1;
    }
}
