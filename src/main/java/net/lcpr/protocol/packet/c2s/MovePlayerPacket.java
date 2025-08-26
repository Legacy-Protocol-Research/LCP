package net.lcpr.protocol.packet.c2s;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.packet.Packet;
import net.lcpr.protocol.utils.EndianInputStream;
import net.lcpr.protocol.utils.EndianOutputStream;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Broadcasts a change in player movement to the server
 *
 * @see net.lcpr.protocol.packet.c2s.MovePlayerPacket.Pos
 * @see net.lcpr.protocol.packet.c2s.MovePlayerPacket.Rot
 * @see net.lcpr.protocol.packet.c2s.MovePlayerPacket.PosRot
 */
public abstract class MovePlayerPacket extends Packet {
    private boolean onGround;
    private boolean bool3B;

    @Override
    public void read(EndianInputStream inputStream) throws IOException {
        byte data = inputStream.readByte();
        onGround = (data & 1) == 1;
        bool3B = ((data >> 1) & 1) == 1;
    }

    @Override
    public void write(EndianOutputStream outputStream) throws IOException {
        outputStream.writeByte((onGround ? 1 : 0) | (bool3B ? 1 : 0));
    }

    /**
     * Broadcasts to the server a change in position
     *
     * @c2s Tells the server about a change in the client's position
     */
    @Getter
    @Setter
    public static class Pos extends MovePlayerPacket {
        private double x, y, z;

        @Override
        public void read(EndianInputStream inputStream) throws IOException {
            x = inputStream.readDouble();
            y = inputStream.readDouble();
            z = inputStream.readDouble();
            super.read(inputStream);
        }

        @Override
        public void write(EndianOutputStream outputStream) throws IOException {
            outputStream.writeDouble(x);
            outputStream.writeDouble(y);
            outputStream.writeDouble(z);
            super.write(outputStream);
        }
    }

    /**
     * Broadcasts to the server a change in rotation
     *
     * @c2s Tells the server about a change in the client's rotation
     */
    @Getter
    @Setter
    public static class Rot extends MovePlayerPacket {
        private float xRot, yRot;

        @Override
        public void read(EndianInputStream inputStream) throws IOException {
            xRot = inputStream.readFloat();
            yRot = inputStream.readFloat();
            super.read(inputStream);
        }

        @Override
        public void write(EndianOutputStream outputStream) throws IOException {
            outputStream.writeFloat(xRot);
            outputStream.writeFloat(yRot);
            super.write(outputStream);
        }
    }

    /**
     * Broadcasts to the server a change in position and rotation
     *
     * @c2s Tells the server about a change in the client's position and rotation
     */
    @Getter
    @Setter
    public static class PosRot extends MovePlayerPacket {
        private double x, y, z;
        private float xRot, yRot;

        @Override
        public void read(EndianInputStream inputStream) throws IOException {
            x = inputStream.readDouble();
            y = inputStream.readDouble();
            z = inputStream.readDouble();
            xRot = inputStream.readFloat();
            yRot = inputStream.readFloat();
            super.read(inputStream);
        }

        @Override
        public void write(EndianOutputStream outputStream) throws IOException {
            outputStream.writeDouble(x);
            outputStream.writeDouble(y);
            outputStream.writeDouble(z);
            outputStream.writeFloat(xRot);
            outputStream.writeFloat(yRot);
            super.write(outputStream);
        }
    }
}
