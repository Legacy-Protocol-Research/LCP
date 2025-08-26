package net.lcpr.protocol.packet;

import lombok.Getter;
import net.lcpr.protocol.LegacyConsoleProtocol;
import net.lcpr.protocol.utils.Side;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
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
     * Reads the DataInputStream to generate the current packet
     * @param inputStream The DataInputStream containing the packet data
     */
    public abstract void read(DataInputStream inputStream) throws IOException;

    /**
     * Writes the current packet to the DataOutputStream
     * @param outputStream The DataOutputStream to be written to
     */
    public abstract void write(DataOutputStream outputStream) throws IOException;

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
     * Reads a UTF-16 string from the DataInputStream
     * @param inputStream The DataInputStream to read from
     * @param maxLength The max length of the string
     * @return The result string
     */
    public String readUTF(DataInputStream inputStream, int maxLength) throws IOException {
        short length = inputStream.readShort();

        if (length > maxLength) {
            LegacyConsoleProtocol.LOGGER.warn("Received string length longer than maximum allowed ({} > {})", length, maxLength);
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; ++i) {
            builder.append(inputStream.readChar());
        }

        return builder.toString();
    }

    /**
     * Writes a UTF-16 string to the DataOutputStream
     * @param outputStream The DataOutputStream to write to
     * @param value The string to write
     */
    public void writeUTF(DataOutputStream outputStream, String value) throws IOException {
        outputStream.writeShort(value.length());

        for (char character : value.toCharArray()) {
            outputStream.writeChar(character);
        }
    }
}
