package net.lcpr.protocol.packet;

import lombok.Getter;
import lombok.Setter;

import java.nio.ByteBuffer;

@Getter
@Setter
public class TradeItemPacket extends Packet {
    private int word18;
    private int word1C;

    @Override
    public void read(ByteBuffer byteBuffer) {
        word18 = byteBuffer.getInt();
        word1C = byteBuffer.getInt();
    }

    @Override
    public void write(ByteBuffer byteBuffer) {
        byteBuffer.putInt(word18);
        byteBuffer.putInt(word1C);
    }
}
