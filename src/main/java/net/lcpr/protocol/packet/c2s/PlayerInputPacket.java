package net.lcpr.protocol.packet.c2s;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;
import net.lcpr.protocol.utils.EndianInputStream;
import net.lcpr.protocol.utils.EndianOutputStream;

import java.io.IOException;

@Getter
@Setter
public class PlayerInputPacket extends Packet {
    private float xxa; // TODO find out what these are
    private float zza; // TODO find out what these are
    private boolean isJumping;
    private boolean isSneaking;

    @Override
    public void read(EndianInputStream inputStream) throws IOException {
        xxa = inputStream.readFloat();
        zza = inputStream.readFloat();
        byte data = inputStream.readByte();
        isJumping = data % 2 == 1;
        isSneaking = data >= 2;
    }

    @Override
    public void write(EndianOutputStream outputStream) throws IOException {
        outputStream.writeFloat(xxa);
        outputStream.writeFloat(zza);

        byte out = 0;
        if (isJumping) out += 1;
        if (isSneaking) out += 2;

        outputStream.writeByte(out);
    }
}
