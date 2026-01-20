package dk.booking.biograf.simple.api.controller;

import dk.booking.biograf.simple.api.dto.OpretReservationRequestDto;
import dk.booking.biograf.simple.api.dto.ReservationDto;
import dk.booking.biograf.simple.service.BiografReservationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by birol on 19-01-2026 at 21:49
 */

@RestController
@RequestMapping("/biograf/api/reservationer")
public class ReservationerController {

    private final BiografReservationService service;

    public ReservationerController(BiografReservationService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ReservationDto> opretReservation(@Valid @RequestBody OpretReservationRequestDto request
    ) {
        ReservationDto created = service.opretReservation(request);

        return ResponseEntity
                .status(201)
                .body(created);
    }

    @DeleteMapping("/{reservationId}")
    public ResponseEntity<Void> aflysReservation(@PathVariable long reservationId) {
        service.aflysReservation(reservationId);
        return ResponseEntity.noContent().build(); // 204
    }
}
