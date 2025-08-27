package net.lcpr.protocol.packet.s2c;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;
import net.lcpr.protocol.utils.EndianInputStream;
import net.lcpr.protocol.utils.EndianOutputStream;
import net.lcpr.protocol.utils.VariableTypes;

import java.io.IOException;

/**
 * Broadcasts a change in experience to the client
 *
 * @s2c Tells the client their XP levels
 */
@Getter
@Setter
public class SetExperiencePacket extends Packet {
    /**
     * The progress through the current level
     */
    private float experienceProgress;
    /**
     * The current xp level
     */
    private int experienceLevel;
    /**
     * The total amount of experience points the player has
     */
    private int totalExperience;

    @Override
    public void read(EndianInputStream inputStream) throws IOException {
        experienceProgress = inputStream.readFloat();
        totalExperience = VariableTypes.readInt(inputStream);
        experienceLevel = VariableTypes.readInt(inputStream);
    }

    @Override
    public void write(EndianOutputStream outputStream) throws IOException {
        outputStream.writeFloat(experienceProgress);
        VariableTypes.writeInt(outputStream, totalExperience);
        VariableTypes.writeInt(outputStream, experienceLevel);
    }
}
