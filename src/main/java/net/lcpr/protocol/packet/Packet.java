package net.lcpr.protocol.packet;

import lombok.Getter;
import net.lcpr.protocol.LegacyConsoleProtocol;
import net.lcpr.protocol.utils.Side;

import java.nio.ByteBuffer;

public abstract class Packet {
    private long createdTime = System.currentTimeMillis();
    private boolean shouldDelay = false;
    /**
     * Which side the packet comes from, either Serverbound, or Clientbound
     */
    @Getter
    private Side origin;

    /**
     * Reads the ByteBuffer to generate the current packet
     * @param byteBuffer The ByteBuffer containing the packet data
     */
    public abstract void read(ByteBuffer byteBuffer);

    /**
     * Writes the current packet to the ByteBuffer
     * @param byteBuffer The ByteBuffer to be written to
     */
    public abstract void write(ByteBuffer byteBuffer);

    public int getEstimatedSize() {
        return 8;
    }

    public boolean canBeInvalidated() {
        return false;
    }

    public boolean isAsync() {
        return false;
    }

    /**
     * Reads a UTF-16 string from the ByteBuffer
     * @param byteBuffer The ByteBuffer to read from
     * @param maxLength The max length of the string
     * @return The result string
     */
    public String readUTF(ByteBuffer byteBuffer, int maxLength) {
        short length = byteBuffer.getShort();

        if (length > maxLength) {
            LegacyConsoleProtocol.LOGGER.warn("Received string length longer than maximum allowed ({} > {})", length, maxLength);
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; ++i) {
            builder.append(byteBuffer.getChar());
        }

        return builder.toString();
    }

    /**
     * Writes a UTF-16 string to the ByteBuffer
     * @param byteBuffer The ByteBuffer to write to
     * @param value The string to write
     */
    public void writeUTF(ByteBuffer byteBuffer, String value) {
        if (value.length() > Short.MAX_VALUE) {
            // TODO do something about this
        }

        byteBuffer.putShort((short) value.length());

        for (char character : value.toCharArray()) {
            byteBuffer.putChar(character);
        }
    }
}
