package net.lcpr.protocol.packet.s2c;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;
import net.lcpr.protocol.utils.EndianInputStream;
import net.lcpr.protocol.utils.EndianOutputStream;
import net.lcpr.protocol.utils.PacketType;

import java.io.IOException;

@Getter
@Setter
public class PlayerCombatPacket extends Packet {
    private int event;
    private int playerId;
    private int killerId;
    private int duration;
    private String message;

    @Override
    public void read(EndianInputStream inputStream) throws IOException {
        event = inputStream.readInt();

        if (event == 1) {
            duration = inputStream.readVarInt();
            killerId = inputStream.readInt();
            return;
        }

        if (event == 2) {
            playerId = inputStream.readVarInt();
            killerId = inputStream.readInt();
            message = inputStream.readUTF(0x7FFF);
        }
    }

    @Override
    public void write(EndianOutputStream outputStream) throws IOException {
        outputStream.writeInt(event);

        if (event == 1) {
            outputStream.writeVarInt(duration);
            outputStream.writeInt(killerId);
            return;
        }

        if (event == 2) {
            outputStream.writeVarInt(playerId);
            outputStream.writeInt(killerId);
            outputStream.writeUTF(message);
        }
    }

    @Override
    public PacketType getType() {
        return PacketType.ClientboundPlayerCombatPacket;
    }
}
