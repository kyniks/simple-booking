package dk.booking.biograf.simple.api.exceptions;

/**
 * Created by birol on 19-01-2026 at 21:30
 */

public class ConflictException extends RuntimeException {
    public ConflictException(String message) {
        super(message);
    }
}

