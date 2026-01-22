package dk.booking.biograf.simple.service;

/**
 * Created by birol on 21-01-2026 at 18:39
 */

import dk.booking.biograf.simple.api.dto.ForestillingDto;
import dk.booking.biograf.simple.persistence.entity.ForestillingEntity;
import dk.booking.biograf.simple.persistence.entity.ReservationPostEntity;
import dk.booking.biograf.simple.persistence.repository.ForestillingRepository;
import dk.booking.biograf.simple.persistence.repository.ReservationPostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class ForestillingServiceImpl implements ForestillingService {
    private final Logger log = LoggerFactory.getLogger(ForestillingServiceImpl.class);

    private final ForestillingRepository forestillingRepository;
    private final ReservationPostRepository reservationPostRepository;

    public ForestillingServiceImpl (ForestillingRepository forestillingRepository,
                                    ReservationPostRepository reservationPostRepository) {
        this.forestillingRepository = forestillingRepository;
        this.reservationPostRepository = reservationPostRepository;
    }

    @Override
    @Transactional
    public List<ForestillingDto> listForestilinger(LocalDate dato) {
        List<ForestillingEntity> forestillingEntities = forestillingRepository.findAll(); // eller findByStartTidBetween
        return forestillingEntities.stream()
                .filter(f -> dato == null || f.getStartTid().toLocalDate().equals(dato))
                .sorted((a, b) -> a.getStartTid().compareTo(b.getStartTid()))
                .map(this::toDtoMedLedigePladser)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public ForestillingDto findForestillingById(long id) {
        ForestillingEntity f = forestillingRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Forestilling findes ikke: id=" + id));

        return toDtoMedLedigePladser(f);
    }

    private ForestillingDto toDtoMedLedigePladser(ForestillingEntity f) {
        int kapacitet = f.getSal().getKapacitet();
        int reserveret = getReserveredeAntalPladser(f.getId());
        int ledige = Math.max(0, kapacitet - reserveret);

        return new ForestillingDto(
                f.getId(),
                f.getFilm().getTitel(),
                f.getStartTid(),
                kapacitet,
                ledige
        );
    }

    private int getReserveredeAntalPladser(long forestillingId) {
        List<ReservationPostEntity> posts =
                reservationPostRepository.findByForestillingId(forestillingId);

        int reserveredePladser = posts.stream()
                .mapToInt(ReservationPostEntity::getAntalPladser)
                .sum();
        log.info("Antal Reserverede Pladser: " + reserveredePladser);

        return reserveredePladser;
    }
}
