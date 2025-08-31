package net.lcpr.protocol.types;

import net.lcpr.protocol.packet.Packet;
import net.lcpr.protocol.utils.EndianInputStream;
import net.lcpr.protocol.utils.EndianOutputStream;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Packet base that holds a packed header which determines what to read
 * <p>
 * More info + bit layout of the packed int can be found <a href="https://Team-Lodestone.github.io/Documentation/LCE/Packets/Types/Flexible%20Packet">here</a>
 */
public class FlexiblePacket extends Packet {
    @Override
    public void read(EndianInputStream inputStream) throws IOException {
        int packed = inputStream.readInt();

        int strings = (packed & 0x03) + ((packed & 0xFC) >> 2) * 4;
        int varints = (packed >> 8) & 0xFFF;
        int bools = (packed >> 20) & 0xFF;
        int floats = (packed >> 28) & 0x0F;

        for (int i = 0; i < strings; i++) {
            this.strings.add(inputStream.readUTF(0x7B));
        }

        for (int i = 0; i < varints; i++) {
            // TODO: implement varint
            // varints.add(inputStream.readVarint());
        }

        // might actually need to be is bigger than 0 but whatever
        if (bools != 0) {
            byte bb = inputStream.readByte();
            for (int i = 0; i < bools; i++) {
                booleans.add((bb & (1 << i)) != 0);
            }
        }

        for (int i = 0; i < floats; i++) {
            this.floats.add(inputStream.readFloat());
        }
    }

    @Override
    public void write(EndianOutputStream outputStream) throws IOException {
        int p = (strings.size() & 0xFF)
                | (varints.size() & 0xFFF) << 8
                | (booleans.size() & 0xFF) << 20
                | floats.size() << 28;

        outputStream.writeInt(p);

        for (String string : strings) outputStream.writeUTF(string);

        // TODO: varint
        // for (Integer varint : varints) outputStream.writeVarint(varint);

        if (!booleans.isEmpty()) {
            char b = 0;
            for (char i = 0; i < booleans.size(); i++) {
                if (booleans.get(i) != false) b |= (char) (1 << i);
            }

            outputStream.writeByte(b);
        }

        for (Float flt : floats) outputStream.writeFloat(flt);
    }

    ArrayList<String> strings;
    ArrayList<Integer> varints;
    ArrayList<Boolean> booleans;
    ArrayList<Float> floats;
}
