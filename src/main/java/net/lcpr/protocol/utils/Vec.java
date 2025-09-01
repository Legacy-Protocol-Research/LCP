package net.lcpr.protocol.utils;

import java.io.IOException;

public class Vec {
    public static class f3 {
        private float x, y, z;

        public f3(float x, float y, float z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public static f3 read(EndianInputStream inputStream) throws IOException {
            return new f3(
                    inputStream.readFloat(),
                    inputStream.readFloat(),
                    inputStream.readFloat()
            );
        }

        public static void write(EndianOutputStream outputStream, f3 value) throws IOException {
            outputStream.writeFloat(value.x);
            outputStream.writeFloat(value.y);
            outputStream.writeFloat(value.z);
        }
    }

    public static class i3 {
        private int x, y, z;

        public i3(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public static i3 read(EndianInputStream inputStream) throws IOException {
            return new i3(
                    inputStream.readInt(),
                    inputStream.readInt(),
                    inputStream.readInt()
            );
        }

        public static void write(EndianOutputStream outputStream, i3 value) throws IOException {
            outputStream.writeInt(value.x);
            outputStream.writeInt(value.y);
            outputStream.writeInt(value.z);
        }
    }
}
