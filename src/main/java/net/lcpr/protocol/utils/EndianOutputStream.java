package net.lcpr.protocol.utils;

import com.google.common.io.LittleEndianDataOutputStream;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * An output stream clone which supports the 2 different endian types used in LCE (Little Endian and Big Endian)
 */
public class EndianOutputStream {
    private final LittleEndianDataOutputStream little;
    private final DataOutputStream big;

    private final boolean isLittleEndian;

    public EndianOutputStream(OutputStream out, boolean isLittleEndian) {
        this.isLittleEndian = isLittleEndian;

        if (isLittleEndian) {
            this.little = new LittleEndianDataOutputStream(out);
            this.big = null;
        } else {
            this.little = null;
            this.big = new DataOutputStream(out);
        }
    }

    /**
     * Writes a byte to the stream (The lower 8 bits of the int)
     *
     * @param v the byte value to write
     * @throws IOException If an I/O error occurs
     */
    public void write(int v) throws IOException {
        if (isLittleEndian) little.write(v);
        else big.write(v);
    }

    /**
     * Writes a boolean to the stream
     *
     * @param v the boolean value to write
     * @throws IOException If an I/O error occurs
     */
    public void writeBoolean(boolean v) throws IOException {
        if (isLittleEndian) little.writeBoolean(v);
        else big.writeBoolean(v);
    }

    /**
     * Writes a byte to the stream
     *
     * @param v the byte value (represented as an int) to write
     * @throws IOException If an I/O error occurs
     */
    public void writeByte(int v) throws IOException {
        if (isLittleEndian) little.writeByte(v);
        else big.writeByte(v);
    }

    /**
     * Writes a short to the stream
     *
     * @param v the short value (represented as an int) to write
     * @throws IOException If an I/O error occurs
     */
    public void writeShort(int v) throws IOException {
        if (isLittleEndian) little.writeShort(v);
        else big.writeShort(v);
    }

    /**
     * Writes a char to the stream
     *
     * @param v the char value (represented as an int) to write
     * @throws IOException If an I/O error occurs
     */
    public void writeChar(int v) throws IOException {
        if (isLittleEndian) little.writeChar(v);
        else big.writeChar(v);
    }

    /**
     * Writes a int to the stream
     *
     * @param v the int value to write
     * @throws IOException If an I/O error occurs
     */
    public void writeInt(int v) throws IOException {
        if (isLittleEndian) little.writeInt(v);
        else big.writeInt(v);
    }

    /**
     * Writes a long to the stream
     *
     * @param v the long value to write
     * @throws IOException If an I/O error occurs
     */
    public void writeLong(long v) throws IOException {
        if (isLittleEndian) little.writeLong(v);
        else big.writeLong(v);
    }

    /**
     * Writes a float to the stream
     *
     * @param v the float value to write
     * @throws IOException If an I/O error occurs
     */
    public void writeFloat(float v) throws IOException {
        if (isLittleEndian) little.writeFloat(v);
        else big.writeFloat(v);
    }

    /**
     * Writes a double to the stream
     *
     * @param v the double value to write
     * @throws IOException If an I/O error occurs
     */
    public void writeDouble(double v) throws IOException {
        if (isLittleEndian) little.writeDouble(v);
        else big.writeDouble(v);
    }

    /**
     * Writes a string as bytes to the stream
     *
     * @param s the string value to write
     * @throws IOException If an I/O error occurs
     */
    public void writeBytes(String s) throws IOException {
        if (isLittleEndian) little.writeBytes(s);
        else big.writeBytes(s);
    }

    /**
     * Writes a string as chars to the stream
     *
     * @param s the string value to write
     * @throws IOException If an I/O error occurs
     */
    public void writeChars(String s) throws IOException {
        if (isLittleEndian) little.writeChars(s);
        else big.writeChars(s);
    }

    /**
     * Writes a string to the stream
     *
     * @param s the string value to write
     * @throws IOException If an I/O error occurs
     */
    public void writeUTF(String s) throws IOException {
        writeShort(s.length());

        for (char character : s.toCharArray()) {
            writeChar(character);
        }
    }
}
