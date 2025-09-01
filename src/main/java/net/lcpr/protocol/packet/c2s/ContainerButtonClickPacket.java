package net.lcpr.protocol.packet.c2s;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;
import net.lcpr.protocol.utils.EndianInputStream;
import net.lcpr.protocol.utils.EndianOutputStream;
import net.lcpr.protocol.utils.PacketType;

import java.io.IOException;

@Getter
@Setter
public class ContainerButtonClickPacket extends Packet {
    private int containerId;
    private int buttonId;

    @Override
    public void read(EndianInputStream inputStream) throws IOException {
        containerId = inputStream.readByte();
        buttonId = inputStream.readByte();
    }

    @Override
    public void write(EndianOutputStream outputStream) throws IOException {
        outputStream.writeByte(containerId);
        outputStream.writeByte(buttonId);
    }

    @Override
    public PacketType getType() {
        return PacketType.ServerboundContainerButtonClickPacket;
    }

    @Override
    public int getEstimatedSize() {
        return 2;
    }
}
