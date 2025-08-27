package net.lcpr.protocol.packet.s2c;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;
import net.lcpr.protocol.utils.EndianInputStream;
import net.lcpr.protocol.utils.EndianOutputStream;

import java.io.IOException;

@Getter
@Setter
public class DamageIndicatorPacket extends Packet {
    private float x;
    private float z;
    private boolean allDirections;

    @Override
    public void read(EndianInputStream inputStream) throws IOException {
        x = inputStream.readFloat();
        z = inputStream.readFloat();
        allDirections = inputStream.readBoolean();
    }

    @Override
    public void write(EndianOutputStream outputStream) throws IOException {
        outputStream.writeFloat(x);
        outputStream.writeFloat(z);
        outputStream.writeBoolean(allDirections);
    }

    @Override
    public int getEstimatedSize() {
        return 9;
    }
}
