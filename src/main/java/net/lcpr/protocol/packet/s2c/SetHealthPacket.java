package net.lcpr.protocol.packet.s2c;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;
import net.lcpr.protocol.utils.EndianInputStream;
import net.lcpr.protocol.utils.EndianOutputStream;
import net.lcpr.protocol.utils.PacketType;

import java.io.IOException;

/**
 * Broadcasts a change in the player's stats (Health, food and saturation)
 *
 * @s2c Tells the client it's health, food level and saturation
 */
@Getter
@Setter
public class SetHealthPacket extends Packet {
    /**
     * The health level of the client
     */
    private float health;
    /**
     * The food level of the client
     */
    private int food;
    /**
     * The saturation level of the client
     */
    private float saturation;
    private int dword18;
    private int dword1C;
    private int dword20;

    @Override
    public void read(EndianInputStream inputStream) throws IOException {
        health = inputStream.readFloat();
        food = inputStream.readVarInt();
        saturation = inputStream.readFloat();
        dword18 = inputStream.readByte() & 0xFF;
        dword1C = inputStream.readVarInt();
        dword20 = inputStream.readVarInt();
    }

    @Override
    public void write(EndianOutputStream outputStream) throws IOException {
        outputStream.writeFloat(health);
        outputStream.writeVarInt(food);
        outputStream.writeFloat(saturation);
        outputStream.writeByte(dword18);
        outputStream.writeVarInt(dword1C);
        outputStream.writeVarInt(dword20);
    }

    @Override
    public PacketType getType() {
        return PacketType.ClientboundSetHealthPacket;
    }

    @Override
    public int getEstimatedSize() {
        return 16;
    }
}
