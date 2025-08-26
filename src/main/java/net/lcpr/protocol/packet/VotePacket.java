package net.lcpr.protocol.packet;

import lombok.Getter;
import lombok.Setter;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

@Getter
@Setter
public class VotePacket extends Packet {
    private int field_28;
    private byte field_2C;
    private int field_30;
    private int field_34;

    @Override
    public void read(DataInputStream inputStream) throws IOException {
        field_28 = inputStream.readInt();
        field_2C = inputStream.readByte();
        field_30 = inputStream.readInt();
        field_34 = inputStream.readInt();
    }

    @Override
    public void write(DataOutputStream outputStream) throws IOException {
        outputStream.writeInt(field_28);
        outputStream.writeByte(field_2C);
        outputStream.writeInt(field_30);
        outputStream.writeInt(field_34);
    }
}
