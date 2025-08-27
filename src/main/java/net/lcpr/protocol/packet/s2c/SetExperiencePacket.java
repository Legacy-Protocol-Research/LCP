package net.lcpr.protocol.packet.s2c;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;
import net.lcpr.protocol.utils.EndianInputStream;
import net.lcpr.protocol.utils.EndianOutputStream;
import net.lcpr.protocol.utils.VariableTypes;

import java.io.IOException;

@Getter
@Setter
public class SetExperiencePacket extends Packet {
    private float experienceProgress;
    private int experienceLevel, totalExperience;

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
