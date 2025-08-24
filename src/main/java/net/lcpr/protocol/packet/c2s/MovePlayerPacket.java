package net.lcpr.protocol.packet.c2s;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;

import java.nio.ByteBuffer;

public abstract class MovePlayerPacket extends Packet {
    private boolean onGround;
    private boolean bool3B;

    @Override
    public void read(ByteBuffer byteBuffer) {
        byte data = byteBuffer.get();
        onGround = (data & 1) == 1;
        bool3B = ((data >> 1) & 1) == 1;
    }

    @Override
    public void write(ByteBuffer byteBuffer) {
        byteBuffer.put((byte) ((onGround ? 1 : 0) | (bool3B ? 1 : 0)));
    }

    @Getter
    @Setter
    public static class Pos extends MovePlayerPacket {
        private double x, y, z;

        @Override
        public void read(ByteBuffer byteBuffer) {
            x = byteBuffer.getDouble();
            y = byteBuffer.getDouble();
            z = byteBuffer.getDouble();
            this.read(byteBuffer);
        }

        @Override
        public void write(ByteBuffer byteBuffer) {
            byteBuffer.putDouble(x);
            byteBuffer.putDouble(y);
            byteBuffer.putDouble(z);
            this.write(byteBuffer);
        }
    }

    @Getter
    @Setter
    public static class Rot extends MovePlayerPacket {
        private float xRot, yRot;

        @Override
        public void read(ByteBuffer byteBuffer) {
            xRot = byteBuffer.getFloat();
            yRot = byteBuffer.getFloat();
            this.read(byteBuffer);
        }

        @Override
        public void write(ByteBuffer byteBuffer) {
            byteBuffer.putFloat(xRot);
            byteBuffer.putFloat(yRot);
            this.write(byteBuffer);
        }
    }

    @Getter
    @Setter
    public static class PosRot extends MovePlayerPacket {
        private double x, y, z;
        private float xRot, yRot;

        @Override
        public void read(ByteBuffer byteBuffer) {
            x = byteBuffer.getDouble();
            y = byteBuffer.getDouble();
            z = byteBuffer.getDouble();
            xRot = byteBuffer.getFloat();
            yRot = byteBuffer.getFloat();
            this.read(byteBuffer);
        }

        @Override
        public void write(ByteBuffer byteBuffer) {
            byteBuffer.putDouble(x);
            byteBuffer.putDouble(y);
            byteBuffer.putDouble(z);
            byteBuffer.putFloat(xRot);
            byteBuffer.putFloat(yRot);
            this.write(byteBuffer);
        }
    }
}
