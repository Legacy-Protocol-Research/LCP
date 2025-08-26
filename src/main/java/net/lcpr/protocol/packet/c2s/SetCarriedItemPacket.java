package net.lcpr.protocol.packet.c2s;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Broadcasts a change in which hotbar slot the player is currently holding
 *
 * @c2s Tells the server which hotbar slot the player is currently holding
 */
@Getter
@Setter
public class SetCarriedItemPacket extends Packet {
    private short itemId;

    @Override
    public void read(DataInputStream inputStream) throws IOException {
        itemId = inputStream.readShort();
    }

    @Override
    public void write(DataOutputStream outputStream) throws IOException {
        outputStream.writeShort(itemId);
    }
}
