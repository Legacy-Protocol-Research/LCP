package net.lcpr.protocol.packet.s2c;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;
import net.lcpr.protocol.utils.EndianInputStream;
import net.lcpr.protocol.utils.EndianOutputStream;
import net.lcpr.protocol.utils.PacketType;
import net.lcpr.protocol.utils.Vec;

import java.io.IOException;

@Getter
@Setter
public class PlayerSleepPacket extends Packet {
    private int playerId;
    private Vec.i3 pos;

    @Override
    public void read(EndianInputStream inputStream) throws IOException {
        playerId = inputStream.readVarInt();
        pos = Vec.i3.read(inputStream);
    }

    @Override
    public void write(EndianOutputStream outputStream) throws IOException {
        outputStream.writeVarInt(playerId);
        Vec.i3.write(outputStream, pos);
    }

    @Override
    public PacketType getType() {
        return PacketType.ClientboundPlayerSleepPacket;
    }
}
