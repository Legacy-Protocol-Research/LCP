package net.lcpr.protocol.packet.s2c;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;
import net.lcpr.protocol.utils.EndianInputStream;
import net.lcpr.protocol.utils.EndianOutputStream;
import net.lcpr.protocol.utils.VariableTypes;

@Getter
@Setter
public class SetCameraPacket extends Packet {
    private int id;

    @Override
    public void read(EndianInputStream inputStream) {
        id = VariableTypes.readInt(inputStream);
    }

    @Override
    public void write(EndianOutputStream outputStream) {
        VariableTypes.writeInt(outputStream, id);
    }
}
