package dk.booking.biograf.simple.domain;

/**
 * Created by birol on 21-01-2026
 */

public record Kunde (
        Long id,
        String navn,
        String email
) {}
