package net.lcpr.protocol.packet.c2s;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;
import net.lcpr.protocol.types.Action;
import net.lcpr.protocol.types.InteractionHand;
import net.lcpr.protocol.utils.EndianInputStream;
import net.lcpr.protocol.utils.EndianOutputStream;
import net.lcpr.protocol.utils.Vec;

import java.io.IOException;

@Getter
@Setter
public class InteractPacket extends Packet {
    private int target;
    private Action action;
    private Vec.f3 location;
    private InteractionHand interactionHand;

    @Override
    public void read(EndianInputStream inputStream) throws IOException {
        target = inputStream.readVarInt();
        action = Action.values()[inputStream.readInt()];
        if (action.equals(Action.INTERACT_AT)) {
            location = Vec.f3.read(inputStream);
        }

        if (action.equals(Action.INTERACT) || action.equals(Action.INTERACT_AT)) {
            interactionHand = InteractionHand.values()[inputStream.readByte()];
        }
    }

    @Override
    public void write(EndianOutputStream outputStream) throws IOException {
        outputStream.writeVarInt(target);
        outputStream.writeInt(action.ordinal());

        if (action.equals(Action.INTERACT_AT)) {
            Vec.f3.write(outputStream, location);
        }

        if (action.equals(Action.INTERACT) || action.equals(Action.INTERACT_AT)) {
            outputStream.writeByte(interactionHand.ordinal());
        }
    }

    @Override
    public int getEstimatedSize() {
        return 9;
    }
}
