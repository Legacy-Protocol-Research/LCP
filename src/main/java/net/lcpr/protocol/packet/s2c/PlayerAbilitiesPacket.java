package net.lcpr.protocol.packet.s2c;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;

import java.nio.ByteBuffer;

@Getter
@Setter
public class PlayerAbilitiesPacket extends Packet {
    private boolean isInvulnerable;
    private boolean isFlying;
    private boolean isEnableFly;
    private boolean isInstabuild;
    private float flyingSpeed;
    private float walkingSpeed;
    private boolean isEnableBuild;
    private int playerId;

    @Override
    public void read(ByteBuffer byteBuffer) {
        byte data = byteBuffer.get();
        isInvulnerable = (data & 1) == 1;
        isFlying = ((data >> 1) & 1) == 1;
        isEnableFly = ((data >> 2) & 1) == 1;
        isInstabuild = ((data >> 3) & 1) == 1;
        isEnableBuild = ((data >> 4) & 1) == 1;
        flyingSpeed = byteBuffer.getFloat();
        walkingSpeed = byteBuffer.getFloat();
        playerId = byteBuffer.getInt();
    }

    @Override
    public void write(ByteBuffer byteBuffer) {
        byte data = 0;
        if (isInvulnerable) data |= 1;
        if (isFlying) data |= 2;
        if (isEnableFly) data |= 4;
        if (isInstabuild) data |= 8;
        if (isEnableBuild) data |= 16;
        byteBuffer.put(data);
        byteBuffer.putFloat(flyingSpeed);
        byteBuffer.putFloat(walkingSpeed);
        byteBuffer.putInt(playerId);
    }
}
