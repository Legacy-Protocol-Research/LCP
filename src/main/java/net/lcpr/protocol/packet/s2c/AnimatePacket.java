package net.lcpr.protocol.packet.s2c;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;
import net.lcpr.protocol.utils.EndianInputStream;
import net.lcpr.protocol.utils.EndianOutputStream;

import java.io.IOException;

@Getter
@Setter
public class AnimatePacket extends Packet {
    private int id;
    private byte action;

    @Override
    public void read(EndianInputStream inputStream) throws IOException {
        id = inputStream.readInt();
        action = inputStream.readByte();
    }

    @Override
    public void write(EndianOutputStream outputStream) throws IOException {
        outputStream.writeInt(id);
        outputStream.writeByte(action);
    }

    @Override
    public int getEstimatedSize() {
        return 5;
    }
}
