package dk.booking.biograf.simple.service;

import dk.booking.biograf.simple.api.dto.OpretReservationRequestDto;
import dk.booking.biograf.simple.api.dto.ReservationDto;

/**
 * Created by birol on 21-01-2026 at 17:59
 */
public interface ReservationService {

    ReservationDto opretReservation(OpretReservationRequestDto request);
    void aflysReservation(long reservationId);
}
