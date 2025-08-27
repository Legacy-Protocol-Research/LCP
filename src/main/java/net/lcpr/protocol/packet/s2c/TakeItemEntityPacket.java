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
public class TakeItemEntityPacket extends Packet {
    private int playerId, itemId, amount;

    @Override
    public void read(EndianInputStream inputStream) throws IOException {
        playerId = VariableTypes.readInt(inputStream);
        itemId = VariableTypes.readInt(inputStream);
        amount = VariableTypes.readInt(inputStream);
    }

    @Override
    public void write(EndianOutputStream outputStream) throws IOException {
        VariableTypes.writeInt(outputStream, playerId);
        VariableTypes.writeInt(outputStream, itemId);
        VariableTypes.writeInt(outputStream, amount);
    }
}
