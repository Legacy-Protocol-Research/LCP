package net.lcpr.protocol.packet.c2s;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;
import net.lcpr.protocol.types.InteractionHand;
import net.lcpr.protocol.utils.EndianInputStream;
import net.lcpr.protocol.utils.EndianOutputStream;
import net.lcpr.protocol.utils.PacketType;

import java.io.IOException;

/**
 * Broadcasts a use of an item (Likely while looking in the air)
 *
 * @c2s Tells the server the client used an item
 */
@Getter
@Setter
public class UseItemPacket extends Packet {
    private InteractionHand interactionHand;

    @Override
    public void read(EndianInputStream inputStream) throws IOException {
        interactionHand = InteractionHand.values()[inputStream.read()];
    }

    @Override
    public void write(EndianOutputStream outputStream) throws IOException {
        outputStream.write(interactionHand.ordinal());
    }

    @Override
    public PacketType getType() {
        return PacketType.ServerboundUseItemPacket;
    }
}
