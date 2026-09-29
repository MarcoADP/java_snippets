package org.example.list;

import java.math.BigDecimal;
import java.time.LocalDate;

public record Transaction(LocalDate date, BigDecimal value, String origin, String destination) {
    @Override
    public String toString() {
        return "[%s] %s | %s => %s\n".formatted(date, value, origin, destination);
    }
}
