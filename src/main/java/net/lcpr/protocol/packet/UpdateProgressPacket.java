package net.lcpr.protocol.packet;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.utils.EndianInputStream;
import net.lcpr.protocol.utils.EndianOutputStream;

import java.io.IOException;

@Getter
@Setter
public class UpdateProgressPacket extends Packet {
    private int progress;

    @Override
    public void read(EndianInputStream inputStream) throws IOException {
        progress = inputStream.readInt();
    }

    @Override
    public void write(EndianOutputStream outputStream) throws IOException {
        outputStream.writeInt(progress);
    }

    @Override
    public int getEstimatedSize() {
        return 1;
    }
}
