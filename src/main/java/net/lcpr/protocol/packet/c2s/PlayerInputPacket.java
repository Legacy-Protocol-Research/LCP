package net.lcpr.protocol.packet.c2s;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;
import net.lcpr.protocol.utils.EndianInputStream;
import net.lcpr.protocol.utils.EndianOutputStream;
import net.lcpr.protocol.utils.PacketType;

import java.io.IOException;

/**
 * Sends the current input of the player to the server
 *
 * @c2s Tells the server about the client's inputs currently
 */
@Getter
@Setter
public class PlayerInputPacket extends Packet {
    private float xxa; // TODO find out what these are
    private float zza; // TODO find out what these are
    /**
     * Whether the player is jumping
     */
    private boolean isJumping;
    /**
     * Whether the player is sneaking
     */
    private boolean isSneaking;

    @Override
    public void read(EndianInputStream inputStream) throws IOException {
        xxa = inputStream.readFloat();
        zza = inputStream.readFloat();
        byte data = inputStream.readByte();
        isJumping = data % 2 == 1;
        isSneaking = data >= 2;
    }

    @Override
    public void write(EndianOutputStream outputStream) throws IOException {
        outputStream.writeFloat(xxa);
        outputStream.writeFloat(zza);

        byte out = 0;
        if (isJumping) out += 1;
        if (isSneaking) out += 2;

        outputStream.writeByte(out);
    }

    @Override
    public PacketType getType() {
        return PacketType.ServerboundPlayerInputPacket;
    }
}
