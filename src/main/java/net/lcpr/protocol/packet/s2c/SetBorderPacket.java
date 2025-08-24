package net.lcpr.protocol.packet.s2c;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;
import net.lcpr.protocol.utils.VariableTypes;

import java.nio.ByteBuffer;

@Getter
@Setter
public class SetBorderPacket extends Packet {
    private int dword_28;
    private int dword_2c;
    private double double_30;
    private double double_38;
    private double double_40;
    private double double_48;
    private long qword_50;
    private int dword_58;
    private int dword_5c;

    @Override
    public void read(ByteBuffer byteBuffer) {
        dword_28 = byteBuffer.getInt();
        switch (dword_28) {
            case 0:
                double_40 = byteBuffer.getDouble();
                break;
            case 1:
                double_48 = byteBuffer.getDouble();
                double_40 = byteBuffer.getDouble();
                qword_50 = VariableTypes.readLong(byteBuffer);
                break;
            case 2:
                double_30 = byteBuffer.getDouble();
                double_38 = byteBuffer.getDouble();
                break;
            case 3:
                double_30 = byteBuffer.getDouble();
                double_38 = byteBuffer.getDouble();
                double_48 = byteBuffer.getDouble();
                double_40 = byteBuffer.getDouble();
                qword_50 = VariableTypes.readLong(byteBuffer);
                dword_2c = VariableTypes.readInt(byteBuffer);
                dword_5c = VariableTypes.readInt(byteBuffer);
                dword_58 = VariableTypes.readInt(byteBuffer);
                break;
            case 4:
                dword_58 = VariableTypes.readInt(byteBuffer);
                break;
            case 5:
                dword_5c = VariableTypes.readInt(byteBuffer);
                break;
        }
    }

    @Override
    public void write(ByteBuffer byteBuffer) {
        byteBuffer.putInt(dword_28);
        switch (dword_28) {
            case 0:
                byteBuffer.putDouble(double_40);
                break;
            case 1:
                byteBuffer.putDouble(double_48);
                byteBuffer.putDouble(double_40);
                VariableTypes.writeLong(byteBuffer, qword_50);
                break;
            case 2:
                byteBuffer.putDouble(double_30);
                byteBuffer.putDouble(double_38);
                break;
            case 3:
                byteBuffer.putDouble(double_30);
                byteBuffer.putDouble(double_38);
                byteBuffer.putDouble(double_48);
                byteBuffer.putDouble(double_40);
                VariableTypes.writeLong(byteBuffer, qword_50);
                VariableTypes.writeInt(byteBuffer, dword_2c);
                VariableTypes.writeInt(byteBuffer, dword_5c);
                VariableTypes.writeInt(byteBuffer, dword_58);
                break;
            case 4:
                VariableTypes.writeInt(byteBuffer, dword_58);
                break;
            case 5:
                VariableTypes.writeInt(byteBuffer, dword_5c);
                break;
        }
    }
}
