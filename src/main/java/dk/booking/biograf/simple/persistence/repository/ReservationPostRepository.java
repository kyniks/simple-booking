package dk.booking.biograf.simple.persistence.repository;

import dk.booking.biograf.simple.persistence.entity.ReservationPostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by birol on 21-01-2026
 */
public interface ReservationPostRepository extends JpaRepository<ReservationPostEntity, Long> {

    List<ReservationPostEntity> findByForestillingId(Long forestillingId);
}
