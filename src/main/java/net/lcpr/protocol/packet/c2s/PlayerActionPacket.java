package net.lcpr.protocol.packet.c2s;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;
import net.lcpr.protocol.types.Direction;
import net.lcpr.protocol.utils.EndianInputStream;
import net.lcpr.protocol.utils.EndianOutputStream;
import net.lcpr.protocol.utils.Vec;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

@Getter
@Setter
public class PlayerActionPacket extends Packet {
    private Vec.i3 pos;
    private Direction direction;
    private int action;
    private int useTime;

    @Override
    public void read(EndianInputStream inputStream) throws IOException {
        action = inputStream.readInt();
        pos = Vec.i3.read(inputStream);
        direction = Direction.values()[inputStream.readUnsignedByte()];
        useTime = inputStream.readInt();
    }

    @Override
    public void write(EndianOutputStream outputStream) throws IOException {
        outputStream.writeInt(action);
        Vec.i3.write(outputStream, pos);
        outputStream.writeByte(direction.ordinal());
        outputStream.writeInt(useTime);
    }

    @Override
    public int getEstimatedSize() {
        return 11;
    }
}
