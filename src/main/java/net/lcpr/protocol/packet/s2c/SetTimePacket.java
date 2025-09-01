package net.lcpr.protocol.packet.s2c;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;
import net.lcpr.protocol.utils.EndianInputStream;
import net.lcpr.protocol.utils.EndianOutputStream;
import net.lcpr.protocol.utils.PacketType;

import java.io.IOException;

/**
 * Broadcasts a time change to a client
 *
 * @s2c Tells the client what time it is
 */
@Getter
@Setter
public class SetTimePacket extends Packet {
    private long gameTime;
    private long dayTime;

    @Override
    public void read(EndianInputStream inputStream) throws IOException {
        gameTime = inputStream.readLong();
        dayTime = inputStream.readLong();
    }

    @Override
    public void write(EndianOutputStream outputStream) throws IOException {
        outputStream.writeLong(gameTime);
        outputStream.writeLong(dayTime);
    }

    @Override
    public PacketType getType() {
        return PacketType.ClientboundSetTimePacket;
    }

    @Override
    public int getEstimatedSize() {
        return 16;
    }

    @Override
    public boolean isAsync() {
        return true;
    }
}
