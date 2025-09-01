package net.lcpr.protocol.packet.s2c;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;
import net.lcpr.protocol.utils.EndianInputStream;
import net.lcpr.protocol.utils.EndianOutputStream;
import net.lcpr.protocol.utils.PacketType;

import java.io.IOException;

/**
 * Broadcasts a powerup effect to the client
 *
 * @s2c Tells the client they have a powerup and for how long
 */
@Getter
@Setter
public class PowerupPacket extends Packet {
    /**
     * The ID of the powerup collected
     */
    private int powerupId;
    /**
     * How long the powerup applies for
     */
    private int powerupTime;

    @Override
    public void read(EndianInputStream inputStream) throws IOException {
        powerupId = inputStream.readInt();
        powerupTime = inputStream.readInt();
    }

    @Override
    public void write(EndianOutputStream outputStream) throws IOException {
        outputStream.writeInt(powerupId);
        outputStream.writeInt(powerupTime);
    }

    @Override
    public PacketType getType() {
        return PacketType.ClientboundPowerupPacket;
    }

    @Override
    public int getEstimatedSize() {
        return 2;
    }
}
