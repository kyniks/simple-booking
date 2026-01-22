package dk.booking.biograf.simple.api.controller;

import dk.booking.biograf.simple.api.dto.OpretReservationRequestDto;
import dk.booking.biograf.simple.api.dto.ReservationDto;
import dk.booking.biograf.simple.service.ReservationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by birol on 19-01-2026 at 21:49
 */

@RestController
@RequestMapping("/biograf/api/reservationer")
public class ReservationerController {

    private final ReservationService reservationService;

    public ReservationerController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("opret")
    public ResponseEntity<ReservationDto> opretReservation(@RequestBody @Valid OpretReservationRequestDto req) {
        ReservationDto created = reservationService.opretReservation(req);
        return ResponseEntity.status(201).body(created);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> aflysReservation(@PathVariable long id) {
        reservationService.aflysReservation(id);
        return ResponseEntity.noContent().build();
    }
}

