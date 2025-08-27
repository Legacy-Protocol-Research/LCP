package net.lcpr.protocol.packet.s2c;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;
import net.lcpr.protocol.utils.EndianInputStream;
import net.lcpr.protocol.utils.EndianOutputStream;

import java.io.IOException;

@Getter
@Setter
public class AddExperienceOrbPacket extends Packet {
    private int id;
    private int x;
    private int y;
    private int z;
    private int value;

    @Override
    public void read(EndianInputStream inputStream) throws IOException {
        id = inputStream.readInt();
        x = inputStream.readInt();
        y = inputStream.readInt();
        z = inputStream.readInt();
        value = inputStream.readShort();
    }

    @Override
    public void write(EndianOutputStream outputStream) throws IOException {
        outputStream.writeInt(id);
        outputStream.writeInt(x);
        outputStream.writeInt(y);
        outputStream.writeInt(z);
        outputStream.writeShort((short) value);
    }

    @Override
    public int getEstimatedSize() {
        return 18;
    }
}
