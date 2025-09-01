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
public class GameEventPacket extends Packet {
    private int event;
    private float param;
    private int playerIndex;

    @Override
    public void read(EndianInputStream inputStream) throws IOException {
        event = inputStream.readUnsignedByte();
        param = inputStream.readFloat();
        playerIndex = inputStream.readInt();
    }

    @Override
    public void write(EndianOutputStream outputStream) throws IOException {
        outputStream.writeByte(event);
        outputStream.writeFloat(param);
        outputStream.writeInt(playerIndex);
    }

    @Override
    public PacketType getType() {
        return PacketType.ClientboundGameEventPacket;
    }

    @Override
    public int getEstimatedSize() {
        return 3;
    }
}
