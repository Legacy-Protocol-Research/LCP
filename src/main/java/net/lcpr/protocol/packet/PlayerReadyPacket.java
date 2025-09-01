package net.lcpr.protocol.packet;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.utils.EndianInputStream;
import net.lcpr.protocol.utils.EndianOutputStream;
import net.lcpr.protocol.utils.PacketType;

import java.io.IOException;

@Getter
@Setter
public class PlayerReadyPacket extends Packet {
    private long playerId;
    private boolean ready;

    @Override
    public void read(EndianInputStream inputStream) throws IOException {
        playerId = inputStream.readUnsignedInt();
        ready = inputStream.readBoolean();
    }

    @Override
    public void write(EndianOutputStream outputStream) throws IOException {
        outputStream.writeInt((int) playerId);
        outputStream.writeBoolean(ready);
    }

    @Override
    public PacketType getType() {
        return PacketType.PlayerReadyPacket;
    }

    @Override
    public int getEstimatedSize() {
        return 5;
    }
}
