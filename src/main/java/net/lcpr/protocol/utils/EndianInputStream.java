package net.lcpr.protocol.utils;

import com.google.common.io.LittleEndianDataInputStream;
import net.lcpr.protocol.LegacyConsoleProtocol;

import java.io.*;

/**
 * An output stream clone which supports the 2 different endian types used in LCE (Little Endian and Big Endian)
 */
public class EndianInputStream {
    private final LittleEndianDataInputStream little;
    private final DataInputStream big;

    private final boolean isLittleEndian;

    public EndianInputStream(InputStream in, boolean isLittleEndian) {
        this.isLittleEndian = isLittleEndian;

        if (isLittleEndian) {
            this.little = new LittleEndianDataInputStream(in);
            this.big = null;
        } else {
            this.little = null;
            this.big = new DataInputStream(in);
        }
    }

    public int read() throws IOException {
        if (isLittleEndian) return little.read();
        else return big.read();
    }

    public void readFully(byte[] b) throws IOException {
        if (isLittleEndian) little.readFully(b);
        else big.readFully(b);
    }

    public void readFully(byte[] b, int off, int len) throws IOException {
        if (isLittleEndian) little.readFully(b, off, len);
        else big.readFully(b, off, len);
    }

    public boolean readBoolean() throws IOException {
        if (isLittleEndian) return little.readBoolean();
        else return big.readBoolean();
    }

    public byte readByte() throws IOException {
        if (isLittleEndian) return little.readByte();
        else return big.readByte();
    }

    public int readUnsignedByte() throws IOException {
        if (isLittleEndian) return little.readUnsignedByte();
        else return big.readUnsignedByte();
    }

    public short readShort() throws IOException {
        if (isLittleEndian) return little.readShort();
        else return big.readShort();
    }

    public int readUnsignedShort() throws IOException {
        if (isLittleEndian) return little.readUnsignedShort();
        else return big.readUnsignedShort();
    }

    public char readChar() throws IOException {
        if (isLittleEndian) return little.readChar();
        else return big.readChar();
    }

    public int readInt() throws IOException {
        if (isLittleEndian) return little.readInt();
        else return big.readInt();
    }

    public long readLong() throws IOException {
        if (isLittleEndian) return little.readLong();
        else return big.readLong();
    }

    public float readFloat() throws IOException {
        if (isLittleEndian) return little.readFloat();
        else return big.readFloat();
    }

    public double readDouble() throws IOException {
        if (isLittleEndian) return little.readDouble();
        else return big.readDouble();
    }

    public String readUTF(int maxLength) throws IOException {
        StringBuilder builder = new StringBuilder();

        short length = isLittleEndian ? little.readShort() : big.readShort();

        if (length > maxLength) {
            LegacyConsoleProtocol.LOGGER.warn("Received string length longer than maximum allowed ({} > {})", length, maxLength);
        }

        if (isLittleEndian) {
            for (int i = 0; i < length; ++i) {
                builder.append(little.readChar());
            }
        } else {
            for (int i = 0; i < length; ++i) {
                builder.append(big.readChar());
            }
        }

        return builder.toString();
    }
}
