package net.lcpr.protocol.packet.s2c;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;
import net.lcpr.protocol.utils.VariableTypes;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

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
    public void read(DataInputStream inputStream) throws IOException {
        dword_28 = inputStream.readInt();
        switch (dword_28) {
            case 0:
                double_40 = inputStream.readDouble();
                break;
            case 1:
                double_48 = inputStream.readDouble();
                double_40 = inputStream.readDouble();
                qword_50 = VariableTypes.readLong(inputStream);
                break;
            case 2:
                double_30 = inputStream.readDouble();
                double_38 = inputStream.readDouble();
                break;
            case 3:
                double_30 = inputStream.readDouble();
                double_38 = inputStream.readDouble();
                double_48 = inputStream.readDouble();
                double_40 = inputStream.readDouble();
                qword_50 = VariableTypes.readLong(inputStream);
                dword_2c = VariableTypes.readInt(inputStream);
                dword_5c = VariableTypes.readInt(inputStream);
                dword_58 = VariableTypes.readInt(inputStream);
                break;
            case 4:
                dword_58 = VariableTypes.readInt(inputStream);
                break;
            case 5:
                dword_5c = VariableTypes.readInt(inputStream);
                break;
        }
    }

    @Override
    public void write(DataOutputStream outputStream) throws IOException {
        outputStream.writeInt(dword_28);
        switch (dword_28) {
            case 0:
                outputStream.writeDouble(double_40);
                break;
            case 1:
                outputStream.writeDouble(double_48);
                outputStream.writeDouble(double_40);
                VariableTypes.writeLong(outputStream, qword_50);
                break;
            case 2:
                outputStream.writeDouble(double_30);
                outputStream.writeDouble(double_38);
                break;
            case 3:
                outputStream.writeDouble(double_30);
                outputStream.writeDouble(double_38);
                outputStream.writeDouble(double_48);
                outputStream.writeDouble(double_40);
                VariableTypes.writeLong(outputStream, qword_50);
                VariableTypes.writeInt(outputStream, dword_2c);
                VariableTypes.writeInt(outputStream, dword_5c);
                VariableTypes.writeInt(outputStream, dword_58);
                break;
            case 4:
                VariableTypes.writeInt(outputStream, dword_58);
                break;
            case 5:
                VariableTypes.writeInt(outputStream, dword_5c);
                break;
        }
    }
}
