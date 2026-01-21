package dk.booking.biograf.simple.service;

import dk.booking.biograf.simple.api.dto.ForestillingDto;
import dk.booking.biograf.simple.api.dto.OpretReservationRequestDto;
import dk.booking.biograf.simple.api.dto.ReservationDto;
import dk.booking.biograf.simple.api.dto.ReservationsPostDto;
import dk.booking.biograf.simple.api.exceptions.ConflictException;
import dk.booking.biograf.simple.api.exceptions.NotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by birol on 19-01-2026 at 21:33
 */
@Service
public class BiografReservationService {

    // Demo data store
    private final Map<Long, ForestillingDto> forestillinger = new ConcurrentHashMap<>();
    private final Map<Long, ReservationDto> reservationer = new ConcurrentHashMap<>();
    private final AtomicLong reservationIdSeq = new AtomicLong(9000);

    public BiografReservationService() {
        // Seed a couple of forestillings (examples)
        forestillinger.put(101L, new ForestillingDto(
                101L, "One Upon Time in the West", OffsetDateTime.now().withHour(19).withMinute(30), 50, 42
        ));
        forestillinger.put(205L, new ForestillingDto(
                205L, "Oppenheimer", OffsetDateTime.now().plusDays(1).withHour(20).withMinute(0), 50, 10
        ));
    }

    public List<ForestillingDto> listForestilinger(LocalDate dato) {
        return forestillinger.values().stream()
                .filter(f -> dato == null || f.getStartTid().toLocalDate().equals(dato))
                .sorted(Comparator.comparing(ForestillingDto::getStartTid))
                .toList();
    }

    public ForestillingDto findForestillingById(long id) {
        ForestillingDto f = forestillinger.get(id);
        if (f == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Forestilling findes ikke: id=" + id
            );
        }
        return f;
    }


    public ReservationDto opretReservation(OpretReservationRequestDto request) {
        // Validate all forestillingId exist and capacity is enough
        for (ReservationsPostDto posts: request.getPosts()) {
            ForestillingDto f = forestillinger.get(posts.getForestillingId());
            if (f == null) {
                throw new NotFoundException("Forestilling findes ikke: id=" + posts.getForestillingId());
            }
            if (f.getLedigePladser() < posts.getAntalPladser()) {
                throw new ConflictException("Ikke nok ledige pladser for forestilling id=" + posts.getForestillingId());
            }
        }

        // Deduct seats (simple, not transactional)
        for (ReservationsPostDto posts : request.getPosts()) {
            ForestillingDto f = forestillinger.get(posts.getForestillingId());
            f.setLedigePladser(f.getLedigePladser() - posts.getAntalPladser());
        }

        long id = reservationIdSeq.incrementAndGet();
        ReservationDto reservation = new ReservationDto(id, OffsetDateTime.now(), new ArrayList<>(request.getPosts()));
        reservationer.put(id, reservation);
        return reservation;
    }

    public void aflysReservation(long reservationId) {
        ReservationDto reservation = reservationer.remove(reservationId);
        if (reservation == null) {
            throw new NotFoundException("Reservation findes ikke: id=" + reservationId);
        }

        // Release seats
        for (ReservationsPostDto post : reservation.getPosts()) {
            ForestillingDto f = forestillinger.get(post.getForestillingId());
            if (f != null) {
                f.setLedigePladser(f.getLedigePladser() + post.getAntalPladser());
            }
        }
    }
}
