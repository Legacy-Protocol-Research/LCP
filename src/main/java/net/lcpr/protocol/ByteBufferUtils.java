package net.lcpr.protocol;

import java.nio.ByteBuffer;

public class ByteBufferUtils {
    public static long getUInt(ByteBuffer byteBuffer) {
        return byteBuffer.getInt() & 0xffffffffL;
    }

    public static void putUInt(ByteBuffer byteBuffer, long value) {
        if (value > 4294967295L) {
            throw new IllegalArgumentException("Long value was greater than UInt bounds.");
        } else if (value < 0) {
            throw new IllegalArgumentException("Long value was less than UInt bounds.");
        }

        byteBuffer.putInt((int) value);
    }

    public static boolean getBoolean(ByteBuffer byteBuffer) {
        return byteBuffer.getInt() == 1;
    }

    public static void putBoolean(ByteBuffer byteBuffer, boolean value) {
        byteBuffer.putInt(value ? 1 : 0);
    }
}
