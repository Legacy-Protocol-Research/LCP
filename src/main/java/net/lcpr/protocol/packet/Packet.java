package net.lcpr.protocol.packet;

import lombok.Getter;
import net.lcpr.protocol.LegacyConsoleProtocol;
import net.lcpr.protocol.utils.EndianInputStream;
import net.lcpr.protocol.utils.EndianOutputStream;
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
     * Reads the EndianInputStream to generate the current packet
     * @param inputStream The EndianInputStream containing the packet data
     */
    public abstract void read(EndianInputStream inputStream) throws IOException;

    /**
     * Writes the current packet to the EndianOutputStream
     * @param outputStream The EndianOutputStream to be written to
     */
    public abstract void write(EndianOutputStream outputStream) throws IOException;

    public int getEstimatedSize() {
        return 8;
    }

    public boolean canBeInvalidated() {
        return false;
    }

    public boolean isAsync() {
        return false;
    }
}
