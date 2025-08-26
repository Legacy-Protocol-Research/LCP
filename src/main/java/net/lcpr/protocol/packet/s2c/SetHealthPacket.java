package net.lcpr.protocol.packet.s2c;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;
import net.lcpr.protocol.utils.EndianInputStream;
import net.lcpr.protocol.utils.EndianOutputStream;
import net.lcpr.protocol.utils.VariableTypes;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

@Getter
@Setter
public class SetHealthPacket extends Packet {
    private float health;
    private int food;
    private float saturation;
    private int dword18;
    private int dword1C;
    private int dword20;

    @Override
    public void read(EndianInputStream inputStream) throws IOException {
        health = inputStream.readFloat();
        food = VariableTypes.readInt(inputStream);
        saturation = inputStream.readFloat();
        dword18 = inputStream.readByte() & 0xFF;
        dword1C = VariableTypes.readInt(inputStream);
        dword20 = VariableTypes.readInt(inputStream);
    }

    @Override
    public void write(EndianOutputStream outputStream) throws IOException {
        outputStream.writeFloat(health);
        VariableTypes.writeInt(outputStream, food);
        outputStream.writeFloat(saturation);
        outputStream.writeByte(dword18);
        VariableTypes.writeInt(outputStream, dword1C);
        VariableTypes.writeInt(outputStream, dword20);
    }

    @Override
    public int getEstimatedSize() {
        return 16;
    }
}
