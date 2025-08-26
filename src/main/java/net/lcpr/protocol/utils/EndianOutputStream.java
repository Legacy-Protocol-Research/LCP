package net.lcpr.protocol.utils;

import com.google.common.io.LittleEndianDataOutputStream;

import java.io.*;

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

    public void write(int v) throws IOException {
        if (isLittleEndian) little.write(v);
        else big.write(v);
    }

    public void writeBoolean(boolean v) throws IOException {
        if (isLittleEndian) little.writeBoolean(v);
        else big.writeBoolean(v);
    }

    public void writeByte(int v) throws IOException {
        if (isLittleEndian) little.writeByte(v);
        else big.writeByte(v);
    }

    public void writeShort(int v) throws IOException {
        if (isLittleEndian) little.writeShort(v);
        else big.writeShort(v);
    }

    public void writeChar(int v) throws IOException {
        if (isLittleEndian) little.writeChar(v);
        else big.writeChar(v);
    }

    public void writeInt(int v) throws IOException {
        if (isLittleEndian) little.writeInt(v);
        else big.writeInt(v);
    }

    public void writeLong(long v) throws IOException {
        if (isLittleEndian) little.writeLong(v);
        else big.writeLong(v);
    }

    public void writeFloat(float v) throws IOException {
        if (isLittleEndian) little.writeFloat(v);
        else big.writeFloat(v);
    }

    public void writeDouble(double v) throws IOException {
        if (isLittleEndian) little.writeDouble(v);
        else big.writeDouble(v);
    }

    public void writeBytes(String s) throws IOException {
        if (isLittleEndian) little.writeBytes(s);
        else big.writeBytes(s);
    }

    public void writeChars(String s) throws IOException {
        if (isLittleEndian) little.writeChars(s);
        else big.writeChars(s);
    }

    public void writeUTF(String s) throws IOException {
        if (isLittleEndian) {
            little.writeShort(s.length());

            for (char character : s.toCharArray()) {
                little.writeChar(character);
            }
        } else {
            big.writeShort(s.length());

            for (char character : s.toCharArray()) {
                big.writeChar(character);
            }
        }
    }
}
