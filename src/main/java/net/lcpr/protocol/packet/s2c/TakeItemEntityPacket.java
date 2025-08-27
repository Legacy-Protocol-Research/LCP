package net.lcpr.protocol.packet.s2c;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;
import net.lcpr.protocol.utils.EndianInputStream;
import net.lcpr.protocol.utils.EndianOutputStream;

import java.io.IOException;

@Getter
@Setter
public class TakeItemEntityPacket extends Packet {
    private int playerId, itemId, amount;

    @Override
    public void read(EndianInputStream inputStream) throws IOException {
        playerId = inputStream.readVarInt();
        itemId = inputStream.readVarInt();
        amount = inputStream.readVarInt();
    }

    @Override
    public void write(EndianOutputStream outputStream) throws IOException {
        outputStream.writeVarInt(playerId);
        outputStream.writeVarInt(itemId);
        outputStream.writeVarInt(amount);
    }
}
