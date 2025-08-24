package net.lcpr.protocol.packet.c2s;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;
import net.lcpr.protocol.types.InteractionHand;

import java.nio.ByteBuffer;

@Getter
@Setter
public class SwingPacket extends Packet {
    private InteractionHand interactionHand;

    @Override
    public void read(ByteBuffer byteBuffer) {
        interactionHand = InteractionHand.values()[byteBuffer.get()];
    }

    @Override
    public void write(ByteBuffer byteBuffer) {
        byteBuffer.put((byte) interactionHand.ordinal());
    }
}
