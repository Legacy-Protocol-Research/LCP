package net.lcpr.protocol.packet;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.utils.EndianInputStream;
import net.lcpr.protocol.utils.EndianOutputStream;
import net.lcpr.protocol.utils.PacketType;
import net.lcpr.protocol.utils.Side;

import java.io.IOException;

/**
 * A bi-direction packet describing vehicle position and rotation data
 *
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
    public void read(EndianInputStream inputStream) throws IOException {
        x = inputStream.readDouble();
        y = inputStream.readDouble();
        z = inputStream.readDouble();
        yRot = inputStream.readFloat();
        xRot = inputStream.readFloat();
    }

    @Override
    public void write(EndianOutputStream outputStream) throws IOException {
        outputStream.writeDouble(x);
        outputStream.writeDouble(y);
        outputStream.writeDouble(z);
        outputStream.writeFloat(yRot);
        outputStream.writeFloat(xRot);
    }

    @Override
    public PacketType getType() {
        return this.getOrigin().equals(Side.CLIENTBOUND) ?
                PacketType.ClientboundMoveVehiclePacket :
                PacketType.ServerboundMoveVehiclePacket;
    }
}
