package net.lcpr.protocol;

import net.lcpr.protocol.utils.EndianInputStream;
import net.lcpr.protocol.utils.EndianOutputStream;

import java.io.IOException;

public class DataStreamUtils {
    public static long readUnsignedInt(EndianInputStream inputStream) throws IOException {
        return inputStream.readInt() & 0xffffffffL;
    }

    public static void writeUnsignedInt(EndianOutputStream outputStream, long value) throws IOException {
        if (value > 4294967295L) {
            throw new IllegalArgumentException("Long value was greater than UInt bounds.");
        } else if (value < 0) {
            throw new IllegalArgumentException("Long value was less than UInt bounds.");
        }

        outputStream.writeInt((int) value);
    }

    public static void test() {
    }
}
