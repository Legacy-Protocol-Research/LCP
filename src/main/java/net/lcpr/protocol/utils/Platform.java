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
    /**
     * Represents the Xbox 360 platform
     */
    X360("Xbox 360", false, 0), // TODO
    /**
     * Represents the Xbox One platform
     */
    XONE("Xbox One", true, 0), // TODO

    /**
     * Represents the PlayStation 3 platform
     */
    PS3("PlayStation 3", false, 0), // TODO
    /**
     * Represents the PlayStation Vita platform
     */
    PSV("PlayStation Vita", true, 0), // TODO
    /**
     * Represents the PlayStation 4 platform
     */
    PS4("PlayStation 4", true, 0), // TODO

    /**
     * Represents the Wii U platform
     */
    WIIU("Wii U", false, 0), // TODO
    /**
     * Represents the Nintendo Switch platform
     */
    NX("Nintendo Switch", true, 1920);

    /**
     * The console name of the platform
     */
    private final String consoleName;
    /**
     * Whether the platform uses little endian or not
     */
    private final boolean isLittleEndian;
    /**
     * The latest protocol version of the platform
     */
    private final int latestProtocolVersion;

    Platform(String consoleName, boolean isLittleEndian, int latestProtocolVersion) {
        this.consoleName = consoleName;
        this.isLittleEndian = isLittleEndian;
        this.latestProtocolVersion = latestProtocolVersion;
    }

    /**
     * Gets an input stream which would be compatible with the platform
     * @param backingStream the stream that backs up the connection
     * @return a compatible stream
     */
    public EndianInputStream getInputStream(InputStream backingStream) {
        return new EndianInputStream(backingStream, isLittleEndian);
    }

    /**
     * Gets an output stream which would be compatible with the platform
     * @param backingStream the stream that backs up the connection
     * @return a compatible stream
     */
    public EndianOutputStream getOutputStream(OutputStream backingStream) {
        return new EndianOutputStream(backingStream, isLittleEndian);
    }

    /**
     * Parse bytes in the platform to the correct endian
     * @param bytes the bytes to parse
     * @return parsed bytes
     */
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
