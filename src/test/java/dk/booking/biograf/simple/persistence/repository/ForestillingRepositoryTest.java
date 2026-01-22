package dk.booking.biograf.simple.persistence.repository;

import dk.booking.biograf.simple.persistence.entity.ForestillingEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class ForestillingRepositoryTest {

    @Autowired
   ForestillingRepository forestillingRepository;


    @Test
    void findById_existing_forestilling() {
        // Forudsætter at data.sql indsætter en forestilling med id=1
        ForestillingEntity f = forestillingRepository.findById(1L).orElse(null);

        assertThat(f).isNotNull();
        assertThat(f.getId()).isEqualTo(1L);
        assertThat(f.getFilm()).isNotNull();
        assertThat(f.getSal()).isNotNull();
        assertThat(f.getStartTid()).isNotNull();
    }
}
