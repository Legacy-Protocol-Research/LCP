package net.lcpr.protocol.packet;

import lombok.Getter;
import lombok.Setter;

import java.nio.ByteBuffer;

@Getter
@Setter
public class VotePacket extends Packet {
    private int field_28;
    private byte field_2C;
    private int field_30;
    private int field_34;

    @Override
    public void read(ByteBuffer byteBuffer) {
        field_28 = byteBuffer.getInt();
        field_2C = byteBuffer.get();
        field_30 = byteBuffer.getInt();
        field_34 = byteBuffer.getInt();
    }

    @Override
    public void write(ByteBuffer byteBuffer) {
        byteBuffer.putInt(field_28);
        byteBuffer.put(field_2C);
        byteBuffer.putInt(field_30);
        byteBuffer.putInt(field_34);
    }
}
