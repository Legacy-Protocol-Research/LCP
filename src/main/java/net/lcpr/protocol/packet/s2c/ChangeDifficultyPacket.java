package net.lcpr.protocol.packet.s2c;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;
import net.lcpr.protocol.types.Difficulty;

import java.nio.ByteBuffer;

@Getter
@Setter
public class ChangeDifficultyPacket extends Packet {
    private Difficulty difficulty;

    @Override
    public void read(ByteBuffer byteBuffer) {
        difficulty = Difficulty.values()[byteBuffer.get()];
    }

    @Override
    public void write(ByteBuffer byteBuffer) {
        byteBuffer.put((byte) difficulty.ordinal());
    }
}
