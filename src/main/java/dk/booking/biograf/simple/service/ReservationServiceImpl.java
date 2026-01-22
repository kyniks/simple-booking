package dk.booking.biograf.simple.service;

import dk.booking.biograf.simple.api.dto.OpretReservationRequestDto;
import dk.booking.biograf.simple.api.dto.ReservationDto;
import dk.booking.biograf.simple.api.dto.ReservationsPostDto;
import dk.booking.biograf.simple.persistence.entity.ForestillingEntity;
import dk.booking.biograf.simple.persistence.entity.ReservationEntity;
import dk.booking.biograf.simple.persistence.entity.ReservationPostEntity;
import dk.booking.biograf.simple.persistence.repository.ForestillingRepository;
import dk.booking.biograf.simple.persistence.repository.ReservationPostRepository;
import dk.booking.biograf.simple.persistence.repository.ReservationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by birol on 19-01-2026 at 21:33
 */

@Service
public class ReservationServiceImpl implements ReservationService {

    private final Logger log = LoggerFactory.getLogger(ReservationServiceImpl.class);

    private final ReservationRepository reservationRepository;
    private final ForestillingRepository forestillingRepository;
    private final ReservationPostRepository reservationPostRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository, ForestillingRepository forestillingRepository, ReservationPostRepository reservationPostRepository) {
        this.reservationRepository = reservationRepository;
        this.forestillingRepository = forestillingRepository;
        this.reservationPostRepository = reservationPostRepository;
    }

    @Transactional
    @Override
    public ReservationDto opretReservation(OpretReservationRequestDto request) {
        log.info("Started opretReservation: ");
        // Validate all forestillingId exist and capacity is enough
        for (ReservationsPostDto post: request.getPosts()) {
            ForestillingEntity f = forestillingRepository.findById(post.getForestillingId())
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND, "Forestilling findes ikke: id=" + post.getForestillingId()));

            int kapacitet = f.getSal().getKapacitet();
            int reserveret = getReserveredeAntalPladser(f.getId());
            int ledige = kapacitet - reserveret;

            log.info("kapacitet: "+kapacitet);
            log.info("reserveret: "+reserveret);
            log.info("ledige: "+ledige);

            if (post.getAntalPladser() > ledige) {
                log.error("Error: getAntalPladser > ledige " + post.getAntalPladser());
                throw new ResponseStatusException(
                        HttpStatus.CONFLICT,
                        "Ikke nok ledige pladser for forestilling id=" + f.getId()
                );
            }
        }

        // opret reservation
        ReservationEntity reservationEntity = new ReservationEntity();
        reservationEntity.setOprettetTid(OffsetDateTime.now());

        List<ReservationPostEntity> posts = new ArrayList<>();

        for (ReservationsPostDto post : request.getPosts()) {
            ForestillingEntity f = forestillingRepository.getReferenceById(post.getForestillingId());

            ReservationPostEntity rp = new ReservationPostEntity();
            rp.setReservation(reservationEntity);
            rp.setforestilling(f);
            rp.setAntalPladser(post.getAntalPladser());

            posts.add(rp);
        }

        reservationEntity.setPosts(posts);

        log.info("Reservation to be saved: "+reservationEntity.getOprettetTid());

        ReservationEntity saved = reservationRepository.save(reservationEntity);

        log.info("Completed oprettet reservation Id: "+saved.getId());

        return toDto(saved);
    }

    @Transactional
    @Override
    public void aflysReservation(long reservationId) {
        ReservationEntity reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Reservation findes ikke: id=" + reservationId));

        reservationRepository.delete(reservation);
    }

    private ReservationDto toDto(ReservationEntity r) {
        return new ReservationDto(
                r.getId(),
                r.getOprettetTid(),
                r.getPosts ().stream()
                        .map(p -> new ReservationsPostDto(p.getForestilling().getId(), p.getAntalPladser()))
                        .toList()
        );
    }

    private int getReserveredeAntalPladser(long forestillingId) {
        List<ReservationPostEntity> posts =
                reservationPostRepository.findByForestillingId(forestillingId);

        log.info("forestillingId " + forestillingId);
        log.info("Antal posts " + posts.size());

        int reserveredePladser = posts.stream()
                .mapToInt(ReservationPostEntity::getAntalPladser)
                .sum();
        log.info("Antal Reserverede Pladser: " + reserveredePladser);

        return reserveredePladser;
    }
}
