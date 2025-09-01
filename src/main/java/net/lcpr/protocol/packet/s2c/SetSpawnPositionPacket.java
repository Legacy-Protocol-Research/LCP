package net.lcpr.protocol.packet.s2c;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;
import net.lcpr.protocol.utils.EndianInputStream;
import net.lcpr.protocol.utils.EndianOutputStream;
import net.lcpr.protocol.utils.PacketType;
import net.lcpr.protocol.utils.Vec;

import java.io.IOException;

/**
 * Sets the spawn position of the player
 *
 * @s2c Tells the client about it's new spawn position
 */
@Getter
@Setter
public class SetSpawnPositionPacket extends Packet {
    /**
     * The new spawn position for the player
     */
    private Vec.i3 pos;

    @Override
    public void read(EndianInputStream inputStream) throws IOException {
        pos = Vec.i3.read(inputStream);
    }

    @Override
    public void write(EndianOutputStream outputStream) throws IOException {
        Vec.i3.write(outputStream, pos);
    }

    @Override
    public PacketType getType() {
        return PacketType.ClientboundSetSpawnPositionPacket;
    }

    @Override
    public int getEstimatedSize() {
        return 12;
    }
}
