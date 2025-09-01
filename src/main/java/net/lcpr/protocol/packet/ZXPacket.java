package net.lcpr.protocol.packet;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.utils.EndianInputStream;
import net.lcpr.protocol.utils.EndianOutputStream;

import java.io.IOException;

@Getter
@Setter
public class ZXPacket extends Packet {
    private char field_28;
    private int x, z;

    @Override
    public void read(EndianInputStream inputStream) throws IOException {
        field_28 = inputStream.readChar();
        x = inputStream.readInt();
        z = inputStream.readInt();
    }

    @Override
    public void write(EndianOutputStream outputStream) throws IOException {
        outputStream.writeChar(field_28);
        outputStream.writeInt(x);
        outputStream.writeInt(z);
    }

    @Override
    public int getEstimatedSize() {
        return 10;
    }
}
