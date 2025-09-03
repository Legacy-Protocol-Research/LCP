package net.lcpr.protocol.packet.s2c;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;
import net.lcpr.protocol.utils.EndianInputStream;
import net.lcpr.protocol.utils.EndianOutputStream;
import net.lcpr.protocol.utils.PacketType;

import java.io.IOException;

@Getter
@Setter
public class SetBorderPacket extends Packet {
    // These field names are assumed! They come from MCP 1.12.2, they are likely correct however.
    private int action;
    private int size;
    private double centerX;
    private double centerZ;
    private double targetSize;
    private double diameter;
    private long shrinkTime;
    private int warningTime;
    private int warningDistance;

    @Override
    public void read(EndianInputStream inputStream) throws IOException {
        action = inputStream.readInt();
        switch (action) {
            case 0:
                targetSize = inputStream.readDouble();
                break;
            case 1:
                diameter = inputStream.readDouble();
                targetSize = inputStream.readDouble();
                shrinkTime = inputStream.readVarLong();
                break;
            case 2:
                centerX = inputStream.readDouble();
                centerZ = inputStream.readDouble();
                break;
            case 3:
                centerX = inputStream.readDouble();
                centerZ = inputStream.readDouble();
                diameter = inputStream.readDouble();
                targetSize = inputStream.readDouble();
                shrinkTime = inputStream.readVarLong();
                size = inputStream.readVarInt();
                warningDistance = inputStream.readVarInt();
                warningTime = inputStream.readVarInt();
                break;
            case 4:
                warningTime = inputStream.readVarInt();
                break;
            case 5:
                warningDistance = inputStream.readVarInt();
                break;
        }
    }

    @Override
    public void write(EndianOutputStream outputStream) throws IOException {
        outputStream.writeInt(action);
        switch (action) {
            case 0:
                outputStream.writeDouble(targetSize);
                break;
            case 1:
                outputStream.writeDouble(diameter);
                outputStream.writeDouble(targetSize);
                outputStream.writeVarLong(shrinkTime);
                break;
            case 2:
                outputStream.writeDouble(centerX);
                outputStream.writeDouble(centerZ);
                break;
            case 3:
                outputStream.writeDouble(centerX);
                outputStream.writeDouble(centerZ);
                outputStream.writeDouble(diameter);
                outputStream.writeDouble(targetSize);
                outputStream.writeVarLong(shrinkTime);
                outputStream.writeVarInt(size);
                outputStream.writeVarInt(warningDistance);
                outputStream.writeVarInt(warningTime);
                break;
            case 4:
                outputStream.writeVarInt(warningTime);
                break;
            case 5:
                outputStream.writeVarInt(warningDistance);
                break;
        }
    }

    @Override
    public PacketType getType() {
        return PacketType.ClientboundSetBorderPacket;
    }
}
