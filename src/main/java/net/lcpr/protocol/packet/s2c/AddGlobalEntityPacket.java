package net.lcpr.protocol.packet.s2c;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;
import net.lcpr.protocol.utils.EndianInputStream;
import net.lcpr.protocol.utils.EndianOutputStream;
import net.lcpr.protocol.utils.Vec;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

@Getter
@Setter
public class AddGlobalEntityPacket extends Packet {
    private int id;
    private byte type;
    private Vec.i3 pos;

    @Override
    public void read(EndianInputStream inputStream) throws IOException {
        id = inputStream.readInt();
        type = inputStream.readByte();
        pos = Vec.i3.read(inputStream);
    }

    @Override
    public void write(EndianOutputStream outputStream) throws IOException {
        outputStream.writeInt(id);
        outputStream.writeByte(type);
        Vec.i3.write(outputStream, pos);
    }

    @Override
    public int getEstimatedSize() {
        return 17;
    }
}
