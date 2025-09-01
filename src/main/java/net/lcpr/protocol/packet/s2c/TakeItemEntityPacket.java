package net.lcpr.protocol.packet.s2c;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;
import net.lcpr.protocol.utils.EndianInputStream;
import net.lcpr.protocol.utils.EndianOutputStream;

import java.io.IOException;

/**
 * Broadcasts to the client that a player has picked up an item
 *
 * @s2c Tells the client a player picked up an item and how much of said item
 */
@Getter
@Setter
public class TakeItemEntityPacket extends Packet {
    /**
     * The ID of the player who picked up the item
     */
    private int playerId;
    /**
     * The ID of the item which the player picked up
     */
    private int itemId;
    /**
     * How much of the item the player picked up
     */
    private int amount;

    @Override
    public void read(EndianInputStream inputStream) throws IOException {
        playerId = inputStream.readVarInt();
        itemId = inputStream.readVarInt();
        amount = inputStream.readVarInt();
    }

    @Override
    public void write(EndianOutputStream outputStream) throws IOException {
        outputStream.writeVarInt(playerId);
        outputStream.writeVarInt(itemId);
        outputStream.writeVarInt(amount);
    }
}
