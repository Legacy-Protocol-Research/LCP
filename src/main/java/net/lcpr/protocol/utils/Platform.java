package net.lcpr.protocol.utils;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * An enum for platforms, used for identification and to determine whether to use
 */
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

    public EndianInputStream getInputStream(InputStream backingStream) {
        return new EndianInputStream(backingStream, isLittleEndian);
    }

    public EndianOutputStream getOutputStream(OutputStream backingStream) {
        return new EndianOutputStream(backingStream, isLittleEndian);
    }
}
