package net.lcpr.protocol.packet;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.DataStreamUtils;
import net.lcpr.protocol.utils.EndianInputStream;
import net.lcpr.protocol.utils.EndianOutputStream;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

@Getter
@Setter
public class PlayerReadyPacket extends Packet {
    private long playerId;
    private boolean ready;

    @Override
    public void read(EndianInputStream inputStream) throws IOException {
        playerId = DataStreamUtils.readUnsignedInt(inputStream);
        ready = inputStream.readBoolean();
    }

    @Override
    public void write(EndianOutputStream outputStream) throws IOException {
        DataStreamUtils.writeUnsignedInt(outputStream, playerId);
        outputStream.writeBoolean(ready);
    }

    @Override
    public int getEstimatedSize() {
        return 5;
    }
}
