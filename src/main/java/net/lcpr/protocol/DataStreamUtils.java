package net.lcpr.protocol;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class DataStreamUtils {
    public static long readUnsignedInt(DataInputStream inputStream) throws IOException {
        return inputStream.readInt() & 0xffffffffL;
    }

    public static void writeUnsignedInt(DataOutputStream outputStream, long value) throws IOException {
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
