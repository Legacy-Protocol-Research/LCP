package net.lcpr.protocol.packet.s2c;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;
import net.lcpr.protocol.utils.EndianInputStream;
import net.lcpr.protocol.utils.EndianOutputStream;
import net.lcpr.protocol.utils.Vec;

import java.io.IOException;

@Getter
@Setter
public class LevelEventPacket extends Packet {
    private int type;
    private int data;
    private Vec.i3 pos;
    private boolean globalEvent;

    @Override
    public void read(EndianInputStream inputStream) throws IOException {
        type = inputStream.readInt();
        pos = Vec.i3.read(inputStream);
        data = inputStream.readInt();
        globalEvent = inputStream.readBoolean();
    }

    @Override
    public void write(EndianOutputStream outputStream) throws IOException {
        outputStream.writeInt(type);
        Vec.i3.write(outputStream, pos);
        outputStream.writeInt(data);
        outputStream.writeBoolean(globalEvent);
    }

    @Override
    public int getEstimatedSize() {
        return 21;
    }
}
