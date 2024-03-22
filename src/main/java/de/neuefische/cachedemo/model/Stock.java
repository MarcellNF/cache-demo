package de.neuefische.cachedemo.model;

import java.time.LocalDateTime;

public record Stock(
        String name,
        double currentPrice,
        LocalDateTime dateTime
) {
}