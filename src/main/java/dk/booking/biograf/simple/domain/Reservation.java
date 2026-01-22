package dk.booking.biograf.simple.domain;

import java.time.OffsetDateTime;
import java.util.List;
/**
 * Created by birol on 21-01-2026 at 11:25
 */

public record Reservation(Long id,
                          OffsetDateTime opretTid,
                          List<ReservationPost> lines) {}

