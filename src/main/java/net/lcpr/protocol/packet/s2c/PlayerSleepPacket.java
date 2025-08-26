package net.lcpr.protocol.packet.s2c;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;
import net.lcpr.protocol.utils.VariableTypes;
import net.lcpr.protocol.utils.Vec;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

@Getter
@Setter
public class PlayerSleepPacket extends Packet {
    private int playerId;
    private Vec.i3 pos;

    @Override
    public void read(DataInputStream inputStream) throws IOException {
        playerId = VariableTypes.readInt(inputStream);
        pos = Vec.i3.read(inputStream);
    }

    @Override
    public void write(DataOutputStream outputStream) throws IOException {
        VariableTypes.writeInt(outputStream, playerId);
        Vec.i3.write(outputStream, pos);
    }
}
