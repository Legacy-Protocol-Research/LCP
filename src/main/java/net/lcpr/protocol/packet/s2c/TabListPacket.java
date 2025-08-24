package net.lcpr.protocol.packet.s2c;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;

import java.nio.ByteBuffer;

@Getter
@Setter
public class TabListPacket extends Packet {
    private String str18;
    private String str38;

    @Override
    public void read(ByteBuffer byteBuffer) {
        str18 = this.readUTF(byteBuffer, 0x7FFF);
        str38 = this.readUTF(byteBuffer, 0x7FFF);
    }

    @Override
    public void write(ByteBuffer byteBuffer) {
        this.writeUTF(byteBuffer, str18);
        this.writeUTF(byteBuffer, str38);
    }
}
