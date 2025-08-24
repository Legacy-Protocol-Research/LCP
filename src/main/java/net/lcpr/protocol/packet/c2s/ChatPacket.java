package net.lcpr.protocol.packet.c2s;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;

import java.nio.ByteBuffer;

@Getter
@Setter
public class ChatPacket extends Packet {
    /**
     * The message sent by the client
     */
    private String message;

    @Override
    public void read(ByteBuffer byteBuffer) {
        message = this.readUTF(byteBuffer, 256);
    }

    @Override
    public void write(ByteBuffer byteBuffer) {
        writeUTF(byteBuffer, message);
    }
}
