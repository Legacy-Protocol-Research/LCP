package net.lcpr.protocol.utils;

import com.google.common.primitives.Bytes;
import lombok.Getter;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * An enum for platforms, used for identification and to determine whether to use
 * small or big endian, also contains protocol versions to correctly handle versions
 */
@Getter
public enum Platform {
    XB360("Xbox 360", false, 0), // TODO
    XONE("Xbox One", true, 0), // TODO

    PS3("PlayStation 3", false, 0), // TODO
    PSV("PlayStation Vita", true, 0), // TODO
    PS4("PlayStation 4", true, 0), // TODO

    WIIU("Wii U", false, 0), // TODO
    NX("Nintendo Switch", true, 1920);

    private final String consoleName;
    private final boolean isLittleEndian;
    private final int latestProtocolVersion;

    Platform(String consoleName, boolean isLittleEndian, int latestProtocolVersion) {
        this.consoleName = consoleName;
        this.isLittleEndian = isLittleEndian;
        this.latestProtocolVersion = latestProtocolVersion;
    }

    public EndianInputStream getInputStream(InputStream backingStream) {
        return new EndianInputStream(backingStream, isLittleEndian);
    }

    public EndianOutputStream getOutputStream(OutputStream backingStream) {
        return new EndianOutputStream(backingStream, isLittleEndian);
    }

    public byte[] parseBytes(byte[] bytes) {
        if (isLittleEndian) { // I love little endian.
            byte[] resultBytes = new byte[bytes.length];
            for (int i = 0; i < bytes.length; i++) {
                resultBytes[bytes.length - 1 - i] = bytes[i];
            }
            return resultBytes;
        }
        return bytes;
    }
}
