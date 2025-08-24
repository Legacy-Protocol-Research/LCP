package net.lcpr.protocol.utils;

import java.nio.ByteBuffer;

public class Vec {
    public static class f3 {
        private float x, y, z;

        public f3() {}
        public f3(float x, float y, float z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public static f3 read(ByteBuffer byteBuffer) {
            return new f3(
                    byteBuffer.getFloat(),
                    byteBuffer.getFloat(),
                    byteBuffer.getFloat()
            );
        }

        public static void write(ByteBuffer byteBuffer, f3 value) {
            byteBuffer.putFloat(value.x);
            byteBuffer.putFloat(value.y);
            byteBuffer.putFloat(value.z);
        }
    }

    public static class i3 {
        private int x, y, z;

        public i3() {}
        public i3(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public static i3 read(ByteBuffer byteBuffer) {
            return new i3(
                    byteBuffer.getInt(),
                    byteBuffer.getInt(),
                    byteBuffer.getInt()
            );
        }

        public static void write(ByteBuffer byteBuffer, i3 value) {
            byteBuffer.putInt(value.x);
            byteBuffer.putInt(value.y);
            byteBuffer.putInt(value.z);
        }
    }
}
