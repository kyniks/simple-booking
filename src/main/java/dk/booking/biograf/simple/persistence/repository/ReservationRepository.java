package dk.booking.biograf.simple.persistence.repository;

import dk.booking.biograf.simple.persistence.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by birol on 21-01-2026 at 13:44
 */

public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {}
