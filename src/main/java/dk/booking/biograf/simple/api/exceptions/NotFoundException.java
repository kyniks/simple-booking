package dk.booking.biograf.simple.api.exceptions;

/**
 * Created by birol on 19-01-2026 at 21:28
 */
public class NotFoundException extends  RuntimeException{
    public NotFoundException(String message) {
        super(message);
    }
}
