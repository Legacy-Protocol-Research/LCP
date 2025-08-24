package net.lcpr.protocol.packet.s2c;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;
import net.lcpr.protocol.utils.VariableTypes;

import java.nio.ByteBuffer;

@Getter
@Setter
public class PlayerCombatPacket extends Packet {
    private int event;
    private int playerId;
    private int killerId;
    private int duration;
    private String message;

    @Override
    public void read(ByteBuffer byteBuffer) {
        event = byteBuffer.getInt();

        if (event == 1) {
            duration = VariableTypes.readInt(byteBuffer);
            killerId = byteBuffer.getInt();
            return;
        }

        if (event == 2) {
            playerId = VariableTypes.readInt(byteBuffer);
            killerId = byteBuffer.getInt();
            message = this.readUTF(byteBuffer, 0x7FFF);
        }
    }

    @Override
    public void write(ByteBuffer byteBuffer) {
        byteBuffer.putInt(event);

        if (event == 1) {
            VariableTypes.writeInt(byteBuffer, duration);
            byteBuffer.putInt(killerId);
            return;
        }

        if (event == 2) {
            VariableTypes.writeInt(byteBuffer, playerId);
            byteBuffer.putInt(killerId);
            this.writeUTF(byteBuffer, message);
        }
    }
}
