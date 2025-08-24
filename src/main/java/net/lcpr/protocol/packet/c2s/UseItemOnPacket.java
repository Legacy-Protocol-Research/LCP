package net.lcpr.protocol.packet.c2s;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;
import net.lcpr.protocol.types.Direction;
import net.lcpr.protocol.types.InteractionHand;
import net.lcpr.protocol.utils.Vec;

import java.nio.ByteBuffer;

@Getter
@Setter
public class UseItemOnPacket extends Packet {
    private Vec.i3 pos;
    private Direction direction;
    private InteractionHand interactionHand;
    private Vec.f3 click;

    @Override
    public void read(ByteBuffer byteBuffer) {
        pos = Vec.i3.read(byteBuffer);
        direction = Direction.values()[byteBuffer.get()];
        interactionHand = InteractionHand.values()[byteBuffer.get()];
        click = Vec.f3.read(byteBuffer);
    }

    @Override
    public void write(ByteBuffer byteBuffer) {
        Vec.i3.write(byteBuffer, pos);
        byteBuffer.put((byte) direction.ordinal());
        byteBuffer.put((byte) interactionHand.ordinal());
        Vec.f3.write(byteBuffer, click);
    }
}
