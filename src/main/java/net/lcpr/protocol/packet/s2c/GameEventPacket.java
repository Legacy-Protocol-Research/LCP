package net.lcpr.protocol.packet.s2c;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

@Getter
@Setter
public class GameEventPacket extends Packet {
    private int event;
    private float param;
    private int playerIndex;

    @Override
    public void read(DataInputStream inputStream) throws IOException {
        event = inputStream.readUnsignedByte();
        param = inputStream.readFloat();
        playerIndex = inputStream.readInt();
    }

    @Override
    public void write(DataOutputStream outputStream) throws IOException {
        outputStream.writeByte(event);
        outputStream.writeFloat(param);
        outputStream.writeInt(playerIndex);
    }

    @Override
    public int getEstimatedSize() {
        return 3;
    }
}
