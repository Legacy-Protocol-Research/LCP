package net.lcpr.protocol;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LegacyConsoleProtocol {
    public static final Logger LOGGER = LoggerFactory.getLogger(LegacyConsoleProtocol.class);

    @Getter @Setter
    private static PacketListener packetListener;
}
