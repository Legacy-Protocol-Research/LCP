package net.lcpr.protocol.packet.s2c;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;
import net.lcpr.protocol.utils.EndianInputStream;
import net.lcpr.protocol.utils.EndianOutputStream;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

@Getter
@Setter
public class AwardStatPacket extends Packet {
    private int stat;
    private String data;

    @Override
    public void read(EndianInputStream inputStream) throws IOException {
        stat = inputStream.readInt();

        int size = inputStream.readInt();
        if (size < 1) return;

        byte[] dataArray = new byte[size];

        inputStream.readFully(dataArray);

        char[] characters = new char[size];

        for (int i = 0; i < size; i++) {
            characters[i] = (char) dataArray[i];
        }

        data = new String(characters);
    }

    @Override
    public void write(EndianOutputStream outputStream) throws IOException {
        outputStream.writeInt(stat);
        outputStream.writeInt(data.length());
        if (!data.isEmpty()) {
            outputStream.writeBytes(data);
        }
    }

    @Override
    public int getEstimatedSize() {
        return 6;
    }
}
