package net.lcpr.protocol.packet;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.utils.Side;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

@Getter
@Setter
public class ContainerAckPacket extends Packet {
    private int containerId;
    private short uid;
    private boolean accepted;

    @Override
    public void read(DataInputStream inputStream) throws IOException {
        containerId = inputStream.readByte();
        uid = inputStream.readShort();
        accepted = inputStream.readBoolean();
    }

    @Override
    public void write(DataOutputStream outputStream) throws IOException {
        outputStream.writeByte(containerId);
        outputStream.writeShort(uid);
        outputStream.writeBoolean(accepted);
    }

    @Override
    public int getEstimatedSize() {
        return this.getOrigin().equals(Side.CLIENTBOUND) ? 4 : super.getEstimatedSize();
    }
}
