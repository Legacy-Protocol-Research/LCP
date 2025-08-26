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

/**
 * Broadcasts to clients how broken a block currently is
 *
 * @s2c Tells the client how broken a particular block is
 */
@Getter
@Setter
public class BlockDestructionPacket extends Packet {
    private int id;
    private Vec.i3 pos;
    private int progress;

    @Override
    public void read(EndianInputStream inputStream) throws IOException {
        id = inputStream.readInt();
        pos = Vec.i3.read(inputStream);
        progress = inputStream.readUnsignedByte() & 0xFF;
    }

    @Override
    public void write(EndianOutputStream outputStream) throws IOException {
        outputStream.writeInt(id);
        Vec.i3.write(outputStream, pos);
        outputStream.writeByte(progress);
    }

    @Override
    public int getEstimatedSize() {
        return 13;
    }
}
