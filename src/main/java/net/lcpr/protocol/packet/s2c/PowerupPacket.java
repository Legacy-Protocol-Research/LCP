package net.lcpr.protocol.packet.s2c;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Broadcasts a powerup effect to the client
 *
 * @s2c Tells the client they have a powerup and for how long
 */
@Getter
@Setter
public class PowerupPacket extends Packet {
    private int powerupId;
    private int powerupTime;

    @Override
    public void read(DataInputStream inputStream) throws IOException {
        powerupId = inputStream.readInt();
        powerupTime = inputStream.readInt();
    }

    @Override
    public void write(DataOutputStream outputStream) throws IOException {
        outputStream.writeInt(powerupId);
        outputStream.writeInt(powerupTime);
    }

    @Override
    public int getEstimatedSize() {
        return 2;
    }
}
