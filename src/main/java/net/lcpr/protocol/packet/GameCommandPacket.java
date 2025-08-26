package net.lcpr.protocol.packet;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.types.GameCommand;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

@Getter
@Setter
public class GameCommandPacket extends Packet {
    private GameCommand gameCommand;
    private String arguments;
    private int argumentLength;

    @Override
    public void read(DataInputStream inputStream) throws IOException {
        // TODO
    }

    @Override
    public void write(DataOutputStream outputStream) throws IOException {
        // TODO
    }
}
