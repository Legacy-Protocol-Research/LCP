package net.lcpr.protocol.packet.s2c;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;
import net.lcpr.protocol.utils.VariableTypes;

import java.nio.ByteBuffer;

@Getter
@Setter
public class SetHealthPacket extends Packet {
    private float health;
    private int food;
    private float saturation;
    private int word18;
    private int word1C;
    private int word20;

    @Override
    public void read(ByteBuffer byteBuffer) {
        health = byteBuffer.getFloat();
        food = VariableTypes.readInt(byteBuffer);
        saturation = byteBuffer.getFloat();
        word18 = byteBuffer.get() & 0xFF;
        word1C = VariableTypes.readInt(byteBuffer);
        word20 = VariableTypes.readInt(byteBuffer);
    }

    @Override
    public void write(ByteBuffer byteBuffer) {
        byteBuffer.putFloat(health);
        VariableTypes.writeInt(byteBuffer, food);
        byteBuffer.putFloat(saturation);
        VariableTypes.writeInt(byteBuffer, word18);
        byteBuffer.put((byte) word1C);
        byteBuffer.put((byte) word20);
    }

    @Override
    public int getEstimatedSize() {
        return 16;
    }
}
