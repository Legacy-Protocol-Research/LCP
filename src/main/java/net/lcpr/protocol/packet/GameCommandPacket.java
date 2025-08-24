package net.lcpr.protocol.packet;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.types.GameCommand;

import java.nio.ByteBuffer;

@Getter
@Setter
public class GameCommandPacket extends Packet {
    private GameCommand gameCommand;
    private String arguments;
    private int argumentLength;

    @Override
    public void read(ByteBuffer byteBuffer) {
        // TODO
    }

    @Override
    public void write(ByteBuffer byteBuffer) {
        // TODO
    }
}
