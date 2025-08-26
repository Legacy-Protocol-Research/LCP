package net.lcpr.protocol.packet;

import lombok.Getter;
import lombok.Setter;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

@Getter
@Setter
public class TradeItemPacket extends Packet {
    private int dword18;
    private int dword1C;

    @Override
    public void read(DataInputStream inputStream) throws IOException {
        dword18 = inputStream.readInt();
        dword1C = inputStream.readInt();
    }

    @Override
    public void write(DataOutputStream outputStream) throws IOException {
        outputStream.writeInt(dword18);
        outputStream.writeInt(dword1C);
    }
}
