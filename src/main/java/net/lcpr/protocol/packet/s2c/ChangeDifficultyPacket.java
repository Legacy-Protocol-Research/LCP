package net.lcpr.protocol.packet.s2c;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;
import net.lcpr.protocol.types.Difficulty;
import net.lcpr.protocol.utils.EndianInputStream;
import net.lcpr.protocol.utils.EndianOutputStream;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Broadcasts a difficulty change to a client
 *
 * @s2c Tells the client what difficulty the server currently is
 */
@Getter
@Setter
public class ChangeDifficultyPacket extends Packet {
    private Difficulty difficulty;

    @Override
    public void read(EndianInputStream inputStream) throws IOException {
        difficulty = Difficulty.values()[inputStream.readUnsignedByte()];
    }

    @Override
    public void write(EndianOutputStream outputStream) throws IOException {
        outputStream.writeByte(difficulty.ordinal());
    }
}
