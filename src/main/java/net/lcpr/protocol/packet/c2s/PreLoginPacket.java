package net.lcpr.protocol.packet.c2s;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;
import net.lcpr.protocol.utils.EndianInputStream;
import net.lcpr.protocol.utils.EndianOutputStream;
import net.lcpr.protocol.utils.PacketType;
import net.lcpr.protocol.utils.Platform;

import java.io.IOException;

/**
 * Sent from the client to initiate login
 *
 * @c2s Tells the server that the client is ready to log in
 */
@Getter
@Setter
public class PreLoginPacket extends Packet {
    private short protocolVersion; // might be something different but the type is correct
    private String name;

    @Override
    public void read(EndianInputStream inputStream) throws IOException {
        protocolVersion = inputStream.readShort();
        name = inputStream.readUTF(0x20);
    }

    @Override
    public void write(EndianOutputStream outputStream, Platform platform) throws IOException {
        outputStream.writeShort(platform.getLatestProtocolVersion());
        outputStream.writeUTF(this.name);
    }

    // No-op, the above would be run instead
    @Override
    public void write(EndianOutputStream outputStream) throws IOException {}

    @Override
    public PacketType getType() {
        return PacketType.ServerboundPreLoginPacket;
    }
}
