package net.lcpr.protocol.utils;

import com.google.common.io.LittleEndianDataInputStream;
import com.google.common.io.LittleEndianDataOutputStream;

import java.io.*;

public enum Platform {
    XB360(false),
    XB1(true),

    PS3(false),
    PSV(true),
    PS4(true),

    WIIU(false),
    SWITCH(true);

    public boolean isLittleEndian() {
        return isLittleEndian;
    }

    private final boolean isLittleEndian;

    Platform(boolean isLittleEndian) {
        this.isLittleEndian = isLittleEndian;
    }

    public DataInputStream getInputStream(InputStream backingStream) {
        DataInputStream stream;

        if (isLittleEndian) {
            stream = new DataInputStream(new LittleEndianDataInputStream(backingStream));
        } else {
            stream = new DataInputStream(backingStream);
        }

        return stream;
    }

    public DataOutputStream getOutputStream(OutputStream backingStream) {
        DataOutputStream stream;

        if (isLittleEndian) {
            stream = new DataOutputStream(new LittleEndianDataOutputStream(backingStream));
        } else {
            stream = new DataOutputStream(backingStream);
        }

        return stream;
    }
}
