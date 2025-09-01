package net.lcpr.protocol.packet.s2c;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;
import net.lcpr.protocol.utils.EndianInputStream;
import net.lcpr.protocol.utils.EndianOutputStream;

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
    public void read(EndianInputStream inputStream) throws IOException {
        dword_28 = inputStream.readInt();
        switch (dword_28) {
            case 0:
                double_40 = inputStream.readDouble();
                break;
            case 1:
                double_48 = inputStream.readDouble();
                double_40 = inputStream.readDouble();
                qword_50 = inputStream.readVarLong();
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
                qword_50 = inputStream.readVarLong();
                dword_2c = inputStream.readVarInt();
                dword_5c = inputStream.readVarInt();
                dword_58 = inputStream.readVarInt();
                break;
            case 4:
                dword_58 = inputStream.readVarInt();
                break;
            case 5:
                dword_5c = inputStream.readVarInt();
                break;
        }
    }

    @Override
    public void write(EndianOutputStream outputStream) throws IOException {
        outputStream.writeInt(dword_28);
        switch (dword_28) {
            case 0:
                outputStream.writeDouble(double_40);
                break;
            case 1:
                outputStream.writeDouble(double_48);
                outputStream.writeDouble(double_40);
                outputStream.writeVarLong(qword_50);
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
                outputStream.writeVarLong(qword_50);
                outputStream.writeVarInt(dword_2c);
                outputStream.writeVarInt(dword_5c);
                outputStream.writeVarInt(dword_58);
                break;
            case 4:
                outputStream.writeVarInt(dword_58);
                break;
            case 5:
                outputStream.writeVarInt(dword_5c);
                break;
        }
    }
}
