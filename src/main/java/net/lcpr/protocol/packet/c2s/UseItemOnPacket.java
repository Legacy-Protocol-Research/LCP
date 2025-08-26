package net.lcpr.protocol.packet.c2s;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;
import net.lcpr.protocol.types.Direction;
import net.lcpr.protocol.types.InteractionHand;
import net.lcpr.protocol.utils.EndianInputStream;
import net.lcpr.protocol.utils.EndianOutputStream;
import net.lcpr.protocol.utils.Vec;

import java.io.IOException;

/**
 * Broadcasts an item use on a certain position to the server
 *
 * @c2s Tells the server the client used an item on a certain position (Where the crosshair is)
 */
@Getter
@Setter
public class UseItemOnPacket extends Packet {
    private Vec.i3 pos;
    private Direction direction;
    private InteractionHand interactionHand;
    private Vec.f3 click;

    @Override
    public void read(EndianInputStream inputStream) throws IOException {
        pos = Vec.i3.read(inputStream);
        direction = Direction.values()[inputStream.readByte()];
        interactionHand = InteractionHand.values()[inputStream.readByte()];
        click = Vec.f3.read(inputStream);
    }

    @Override
    public void write(EndianOutputStream outputStream) throws IOException {
        Vec.i3.write(outputStream, pos);
        outputStream.writeByte(direction.ordinal());
        outputStream.writeByte(interactionHand.ordinal());
        Vec.f3.write(outputStream, click);
    }
}
