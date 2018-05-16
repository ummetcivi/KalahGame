package com.ummetcivi.kalahgame.util;

import java.util.UUID;

public final class RandomIdGenerator {
    private RandomIdGenerator() {
    }

    public static String generateId() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
