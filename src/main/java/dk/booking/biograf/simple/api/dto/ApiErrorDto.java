package dk.booking.biograf.simple.api.dto;
import java.time.OffsetDateTime;

/**
 * Created by birol on 19-01-2026 at 21:20
 */

public class ApiErrorDto {
    private OffsetDateTime timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

    public ApiErrorDto() {}

    public ApiErrorDto(OffsetDateTime timestamp, Integer status, String error, String message, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    public OffsetDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(OffsetDateTime timestamp) { this.timestamp = timestamp; }

    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }

    public String getError() { return error; }
    public void setError(String error) { this.error = error; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getPath() { return path; }
    public void setPath(String path) { this.path = path; }
}
