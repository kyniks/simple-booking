package dk.booking.biograf.simple.persistence.repository;

import dk.booking.biograf.simple.persistence.entity.SalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by birol on 21-01-2026
 */
public interface SalRepository extends JpaRepository<SalEntity, Long> {}
