package net.lcpr.protocol.types;

import java.util.HashMap;
import java.util.Map;

public enum DisconnectReason {
    ZERO(0),
    CLOSED(2),
    KICKED(8),
    TIMEOUT(10),
    OVERFLOW(11),
    TWENTY_NINE(29);

    private static final Map<Integer, DisconnectReason> BY_ID = HashMap.newHashMap(values().length);

    public static DisconnectReason fromId(int id) {
        return BY_ID.get(id);
    }

    private final int id;

    public int getId() {
        return id;
    }

    DisconnectReason(int id) {
        this.id = id;
    }

    static {
        for (DisconnectReason reason : values()) {
            BY_ID.put(reason.id, reason);
        }
    }
}
