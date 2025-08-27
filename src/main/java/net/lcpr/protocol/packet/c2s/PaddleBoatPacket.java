package net.lcpr.protocol.packet.c2s;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;
import net.lcpr.protocol.utils.EndianInputStream;
import net.lcpr.protocol.utils.EndianOutputStream;

import java.io.IOException;

/**
 * Broadcasts when the client paddles a boat to the server
 *
 * @c2s Tells the server about the client paddling a boat
 */
@Getter
@Setter
public class PaddleBoatPacket extends Packet {
    private boolean left;
    private boolean right;

    @Override
    public void read(EndianInputStream inputStream) throws IOException {
        left = inputStream.readBoolean();
        right = inputStream.readBoolean();
    }

    @Override
    public void write(EndianOutputStream outputStream) throws IOException {
        outputStream.writeBoolean(left);
        outputStream.writeBoolean(right);
    }
}
