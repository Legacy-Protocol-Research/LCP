package net.lcpr.protocol.packet;

import lombok.Getter;
import lombok.Setter;
import net.lcpr.protocol.utils.*;

import java.io.IOException;
import java.util.Deque;

public abstract class Packet {
    @Getter
    private long createdTime = System.currentTimeMillis();
    @Getter @Setter
    private boolean shouldDelay = false;
    /**
     * Which side the packet comes from, either Serverbound, or Clientbound
     */
    @Getter
    private Side origin;

    /**
     * Reads the EndianInputStream to generate the current packet
     * @param inputStream The EndianInputStream containing the packet data
     * @param platform The Platform for context
     */
    public void read(EndianInputStream inputStream, Platform platform) throws IOException {
        read(inputStream);
    }

    /**
     * Writes the current packet to the EndianOutputStream
     * @param outputStream The EndianOutputStream to be written to
     * @param platform The Platform for context
     */
    public void write(EndianOutputStream outputStream, Platform platform) throws IOException {
        write(outputStream);
    }

    /**
     * Reads the EndianInputStream to generate the current packet
     * @param inputStream The EndianInputStream containing the packet data
     */
    public abstract void read(EndianInputStream inputStream) throws IOException;

    /**
     * Writes the current packet to the EndianOutputStream
     * @param outputStream The EndianOutputStream to be written to
     */
    public abstract void write(EndianOutputStream outputStream) throws IOException;

    /**
     * Get the type of the current packet
     * @return the packet type
     */
    public abstract PacketType getType();

    /**
     * Gets the expected size of the packet
     * @return the expected size
     */
    public int getEstimatedSize() {
        return 8;
    }

    public boolean canBeInvalidated() {
        return false;
    }

    public boolean isAsync() {
        return false;
    }

    public boolean tryReplaceDuplicatePacket(Deque<Packet> outgoingQueue) {
        return false;
    }

    public static void writePacket(Packet packet, EndianOutputStream outputStream, long field_164) {
        // TODO
    }

    public static Packet readPacket(EndianInputStream inputStream, long field_160, boolean field_119) {
        return null; // TODO
    }
}
