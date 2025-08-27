package net.lcpr.protocol.packet.s2c;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;
import net.lcpr.protocol.utils.EndianInputStream;
import net.lcpr.protocol.utils.EndianOutputStream;
import net.lcpr.protocol.utils.VariableTypes;

import java.io.IOException;

/**
 * Broadcasts a cooldown to a certain item for a certain period of time
 *
 * @s2c Tells the client a certain item in on cooldown and for how long
 */
@Getter
@Setter
public class CooldownPacket extends Packet {
    private int itemId;
    private int cooldown;

    @Override
    public void read(EndianInputStream inputStream) throws IOException {
        itemId = VariableTypes.readInt(inputStream);
        cooldown = VariableTypes.readInt(inputStream);
    }

    @Override
    public void write(EndianOutputStream outputStream) throws IOException {
        VariableTypes.writeInt(outputStream, itemId);
        VariableTypes.writeInt(outputStream, cooldown);
    }
}
