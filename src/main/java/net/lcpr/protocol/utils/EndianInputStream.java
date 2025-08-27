package net.lcpr.protocol.utils;

import com.google.common.io.LittleEndianDataInputStream;
import net.lcpr.protocol.LegacyConsoleProtocol;

import java.io.*;

/**
 * An input stream clone which supports the 2 different endian types used in LCE (Little Endian and Big Endian)
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

    /**
     * Returns a {@code 0-255} byte data as an {@code int}, or {@code -1} if the stream has ended.
     *
     * @return The next byte of data, or {@code -1} if the end of the stream has been reached.
     * @throws IOException If an I/O error occurs
     */
    public int read() throws IOException {
        if (isLittleEndian) return little.read();
        else return big.read();
    }

    /**
     * Reads the stream fully into the byte array provided.
     *
     * @param b the byte array to write to
     * @throws IOException If an I/O error occurs
     * @throws EOFException If the end of stream is reached before reading fully
     */
    public void readFully(byte[] b) throws IOException {
        readFully(b, 0, b.length);
    }

    /**
     * Reads the stream fully into the byte array provided with an offset and length.
     *
     * @param b the byte array to write to
     * @throws IOException If an I/O error occurs
     * @throws EOFException If the end of stream is reached before reading fully
     */
    public void readFully(byte[] b, int off, int len) throws IOException {
        if (isLittleEndian) little.readFully(b, off, len);
        else big.readFully(b, off, len);
    }

    /**
     * Reads a boolean from the stream
     *
     * @return the boolean value
     * @throws IOException If an I/O error occurs
     */
    public boolean readBoolean() throws IOException {
        if (isLittleEndian) return little.readBoolean();
        else return big.readBoolean();
    }

    /**
     * Reads a byte from the stream
     *
     * @return the byte value
     * @throws IOException If an I/O error occurs
     */
    public byte readByte() throws IOException {
        if (isLittleEndian) return little.readByte();
        else return big.readByte();
    }

    /**
     * Reads an unsigned byte from the stream
     *
     * @return the unsigned byte value as an int
     * @throws IOException If an I/O error occurs
     */
    public int readUnsignedByte() throws IOException {
        if (isLittleEndian) return little.readUnsignedByte();
        else return big.readUnsignedByte();
    }

    /**
     * Reads a short from the stream
     *
     * @return the short value
     * @throws IOException If an I/O error occurs
     */
    public short readShort() throws IOException {
        if (isLittleEndian) return little.readShort();
        else return big.readShort();
    }

    /**
     * Reads an unsigned short from the stream
     *
     * @return the unsigned short value as an int
     * @throws IOException If an I/O error occurs
     */
    public int readUnsignedShort() throws IOException {
        if (isLittleEndian) return little.readUnsignedShort();
        else return big.readUnsignedShort();
    }

    /**
     * Reads a char from the stream
     *
     * @return the char value
     * @throws IOException If an I/O error occurs
     */
    public char readChar() throws IOException {
        if (isLittleEndian) return little.readChar();
        else return big.readChar();
    }

    /**
     * Reads an int from the stream
     *
     * @return the int value
     * @throws IOException If an I/O error occurs
     */
    public int readInt() throws IOException {
        if (isLittleEndian) return little.readInt();
        else return big.readInt();
    }

    /**
     * Reads an unsigned int from the stream
     *
     * @return the unsigned int value as a long
     * @throws IOException If an I/O error occurs
     */
    public long readUnsignedInt() throws IOException {
        return (isLittleEndian ? little : big).readInt() & 0xffffffffL;
    }

    /**
     * Reads a long from the stream
     *
     * @return the long value
     * @throws IOException If an I/O error occurs
     */
    public long readLong() throws IOException {
        if (isLittleEndian) return little.readLong();
        else return big.readLong();
    }

    /**
     * Reads a float from the stream
     *
     * @return the float value
     * @throws IOException If an I/O error occurs
     */
    public float readFloat() throws IOException {
        if (isLittleEndian) return little.readFloat();
        else return big.readFloat();
    }

    /**
     * Reads a double from the stream
     *
     * @return the double value
     * @throws IOException If an I/O error occurs
     */
    public double readDouble() throws IOException {
        if (isLittleEndian) return little.readDouble();
        else return big.readDouble();
    }

    /**
     * Reads a string from the stream
     *
     * @return the string value
     * @throws IOException If an I/O error occurs
     */
    public String readUTF(int maxLength) throws IOException {
        StringBuilder builder = new StringBuilder();

        short length = readShort();

        if (length > maxLength) {
            LegacyConsoleProtocol.LOGGER.warn("Received string length longer than maximum allowed ({} > {})", length, maxLength);
        }

        for (int i = 0; i < length; ++i) {
            builder.append(readChar());
        }

        return builder.toString();
    }
}
