package net.lcpr.protocol.packet;

import lombok.Getter;
import lombok.Setter;

import java.nio.ByteBuffer;

/**
 * @c2s This will update the server on where the vehicle the client is currently riding is.
 * @s2c This will update the client on where a vehicle is in the world.
 */
@Getter
@Setter
public class MoveVehiclePacket extends Packet {
    /**
     * Determines the x position of the vehicle and rider
     */
    private double x;
    /**
     * Determines the y position of the vehicle and rider
     */
    private double y;
    /**
     * Determines the z position of the vehicle and rider
     */
    private double z;
    /**
     * Determines the x head rotation of the vehicle and rider
     */
    private float xRot;
    /**
     * Determines the y head rotation of the vehicle and rider
     */
    private float yRot;

    @Override
    public void read(ByteBuffer byteBuffer) {
        x = byteBuffer.getDouble();
        y = byteBuffer.getDouble();
        z = byteBuffer.getDouble();
        yRot = byteBuffer.getFloat();
        xRot = byteBuffer.getFloat();
    }

    @Override
    public void write(ByteBuffer byteBuffer) {
        byteBuffer.putDouble(x);
        byteBuffer.putDouble(y);
        byteBuffer.putDouble(z);
        byteBuffer.putFloat(yRot);
        byteBuffer.putFloat(xRot);
    }
}
