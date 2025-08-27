package net.lcpr.protocol.packet.s2c;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;
import net.lcpr.protocol.utils.EndianInputStream;
import net.lcpr.protocol.utils.EndianOutputStream;

import java.io.IOException;

/**
 * Broadcasts a change in a player's abilities
 *
 * @s2c Tells the client about a player's abilities
 */
@Getter
@Setter
public class PlayerAbilitiesPacket extends Packet {
    /**
     * Whether the player can take damage or not
     */
    private boolean isInvulnerable;
    /**
     * Whether the player is flying or not
     */
    private boolean isFlying;
    /**
     * Whether the player can fly or not
     */
    private boolean isEnableFly;
    /**
     * Whether the player can instabuild or not
     */
    private boolean isInstabuild;
    /**
     * The flying speed of the player
     */
    private float flyingSpeed;
    /**
     * The walking speed of the player
     */
    private float walkingSpeed;
    /**
     * Whether the playuer can build or not
     */
    private boolean isEnableBuild;
    /**
     * The ID of the player
     */
    private int playerId;

    @Override
    public void read(EndianInputStream inputStream) throws IOException {
        byte data = inputStream.readByte();
        isInvulnerable = (data & 1) == 1;
        isFlying = ((data >> 1) & 1) == 1;
        isEnableFly = ((data >> 2) & 1) == 1;
        isInstabuild = ((data >> 3) & 1) == 1;
        isEnableBuild = ((data >> 4) & 1) == 1;
        flyingSpeed = inputStream.readFloat();
        walkingSpeed = inputStream.readFloat();
        playerId = inputStream.readInt();
    }

    @Override
    public void write(EndianOutputStream outputStream) throws IOException {
        int data = 0;
        if (isInvulnerable) data |= 1;
        if (isFlying) data |= 2;
        if (isEnableFly) data |= 4;
        if (isInstabuild) data |= 8;
        if (isEnableBuild) data |= 16;
        outputStream.writeByte(data);
        outputStream.writeFloat(flyingSpeed);
        outputStream.writeFloat(walkingSpeed);
        outputStream.writeInt(playerId);
    }
}
