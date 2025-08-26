package net.lcpr.protocol.packet.s2c;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;
import net.lcpr.protocol.utils.Vec;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

@Getter
@Setter
public class BlockDestructionPacket extends Packet {
    private int id;
    private Vec.i3 pos;
    private int progress;

    @Override
    public void read(DataInputStream inputStream) throws IOException {
        id = inputStream.readInt();
        pos = Vec.i3.read(inputStream);
        progress = inputStream.readUnsignedByte() & 0xFF;
    }

    @Override
    public void write(DataOutputStream outputStream) throws IOException {
        outputStream.writeInt(id);
        Vec.i3.write(outputStream, pos);
        outputStream.writeByte(progress);
    }

    @Override
    public int getEstimatedSize() {
        return 13;
    }
}
