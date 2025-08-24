package net.lcpr.protocol.packet.c2s;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;
import net.lcpr.protocol.types.Action;
import net.lcpr.protocol.types.InteractionHand;
import net.lcpr.protocol.utils.VariableTypes;
import net.lcpr.protocol.utils.Vec;

import java.nio.ByteBuffer;

@Getter
@Setter
public class InteractPacket extends Packet {
    private int target;
    private Action action;
    private Vec.f3 location;
    private InteractionHand interactionHand;

    @Override
    public void read(ByteBuffer byteBuffer) {
        target = VariableTypes.readInt(byteBuffer);
        action = Action.values()[byteBuffer.getInt()];
        if (action.equals(Action.INTERACT_AT)) {
            location = Vec.f3.read(byteBuffer);
        }

        if (action.equals(Action.INTERACT) || action.equals(Action.INTERACT_AT)) {
            interactionHand = InteractionHand.values()[byteBuffer.get()];
        }
    }

    @Override
    public void write(ByteBuffer byteBuffer) {
        VariableTypes.writeInt(byteBuffer, target);
        byteBuffer.putInt(action.ordinal());

        if (action.equals(Action.INTERACT_AT)) {
            Vec.f3.write(byteBuffer, location);
        }

        if (action.equals(Action.INTERACT) || action.equals(Action.INTERACT_AT)) {
            byteBuffer.put((byte) interactionHand.ordinal());
        }
    }

    @Override
    public int getEstimatedSize() {
        return 9;
    }
}
