package net.lcpr.protocol.packet.s2c;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

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
