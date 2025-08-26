package net.lcpr.protocol.packet.c2s;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

@Getter
@Setter
public class ChatPacket extends Packet {
    /**
     * The message sent by the client
     */
    private String message;

    @Override
    public void read(DataInputStream inputStream) throws IOException {
        message = this.readUTF(inputStream, 256);
    }

    @Override
    public void write(DataOutputStream outputStream) throws IOException {
        this.writeUTF(outputStream, message);
    }
}
