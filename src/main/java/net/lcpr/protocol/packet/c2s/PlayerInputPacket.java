package net.lcpr.protocol.packet.c2s;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;

import java.nio.ByteBuffer;

@Getter
@Setter
public class PlayerInputPacket extends Packet {
    private float xxa;
    private float zza;
    private boolean isJumping;
    private boolean isSneaking;

    @Override
    public void read(ByteBuffer byteBuffer) {
        xxa = byteBuffer.getFloat();
        zza = byteBuffer.getFloat();
        byte data = byteBuffer.get();
        isJumping = data % 2 == 1;
        isSneaking = data >= 2;
    }

    @Override
    public void write(ByteBuffer byteBuffer) {
        byteBuffer.putFloat(xxa);
        byteBuffer.putFloat(zza);

        byte out = 0;
        if (isJumping) out += 1;
        if (isSneaking) out += 2;

        byteBuffer.put(out);
    }
}
