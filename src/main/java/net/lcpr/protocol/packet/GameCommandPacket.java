package net.lcpr.protocol.packet;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.types.GameCommand;
import net.lcpr.protocol.utils.EndianInputStream;
import net.lcpr.protocol.utils.EndianOutputStream;

import java.io.IOException;

@Getter
@Setter
public class GameCommandPacket extends Packet {
    private GameCommand gameCommand;
    private String arguments;
    private int argumentLength;

    @Override
    public void read(EndianInputStream inputStream) throws IOException {
        // TODO
    }

    @Override
    public void write(EndianOutputStream outputStream) throws IOException {
        // TODO
    }
}
