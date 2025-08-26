package net.lcpr.protocol.packet.c2s;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public abstract class MovePlayerPacket extends Packet {
    private boolean onGround;
    private boolean bool3B;

    @Override
    public void read(DataInputStream inputStream) throws IOException {
        byte data = inputStream.readByte();
        onGround = (data & 1) == 1;
        bool3B = ((data >> 1) & 1) == 1;
    }

    @Override
    public void write(DataOutputStream outputStream) throws IOException {
        outputStream.writeByte((onGround ? 1 : 0) | (bool3B ? 1 : 0));
    }

    @Getter
    @Setter
    public static class Pos extends MovePlayerPacket {
        private double x, y, z;

        @Override
        public void read(DataInputStream inputStream) throws IOException {
            x = inputStream.readDouble();
            y = inputStream.readDouble();
            z = inputStream.readDouble();
            super.read(inputStream);
        }

        @Override
        public void write(DataOutputStream outputStream) throws IOException {
            outputStream.writeDouble(x);
            outputStream.writeDouble(y);
            outputStream.writeDouble(z);
            super.write(outputStream);
        }
    }

    @Getter
    @Setter
    public static class Rot extends MovePlayerPacket {
        private float xRot, yRot;

        @Override
        public void read(DataInputStream inputStream) throws IOException {
            xRot = inputStream.readFloat();
            yRot = inputStream.readFloat();
            super.read(inputStream);
        }

        @Override
        public void write(DataOutputStream outputStream) throws IOException {
            outputStream.writeFloat(xRot);
            outputStream.writeFloat(yRot);
            super.write(outputStream);
        }
    }

    @Getter
    @Setter
    public static class PosRot extends MovePlayerPacket {
        private double x, y, z;
        private float xRot, yRot;

        @Override
        public void read(DataInputStream inputStream) throws IOException {
            x = inputStream.readDouble();
            y = inputStream.readDouble();
            z = inputStream.readDouble();
            xRot = inputStream.readFloat();
            yRot = inputStream.readFloat();
            super.read(inputStream);
        }

        @Override
        public void write(DataOutputStream outputStream) throws IOException {
            outputStream.writeDouble(x);
            outputStream.writeDouble(y);
            outputStream.writeDouble(z);
            outputStream.writeFloat(xRot);
            outputStream.writeFloat(yRot);
            super.write(outputStream);
        }
    }
}
