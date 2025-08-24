package net.lcpr.protocol.packet.s2c;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;
import net.lcpr.protocol.utils.VariableTypes;

import java.nio.ByteBuffer;

@Getter
@Setter
public class SetExperiencePacket extends Packet {
    private float experienceProgress;
    private int experienceLevel, totalExperience;

    @Override
    public void read(ByteBuffer byteBuffer) {
        experienceProgress = byteBuffer.getFloat();
        totalExperience = VariableTypes.readInt(byteBuffer);
        experienceLevel = VariableTypes.readInt(byteBuffer);
    }

    @Override
    public void write(ByteBuffer byteBuffer) {
        byteBuffer.putFloat(experienceProgress);
        VariableTypes.writeInt(byteBuffer, totalExperience);
        VariableTypes.writeInt(byteBuffer, experienceLevel);
    }
}
