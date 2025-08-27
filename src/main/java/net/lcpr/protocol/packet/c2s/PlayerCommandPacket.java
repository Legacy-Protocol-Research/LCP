package net.lcpr.protocol.packet.c2s;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;
import net.lcpr.protocol.utils.EndianInputStream;
import net.lcpr.protocol.utils.EndianOutputStream;
import net.lcpr.protocol.utils.VariableTypes;

import java.io.IOException;

@Getter
@Setter
public class PlayerCommandPacket extends Packet {
    private int id;
    private int action;
    private int data;

    @Override
    public void read(EndianInputStream inputStream) throws IOException {
        id = VariableTypes.readInt(inputStream);
        action = inputStream.readInt();
        data = VariableTypes.readInt(inputStream);
    }

    @Override
    public void write(EndianOutputStream outputStream) throws IOException {
        VariableTypes.writeInt(outputStream, id);
        outputStream.writeInt(action);
        VariableTypes.writeInt(outputStream, data);
    }

    @Override
    public int getEstimatedSize() {
        return 9;
    }
}
