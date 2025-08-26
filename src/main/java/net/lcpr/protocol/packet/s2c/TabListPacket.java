package net.lcpr.protocol.packet.s2c;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

@Getter
@Setter
public class TabListPacket extends Packet {
    private String str18;
    private String str38;

    @Override
    public void read(DataInputStream inputStream) throws IOException {
        str18 = this.readUTF(inputStream, 0x7FFF);
        str38 = this.readUTF(inputStream, 0x7FFF);
    }

    @Override
    public void write(DataOutputStream outputStream) throws IOException {
        this.writeUTF(outputStream, str18);
        this.writeUTF(outputStream, str38);
    }
}
