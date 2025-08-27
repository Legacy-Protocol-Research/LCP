package net.lcpr.protocol.packet.c2s;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;
import net.lcpr.protocol.utils.EndianInputStream;
import net.lcpr.protocol.utils.EndianOutputStream;

import java.io.IOException;

/**
 * Broadcasts a chat message to the server
 *
 * @c2s Tells the server about a chat message the client wants to send
 */
@Getter
@Setter
public class ChatPacket extends Packet {
    /**
     * The message sent by the client
     */
    private String message;

    @Override
    public void read(EndianInputStream inputStream) throws IOException {
        message = inputStream.readUTF(256);
    }

    @Override
    public void write(EndianOutputStream outputStream) throws IOException {
        outputStream.writeUTF(message);
    }
}
