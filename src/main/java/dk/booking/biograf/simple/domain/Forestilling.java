package dk.booking.biograf.simple.domain;

import java.time.OffsetDateTime;
/**
 * Created by birol on 21-01-2026
 */

public record Forestilling(Long id,
                           Film film,
                           Sal sal,
                           OffsetDateTime startTime) {}
